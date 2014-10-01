package uk.co.revsys.subscription.manager.action.model;

import uk.co.revsys.objectology.action.model.AbstractAction;

public class DisableAccountAction extends AbstractAction{

    @Override
    public String getNature() {
        return "disableAccount";
    }

}
