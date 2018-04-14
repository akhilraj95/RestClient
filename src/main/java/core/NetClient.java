package core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */
public interface NetClient
{
    Response get(URI uri, HashMap<String, String> headers) throws IOException;

    Response urlEncodedPost(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers)
            throws IOException;

    Response urlEncodedPut(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers);

    Response urlEncodedDelete(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers);

    Response jsonPost(URI uri, Object request, HashMap<String, String> headers);

    Response jsonPut(URI uri, Object request, HashMap<String, String> headers);

    Response jsonDelete(URI uri, Object request, HashMap<String, String> headers);

    Response StringPost(URI uri, String request, HashMap<String, String> headers);

    Response StringPut(URI uri, String request, HashMap<String, String> headers);

    Response StringDelete(URI uri, String request, HashMap<String, String> headers);
}
