package core;

import org.apache.http.Header;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */
public class Response
{
    private String body;
    private HashMap<String, String> headers;
    private int statusCode;

    public Response(String responseString, Header[] responseHeaders, int statuscode)
    {
        this.body = responseString;
        this.statusCode = statuscode;
        headers = new HashMap<>();
        Arrays.asList(responseHeaders).forEach(x -> headers.put(x.getName(), x.getValue()));
    }

    public String getBody()
    {
        return body;
    }

    public HashMap<String, String> getHeaders()
    {
        return headers;
    }

    public int getStatusCode()
    {
        return statusCode;
    }
}
