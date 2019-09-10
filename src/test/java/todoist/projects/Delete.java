package todoist.projects;

import org.testng.annotations.Test;
import todoist.BaseClass;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Delete extends BaseClass {

    long id;

    @Test
    public void deleteProject() throws IOException {

        id = 2215201674L;

        deleteRequest = createHttpDelete(PROJECTS_ENDPOINT + "/" + id);
        setPersonalAuthorizationToken(deleteRequest);

        response = sendRequest(client, deleteRequest);

        assertEquals(getStatusCode(response), 204);

    }

}
