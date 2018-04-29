package samples.httpbin;

import core.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.util.Map;
@Getter
@AllArgsConstructor
public class FileResponse implements Response<File> {
    private final File body;
    private final Map<String, String> headers;
    private final int statusCode;

    @Override
    public <S> S as(Class<S> clazz) {
        throw new IllegalArgumentException();
    }
}
