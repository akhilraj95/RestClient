package samples;

import apache.RestClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.HttpRequest;
import core.Response;
import core.responsehandler.ResponseHandler;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akhil raj azhikodan on 15/4/18.
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class LowTrafficClient extends RestClient
{
    Map<String, String> headers = new HashMap<String, String>() {{
        put("Header1","Value1");
        put("Header2","Value2");
    }};

    Map<String, String> entity = new HashMap<String, String>() {{
        put("Body1","BodyValue1");
        put("Body2","BodyValue2");
    }};


    private static final Gson gson  = new Gson();



    public SimpleResponse getSample() throws URISyntaxException, IOException
    {

        URI uri = new URI("https://httpbin.org/get");

     Response response = call(new HttpRequest.HttpRequestBuilder().get(uri).headers(headers).build());
     if(response.getStatusCode()!=200){
         throw new RuntimeException("request failed");
     }
     return gson.fromJson(response.getBody(), SimpleResponse.class);
    }



    public SimpleResponse postUrlEncodedSample(Map<String, String> entity) throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");

        Response response = call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).urlEncodedEntity(entity).build());
        return gson.fromJson(response.getBody(), SimpleResponse.class);
    }


    public Dto postJsonSample(Dto dto) throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");

        Response response = call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).jsonEntity(dto).build());
        return gson.fromJson(response.getBody(), Dto.class);
    }


    public SimpleResponse postJsonWithTimeoutSample(Dto dto) throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");

Response response = call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).jsonEntity(dto).setConnTimeoutInSec(100).setSocketTimeoutSec(100).build());
return gson.fromJson(response.getBody(), new TypeToken<SimpleResponse<Dto>>(){}.getType());
    }




    public Object postJsonWithTimeoutWithHandlerSample() throws Exception
    {
        URI uri = new URI("https://httpbin.org/post");
        Dto dto = new Dto();
        dto.setName("akhil");
        dto.setNumber(1);

        ResponseHandler handler = new ResponseHandler(String.class)
        {
            @Override
            public Object on2XX(Response response)
            {
               return response.getBody();
            }

            @Override
            public Object on3XX(Response response) throws Exception
            {
                throw new Exception("lol you are screwed");
            }
        };

return handler.handle(call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).jsonEntity(dto).setConnTimeoutInSec(30).setSocketTimeoutSec(30).build()));
        //We should only support this
//        System.out.println(RestClient.post(uri).headers(headers).jsonEntity(dto).setConnTimeoutInSec(30).setSocketTimeoutSec(30).executeWithHandler(handler));

        // We have to stop this
//        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
    }


    public SimpleResponse postStringSample(String entity) throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");

        Response response = call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).stringEntity(entity).setConnTimeoutInSec(100).setSocketTimeoutSec(100).build());
        return gson.fromJson(response.getBody(), SimpleResponse.class);
    }
}
