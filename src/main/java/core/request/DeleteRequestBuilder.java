package core.request;

import core.RequestType;

import java.net.URI;

/**
 * Created by akhil raj azhikodan on 22/4/18.
 */
public class DeleteRequestBuilder extends HttpRequestBuilderWithBody
{
    public DeleteRequestBuilder(URI uri) {
        this.requestType = RequestType.DELETE;
        this.uri = uri;
    }
}
