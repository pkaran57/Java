package com.ker.spring.Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public final class StringUtil {
    private static final Logger LOGGER = LogManager.getLogger(StringUtil.class);
    private final static String SEPARATOR = " ";

    public final String joinString(final String str1, final String str2){
        LOGGER.trace("joinString() called");
        return str1 + SEPARATOR + str2;
    }
}
