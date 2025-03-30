package springmvc_ims.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;

public class HelloControllerTest extends TestCase {
	
	@InjectMocks
	private HelloController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
    public void testHandleRequestView() throws Exception {
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        String nowValue = (String) modelAndView.getModel().get("now");
        assertNotNull(nowValue);
    }

}
