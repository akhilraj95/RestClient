package core.response;

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
