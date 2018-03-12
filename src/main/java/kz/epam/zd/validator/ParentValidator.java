package kz.epam.zd.validator;

public abstract class ParentValidator implements Validator {

    private String message;

    ParentValidator() {
    }


    @Override
    public Boolean isValid(String parameter) {
        throw new UnsupportedOperationException("Validator method not supported.");
    }

    @Override
    public Boolean isValid(String parameter, String otherParameter) {
        throw new UnsupportedOperationException("Validator method not supported.");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
