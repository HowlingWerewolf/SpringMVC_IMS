package com.ims.web.controller;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloControllerTest {

	@Test
	void testGreetReturnsMessageAndNow() {
		final HelloController controller = new HelloController();
		final var resp = controller.greet();
		assertNotNull(resp);
		// status code 200 expected
		assertEquals(200, resp.getStatusCode().value());
		final Map<String, String> body = resp.getBody();
		assertNotNull(body);
		assertTrue(body.containsKey("message"));
		assertTrue(body.containsKey("now"));
	}

}
