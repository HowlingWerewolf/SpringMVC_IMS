package com.ims.web.form;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Getter
@Component
@Slf4j
public class PriceIncrease {

    private int percentage;

    public void setPercentage(final int i) {
        percentage = i;
        log.info("Percentage set to {}", i);
    }

}
