package kz.epam.zd.validator;

/**
 * Validator on equals two fields
 */

public class FieldsEqualsValidator extends ParentValidator implements Validator {

    @Override
    public Boolean isValid(String field, String otherField) {

        boolean checkResult = false;
        if (field.equals(otherField)) checkResult = true;
        return checkResult;
    }

}
