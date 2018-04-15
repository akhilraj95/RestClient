package samples;

import core.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.net.URISyntaxException;

@RunWith(BlockJUnit4ClassRunner.class)
public class LowTrafficClientTest {
    LowTrafficClient lowTrafficClient = new LowTrafficClient();

    @Test
    public void getSimpleTest() throws IOException, URISyntaxException {
        Response response = lowTrafficClient.getSample();
        Assert.assertNotNull(response);
    }

    @Test
    public  void postUrlEncodedSimpleTest() throws IOException, URISyntaxException {
        Response response = lowTrafficClient.postUrlEncodedSample();
        Assert.assertNotNull(response);
    }

    @Test
    public void postJsonSimple() throws IOException, URISyntaxException {
        Response response = lowTrafficClient.postJsonWithTimeoutSample();
        Assert.assertNotNull(response);
    }

    @Test
    public void postJsonWithTimeoutWithHandlerSampleTest() throws Exception {
        Object response = lowTrafficClient.postJsonWithTimeoutWithHandlerSample();
        Assert.assertNotNull(response);
    }

    @Test
    public void postStringSimpeTest() throws IOException, URISyntaxException {
        Response response = lowTrafficClient.postStringSample();
        Assert.assertNotNull(response);
    }
}
