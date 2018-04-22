package samples.httpbin;

import apache.RestClient;
import core.request.HttpRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by akhil raj azhikodan on 22/4/18.
 */
public class HttpbinClient extends RestClient
{
    String host = "https://httpbin.org";


    String getMyIP() throws ApplicationException
    {
        try
        {
            URI uri = new URI(host + "/ip");
            return (String) call(HttpRequest.get(uri).build()).getBody();
        }
        catch (IOException | URISyntaxException e)
        {
            throw new ApplicationException();
        }
    }

    HttpbinResponse makeGetQueryWithParams() throws ApplicationException
    {
        try
        {
            URI uri = new URI(host + "/get?query1=value1");

            return call(HttpRequest.get(uri).build()).as(HttpbinResponse.class);
        }
        catch (IOException | URISyntaxException e)
        {
            throw new ApplicationException();
        }
    }

    HttpbinResponse makePostQueryWithBody() throws ApplicationException
    {
        try
        {
            URI uri = new URI(host + "/post");
            Dto dto = new Dto(1, "httpclient");

            return call(HttpRequest.post(uri).jsonEntity(dto).build()).as(HttpbinResponse.class);
        }
        catch (IOException | URISyntaxException e)
        {
            throw new ApplicationException();
        }
    }
}
