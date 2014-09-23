package uk.co.revsys.subscription.manager.action;

import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.revsys.objectology.action.ActionRequest;
import uk.co.revsys.objectology.action.handler.AbstractActionHandler;
import uk.co.revsys.objectology.action.handler.ActionInvocationException;
import uk.co.revsys.objectology.dao.DaoException;
import uk.co.revsys.objectology.model.instance.LinkedObjects;
import uk.co.revsys.objectology.model.instance.OlogyInstance;
import uk.co.revsys.objectology.model.instance.Property;
import uk.co.revsys.objectology.service.OlogyObjectServiceFactory;

public class DisableAccountActionHandler extends AbstractActionHandler<DisableAccountAction>{

    @Override
    public OlogyInstance invoke(OlogyInstance account, DisableAccountAction action, ActionRequest request) throws ActionInvocationException {
        try {
            account.getAttribute("status", Property.class).setValue("disabled");
            OlogyObjectServiceFactory.getOlogyInstanceService().update(account);
            for(OlogyInstance user: account.getAttribute("users", LinkedObjects.class).getAssociatedObjects()){
                user.getAttribute("status", Property.class).setValue("disabled");
                OlogyObjectServiceFactory.getOlogyInstanceService().update(user);
            }
            return account;
        } catch (DaoException ex) {
            throw new ActionInvocationException(ex);
        }
    }

}
