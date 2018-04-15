package samples;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RunWith(BlockJUnit4ClassRunner.class)
public class LowTrafficClientTest {
    LowTrafficClient lowTrafficClient = new LowTrafficClient();

    @Test
    public void getSimpleTest() throws IOException, URISyntaxException {
        SimpleResponse response = lowTrafficClient.getSample();
        Assert.assertNotNull(response);
    }

    @Test
    public void postUrlEncodedSimpleTest() throws IOException, URISyntaxException {
        Map<String, String> entity = new HashMap<String, String>() {{
            put("Body1", "BodyValue1");
            put("Body2", "BodyValue2");
        }};
        SimpleResponse response = lowTrafficClient.postUrlEncodedSample(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(entity, response.getForm());
    }

    @Test
    public void postJsonSimple() throws IOException, URISyntaxException {
        Dto dto = new Dto();
        dto.setName("akhil");
        dto.setNumber(1);
        SimpleResponse<Dto> response = lowTrafficClient.postJsonWithTimeoutSample(dto);
        Assert.assertNotNull(response.getJson());
        Assert.assertTrue(response.getJson() instanceof Dto);
    }

    @Test
    public void postJsonWithTimeoutWithHandlerSampleTest() throws Exception {
        Object response = lowTrafficClient.postJsonWithTimeoutWithHandlerSample();
        Assert.assertNotNull(response);
    }

    @Test
    public void postStringSimpeTest() throws IOException, URISyntaxException {
        String entity = "<xml>test</xml>";
        SimpleResponse response = lowTrafficClient.postStringSample(entity);
        Assert.assertNotNull(response);
        Assert.assertEquals(entity, response.getData());
    }
}
