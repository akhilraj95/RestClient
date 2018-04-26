package core.response;

import com.google.gson.Gson;
import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * Created by akhil raj azhikodan on 23/4/18.
 */
@Getter
@AllArgsConstructor
public class BasicResponse implements Response<String> {
    private static Gson gson = new Gson();

    @Nullable
    protected final String body;
    protected final Map<String, String> headers;
    protected final int statusCode;

    @Override
    public <S> S as(Class<S> clazz) {
        return gson.fromJson(body, clazz);
    }
}
