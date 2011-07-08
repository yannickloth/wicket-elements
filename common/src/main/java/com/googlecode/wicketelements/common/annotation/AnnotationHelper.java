/*
 *  Copyright 2011 Yannick LOTH.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package com.googlecode.wicketelements.common.annotation;

import java.lang.annotation.Annotation;

import static com.googlecode.jbp.common.requirements.ParamRequirements.INSTANCE;

/**
 * Provides utility methods process annotations.
 *
 * @author Yannick LOTH
 */
public final class AnnotationHelper {

    private AnnotationHelper() {
    }

    /**
     * Checks if the class or its superclass and interface hierarchies have the
     * specified annotation.
     *
     * @param classParam      The class to check for the specified annotation.
     * @param annotationClass The specified annotation.
     * @return <code>true</code> if the class has the specified annotation,
     *         <code>false</else>.
     */
    public static <A extends Annotation> boolean hasAnnotation(Class<?> classParam, final Class<A> annotationClass) {
        return hasAnnotation(classParam, annotationClass, true, true);
    }

    /**
     * Checks if the class has the specified annotation.
     *
     * @param classParam                   The class to check for the specified annotation.
     * @param annotationClass              The specified annotation.
     * @param recursivelyCheckInterfaces   <code>true</code> if the interface
     *                                     hierarchy also has to be checked for the specified annotation.
     * @param recursivelyCheckSuperClasses <code>true</code> if the superclass
     *                                     hierarchy also has to be checked for the specified annotation.
     * @return <code>true</code> if the class has the specified annotation,
     *         <code>false</else>.
     */
    public static <A extends Annotation> boolean hasAnnotation(Class<?> classParam, final Class<A> annotationClass, final boolean recursivelyCheckSuperClasses, final boolean recursivelyCheckInterfaces) {
        INSTANCE.requireNotNull(classParam);
        INSTANCE.requireNotNull(annotationClass);
        if (!annotationClass.isAnnotation()) {
            throw new IllegalArgumentException("Parameter 'annotationClass' must be the Class of an annotation type, but is not.");
        }
        if (classParam.getAnnotation(annotationClass) != null) {
            return true;
        }
        if (recursivelyCheckSuperClasses) {
            final Class superClass = classParam.getSuperclass();
            if (superClass != null) {
                return hasAnnotation(superClass, annotationClass, recursivelyCheckSuperClasses, recursivelyCheckInterfaces);
            }
        }
        if (recursivelyCheckInterfaces) {
            for (final Class<?> interfaceClass : classParam.getInterfaces()) {
                if (hasAnnotation(interfaceClass, annotationClass, recursivelyCheckSuperClasses, recursivelyCheckInterfaces)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the specified annotation from the class or it supertype hierarchies.
     *
     * @param <A>
     * @param classParam      The class to check for the specified annotation.
     * @param annotationClass The specified annotation to return.
     * @return Returns the specified annotation or <code>null</code> if none
     *         found.
     */
    public static <A extends Annotation> A getAnnotation(Class classParam, final Class<A> annotationClass) {
        return getAnnotation(classParam, annotationClass, true, true);
    }

    /**
     * Gets the specified annotation from the class or it supertype hierarchies.
     *
     * @param <A>
     * @param classParam                   The class to check for the specified annotation.
     * @param annotationClass              The specified annotation to return.
     * @param recursivelyCheckInterfaces   <code>true</code> if the interface
     *                                     hierarchy also has to be checked for the specified annotation.
     * @param recursivelyCheckSuperClasses <code>true</code> if the superclass
     *                                     hierarchy also has to be checked for the specified annotation.
     * @return Returns the specified annotation or <code>null</code> if none
     *         found.
     */
    public static <A extends Annotation> A getAnnotation(Class classParam, final Class<A> annotationClass, final boolean recursivelyCheckSuperClasses, final boolean recursivelyCheckInterfaces) {
        INSTANCE.requireNotNull(classParam);
        INSTANCE.requireNotNull(annotationClass);
        if (!annotationClass.isAnnotation()) {
            throw new IllegalArgumentException("Parameter 'annotationClass' must be the Class of an annotation type, but is not.");
        }
        {
            final A annot = (A) classParam.getAnnotation(annotationClass);
            if (annot != null) {
                return annot;
            }
        }
        if (recursivelyCheckSuperClasses) {
            final Class superClass = classParam.getSuperclass();
            if (superClass != null) {
                final A annot = getAnnotation(superClass, annotationClass, recursivelyCheckSuperClasses, recursivelyCheckInterfaces);
                if (annot != null) {
                    return annot;
                }
            }
        }
        if (recursivelyCheckInterfaces) {
            for (final Class<?> interfaceClass : classParam.getInterfaces()) {
                final A annot = getAnnotation(interfaceClass, annotationClass, recursivelyCheckSuperClasses, recursivelyCheckInterfaces);
                if (annot != null) {
                    return annot;
                }
            }
        }
        return null;
    }
}
