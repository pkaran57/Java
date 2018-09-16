package com.ker.springboot.zip;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/*Imp: AOP provides the ability to implement crosscutting logic—that is, logic that applies to many parts of your application—in a single place and to have that logic applied
across your application automatically. Spring’s approach to AOP is to create dynamic proxies to the target objects and weave the objects with the
configured advice to execute the crosscutting logic.

Imp: AOP Terminologies
Join Points: A Join Point is a point in the execution of the application where an aspect can be plugged in. This point could be a method being called, an exception being thrown, or even a field being modified.
Pointcuts: Pointcuts are expressions that are matched with Join points to determine whether advice needs to be executed or not. This is a set of more than one Join point where an advice should be executed.
Advice: Advice defines both the when and where of an aspect. The actual action to be taken before or after method execution. Actual piece of code that is invoked during program execution by Spring's Aspect Oriented Programming framework.
Aspects: An aspect is the merger of Advice and Pointcuts. Advice and pointcuts define everything known about an aspect, what it does and where and when it does it.
Introductions: An Introduction allows adding new methods or attributes to existing classes. The new method and instance variable can be introduced to existing classes without having to change them, giving them new state and behavior.
Weaving: Weaving is the process of applying aspects to a target object to create a new proxied object. The target object at the specified Join points.

Imp: Types of Advice
Before: This advice runs before the execution of Join point methods. This functionality occurs before the advised method is invoked.
After: This advice gets executed after the Join point method finishes executing. This functionality occurs after the advice method completes, regardless of outcome.
After-returning: This advice method executes only if the Join point method executes normally. This functionality occurs after the advice method successfully completes.
After-throwing: This advice gets executed only when the join point method throws an exception., We can use this to roll back the transaction declaratively.
Around: Wraps the advised method, providing functionality before and after the advised method is invoked.

Imp: Good to have only one point cut file per project so that they are all in one place and easy to find*/
@Log4j2
@Aspect    // aspect = joint point + advice. Process of implementing AOP is called weaving
@Configuration
public class ZipCodePointAccessAOP {

    // Expression example: @Pointcut("execution(public String org.baeldung.dao.FooDao.findById(Long))")
    // Pointcut above will match exactly the execution of findById method of the FooDao class. This works, but it is not very flexible. Suppose we would like to match all the
    // methods of the FooDao class, which may have different signatures, return types, and arguments. To achieve this we may use wildcards: @Pointcut("execution(* org.baeldung.dao.FooDao.*(..))")
    // See OneNote for more information

    // execution(* PACKAGE.*.*(..))
    // above translates to: anyReturnType Package.classname.method(anyargs)
    @Before(value="execution(* com.ker.springboot.zip.ZipDaoJDBC.getLocationInfoForZip(..))")    // expression is called a point cut
    public void before(final JoinPoint joinPoint){
        // this method implementation is called advice
        log.info("@Before : Intercepted before following method call execution: " + joinPoint);
    }

    // only execute in case no exception thrown i.e. method executes and returns just fine
    @AfterReturning(value="execution(* com.ker.springboot.zip.ZipDaoJDBC.getLocationInfoForZip(..))", returning = "result")
    public void afterReturning(final JoinPoint joinPoint, final String result){
        log.info("@AfterReturning : Intercepted after successful execution was completed for following method: {}. Returning the following information: {}", joinPoint, result);
    }

    @AfterThrowing(value="execution(* com.ker.springboot.zip.ZipCodeService.throwException(..))", throwing = "exception")
    public void afterThrowing(final JoinPoint joinPoint, final Throwable exception){
        log.error("@AfterThrowing : Following exception thrown: {} by following method {}", exception, joinPoint.getSignature().getName());
    }

    // called irrespective of a method throwing an error or returning just fine
    @After(value="execution(* com.ker.springboot.zip.ZipCodeService.*(..))")
    public void after(final JoinPoint joinPoint){
        log.info("@After : {} method executed with following args - {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @Around(value="execution(* com.ker.springboot.zip.ZipCodeService.getZipData(..))")
    public String around(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final long callTime = System.currentTimeMillis();
        String returnValue = (String) proceedingJoinPoint.proceed();
        final long callCompletionTime = System.currentTimeMillis();
        log.info("@Around : Time taken for execution of {} = {} ms", proceedingJoinPoint.getSignature().getName(), callCompletionTime - callTime);
        return returnValue + " (was touched by @Around advice)";
    }
}
