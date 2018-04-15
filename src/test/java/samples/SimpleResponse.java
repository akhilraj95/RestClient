package samples;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@Getter
@NoArgsConstructor
@Setter
public class SimpleResponse<T> {
    /*

  "args": {},
  "headers": {
    "Accept": "\*\/\*",
            "Connection": "close",
            "Host": "httpbin.org",
            "User-Agent": "curl/7.54.0"
},
        "origin": "122.167.185.132",
        "url": "https://httpbin.org/get"
        }
     */
    private Map<String, String> args;
    private Map<String, String> headers;
    private String origin;
    private String url;
    private Map<String, String> form;
    private T json;
    private String data;
}
