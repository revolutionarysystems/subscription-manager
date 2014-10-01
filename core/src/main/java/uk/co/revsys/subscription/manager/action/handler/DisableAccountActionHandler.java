package uk.co.revsys.subscription.manager.action.handler;

import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.revsys.objectology.action.ActionRequest;
import uk.co.revsys.objectology.action.handler.AbstractActionHandler;
import uk.co.revsys.objectology.action.handler.exception.ActionInvocationException;
import uk.co.revsys.objectology.dao.DaoException;
import uk.co.revsys.objectology.exception.ValidationException;
import uk.co.revsys.objectology.model.instance.LinkedObjects;
import uk.co.revsys.objectology.model.instance.OlogyInstance;
import uk.co.revsys.objectology.model.instance.Property;
import uk.co.revsys.objectology.service.ServiceFactory;
import uk.co.revsys.subscription.manager.action.model.DisableAccountAction;

public class DisableAccountActionHandler extends AbstractActionHandler<DisableAccountAction>{

    @Override
    public OlogyInstance doInvoke(OlogyInstance account, DisableAccountAction action, ActionRequest request) throws ActionInvocationException {
        try {
            account.getAttribute("status", Property.class).setValue("disabled");
            ServiceFactory.getOlogyInstanceService().update(account);
            for(OlogyInstance user: account.getAttribute("users", LinkedObjects.class).getAssociatedObjects()){
                user.getAttribute("status", Property.class).setValue("disabled");
                ServiceFactory.getOlogyInstanceService().update(user);
            }
            return account;
        } catch (DaoException ex) {
            throw new ActionInvocationException(ex);
        } catch (ValidationException ex) {
            throw new ActionInvocationException(ex);
        }
    }

}
