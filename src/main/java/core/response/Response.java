package core.response;

import com.google.gson.Gson;
import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.http.Header;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akhil raj azhikodan on 14/4/18.
 */
@AllArgsConstructor
@Getter
public class Response<T> {

    static Gson gson = new Gson();

    @Nullable
    protected final T body;
    protected final Map<String, String> headers;
    protected final int statusCode;
}
