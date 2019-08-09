package todoist.projects;

import org.testng.annotations.Test;
import todoist.BaseClass;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get extends BaseClass {

    @Test
    public void getAllProjects() throws IOException {

        request = createHttpGet(PROJECTS_ENDPOINT);
        setPersonalAuthorizationToken(request);

        response = sendRequest(client, request);
        printResponseBody(response);

        assertEquals(getStatusCode(response), 200);
    }

}
