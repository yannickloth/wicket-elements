package com.googlecode.wicketelements.common.annotation;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.util.List;

public class AnnotationHelperTest {

    @Test
    public void testIsQualifiedAnnotationPresentNonePresent() {
        Assert.assertFalse(AnnotationHelper.isQualifiedAnnotationPresent(getClass(), QualifyingAnnotation.class), "The class has no qualified annotation but the method says it has...");
    }

    @Test
    public void testIsQualifiedAnnotationPresentOnePresent() {
        Assert.assertTrue(AnnotationHelper.isQualifiedAnnotationPresent(AnnotatedClass.class, QualifyingAnnotation.class), "The class has no qualified annotation but the method says it has...");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIsQualifiedAnnotationPresentAnnotParamNull() {
        AnnotationHelper.isQualifiedAnnotationPresent(getClass(), null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIsQualifiedAnnotationPresentObjectClassParamNull() {
        AnnotationHelper.isQualifiedAnnotationPresent(null, null);
    }

    @Test
    public void testGetQualifiedAnnotationsNone() {
        List<Annotation> annots = AnnotationHelper.getQualifiedAnnotations(getClass(), QualifyingAnnotation.class);
        Assert.assertEquals(annots.size(), 0, "No qualified annotation should be returned in the list.");
    }

    @Test
    public void testGetQualifiedAnnotationsOne() {
        List<Annotation> annots = AnnotationHelper.getQualifiedAnnotations(AnnotatedClass.class, QualifyingAnnotation.class);
        Assert.assertEquals(annots.size(), 1, "Exactly one qualified annotation should be returned in the list.");
    }

    @Test
    public void testGetQualifiedAnnotationsTwo() {
        List<Annotation> annots = AnnotationHelper.getQualifiedAnnotations(TwiceAnnotatedClass.class, QualifyingAnnotation.class);
        Assert.assertEquals(annots.size(), 2, "Exactly two qualified annotations should be returned in the list.");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetQualifiedAnnotationsAnnotParamNull() {
        AnnotationHelper.getQualifiedAnnotations(getClass(), null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetQualifiedAnnotationsObjectClassParamNull() {
        AnnotationHelper.getQualifiedAnnotations(null, null);
    }
}
