package samples;

import apache.RestClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.HttpRequest;
import core.Response;
import core.responsehandler.ResponseHandler;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by akhil raj azhikodan on 15/4/18.
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class LowTrafficClient extends RestClient {
    Map<String, String> headers = new HashMap<String, String>() {{
        put("Header1", "Value1");
        put("Header2", "Value2");
    }};

    private static final Gson gson = new Gson();


    public SimpleResponse getSample() throws URISyntaxException, IOException {

        URI uri = new URI("https://httpbin.org/get");

        Response<String> response = call(new HttpRequest.HttpRequestBuilder().get(uri).headers(headers).build());
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("request failed");
        }
        return gson.fromJson(response.getBody(), SimpleResponse.class);
    }


    public SimpleResponse postUrlEncodedSample(Map<String, String> entity) throws URISyntaxException, IOException {
        URI uri = new URI("https://httpbin.org/post");

        Response<String> response = call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).urlEncodedEntity(entity).build());
        return gson.fromJson(response.getBody(), SimpleResponse.class);
    }


    public Dto postJsonSample(Dto dto) throws URISyntaxException, IOException {
        URI uri = new URI("https://httpbin.org/post");

        Response<String> response = call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).jsonEntity(dto).build());
        return gson.fromJson(response.getBody(), Dto.class);
    }


    public SimpleResponse postJsonWithTimeoutSample(Dto dto) throws URISyntaxException, IOException {
        URI uri = new URI("https://httpbin.org/post");

        Response<String> response = call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).jsonEntity(dto).setConnTimeoutInSec(100).setSocketTimeoutSec(100).build());
        return gson.fromJson(response.getBody(), new TypeToken<SimpleResponse<Dto>>() {
        }.getType());
    }


    public SimpleResponse postJsonWithTimeoutWithHandlerSample() throws URISyntaxException, IOException {
        URI uri = new URI("https://httpbin.org/post");
        Dto dto = new Dto();
        dto.setName("akhil");
        dto.setNumber(1);

        ResponseHandler<HttpResponse, SimpleResponse<Dto>> handler = (httpResponse) -> {
            HttpEntity entity = httpResponse.getEntity();
            String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
            EntityUtils.consume(entity);

            int statuscode = httpResponse.getStatusLine().getStatusCode();
            Map<String, String> responseHeaders = Arrays.asList(httpResponse.getAllHeaders()).stream().
                    collect(Collectors.toMap(Header::getName, Header::getValue));

            return new Response<>(gson.fromJson(responseString, SimpleResponse.class), responseHeaders, statuscode);
        };

        Response<SimpleResponse<Dto>> response = call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).jsonEntity(dto).
                setConnTimeoutInSec(30).setSocketTimeoutSec(30).setHandler(handler).build());
        return response.getBody();
        //We should only support this
//        System.out.println(RestClient.post(uri).headers(headers).jsonEntity(dto).setConnTimeoutInSec(30).setSocketTimeoutSec(30).executeWithHandler(handler));

        // We have to stop this
//        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
    }


    public SimpleResponse postStringSample(String entity) throws URISyntaxException, IOException {
        URI uri = new URI("https://httpbin.org/post");

        Response<String> response = call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).stringEntity(entity).setConnTimeoutInSec(100).setSocketTimeoutSec(100).build());
        return gson.fromJson(response.getBody(), SimpleResponse.class);
    }
}
