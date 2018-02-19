package kz.epam.zd.util;

import kz.epam.zd.exception.PropertyManagerException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to manage properties through property files.
 */
public class PropertyManager {

    private Properties properties;

    public PropertyManager(String propertyFile) throws PropertyManagerException {
        try (InputStream in = PropertyManager.class.getClassLoader().getResourceAsStream(propertyFile)) {
            if (in != null) {
                properties = new Properties();
                properties.load(in);
            }
        } catch (IOException e) {
            throw new PropertyManagerException(e);
        }
    }

    public String getPropertyKey(String key) throws PropertyManagerException {
        if (properties == null) throw new PropertyManagerException("Couldn't load properties.");
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }
}
