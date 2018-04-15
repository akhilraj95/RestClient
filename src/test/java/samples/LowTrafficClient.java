package samples;

import apache.RestClient;
import core.EntityType;
import core.HttpRequest;
import core.RequestType;
import org.junit.Test;
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


    @Test
    public void getSample() throws URISyntaxException, IOException
    {

        URI uri = new URI("https://httpbin.org/get");

        //We should only support this
        System.out.println(RestClient.get(uri).headers(headers).execute());

        // We have to stop this
        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
    }


    @Test
    public void postUrlEncodedSample() throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");

        //We should only support this
        System.out.println(RestClient.post(uri).headers(headers).urlEncodedEntity(entity).execute());

        // We have to stop this
//        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
    }

    @Test
    public void postJsonSample() throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");
        Dto dto = new Dto();
        dto.setName("akhil");
        dto.setNumber(1);

        //We should only support this
        System.out.println(RestClient.post(uri).headers(headers).jsonEntity(dto).execute());

        // We have to stop this
//        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
    }

    @Test
    public void postStringSample() throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/post");


        //We should only support this
        System.out.println(RestClient.post(uri).headers(headers).stringEntity("<xml>test</xml>").execute());

        // We have to stop this
//        System.out.println(new RestClient().call(new HttpRequest(RequestType.GET, uri, headers, EntityType.URLENCODED, entity)));
    }
}
