package zd.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zd.exception.PropertyManagerException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private static final Logger logger = LoggerFactory.getLogger(PropertyManager.class);
    private Properties properties;

    public PropertyManager(String propertyFileName) throws PropertyManagerException {
        properties = new Properties();
        try (InputStream in = PropertyManager.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            properties.load(in);
        } catch (IOException e) {
            throw new PropertyManagerException(e);
        }
    }

    public String getPropertyKey(String key) throws PropertyManagerException {
        if (properties == null) throw new PropertyManagerException("Properties not loaded.");
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }

}
