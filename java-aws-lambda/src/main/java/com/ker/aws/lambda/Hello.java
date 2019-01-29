package com.ker.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @See <a href="https://docs.aws.amazon.com/lambda/latest/dg/java-programming-model-handler-types.html">AWS Lambda Function Handler in Java</a>
 */
public class Hello implements RequestHandler<Void, String> {

  /**
   * The inputType and outputType can be one of the following:
   *
   * <p>• Primitive Java types (such as String or int).
   *    • Predefined AWS event types defined in the aws-lambda-java-events library. For example S3Event is one of the POJOs predefined in the library that provides methods for you to easily read
   *      information from the incoming Amazon S3 event. • You can also write your own POJO class. AWS Lambda will automatically serialize and deserialize input and output JSON based on the POJO type.
   *    • Stream type (If you do not want to use POJOs or if Lambda's serialization approach does not meet your needs, you can use the byte stream implementation.
   *
   * <p>(package.class::method-reference) is the handler. Example :
   * com.ker.aws.lambda.Hello::handleRequest
   *
   * <p>If your Java code contains multiple methods with same name as the handler name, then AWS Lambda uses the following rules to pick a method to invoke:
   * 1. Select the method with the largest number of parameters.
   * 2. If two or more methods have the same number of parameters, AWS Lambda selects the method that has the Context as the last parameter. If none or all of these methods
   *    have the Context parameter, then the behavior is undefined.
   *
   * @param context You can omit the Context object from the handler method signature if it isn't needed.
   * @return
   */
  public String handleRequest(Void voidParam, Context context) {
        return "Hello World from Lambda!";
    }
}
