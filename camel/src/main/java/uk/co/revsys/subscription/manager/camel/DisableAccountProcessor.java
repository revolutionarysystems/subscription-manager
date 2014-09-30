package uk.co.revsys.subscription.manager.camel;

import uk.co.revsys.esb.component.HttpProxyProcessor;

public class DisableAccountProcessor extends HttpProxyProcessor{

    private String id;
    
    public DisableAccountProcessor(String baseUrl) {
        super(baseUrl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getHttpMethod() {
        return PUT;
    }

    @Override
    public String getContentType() {
        return APPLICATION_FORM_URLENCODED;
    }

    @Override
    public String getUrlPath() {
        return "/account/" + getId() + "/action/disable";
    }

}
