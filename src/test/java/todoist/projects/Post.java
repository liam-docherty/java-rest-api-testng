package todoist.projects;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import todoist.BaseClass;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Post extends BaseClass {

    @Test
    public void createProject() throws IOException {

        HttpPost request = new HttpPost(PROJECTS_ENDPOINT);

        // TODO: These could be common util methods
        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN);
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
