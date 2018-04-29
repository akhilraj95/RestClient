package samples.httpbin;

import core.exception.ResponseHandlerException;
import core.response.Response;
import core.responsehandler.ResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FileHandler implements ResponseHandler<HttpResponse, File> {
    private final String PREFIX = "ENDURANCE";

    @Override
    public Response<File> handle(HttpResponse httpResponse) throws ResponseHandlerException {
        try {
            HttpEntity entity = httpResponse.getEntity();
            File tempFile = File.createTempFile(PREFIX, PREFIX);
            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                entity.writeTo(outputStream);
            }

            int statuscode = httpResponse.getStatusLine().getStatusCode();
            Map<String, String> responseHeaders = Arrays.asList(httpResponse.getAllHeaders()).stream().
                    collect(Collectors.toMap(Header::getName, Header::getValue));

            return new FileResponse(tempFile, responseHeaders, statuscode);
        } catch (IOException e) {
            throw new ResponseHandlerException(e);
        }
    }
}
