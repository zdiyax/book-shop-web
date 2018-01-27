package kz.epam.zd.validator;

/**
 * Validator for length of values entered in form's fields
 */
public class LengthValidator extends ParentValidator implements Validator {

    private Integer minLength;
    private Integer maxLength;

    public LengthValidator() {
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public Boolean isValid(String parameter) {
        int length = parameter.length();
        return length >= minLength && length <= maxLength;
    }

    @Override
    public String toString() {
        return "LengthValidator{" +
                "minLength=" + minLength +
                ", maxLength=" + maxLength +
                ", message=" + this.getMessage() +
                '}';
    }
}
