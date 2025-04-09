package com.ims.web.validator;

import com.ims.web.form.PriceIncrease;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
@Getter
@Setter
public class PriceIncreaseValidator implements Validator {

    private final int DEFAULT_MIN_PERCENTAGE = 0;
    private final int DEFAULT_MAX_PERCENTAGE = 50;
    private int minPercentage = DEFAULT_MIN_PERCENTAGE;
    private int maxPercentage = DEFAULT_MAX_PERCENTAGE;

    @SuppressWarnings("rawtypes")
    public boolean supports(final Class clazz) {
        return PriceIncrease.class.equals(clazz);
    }

    public void validate(final Object obj, final Errors errors) {
        log.info("Validation started!");

        final PriceIncrease pi = (PriceIncrease) obj;

        if (pi == null) {
            errors.rejectValue("percentage", "error.not-specified", null, "Value required.");
            return;
        }

        log.info("Validating with {}: {}", pi, pi.getPercentage());
        if (pi.getPercentage() > maxPercentage) {
            errors.rejectValue("percentage", "error.too-high",
                    new Object[]{maxPercentage}, "Value too high.");
        }
        if (pi.getPercentage() <= minPercentage) {
            errors.rejectValue("percentage", "error.too-low",
                    new Object[]{minPercentage}, "Value too low.");
        }
    }

}
