package com.codingtest.fxtransactionvalidator.validator.check;

import com.codingtest.fxtransactionvalidator.model.VanillaOptionFxTransaction;
import com.codingtest.fxtransactionvalidator.model.constant.VanillaOptionStyle;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
public class VanillaOptionStyleCheck implements Check<VanillaOptionFxTransaction> {

    private final List<String> supportedStyles = List.of(VanillaOptionStyle.AMERICAN, VanillaOptionStyle.EUROPEAN);

    @Override
    public ConstraintViolation check(VanillaOptionFxTransaction tx) {
        log.debug("Try to check vanilla option transaction style. Transaction: {}", tx);

        if (null == tx) {
            throw new IllegalArgumentException("Unable to validate. Transaction is null.");
        }

        String txStyle = tx.getStyle();
        if (!StringUtils.hasText(txStyle)) {
            throw new IllegalArgumentException("Vanilla option transaction style is null or empty.");
        }

        for (String style : supportedStyles) {
            if (style.equalsIgnoreCase(txStyle)) {
                return null;
            }
        }

        log.debug("Vanilla option transaction style is successfully validated. Transaction: {}", tx);
        return new ConstraintViolation("Unsupported style of vanilla option transaction", txStyle);
    }

}
