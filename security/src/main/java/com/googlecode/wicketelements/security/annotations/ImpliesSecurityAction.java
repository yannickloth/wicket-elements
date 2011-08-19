package com.googlecode.wicketelements.security.annotations;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ImpliesSecurityAction {
    Class<? extends Annotation>[] impliedActions();
}
