package kz.epam.zd.validator;

public class RegExValidator extends ParentValidator implements Validator {

    private String regex;

    public RegExValidator() {
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public Boolean isValid(String parameter) {
        return parameter.matches(regex);
    }

    @Override
    public String toString() {
        return "RegExValidator{" +
                "regex='" + regex + '\'' +
                ", message=" + this.getMessage() +
                '}';
    }
}
