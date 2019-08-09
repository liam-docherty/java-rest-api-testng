package todoist.projects;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import todoist.BaseTest;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get {

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
    public void getAllProjects() throws IOException {

        HttpGet request = new HttpGet(BaseTest.PROJECTS_ENDPOINT);

        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + BaseTest.TOKEN);

        response = client.execute(request);

        System.out.println(EntityUtils.toString(response.getEntity()));

        // TODO: This should be in a Util since it will be used in every test
        int statusCode = response.getStatusLine().getStatusCode();

        assertEquals(statusCode, 200);
    }

}
