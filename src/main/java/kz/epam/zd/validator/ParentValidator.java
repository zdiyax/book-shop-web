package kz.epam.zd.validator;

import kz.epam.zd.exception.ValidatorException;

public class ParentValidator implements Validator {

    private String message;

    ParentValidator() {
    }


    @Override
    public Boolean isValid(String parameter) throws ValidatorException {
        return null;
    }

    @Override
    public Boolean isValid(String parameter, String otherParameter) throws ValidatorException {
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
