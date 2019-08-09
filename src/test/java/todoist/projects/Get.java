package todoist.projects;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import todoist.BaseClass;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get extends BaseClass {

    // TODO: These will need to be changed once tests are refactored
    @BeforeMethod
    public void setupData() {

        System.out.println("Will eventually be used to create data for GET test");

    }

    @AfterMethod
    public void deleteData() throws IOException {

        System.out.println("Will eventually be used to delete data after GET test");

    }

    @Test
    public void getAllProjects() throws IOException {

        request = createHttpGet(PROJECTS_ENDPOINT);
        setPersonalAuthorizationToken(request);

        response = sendRequest(client, request);
        printResponseBody(response);

        assertEquals(getStatusCode(response), 200);
    }

}
