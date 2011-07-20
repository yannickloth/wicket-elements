package com.googlecode.wicketelements.security.annotations;

/**
 * Annotation used to declare security constraints that should be validated when instantiating Apache Wicket components.
 */
@SecurityActionQualifier
public @interface InstantiateAction {
    String permission() default "";

    String[] constraints() default {};
}
