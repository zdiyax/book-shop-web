package kz.epam.zd.validator;


import kz.epam.zd.exception.PropertyManagerException;
import kz.epam.zd.exception.ValidatorException;
import kz.epam.zd.util.PropertyManager;
import kz.epam.zd.util.ValidatorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Utility class for validating html forms. Uses settings from property file to get
 * validation type, validation rules and error messages.
 */
public class FormValidator {

    private static final Logger log = LoggerFactory.getLogger(FormValidator.class);
    private static final String FORM_PROPERTY_FILE_NAME = "validation.properties";
    private static final String PROPERTY_KEY_DOT = ".";
    private static final String REGEX_FOR_NUMBER = "[0-9]*";
    private static final String FIELDS_NOT_EQUAL_ERROR_MESSAGE = "fields.not.equal.message";
    private static Properties formProperties;
    private Map<String, List<String>> fieldErrors = new HashMap<>();

    public FormValidator() throws ValidatorException {
        if (formProperties == null) {
            loadFormProperties();
        }
    }

    /**
     * Loads properties from property file.
     * @throws ValidatorException if file is not accessible or properties cannot be loaded.
     */
    private void loadFormProperties() throws ValidatorException {

        PropertyManager propertyManager;
        try {
            propertyManager = new PropertyManager(FORM_PROPERTY_FILE_NAME);
        } catch (PropertyManagerException e) {
            throw new ValidatorException(e);
        }
        formProperties = propertyManager.getProperties();
    }

    /**
     * Checks whether error map is empty and sets errors to session from the map if not
     * @param req http request
     * @param fieldErrors error map
     * @return true if there are errors in form
     */
    public boolean hasFieldsErrors(HttpServletRequest req, Map<String, List<String>> fieldErrors) {
        boolean isError = false;
        if (!fieldErrors.isEmpty()) {
            ValidatorHelper.setErrorsToSession(req, fieldErrors);
            isError = true;
        }
        return isError;
    }

    /**
     * Validates the form using rules from properties.
     * @param formName form name to be passed and searched in property file
     * @param request http request
     * @return map of form fields to errors
     * @throws ValidatorException if one of validators used fails to execute
     */
    public Map<String, List<String>> validate(String formName, HttpServletRequest request) throws ValidatorException {
        ValidatorHelper.deleteErrorsFromSession(request);
        Map<String, List<Validator>> fieldValidators = getParameterValidatorMap(formName, request);
        for (Map.Entry<String, List<Validator>> entry : fieldValidators.entrySet()) {
            String key = entry.getKey();
            String value = request.getParameter(key);
            List<String> errorMessages = new ArrayList<>();
            for (Validator validator : entry.getValue()) {
                if (!validator.isValid(value)) {
                    log.debug("Warning! Try to validate parameter \"{}\" with value \"{}\" use validator {}. " +
                            "Result: {}", key, value, validator.getClass().getSimpleName(), validator.isValid(value));
                    errorMessages.add(validator.getMessage());
                }
            }
            if (!errorMessages.isEmpty()) {
                fieldErrors.put(key, errorMessages);
            }
        }
        return fieldErrors;
    }

    /**
     * Reads property file and returns map of form input names to validators.
     * @param formName html form name
     * @param request http request
     * @return map of form fields to list of validators to be used
     * @throws ValidatorException if {@code getValidator() fails to execute
     */
    private Map<String, List<Validator>> getParameterValidatorMap(String formName, HttpServletRequest request) throws ValidatorException {

        Map<String, List<Validator>> fieldValidators = new HashMap<>();
        List<Validator> validators;
        Enumeration<String> attributeNames = request.getParameterNames();
        while (attributeNames.hasMoreElements()) {
            String fieldName = attributeNames.nextElement();
            String value = request.getParameter(fieldName);
            String formFieldName = formName + PROPERTY_KEY_DOT + fieldName;
            log.debug("Validator received parameter \"{}\" with value \"{}\"", fieldName, value);
            validators = getValidators(formFieldName);
            if (!validators.isEmpty()) fieldValidators.put(fieldName, validators);
        }
        return fieldValidators;
    }

    /**
     * Using the properties from property file builds the list of validators to be used to certain form field.
     * @param formFieldName field name from html form
     * @return list of validators to be used to the field
     * @throws ValidatorException if {@code getValidator() fails to execute
     */
    private List<Validator> getValidators(String formFieldName) throws ValidatorException {

        final int DEFINE_VALIDATOR_NUMBER = 1;
        List<Validator> validators = new ArrayList<>();
        Validator validator;
        for (Map.Entry<?, ?> property : formProperties.entrySet()) {
            String key = (String) property.getKey();
            String value = (String) property.getValue();
            String validatorNameNumber = key.substring(key.length() - DEFINE_VALIDATOR_NUMBER, key.length());
            if ((key.startsWith(formFieldName)) && (validatorNameNumber.matches(REGEX_FOR_NUMBER))) {
                validator = getValidator(validatorNameNumber, formFieldName, value);
                validators.add(validator);
            }
        }
        return validators;
    }


    private Validator getValidator(String validatorNumberName, String formFiledName, String validatorName) throws ValidatorException {

        Class validatorClass;
        Validator validator;
        try {
            validatorClass = Class.forName(validatorName);
            validator = (Validator) validatorClass.newInstance();
            validatorSetFields(validatorClass, validator, validatorNumberName, formFiledName);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ValidatorException(e);
        }
        log.debug("Will be used validator: {}", validator.toString());
        return validator;

    }

    private void validatorSetFields(Class validatorClass, Validator validator, String validatorNumberName, String formFiledName) throws ValidatorException {
        for (Map.Entry<?, ?> property : formProperties.entrySet()) {
            String key = (String) property.getKey();
            String value = (String) property.getValue();
            if (key.startsWith(formFiledName + PROPERTY_KEY_DOT + validatorNumberName + PROPERTY_KEY_DOT)) {
                try {
                    Object valueObject = parseValue(value);
                    String keyFieldName = formFiledName + PROPERTY_KEY_DOT + validatorNumberName + PROPERTY_KEY_DOT;
                    String filedValidatorName = key.substring(keyFieldName.length(), key.length());
                    BeanInfo beanInfo = Introspector.getBeanInfo(validatorClass);
                    PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
                    boolean done = false;
                    for (int i = 0; !done && i < descriptors.length; i++) {
                        if (descriptors[i].getName().equals(filedValidatorName)) {
                            descriptors[i].getWriteMethod().invoke(validator, valueObject);
                            done = true;
                        }
                    }
                } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                    throw new ValidatorException(e);
                }
            }
        }
    }


    private Object parseValue(String value) {

        if (value.matches(REGEX_FOR_NUMBER)) return new Integer(value);
        return value;
    }

    public void checkFieldsOnEquals(String field, String otherField, HttpServletRequest request) {
        if ((request.getParameter(field) != null) && (request.getParameter(otherField) != null)) {
            String checkField = request.getParameter(field);
            String checkOtherField = request.getParameter(otherField);
            FieldsEqualsValidator fieldValidator = new FieldsEqualsValidator();
            log.debug("Validator {} try to validate values of field {} and field {} on equals",
                    fieldValidator.getClass().getSimpleName(), field, otherField);
            if (!fieldValidator.isValid(checkField, checkOtherField))
                fillErrorMap(otherField, FIELDS_NOT_EQUAL_ERROR_MESSAGE);
            log.debug("Result is {}", fieldValidator.isValid(checkField, checkOtherField));
        }
    }

    /**
     * Fills the errorMap with form field name and error code entries.
     * @param fieldName name of a form field
     * @param errorMessagePropertyKey error code
     */
    private void fillErrorMap(String fieldName, String errorMessagePropertyKey) {
        List<String> errorMessages = new ArrayList<>();
        String errorMessage = formProperties.getProperty(errorMessagePropertyKey);
        errorMessages.add(errorMessage);
        fieldErrors.put(fieldName, errorMessages);
    }
}
