package com.googlecode.wicketelements.common.annotation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AnnotationHelperTest {

    @Test
    public void testHasQualifiedAnnotationsNonePresent() {
        Assert.assertFalse(AnnotationHelper.isQualifiedAnnotationPresent(getClass(), QualifyingAnnotation.class), "The class has no qualified annotation but the method says it has...");
    }

    @Test
    public void testHasQualifiedAnnotationsOnePresent() {
        Assert.assertTrue(AnnotationHelper.isQualifiedAnnotationPresent(AnnotatedClass.class, QualifyingAnnotation.class), "The class has no qualified annotation but the method says it has...");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testHasQualifiedAnnotationsAnnotParamNull() {
        AnnotationHelper.isQualifiedAnnotationPresent(getClass(), null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testHasQualifiedAnnotationsObjectClassParamNull() {
        AnnotationHelper.isQualifiedAnnotationPresent(null, null);
    }
}
