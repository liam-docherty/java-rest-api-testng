import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;


// TODO: Is there a way to move this to BaseClass?
import static org.testng.Assert.assertEquals;

public class PostAndDelete extends BaseClass {

    // TODO: This should move to the base class
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
    public void deleteRepoReturns204() throws IOException {

        HttpDelete request = new HttpDelete("http://api.github.com/repos/liam-docherty/deleteme");

        // TODO: Create variable to hold this token. Make sure that it's not checked in with a value set
        request.setHeader(HttpHeaders.AUTHORIZATION, "token " + TOKEN);
        response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(statusCode, 204);

    }

    @Test
    public void createRepoReturns201() throws IOException {

        HttpPost request = new HttpPost("http://api.github.com/user/repos/");

        // TODO: This should be added to a Util
        String login = USERNAME + ":" + PASSWORD;
        byte[] encodedLogin = Base64.encodeBase64(login.getBytes(Charset.forName("ISO-8859-1")));
        String auth = "Basic " + new String(encodedLogin);

        // TODO: Here we are hardcoding the body as a string. I want to be able to programmatically build the JSON, supplying values for each field
        String json = "{\"name\": \"deleteme\"}";

        request.setHeader(HttpHeaders.AUTHORIZATION, auth);
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(statusCode, 201);

    }

}
