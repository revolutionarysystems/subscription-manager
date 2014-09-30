package uk.co.revsys.subscription.manager.camel;

import uk.co.revsys.esb.component.HttpProxyProcessor;

public class SettingsViewProcessor extends HttpProxyProcessor{
 
    private String account;
    
    public SettingsViewProcessor(String baseUrl) {
        super(baseUrl);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String getHttpMethod() {
        return GET;
    }

    @Override
    public String getUrlPath() {
        return "/account/" + getAccount();
    }

    @Override
    public String getQueryString() {
        return "view=settings";
    }

}
