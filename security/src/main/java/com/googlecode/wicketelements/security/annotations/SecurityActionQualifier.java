package com.googlecode.wicketelements.security.annotations;

import java.lang.annotation.*;

/**
 * Annotation used to qualify actions (default ones are: instantiate, render, enable) on Apache Wicket components.
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecurityActionQualifier {
}
