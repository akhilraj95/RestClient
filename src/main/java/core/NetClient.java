package core;

import java.net.URI;
import java.util.HashMap;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */
public interface NetClient
{
    Response get(URI uri, HashMap<String, String> headers);

    Response post(URI uri, HashMap<String, String> requestParams, HashMap<String, String> headers);

    Response put(URI uri,HashMap<String, String> requestParams, HashMap<String, String> headers);

    Response delete(URI uri,HashMap<String, String> requestParams, HashMap<String, String> headers);
}
