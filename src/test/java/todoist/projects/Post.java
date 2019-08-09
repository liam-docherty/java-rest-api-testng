package todoist.projects;

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

        postRequest = createHttpPost(PROJECTS_ENDPOINT);
        setPersonalAuthorizationToken(postRequest);
        setApplicationJsonContentType(postRequest);

        // TODO: Here we are hardcoding the body as a string. I want to be able to programmatically build the JSON, supplying values for each field
        String json = "{\"name\": \"deleteme\"}";

        postRequest.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        response = sendRequest(client, postRequest);

        System.out.println("Request: " + EntityUtils.toString(postRequest.getEntity()));
        System.out.println("Response: " + EntityUtils.toString(response.getEntity()));

        assertEquals(getStatusCode(response), 200);

    }

}
