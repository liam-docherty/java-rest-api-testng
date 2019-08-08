import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

import static entities.User.ID;
import static entities.User.LOGIN;

public class BodyTestWithSimpleMap extends BaseClass {

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
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/liam-docherty");

        response = client.execute(get);

        String body = EntityUtils.toString(response.getEntity());

        JSONObject jsonBody = new JSONObject(body);

        String login = (String) getValueFor(jsonBody, LOGIN);

        assertEquals(login, "liam-docherty");
    }

    @Test
    public void returnsCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/liam-docherty");

        response = client.execute(get);

        String body = EntityUtils.toString(response.getEntity());

        JSONObject jsonBody = new JSONObject(body);

        Integer id = (Integer) getValueFor(jsonBody, ID);

        assertEquals(id, Integer.valueOf(53443583));
    }

    private Object getValueFor(JSONObject jsonBody, String key) {
        return jsonBody.get(key);
    }

}
