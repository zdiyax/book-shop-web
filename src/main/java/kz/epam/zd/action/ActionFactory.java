package kz.epam.zd.action;

import kz.epam.zd.exception.ActionFactoryException;
import kz.epam.zd.exception.PropertyManagerException;
import kz.epam.zd.util.PropertyManager;

/**
 * Factory pattern implementation for creating actions.
 */
public class ActionFactory {

    private static final String ACTIONS_PROPERTY_FILE_NAME = "actions.properties";
    private PropertyManager propertyManager;

    /**
     * Loads actions from the property file.
     *
     * @throws ActionFactoryException wraps {@code PropertyManagerException}
     */
    public void loadActions() throws ActionFactoryException {
        try {
            propertyManager = new PropertyManager(ACTIONS_PROPERTY_FILE_NAME);
        } catch (PropertyManagerException e) {
            throw new ActionFactoryException(e);
        }
    }

    /**
     * Gets corresponding action class instance by its name from the property file.
     *
     * @param actionName name of action comes as a parameter in http request
     * @return corresponding action instance
     * @throws ActionFactoryException if there is no corresponding action in the
     * property file, property file was not loaded or if there is no access to it
     */
    public Action getAction(String actionName) throws ActionFactoryException {
        Action action;
        try {
            String actionClassName = propertyManager.getPropertyKey(actionName);
            Class actionClass = Class.forName(actionClassName);
            action = (Action) actionClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | PropertyManagerException e) {
            throw new ActionFactoryException(e);
        }
        return action;
    }
}
