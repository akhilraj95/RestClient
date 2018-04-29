package samples.httpbin;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by akhil raj azhikodan on 22/4/18.
 */
public class HttpbinService
{
    static HttpbinClient httpbinClient = new HttpbinClient();

    @Test
    public void printIP() throws ApplicationException
    {
        System.out.println(httpbinClient.getMyIP());
    }

    @Test
    public void printGetWithQuery() throws IOException, URISyntaxException {
        System.out.println(httpbinClient.makeGetQueryWithParams());
    }

    @Test
    public void makePostQueryWithBody() throws IOException, URISyntaxException {
        System.out.println(httpbinClient.makePostQueryWithBody());
    }
}
