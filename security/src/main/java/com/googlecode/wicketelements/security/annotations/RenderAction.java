package com.googlecode.wicketelements.security.annotations;

import java.lang.annotation.*;

/**
 * Annotation used to declare security constraints that should be validated when rendering Apache Wicket components.
 */
@SecurityActionQualifier
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RenderAction {
    String permission() default "";

    String[] constraints() default {};
}
