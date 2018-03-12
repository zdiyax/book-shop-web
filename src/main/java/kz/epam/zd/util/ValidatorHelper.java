package kz.epam.zd.util;

import kz.epam.zd.exception.ValidatorException;
import kz.epam.zd.validator.FormValidator;
import kz.epam.zd.validator.RegexValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.*;

/**
 * Utility class to solve validation problems.
 */
public class ValidatorHelper {

    private static final Logger log = LoggerFactory.getLogger(ValidatorHelper.class);
    private static final String REGEX_FOR_QUANTITY = "[1-9]*";
    private static final String CART_INVALID_INPUT_MESSAGE = "cart.invalid.input.message";
    private static final String HYPHEN_SIGN = "-";
    private static final String EMPTY_STRING = "";


    private ValidatorHelper() {
    }

    public static boolean checkForm(HttpServletRequest req, String validateForm) throws ValidatorException {
        boolean checkResult = false;
        FormValidator validator = new FormValidator();
        Map<String, List<String>> fieldErrors = validator.validate(validateForm, req);
        if (validator.hasFieldsErrors(req, fieldErrors)) checkResult = true;
        return checkResult;
    }

    public static void deleteErrorsFromSession(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String sessionAttribute = attributeNames.nextElement();
            if (sessionAttribute.endsWith(FORM_ERRORS))
                request.getSession().removeAttribute(sessionAttribute);
        }
    }

    //getKey.replace result is now set to string and passed to request
    public static void setErrorsToSession(HttpServletRequest req, Map<String, List<String>> fieldErrors) {
        for (Map.Entry<String, List<String>> entry : fieldErrors.entrySet()) {
            String result = entry.getKey().replace(HYPHEN_SIGN, EMPTY_STRING);
            req.getSession().setAttribute(result + FORM_ERRORS, entry.getValue());
            for (String errorMessage : entry.getValue()) {
                log.debug("Field \"{}\" ended up with error message \"{}\"", entry.getKey(), errorMessage);
            }
        }
    }

    public static boolean checkCartForm(HttpServletRequest req, HashMap books) {
        RegexValidator regexValidator = new RegexValidator();
        regexValidator.setRegex(REGEX_FOR_QUANTITY);
        for (int i = 0; i < books.size(); i++) {
            if (!regexValidator.isValid(req.getParameter(QUANTITY + i))) {
                req.getSession().setAttribute(CART + FORM_ERRORS, CART_INVALID_INPUT_MESSAGE);
                return true;
            }
        }
        return false;
    }
}


