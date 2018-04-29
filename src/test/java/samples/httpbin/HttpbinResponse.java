package samples.httpbin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@NoArgsConstructor
@Setter
@ToString
public class HttpbinResponse<T> {
    private Map<String, String> args;
    private Map<String, String> headers;
    private String origin;
    private String url;
    private Map<String, String> form;
    private T json;
    private String data;
}
