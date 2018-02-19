package kz.epam.zd.validator;

public class FieldsEqualsValidator extends ParentValidator {
    @Override
    public Boolean isValid(String field, String otherField) {
        return field.equals(otherField);
    }
}
