package com.ims.web.controller;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import com.ims.service.ProductService;
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
public class ProductAddFormController {

    @Autowired
    private ProductService productService;

    @Autowired
    @Qualifier("productValidator")
    private Validator validator;

    /* TODO: handle with service */
    @Autowired
    private ProductDaoImpl productDao;

    @InitBinder
    private void initBinder(final WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @PostMapping(value = "/productadd")
    public ModelAndView onSubmit(@ModelAttribute("productadd") @Valid final Product command,
                                 final BindingResult result) {

        if (result.hasErrors()) {
            log.info("I know something is not ok with the PI. Errors below:");
            for (final ObjectError error : result.getAllErrors()) {
                log.info(error.getDefaultMessage());
            }
            return null;
        }

        String description = command.getDescription();
        Double price = command.getPrice();

        log.info("adding to DB this product: {} with price {}", description, price);
        productDao.save(command);
        log.info("returning from ProductDeleteController");

        return new ModelAndView(new RedirectView("hello"));
    }

    protected Object formBackingObject(final HttpServletRequest request) {
        final Product product = Product.builder()
                .description("dummy")
                .price(1.0d)
                .build();
        log.info("productadd object set with {} with price {}", product.getDescription(), product.getPrice());
        return product;
    }

    @GetMapping(value = "/productadd")
    public String displayLogin(final Model model) {
        model.addAttribute("productadd", Product.builder().build());
        return "productadd";
    }

}
