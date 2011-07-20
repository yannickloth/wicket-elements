package com.googlecode.wicketelements.security;


import org.apache.wicket.protocol.http.WebSession;

public interface SessionInvalidator {
    void invalidate(final WebSession sessionParam);
}
