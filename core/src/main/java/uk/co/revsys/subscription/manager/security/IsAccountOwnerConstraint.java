package uk.co.revsys.subscription.manager.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.shiro.SecurityUtils;
import uk.co.revsys.objectology.dao.DaoException;
import uk.co.revsys.objectology.model.instance.Link;
import uk.co.revsys.objectology.model.instance.OlogyInstance;
import uk.co.revsys.objectology.security.SecurityConstraint;
import uk.co.revsys.user.manager.model.User;

public class IsAccountOwnerConstraint implements SecurityConstraint {

    @Override
    public boolean isSatisfied(OlogyInstance instance) {
        User user = SecurityUtils.getSubject().getPrincipals().oneByType(User.class);
        OlogyInstance account;
        if (instance.getType().equals("account")) {
            account = instance;
        } else {
            try {
                account = (OlogyInstance) instance.getAttribute("account", Link.class).getAssociatedObject();
            } catch (DaoException ex) {
                throw new RuntimeException(ex);
            }
        }
        return account.getAttribute("mainUser").equals(user.getAttributes().get("subscription-manager-user-id"));
    }

    @Override
    public String getNature() {
        return "isAccountOwner";
    }

}
