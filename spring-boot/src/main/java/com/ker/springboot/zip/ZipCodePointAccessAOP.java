package com.ker.springboot.zip;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

// Good to have only one point cut file per project so that they are all in one place and easy to find
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
