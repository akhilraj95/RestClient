package core.response;

import java.util.Map;

/**
 * Created by akhil raj azhikodan on 23/4/18.
 */
public class BasicResponse extends Response<String>
{
    public BasicResponse(String body, Map<String, String> headers, int statusCode)
    {
        super(body, headers, statusCode);
    }

    public <S> S as(Class<S> clazz)
    {
        return gson.fromJson(body, clazz);
    }
}
