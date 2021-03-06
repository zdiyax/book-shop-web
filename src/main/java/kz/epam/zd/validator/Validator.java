package kz.epam.zd.validator;

import kz.epam.zd.exception.ValidatorException;

/**
 * Generic interface for validators.
 */
public interface Validator {

    Boolean isValid(String parameter) throws ValidatorException;

    Boolean isValid(String parameter, String otherParameter) throws ValidatorException;


    String getMessage();

    void setMessage(String msg);

}
