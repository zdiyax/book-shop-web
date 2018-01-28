package kz.epam.zd.validator;

public class FieldsEqualsValidator extends ParentValidator implements Validator {
    @Override
    public Boolean isValid(String field, String otherField) {
        return field.equals(otherField);
    }
}
