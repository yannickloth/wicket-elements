package com.googlecode.wicketelements.security.annotations;

import java.lang.annotation.*;

/**
 * Annotation used to declare security constraints that should be validated when enabling Apache Wicket components.
 */
@SecurityActionQualifier
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableAction {
    String permission() default "";

    String[] constraints() default {};
}
