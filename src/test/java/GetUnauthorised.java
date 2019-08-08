import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class GetUnauthorised extends BaseClass {

    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @DataProvider
    private Object[][] endpoints() {
        return new Object[][] {
                {"/user"},
                {"/user/followers"},
                {"/notifications"}
        };
    }

    // TODO: This approach could be used for every GET endpoint and status code if all we wanted to test was the status code
    @Test(dataProvider = "endpoints")
    public void endpointReturns401(String endpoint) throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + endpoint);

        response = client.execute(get);

        int statusCode = response.getStatusLine().getStatusCode();

        assertEquals(statusCode, 401);
    }
}
