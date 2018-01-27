package kz.epam.zd.validator;


import kz.epam.zd.exception.PropertyManagerException;
import kz.epam.zd.exception.ValidatorException;
import kz.epam.zd.util.PropertyManager;
import kz.epam.zd.util.ValidatorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Validator for form's fields.
 * .Read validation rules from property file.
 */

public class FormValidator {

    private static final Logger logger = LoggerFactory.getLogger(FormValidator.class);
    private static final String FORM_PROPERTY_FILE_NAME = "forms.properties";
    private static final String PROPERTY_KEY_DOT = ".";
    private static final String REGEX_FOR_NUMBER = "[0-9]*";
    private static final int ZERO_FILE_SIZE = 0;
    private static final String FIELDS_NOT_EQUAL_ERROR_MESSAGE = "fields.not.equal.message";
    private static final String LIST_NOT_SELECTED_ERROR_MESSAGE = "drop.down.list.item.not.select";
    private static final String WRONG_CONTENT_TYPE_ERROR_MESSAGE = "add.room.photo.error";
    private static final String WRONG_FILE_SIZE_ERROR_MESSAGE = "add.room.photo.size.error";
    private static Properties formProperties;
    private Map<String, List<String>> fieldErrors = new HashMap<>();

    public FormValidator() throws ValidatorException {

        if (formProperties == null) {
            loadFormProperties();
        }
    }

    /**
     * Load properties for form's fields validation.
     *
     * @throws ValidatorException if any exceptions occurred
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
     * If collection with errors not empty invoke methods for save them to session.
     *
     * @param req         http request for getting session
     * @param fieldErrors collection with validator errors
     * @return true if collection consist any errors
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
     * Validate form by rules.
     *
     * @param formName name of form which will be validate
     * @param request  http request consist values from form's fields
     * @return collection map with name of field and list of validator errors message for that field
     * @throws ValidatorException wrap for any exception
     */
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
                    logger.debug("Warning! Try to validate parameter \"{}\" with value \"{}\" use validator {}. " +
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

    /**
     * Fill map collection with name of field and validators for that field
     *
     * @param formName name of form which will be validate
     * @param request  http request consist values from form's fields
     * @return collection map with name of fields and list of validators for that field
     * @throws ValidatorException wrap for any exception
     */
    private Map<String, List<Validator>> getParameterValidatorsMap(String formName, HttpServletRequest request) throws ValidatorException {

        Map<String, List<Validator>> fieldValidators = new HashMap<>();
        List<Validator> validators;
        //get all attributes of fields from request
        Enumeration<String> attributeNames = request.getParameterNames();
        while (attributeNames.hasMoreElements()) {
            String fieldName = attributeNames.nextElement();
            String value = request.getParameter(fieldName);
            String formFieldName = formName + PROPERTY_KEY_DOT + fieldName;
            logger.debug("From form received parameter \"{}\" with value \"{}\"", fieldName, value);
            //get validator for each field
            validators = getValidators(formFieldName);
            //if the list of validators not empty add it to map collection under the name of field
            if (!validators.isEmpty()) fieldValidators.put(fieldName, validators);
        }
        return fieldValidators;
    }

    /**
     * Fill the list by validators for each field
     *
     * @param formFieldName name of form's field
     * @return the list of validators for each field
     * @throws ValidatorException wrap for any exception
     */
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

    /**
     * Get validator from property string uses reflection
     *
     * @param validatorNumberName number of validator for determine its fields in properties
     * @param formFiledName       name of field
     * @param validatorName       name of validator for its creating by reflection
     * @return validator for field
     * @throws ValidatorException wrap for any exception
     */
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
        logger.debug("Will be used validator: {}", validator.toString());
        return validator;

    }

    /**
     * Search and set values for validation from properties to fields of validator by reflection
     *
     * @param validatorClass      validator's class
     * @param validator           validator's object
     * @param validatorNumberName number of validator for determine its fields in properties
     * @param formFiledName       name of field
     * @throws ValidatorException wrap for any exception
     */
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

    /**
     * Parsing value by its type
     *
     * @param value the value for parsing
     * @return detected value
     */
    private Object parseValue(String value) {

        if (value.matches(REGEX_FOR_NUMBER)) return new Integer(value);
        return value;
    }

    /**
     * Validate two fields on equals each other
     *
     * @param field      first field
     * @param otherField second field
     * @param request    http request with values of fields
     */
    public void checkFieldsOnEquals(String field, String otherField, HttpServletRequest request) {

        if ((request.getParameter(field) != null) && (request.getParameter(otherField) != null)) {
            String checkField = request.getParameter(field);
            String checkOtherField = request.getParameter(otherField);
            FieldsEqualsValidator fieldValidator = new FieldsEqualsValidator();
            logger.debug("Validator {} try to validate values of field {} and filed {} on equals",
                    fieldValidator.getClass().getSimpleName(), field, otherField);
            if (!fieldValidator.isValid(checkField, checkOtherField))
                fillErrorMap(otherField, FIELDS_NOT_EQUAL_ERROR_MESSAGE);
            logger.debug("Result is {}", fieldValidator.isValid(checkField, checkOtherField));
        }
    }

    /**
     * Validate dropdown list on form if no one item was selected
     *
     * @param parameter name of parameter
     * @param req       http request with values of parameter
     */
    public void checkDropDownListOnSelect(String parameter, HttpServletRequest req) {

        String checkParameter = req.getParameter(parameter);
        NotNullValidator nullValidator = new NotNullValidator();
        logger.debug("Validator {} try to validate value of parameter {} on NULL", nullValidator.getClass().getSimpleName(), parameter);
        if (!nullValidator.isValid(checkParameter)) fillErrorMap(parameter, LIST_NOT_SELECTED_ERROR_MESSAGE);
        logger.debug("Result is {}", nullValidator.isValid(checkParameter));

    }

    /**
     * Validate image content type
     *
     * @param parameter name of parameter
     * @param req       http request with values of parameter
     * @throws ValidatorException wrap for any exception
     */
    public void checkImageContentType(String parameter, HttpServletRequest req) throws ValidatorException {

        final String FILE_FORM_CONTENT_HEADER = "application/x-www-form-urlencoded";
        if (!FILE_FORM_CONTENT_HEADER.equals(req.getContentType())) {
            try {
                Part photoPart = req.getPart(parameter);
                if (photoPart.getSize() != ZERO_FILE_SIZE) {
                    String contentType = photoPart.getContentType();
                    ImageValidator validator = new ImageValidator();
                    logger.debug("Validator {} try to validate value of content type \"{}\"", validator.getClass().getSimpleName(), contentType);
                    if (!validator.isValid(contentType)) fillErrorMap(parameter, WRONG_CONTENT_TYPE_ERROR_MESSAGE);
                    logger.debug("Result is {}", validator.isValid(contentType));
                }
            } catch (IOException | ServletException e) {
                throw new ValidatorException(e);
            }

        }

    }

    /**
     * Validate file size
     *
     * @param parameter name of parameter
     * @param req       http request with values of parameter
     * @throws ValidatorException wrap
     */
    public void checkFileMaxSize(String parameter, HttpServletRequest req) throws ValidatorException {

        final String FILE_FORM_CONTENT_HEADER = "application/x-www-form-urlencoded";
        if (!FILE_FORM_CONTENT_HEADER.equals(req.getContentType())) {
            try {
                Part photoPart = req.getPart(parameter);
                if (photoPart.getSize() != ZERO_FILE_SIZE) {
                    Long fileSize = photoPart.getSize();
                    FileSizeValidator validator = new FileSizeValidator();
                    logger.debug("Validator {} try to validate value of size \"{}\"", validator.getClass().getSimpleName(), fileSize);
                    if (!validator.isValid(fileSize)) fillErrorMap(parameter, WRONG_FILE_SIZE_ERROR_MESSAGE);
                    logger.debug("Result is {}", validator.isValid(fileSize));
                }
            } catch (IOException | ServletException e) {
                throw new ValidatorException(e);
            }

        }
    }

    /**
     * Add name of field and errors for its to collection
     *
     * @param parameter               name of parameter
     * @param errorMessagePropertyKey key from property file for searching message about error
     */
    private void fillErrorMap(String parameter, String errorMessagePropertyKey) {

        List<String> errorMessages = new ArrayList<>();
        String errorMessage = formProperties.getProperty(errorMessagePropertyKey);
        errorMessages.add(errorMessage);
        fieldErrors.put(parameter, errorMessages);
    }
}
