
package uk.co.revsys.subscription.manager.action.handler;

import java.util.HashMap;
import java.util.Map;
import org.easymock.Capture;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.co.revsys.objectology.action.ActionRequest;
import uk.co.revsys.objectology.mapping.json.JsonInstanceMapper;
import uk.co.revsys.objectology.model.ReferenceType;
import uk.co.revsys.objectology.model.instance.Dictionary;
import uk.co.revsys.objectology.model.instance.Link;
import uk.co.revsys.objectology.model.instance.OlogyInstance;
import uk.co.revsys.objectology.model.instance.Property;
import uk.co.revsys.objectology.model.template.DictionaryTemplate;
import uk.co.revsys.objectology.model.template.LinkTemplate;
import uk.co.revsys.objectology.model.template.OlogyTemplate;
import uk.co.revsys.objectology.model.template.PropertyTemplate;
import uk.co.revsys.objectology.service.OlogyInstanceService;
import uk.co.revsys.objectology.service.ServiceFactory;
import uk.co.revsys.subscription.manager.action.model.UpdateSettingsAction;
import uk.co.revsys.subscription.manager.constraint.BooleanConstraint;
import uk.co.revsys.subscription.manager.constraint.Constraint;
import uk.co.revsys.subscription.manager.constraint.DefaultConstraintMap;
import uk.co.revsys.subscription.manager.constraint.EnumerationConstraint;

public class UpdateSettingsActionHandlerTest {

    public UpdateSettingsActionHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testInvoke() throws Exception {
        IMocksControl mocksControl = EasyMock.createControl();
        OlogyInstanceService mockService = mocksControl.createMock(OlogyInstanceService.class);
        ServiceFactory.setOlogyInstanceService(mockService);
        OlogyTemplate productTemplate = new OlogyTemplate();
        OlogyTemplate settingRuleTemplate = new OlogyTemplate();
        settingRuleTemplate.setAttributeTemplate("constraints", new PropertyTemplate());
        productTemplate.setAttributeTemplate("settings", new DictionaryTemplate(settingRuleTemplate));
        OlogyInstance product = new OlogyInstance(productTemplate);
        Dictionary settingsRules = new Dictionary();
        OlogyInstance serviceModeSettingRules = new OlogyInstance(settingRuleTemplate);
        serviceModeSettingRules.setAttribute("constraints", new Property("boolean"));
        settingsRules.put("serviceMode", serviceModeSettingRules);
        OlogyInstance maxCapturesSettingRules = new OlogyInstance(settingRuleTemplate);
        maxCapturesSettingRules.setAttribute("constraints", new Property("enumeration(50, 100, unlimited)"));
        settingsRules.put("maxCaptures", maxCapturesSettingRules);
        product.setAttribute("settings", settingsRules);
        OlogyTemplate subscriptionTemplate = new OlogyTemplate();
        subscriptionTemplate.setAttributeTemplate("governingProduct", new LinkTemplate("product", ReferenceType.id));
        OlogyTemplate settingTemplate = new OlogyTemplate();
        settingTemplate.setAttributeTemplate("value", new PropertyTemplate());
        subscriptionTemplate.setAttributeTemplate("settings", new DictionaryTemplate(settingTemplate));
        OlogyInstance subscription = new OlogyInstance(subscriptionTemplate);
        subscription.setAttribute("governingProduct", new Link("1234"));
        subscription.setAttribute("settings", new Dictionary());        
        ActionRequest request = new ActionRequest();
        request.getParameters().put("settings", "{\"serviceMode\": {\"value\": \"true\"}, \"maxCaptures\": {\"value\": \"50\"}}");
        UpdateSettingsAction action = new UpdateSettingsAction();
        expect(mockService.findById("product", "1234")).andReturn(product);
        Capture<OlogyInstance> capture = new Capture<OlogyInstance>();
        expect(mockService.update(capture(capture))).andReturn(null);
        mocksControl.replay();
        UpdateSettingsActionHandler actionHandler = new UpdateSettingsActionHandler(new JsonInstanceMapper(), new DefaultConstraintMap());
        actionHandler.invoke(subscription, action, request);
        mocksControl.verify();
        OlogyInstance result = capture.getValue();
        assertEquals("true", ((Dictionary<OlogyInstance>)result.getAttribute("settings", Dictionary.class)).get("serviceMode").getAttribute("value", Property.class).toString());
    }

}