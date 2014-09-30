package uk.co.revsys.subscription.manager.camel;

import uk.co.revsys.esb.component.HttpProxyComponent;
import java.util.Map;

import org.apache.camel.Processor;

public class SubscriptionManagerComponent extends HttpProxyComponent{

    @Override
    protected void populateMappings(Map<String, Class<? extends Processor>> mappings) {
        mappings.put("disableAccount", DisableAccountProcessor.class);
        mappings.put("settings", SettingsViewProcessor.class);
    }
    
}
