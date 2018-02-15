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
public class FormValidator {

    private static final Logger log = LoggerFactory.getLogger(FormValidator.class);
    private static final String FORM_PROPERTY_FILE_NAME = "validation.properties";
    private static final String PROPERTY_KEY_DOT = ".";
    private static final String REGEX_FOR_NUMBER = "[0-9]*";
    private static final String FIELDS_NOT_EQUAL_ERROR_MESSAGE = "fields.not.equal.message";
    private static final String LIST_NOT_SELECTED_ERROR_MESSAGE = "drop.down.list.item.not.select";
    private static Properties formProperties;
    private Map<String, List<String>> fieldErrors = new HashMap<>();

    public FormValidator() throws ValidatorException {

        if (formProperties == null) {
            loadFormProperties();
        }
    }

    private void loadFormProperties() throws ValidatorException {

        PropertyManager propertyManager;
        try {
            propertyManager = new PropertyManager(FORM_PROPERTY_FILE_NAME);
        } catch (PropertyManagerException e) {
            throw new ValidatorException(e);
        }
        formProperties = propertyManager.getProperties();
    }

    public boolean hasFieldsErrors(HttpServletRequest req, Map<String, List<String>> fieldErrors) {

        boolean isError = false;
        if (!fieldErrors.isEmpty()) {
            ValidatorHelper.setErrorsToSession(req, fieldErrors);
            isError = true;
        }
        return isError;
    }

    public Map<String, List<String>> validate(String formName, HttpServletRequest request) throws ValidatorException {
        //delete errors from previous validation
        ValidatorHelper.deleteErrorsFromSession(request);
        //get collection with name of field and list of validators for that field
        Map<String, List<Validator>> fieldValidators = getParameterValidatorsMap(formName, request);
        for (Map.Entry<String, List<Validator>> entry : fieldValidators.entrySet()) {
            String key = entry.getKey();
            String value = request.getParameter(key);
            List<String> errorMessages = new ArrayList<>();
            for (Validator validator : entry.getValue()) {
                //check field's value by its validator
                if (!validator.isValid(value)) {
                    log.debug("Warning! Try to validate parameter \"{}\" with value \"{}\" use validator {}. " +
                            "Result: {}", key, value, validator.getClass().getSimpleName(), validator.isValid(value));
                    //add message about error to the list of message
                    errorMessages.add(validator.getMessage());
                }
            }
            //if list of error messages not empty add it to map collection under the name of checking field
            if (!errorMessages.isEmpty()) {
                fieldErrors.put(key, errorMessages);
            }
        }
        return fieldErrors;
    }

    private Map<String, List<Validator>> getParameterValidatorsMap(String formName, HttpServletRequest request) throws ValidatorException {

        Map<String, List<Validator>> fieldValidators = new HashMap<>();
        List<Validator> validators;
        //get all attributes of fields from request
        Enumeration<String> attributeNames = request.getParameterNames();
        while (attributeNames.hasMoreElements()) {
            String fieldName = attributeNames.nextElement();
            String value = request.getParameter(fieldName);
            String formFieldName = formName + PROPERTY_KEY_DOT + fieldName;
            log.debug("From form received parameter \"{}\" with value \"{}\"", fieldName, value);
            //get validator for each field
            validators = getValidators(formFieldName);
            //if the list of validators not empty add it to map collection under the name of field
            if (!validators.isEmpty()) fieldValidators.put(fieldName, validators);
        }
        return fieldValidators;
    }

    private List<Validator> getValidators(String formFieldName) throws ValidatorException {

        final int DEFINE_VALIDATOR_NUMBER = 1;
        List<Validator> validators = new ArrayList<>();
        Validator validator;
        //search validators for field in properties
        for (Map.Entry<?, ?> property : formProperties.entrySet()) {
            String key = (String) property.getKey();
            String value = (String) property.getValue();
            String validatorNameNumber = key.substring(key.length() - DEFINE_VALIDATOR_NUMBER, key.length());
            if ((key.startsWith(formFieldName)) && (validatorNameNumber.matches(REGEX_FOR_NUMBER))) {
                //get validator for field
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
            //create validator by reflection
            validatorClass = Class.forName(validatorName);
            validator = (Validator) validatorClass.newInstance();
            //fill validator fields values for validation
            validatorSetFields(validatorClass, validator, validatorNumberName, formFiledName);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ValidatorException(e);
        }
        log.debug("Will be used validator: {}", validator.toString());
        return validator;

    }

    private void validatorSetFields(Class validatorClass, Validator validator, String validatorNumberName, String formFiledName) throws ValidatorException {

        //search validators field's values in properties
        for (Map.Entry<?, ?> property : formProperties.entrySet()) {
            String key = (String) property.getKey();
            String value = (String) property.getValue();
            if (key.startsWith(formFiledName + PROPERTY_KEY_DOT + validatorNumberName + PROPERTY_KEY_DOT)) {
                try {
                    //set found value to object
                    Object valueObject = parseValue(value);
                    String keyFieldName = formFiledName + PROPERTY_KEY_DOT + validatorNumberName + PROPERTY_KEY_DOT;
                    String filedValidatorName = key.substring(keyFieldName.length(), key.length());
                    //get validators field by reflection
                    BeanInfo beanInfo = Introspector.getBeanInfo(validatorClass);
                    PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
                    boolean done = false;
                    //write values to validators fields
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

    private void fillErrorMap(String parameter, String errorMessagePropertyKey) {
        List<String> errorMessages = new ArrayList<>();
        String errorMessage = formProperties.getProperty(errorMessagePropertyKey);
        errorMessages.add(errorMessage);
        fieldErrors.put(parameter, errorMessages);
    }
}
