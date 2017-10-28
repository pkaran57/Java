package LanguageCore;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*Since JDK 5, Java has supported a feature that enables you to embed supplemental information into a source file.
This information, called an imp: annotation, does not change the actions of a program.
However, this information can be used by various tools during both development and deployment. For example, an annotation
might be processed by a source-code generator.*/
public class Annotations {

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
    }

    //annotate annotationsDemo method with MethodAnnotation
    @MethodAnnotation(methodDescription = "This is an annotated method description", methodID = 1)
    public static void annotationsDemo(){
    }
}
