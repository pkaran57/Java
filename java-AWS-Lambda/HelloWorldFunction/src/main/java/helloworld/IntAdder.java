package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Handler for requests to Lambda function.
 * Handlers - https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html
 * Context - https://docs.aws.amazon.com/lambda/latest/dg/java-context.html
 */
public class IntAdder implements RequestHandler<Integer, Integer> {

    public Integer handleRequest(final Integer input, final Context context) {
        return input == null ? 0 : input + 1;
    }
}
