package samples;

import apache.RestClient;
import core.HttpRequest;
import core.builder.RequestBuilder;
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

    @Test
    public void getSample() throws URISyntaxException, IOException
    {
        URI uri = new URI("https://httpbin.org/get");

        Map<String, String> headers = new HashMap<String, String>() {{
            put("Header1","Value1");
            put("Header2","Value2");
        }};

        Map<String, String> entity = new HashMap<String, String>() {{
            put("Body1","BodyValue1");
            put("Body2","BodyValue2");
        }};

        HttpRequest request = RequestBuilder.get(uri).headers(headers).urlEncodedEntity(entity).build();

        System.out.println(execute(request).getBody());
    }
}
