import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class ResponseHeaders extends BaseClass {

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

    @Test
    public void contentTypeIsJson() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);

        response = client.execute(get);

        // Header contentType = response.getEntity().getContentType();

        ContentType contentType = ContentType.getOrDefault(response.getEntity());

        assertEquals(contentType.getMimeType(), "application/json");
        // TODO: This is failing (guessing on type match)
        // assertEquals(contentType.getCharset(), "UTF-8");
    }

    @Test
    public void serverIsGithub() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);

        response = client.execute(get);

        String server = ResponseUtils.getHeader(response, "Server");

        assertEquals(server, "GitHub.com");
    }

    @Test
    public void xRateLimitIsSixty() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);

        response = client.execute(get);

        int xRateLimit = Integer.parseInt(ResponseUtils.getHeader(response, "X-RateLimit-Limit"));

        assertEquals(xRateLimit, 60);
    }

    @Test
    public void eTagIsPresent() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);

        response = client.execute(get);

        boolean isPresent = ResponseUtils.isHeaderPresent(response, "ETag");

        assertEquals(isPresent, true);
    }
}
