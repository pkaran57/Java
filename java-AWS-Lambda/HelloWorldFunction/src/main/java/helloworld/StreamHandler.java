package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StreamHandler implements RequestStreamHandler {

    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {
        try (inputStream; outputStream) {
            String inputJSON = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            outputStream.write("output".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}