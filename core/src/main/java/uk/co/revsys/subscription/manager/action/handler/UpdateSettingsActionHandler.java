package uk.co.revsys.subscription.manager.action.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.revsys.objectology.action.ActionRequest;
import uk.co.revsys.objectology.action.handler.AttributeActionHandler;
import uk.co.revsys.objectology.action.handler.exception.ActionInvocationException;
import uk.co.revsys.objectology.dao.DaoException;
import uk.co.revsys.objectology.mapping.DeserialiserException;
import uk.co.revsys.objectology.mapping.json.JsonInstanceMapper;
import uk.co.revsys.objectology.model.instance.Dictionary;
import uk.co.revsys.objectology.model.instance.Link;
import uk.co.revsys.objectology.model.instance.OlogyInstance;
import uk.co.revsys.objectology.model.instance.Property;
import uk.co.revsys.objectology.service.ServiceFactory;
import uk.co.revsys.subscription.manager.action.model.UpdateSettingsAction;
import uk.co.revsys.subscription.manager.constraint.Constraint;
import uk.co.revsys.subscription.manager.constraint.FailedConstraintException;

public class UpdateSettingsActionHandler extends AttributeActionHandler<UpdateSettingsAction, Dictionary<OlogyInstance>> {

    private final JsonInstanceMapper instanceMapper;
    private final Map<String, Constraint> constraints;

    public UpdateSettingsActionHandler(JsonInstanceMapper instanceMapper, Map<String, Constraint> constraints) {
        this.instanceMapper = instanceMapper;
        this.constraints = constraints;
    }

    @Override
    public String getAttributeName(UpdateSettingsAction action) {
        return "settings";
    }

    @Override
    public OlogyInstance doInvoke(OlogyInstance instance, Dictionary<OlogyInstance> settings, UpdateSettingsAction action, ActionRequest request) throws ActionInvocationException {
        try {
            String json = getRequiredParameter(request, "settings");
            Dictionary<OlogyInstance> newSettings = (Dictionary) instanceMapper.deserialise(json, settings.getTemplate());
            OlogyInstance product = (OlogyInstance) instance.getAttribute("governingProduct", Link.class).getAssociatedObject();
            Dictionary<OlogyInstance> settingsRules = product.getAttribute("settings", Dictionary.class);
            for (Map.Entry<String, OlogyInstance> entry : newSettings.getMap().entrySet()) {
                String settingName = entry.getKey();
                OlogyInstance setting = entry.getValue();
                OlogyInstance settingRules = settingsRules.get(settingName);
                if (settingRules == null) {
                    throw new ActionInvocationException("Unexpected setting '" + settingName + "'");
                }
                String constraintString = settingRules.getAttribute("constraints", Property.class).getValue();
                String constraintType = constraintString;
                if(constraintType.contains("(")){
                    constraintType = constraintType.substring(0, constraintType.indexOf("("));
                }
                Constraint constraint = constraints.get(constraintType);
                if(constraint == null){
                    throw new ActionInvocationException("Unknown constraint '" + constraintString + "' for setting '" + settingName + "'");
                }
                try {
                    constraint.check(constraintString, setting.getAttribute("value", Property.class).toString());
                } catch (FailedConstraintException ex) {
                    throw new ActionInvocationException("Invalid setting '" + settingName + "': " + ex.getMessage());
                }
                settings.put(entry.getKey(), entry.getValue());
            }
            ServiceFactory.getOlogyInstanceService().update(instance);
            return instance;
        } catch (DeserialiserException ex) {
            throw new ActionInvocationException(ex);
        } catch (DaoException ex) {
            throw new ActionInvocationException(ex);
        }
    }

}
