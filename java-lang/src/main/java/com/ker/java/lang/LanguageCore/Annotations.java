package com.ker.java.lang.LanguageCore;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/*Since JDK 5, Java has supported a feature that enables you to embed supplemental information into a source file.
This information, called an imp: annotation, does not change the actions of a program.
However, this information can be used by various tools during both development and deployment. For example, an annotation
might be processed by a source-code generator.*/
public class Annotations {

    @RepeatableAnnotation({"Test", "1"})
    @RepeatableAnnotation({"Test", "2"})
    @Target({ElementType.METHOD})
    /*A retention policy determines at what point annotation should be discarded.
    Java defined 3 types of retention policies through java.lang.annotation.RetentionPolicy enumeration. It has SOURCE, CLASS and RUNTIME.
    Annotation with retention policy SOURCE will be retained only with source code, and discarded during compile time.
    Annotation with retention policy CLASS will be retained till compiling the code, and discarded during runtime. i.e. will be present in .class files
    Annotation with retention policy RUNTIME will be available to the JVM through runtime.
    The retention policy will be specified by using java built-in annotation @Retention, and we have to pass the retention policy type.
    NOTE: An annotation on a local variable declaration is not retained in the .class file.
    imp: The default retention policy type is CLASS.*/
    @Retention(RetentionPolicy.RUNTIME)
    /*@interface tells the compiler that an annotation type is being declared. All annotation types automatically extend the Annotation
    interface. Thus, Annotation is a super-interface of all annotations Annotation interface is defined in java.lang.annotation package.
    It overrides hashCode( ), equals( ), and toString( ), which are defined by Object. It also specifies annotationType( ),
    which returns a Class object that represents the invoking annotation.*/
    private @interface MethodAnnotation {

        /*All annotations consist solely of method declarations. However, you don’t provide bodies for these methods.
        Instead, Java implements these methods. Moreover, the methods act much like fields.*/
        String methodDescription();
        int methodID();
        String defaultField() default "defaultValue";    // default value for an annotation field
    }

    // The value of the @Repeatable meta-annotation, in parentheses, is the type of the container annotation that the
    // Java compiler generates to store repeating annotations.
    @Repeatable(RepeatableAnnotations.class)
    @interface RepeatableAnnotation {
        String[] value();
    }

    //The containing annotation type must have a value element with an array type. The component type of the array type
    // must be the repeatable annotation type.
    @interface RepeatableAnnotations{
        RepeatableAnnotation[] value();
    }

    //annotate annotationsDemo method with MethodAnnotation
    @MethodAnnotation(methodDescription = "This is an annotated method description", methodID = 1)
    /*@SuppressWarnings(value = "unchecked")
    void myMethod() { ... }
    If there is just one element named value, then the name can be omitted, as in:

    @SuppressWarnings("unchecked")
    void myMethod() { ... }*/
    public static void annotationsDemo(){
        /*TOPIC: Obtain annotations at runtime using reflections

        Although annotations are designed mostly for use by other development or deployment
        tools, if they specify a retention policy of RUNTIME, then they can be queried at run time
        by any Java program through the use of reflection. Reflection is the feature that enables
        information about a class to be obtained at run time. The reflection API is contained in
        the java.lang.reflect package.*/

        try {
            Method m = Annotations.class.getMethod("annotationsDemo");
            MethodAnnotation methodAnnotation = m.getAnnotation(MethodAnnotation.class);

            if(m.isAnnotationPresent(MethodAnnotation.class)){
                System.out.println("annotationsDemo() method is annotated by the MethodAnnotation annotation");
            }

            System.out.println("MethodAnnotation for annotationsDemo(): methodDescription - " + methodAnnotation.methodDescription() + ", methodID - " + methodAnnotation.methodID());

            RepeatableAnnotation[] repeatableAnnotations = m.getAnnotationsByType(RepeatableAnnotation.class);

            if(repeatableAnnotations.length != 0){
                System.out.println("\nFollowing are repeatable annotations:\n");
                for(RepeatableAnnotation repeatableAnnotation: repeatableAnnotations)
                    System.out.println(repeatableAnnotation);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /*Some Built-In Annotations

    @Documented
    The @Documented annotation is a marker interface that tells a tool that an annotation is  to be documented.
    It is designed to be used only as an annotation to an annotation declaration.

    @Target
    The @Target annotation specifies the types of items to which an annotation can be applied. It is designed to be used
    only as an annotation to another annotation. @Target takes one argument, which is an array of constants of the
    ElementType enumeration. Example: @Target( { ElementType.FIELD, ElementType.LOCAL_VARIABLE } )
    If you don't use @Target, then, except for type parameters, the annotation can be used on any declaration.

    Target Constant                 Annotation Can Be Applied To
    ANNOTATION_TYPE                     Another annotation
    CONSTRUCTOR                         Constructor
    FIELD                               Field
    LOCAL_VARIABLE                      Local variable
    METHOD                              Method
    PACKAGE                             Package
    PARAMETER                           Parameter
    TYPE                                Class, interface, or enumeration
    TYPE_PARAMETER                      Type parameter (Added by JDK 8.)
    TYPE_USE                            Type use (Added by JDK 8.)

    @Inherited
    @Inherited is a marker annotation that can be used only on another annotation declaration.
    Furthermore, it affects only annotations that will be used on class declarations. @Inherited
    causes the annotation for a superclass to be inherited by a subclass. Therefore, when a
    request for a specific annotation is made to the subclass, if that annotation is not present in
    the subclass, then its superclass is checked. If that annotation is present in the superclass,
    and if it is annotated with @Inherited, then that annotation will be returned.

    @Override
    @Override is a marker annotation that can be used only on methods. A method annotated
    with @Override must override a method from a superclass. If it doesn’t, a compile-time
    error will result. It is used to ensure that a superclass method is actually overridden, and not
    simply overloaded.

    @Deprecated
    @Deprecated is a marker annotation. It indicates that a declaration is obsolete and has
    been replaced by a newer form.

    @FunctionalInterface
    @FunctionalInterface is a marker annotation added by JDK 8 and designed for use on
    interfaces. It indicates that the annotated interface is a functional interface. A functional
    interface is an interface that contains one and only one abstract method. Functional interfaces
    are used by lambda expressions. (See Chapter 15 for details on functional interfaces and
    lambda expressions.) If the annotated interface is not a functional interface, a compilation
    error will be reported. It is important to understand that @FunctionalInterface is not
    needed to create a functional interface. Any interface with exactly one abstract method is,
    by definition, a functional interface. Thus, @FunctionalInterface is purely informational.

    @SafeVarargs
    @SafeVarargs is a marker annotation that can be applied to methods and constructors. It
    indicates that no unsafe actions related to a varargs parameter occur. It is used to suppress
    unchecked warnings on otherwise safe code as it relates to non-reifiable vararg types and
    parameterized array instantiation. (A non-reifiable type is, essentially, a generic type.
    Generics are described in Chapter 14.) It must be applied only to vararg methods or
    constructors that are static or final.

    @SuppressWarnings
    @SuppressWarnings specifies that one or more warnings that might be issued by the
    compiler are to be suppressed. The warnings to suppress are specified by name, in
    string form.
    */
}
