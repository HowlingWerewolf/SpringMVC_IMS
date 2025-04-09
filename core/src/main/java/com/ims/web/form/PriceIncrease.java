package com.ims.web.form;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PriceIncrease {

    private int percentage;

    public void setPercentage(int i) {
        percentage = i;
        log.info("Percentage set to " + i);
    }

    public int getPercentage() {
        return percentage;
    }

}
