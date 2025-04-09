package com.ims.web.controller;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import com.ims.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class ProductDeleteController {

    @Autowired
    private ProductService productService;

    /* TODO: handle with service */
    @Autowired
    private ProductDaoImpl productDao;

    @RequestMapping(method = RequestMethod.POST, value = "/productdelete")
    public ModelAndView onSubmit(@ModelAttribute("productdelete") final Product command,
                                 final BindingResult result) {

        if (result.hasErrors()) {
            log.info("I know something is not ok. Errors below:");
            for (final ObjectError error : result.getAllErrors()) {
                log.info(error.getDefaultMessage());
            }
            return null;
        }

        final int id = command.getId();
        final String description = command.getDescription();
        final Double price = command.getPrice();

        log.info("deleting from DB this product: {} with price {} with ID {}",
                description, price, id);

        productDao.delete(command);

        log.info("returning from PriceIncreaseForm");

        return new ModelAndView(new RedirectView("hello"));
    }

    protected Object formBackingObject(final HttpServletRequest request) {
        final Product product = Product.builder()
                .description("dummy")
                .price(-1.0d)
                .build();
        log.info("productdelete object set with {} with price {}",
                product.getDescription(), product.getPrice());
        return product;
    }

    @GetMapping(value = "/productdelete")
    public ModelAndView displayLogin(final Model model) {

        model.addAttribute("productdelete", Product.builder().build());
        final String now = (new java.util.Date()).toString();
        log.info("returning productdelete view with " + now);

        final Map<String, Object> myModel = new HashMap<>();
        myModel.put("now", now);
        myModel.put("products", this.productService.getProducts());

        return new ModelAndView("productdelete", "model", myModel);
    }

}
