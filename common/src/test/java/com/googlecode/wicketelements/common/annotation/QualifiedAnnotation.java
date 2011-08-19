package com.googlecode.wicketelements.common.annotation;

import java.lang.annotation.*;

@QualifyingAnnotation
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface QualifiedAnnotation {
}
