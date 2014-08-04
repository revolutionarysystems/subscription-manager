package uk.co.revsys.subscription.manager.security;

import org.apache.shiro.SecurityUtils;
import uk.co.revsys.objectology.model.instance.OlogyInstance;
import uk.co.revsys.objectology.security.SecurityConstraint;
import uk.co.revsys.user.manager.model.User;

public class IsUserConstraint implements SecurityConstraint{

    @Override
    public boolean isSatisfied(OlogyInstance instance) {
        User user = SecurityUtils.getSubject().getPrincipals().oneByType(User.class);
        return instance.getId().equals(user.getAttributes().get("subscription-manager-user-id"));
    }

    @Override
    public String getNature() {
        return "isUser";
    }

}
