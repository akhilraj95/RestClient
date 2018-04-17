package core;

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
    @Nullable
    private final T body;
    private final Map<String, String> headers;
    private final int statusCode;
}
