package com.ims.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to serve Angular SPA index.html for client-side routing.
 * This ensures that unmapped Angular routes are served with the index.html file,
 * allowing Angular's router to handle the routing on the client side.
 */
@Controller
public class AngularSpaController {

    @GetMapping(value = "/")
    public String index() {
        return "forward:/index.html";
    }
}

