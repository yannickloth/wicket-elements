package com.googlecode.wicketelements.security.annotations;

import org.apache.wicket.Page;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SignIn {
    Class<? extends Page> page();
}
