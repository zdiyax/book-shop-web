package kz.epam.zd.validator;

import kz.epam.zd.exception.PropertyManagerException;
import kz.epam.zd.exception.ValidatorException;
import kz.epam.zd.util.PropertyManager;

public class FileSizeValidator extends ParentValidator implements Validator {

    private static final String FORM_PROPERTY_FILE_NAME = "validation.properties";
    private static final String MAX_FILE_SIZE_KEY = "max.upload.image.size";

    @Override
    public Boolean isValid(Long parameter) throws ValidatorException {

        boolean checkResult = false;
        Long maxFileSize;
        try {
            PropertyManager pm = new PropertyManager(FORM_PROPERTY_FILE_NAME);
            maxFileSize = Long.parseLong(pm.getPropertyKey(MAX_FILE_SIZE_KEY));
        } catch (PropertyManagerException e) {
            throw new ValidatorException(e);
        }

        if (parameter <= maxFileSize) checkResult = true;

        return checkResult;
    }
}
