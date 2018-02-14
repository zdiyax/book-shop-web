package kz.epam.zd.action;

import kz.epam.zd.exception.ActionFactoryException;
import kz.epam.zd.exception.PropertyManagerException;
import kz.epam.zd.util.PropertyManager;

public class ActionFactory {

    private static final String ACTION_PROPERTY_FILE_NAME = "actions.properties";
    private PropertyManager propertyManager;

    public void loadActions() throws ActionFactoryException {
        try {
            propertyManager = new PropertyManager(ACTION_PROPERTY_FILE_NAME);
        } catch (PropertyManagerException e) {
            throw new ActionFactoryException(e);
        }
    }

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
