package com.googlecode.wicketelements.security.annotations;

/**
 * Annotation used to declare security constraints that should be validated when enabling Apache Wicket components.
 */
@SecurityActionQualifier
public @interface EnableAction {
    String permission() default "";

    String[] constraints() default {};
}
