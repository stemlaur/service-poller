package com.livi.servicepoller.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This entity represents the definition of a service.
 * A service is unique regarding its url.
 */
@EqualsAndHashCode
@ToString
public final class ServiceDefinition {
    private final String url;

    public ServiceDefinition(final String url) {
        this.url = Validate.notNull(url);
        validateUrlPattern(url);
    }

    private static void validateUrlPattern(final String url) {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(url);
        if (!matcher.matches()) {
            throw new ServiceURLIsNotValid(url);
        }
    }

    public String url() {
        return this.url;
    }
}
