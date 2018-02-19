package kz.epam.zd.validator;

public class RegexValidator extends ParentValidator {

    private String regex;

    public RegexValidator() {
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
        return "RegexValidator{" +
                "regex='" + regex + '\'' +
                ", message=" + this.getMessage() +
                '}';
    }
}
