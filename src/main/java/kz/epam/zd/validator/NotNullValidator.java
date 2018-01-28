package kz.epam.zd.validator;

public class NotNullValidator extends ParentValidator implements Validator {

    @Override
    public Boolean isValid(String parameter) {

        boolean checkResult = true;
        if (parameter == null) checkResult = false;
        return checkResult;
    }
}
