package apache;

import core.NetClient;
import core.Response;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Optional;


/**
 * Created by akhil raj azhikodan on 14/4/18.
 */
public abstract class RestClient implements NetClient
{
    private CloseableHttpClient httpClient;

    private Response executeMethod(RequestBuilder requestBuilder,
                                   URI uri,
                                   HashMap<String, String> headers,
                                   HashMap<String, String> queryParams) throws IOException
    {
        Optional.ofNullable(headers).ifPresent(x -> x.forEach(requestBuilder::addHeader));
        requestBuilder.setUri(uri);

        try(CloseableHttpResponse httpResponse = httpClient.execute(requestBuilder.build()))
        {
            HttpEntity entity = httpResponse.getEntity();

            String responseString = (Optional.ofNullable(entity).isPresent()) ? EntityUtils.toString(entity, "UTF-8") : null;
            Header[] responseHeaders = httpResponse.getAllHeaders();
            int statuscode = httpResponse.getStatusLine().getStatusCode();

            Response response = new Response( responseString, responseHeaders, statuscode);

            EntityUtils.consume(entity);
            return response;
        }
    }


    @Override
    public Response get(URI uri, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response post(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response put(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response delete(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers)
    {
        return null;
    }
}