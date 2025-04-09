package com.ims.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelloControllerTest {
	
	@InjectMocks
	private HelloController controller;

    @BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
	public void testHandleRequestView() {
        final ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("hello", modelAndView.getViewName());

        final Map<String, Object> model = modelAndView.getModel();
        assertNotNull(model);
    }

}
