package zd.validator;

import zd.exception.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    public PatternValidator(String patternKey){
        pattern = Pattern.compile(patternKey);
    }

    /**
     * Validate username with regular expression
     * @param username username for validation
     */
    @Override
    public void validate(String username) throws ValidatorException {
        matcher = pattern.matcher(username);
        if (!matcher.matches()) {
            throw new ValidatorException();
        }
    }

}
