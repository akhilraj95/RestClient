package apache;

import core.NetClient;
import core.Response;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Created by akhil raj azhikodan on 14/4/18.
 */
public  class RestClient implements NetClient
{
    private CloseableHttpClient httpClient;

    private Response executeMethod(RequestBuilder requestBuilder,
                                   URI uri,
                                   HashMap<String, String> headers) throws IOException
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
    public Response get(URI uri, HashMap<String, String> headers) throws IOException
    {
        return executeMethod(RequestBuilder.get(), uri, headers);
    }

    @Override
    public Response urlEncodedPost(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers)
            throws IOException
    {
        List<NameValuePair> params = requestParams.entrySet()
                .stream()
                .map(x -> new BasicNameValuePair(x.getKey(), x.getValue()))
                .collect(Collectors.toList());

        RequestBuilder requestBuilder = RequestBuilder.put().setEntity(new UrlEncodedFormEntity(params));
        return executeMethod(requestBuilder, uri, headers);
    }

    @Override
    public Response urlEncodedPut(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response urlEncodedDelete(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response jsonPost(URI uri, Object request, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response jsonPut(URI uri, Object request, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response jsonDelete(URI uri, Object request, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response StringPost(URI uri, String request, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response StringPut(URI uri, String request, HashMap<String, String> headers)
    {
        return null;
    }

    @Override
    public Response StringDelete(URI uri, String request, HashMap<String, String> headers)
    {
        return null;
    }
}