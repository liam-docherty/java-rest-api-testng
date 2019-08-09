package todoist.projects;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import todoist.BaseClass;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Post {

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
    public void createProject() throws IOException {

        HttpPost request = new HttpPost(BaseClass.PROJECTS_ENDPOINT);

        // TODO: These could be common util methods
        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + BaseClass.TOKEN);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        // TODO: Here we are hardcoding the body as a string. I want to be able to programmatically build the JSON, supplying values for each field
        String json = "{\"name\": \"deleteme\"}";

        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        response = client.execute(request);

        System.out.println("Request: " + EntityUtils.toString(request.getEntity()));
        System.out.println("Response: " + EntityUtils.toString(response.getEntity()));

        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(statusCode, 200);

    }

}
