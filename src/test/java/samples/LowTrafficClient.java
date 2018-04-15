package samples;

import apache.RestClient;
import core.HttpRequest;
import core.Response;
import core.responsehandler.ResponseHandler;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
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




    public Response getSample() throws URISyntaxException, IOException
    {

        URI uri = new URI("https://httpbin.org/get");

        //We should only support this
     return   call(new HttpRequest.HttpRequestBuilder().get(uri).headers(headers).build());
//        Assert.assertNotNull(response.getHeaders());
//        System.out.println(RestClient.get(uri).headers(headers).execute());
    }



    public Response postUrlEncodedSample() throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");

        //We should only support this
        return call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).urlEncodedEntity(entity).build());
//        System.out.println(RestClient.post(uri).headers(headers).urlEncodedEntity(entity).execute());

        // We have to stop this
//        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
    }


    public Response postJsonSample() throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");
        Dto dto = new Dto();
        dto.setName("akhil");
        dto.setNumber(1);

        return call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).jsonEntity(dto).build());
        //We should only support this
//        System.out.println(RestClient.post(uri).headers(headers).jsonEntity(dto).execute());

        // We have to stop this
//        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
    }


    public Response postJsonWithTimeoutSample() throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");
        Dto dto = new Dto();
        dto.setName("akhil");
        dto.setNumber(1);

return call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).jsonEntity(dto).setConnTimeoutInSec(100).setSocketTimeoutSec(100).build());
        //We should only support this
//        System.out.println(RestClient.post(uri).headers(headers).jsonEntity(dto).setConnTimeoutInSec(100).setSocketTimeoutSec(100).execute());

        // We have to stop this
//        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
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


    public Response postStringSample() throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");
        String entity = "<xml>test</xml>";

        return call(new HttpRequest.HttpRequestBuilder().post(uri).headers(headers).stringEntity(entity).setConnTimeoutInSec(100).setSocketTimeoutSec(100).build());
        //We should only support this
//        System.out.println(RestClient.post(uri).headers(headers).stringEntity("<xml>test</xml>").execute());

        // We have to stop this
//        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
    }
}
