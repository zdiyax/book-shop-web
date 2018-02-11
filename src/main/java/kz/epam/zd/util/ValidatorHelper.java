package kz.epam.zd.util;

import kz.epam.zd.exception.ValidatorException;
import kz.epam.zd.validator.FormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static kz.epam.zd.util.ConstantHolder.FORM_ERRORS;

public class ValidatorHelper {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorHelper.class);


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

    public static void setErrorsToSession(HttpServletRequest req, Map<String, List<String>> fieldErrors) {

        for (Map.Entry<String, List<String>> entry : fieldErrors.entrySet()) {
            req.getSession().setAttribute(entry.getKey() + FORM_ERRORS, entry.getValue());
            for (String errorMessage : entry.getValue()) {
                logger.debug("In filed \"{}\" found error message \"{}\"", entry.getKey(), errorMessage);
            }
        }
    }

}


