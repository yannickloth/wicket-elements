package com.googlecode.wicketelements.security.annotations;

/**
 * Annotation used to declare security constraints that should be validated when rendering Apache Wicket components.
 */
@SecurityActionQualifier
public @interface RenderAction {
    String permission() default "";

    String[] constraints() default {};
}
