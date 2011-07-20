package com.googlecode.wicketelements.security.annotations;

import org.apache.wicket.Page;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SignInPage {
    Class<? extends Page> page();
}
