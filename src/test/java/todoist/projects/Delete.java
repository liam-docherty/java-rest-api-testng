package todoist.projects;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import todoist.BaseClass;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Delete {

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
    public void deleteProject() throws IOException {

        long id = 2215195206L;

        HttpDelete request = new HttpDelete(BaseClass.PROJECTS_ENDPOINT + "/" + id);

        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + BaseClass.TOKEN);

        response = client.execute(request);

        assertEquals(response.getStatusLine().getStatusCode(), 204);

    }
}
