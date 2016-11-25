package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private static final String PROPERTY_FILE_NAME = "db.properties";
    private static final Logger log = LoggerFactory.getLogger(PropertyManager.class);
    private static PropertyManager instance;
    private final Properties properties;

    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    private PropertyManager() {
        properties = new Properties();
        ClassLoader classLoader = PropertyManager.class.getClassLoader();
        try (InputStream in = classLoader.getResourceAsStream(PROPERTY_FILE_NAME)) {
            properties.load(in);
        } catch (IOException e) {
            log.debug("Couldn't load the properties: {}" + e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}