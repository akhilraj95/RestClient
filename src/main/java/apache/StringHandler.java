package apache;

import core.response.BasicResponse;
import core.response.Response;
import core.responsehandler.ResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StringHandler implements ResponseHandler<HttpResponse, String> {

    @Override
    public BasicResponse handle(HttpResponse httpResponse) throws IOException
    {
        try {
            HttpEntity entity = httpResponse.getEntity();
            String responseString = (Optional.ofNullable(entity).isPresent()) ? EntityUtils.toString(entity, Charset.defaultCharset()) : null;
            EntityUtils.consume(entity);

            int statuscode = httpResponse.getStatusLine().getStatusCode();
            Map<String, String> responseHeaders = Arrays.asList(httpResponse.getAllHeaders()).stream().
                    collect(Collectors.toMap(Header::getName, Header::getValue));

            return new BasicResponse(responseString, responseHeaders, statuscode);
        } catch (IOException e) {
            throw new ResponseHandlerException(e);
        }
    }
}
