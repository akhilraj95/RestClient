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
public interface Response<T> {
    T getBody();
    Map<String, String> getHeaders();
    int getStatusCode();
    <S> S as(Class<S> clazz);
}
