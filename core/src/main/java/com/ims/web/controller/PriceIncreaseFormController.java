package com.ims.web.controller;

import com.ims.service.ProductService;
import com.ims.web.form.PriceIncrease;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
public class PriceIncreaseFormController {

    @Autowired
    private ProductService productService;

    @Autowired
    @Qualifier("priceIncreaseValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @PostMapping(value = "/priceincrease")
    public ModelAndView onSubmit(@ModelAttribute("priceincrease") @Valid final PriceIncrease command,
                                 final BindingResult result) {

        // TODO if the product list is empty, do not throw error
        if (result.hasErrors()) {
            log.info("I know something is not ok with the PI. Errors below:");
            for (final ObjectError error : result.getAllErrors()) {
                log.info(error.getDefaultMessage());
            }
            return null;
        }

        final int increase = command.getPercentage();
        log.info("Increasing prices by {}%.", increase);
        productService.increasePrice(increase);

        log.info("returning from PriceIncreaseForm");

        return new ModelAndView(new RedirectView("hello"));
    }

    protected Object formBackingObject(final HttpServletRequest request) {
        final PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        log.info("priceincrease object set with {}%", priceIncrease.getPercentage());
        return priceIncrease;
    }

    @GetMapping(value = "/priceincrease")
    public String displayLogin(final Model model) {

        // very special thanks for this solution!!!  http://stackoverflow.com/questions/8781558/neither-bindingresult-nor-plain-target-object-for-bean-name-available-as-request
        model.addAttribute("priceincrease", new PriceIncrease());
        return "priceincrease";
    }

}
