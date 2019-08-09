package todoist;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import utils.RequestUtils;

import java.io.IOException;

public class BaseClass {

    public static final String BASE_ENDPOINT = "https://api.todoist.com/rest/v1";
    public static final String PROJECTS_ENDPOINT = BASE_ENDPOINT + "/projects";
    public static final String TOKEN = "a72e91d0ab45952b4a22ac4aef77041daf569a9b";


    // TODO: Need to work out how to use these for all HTTP methods
    public static void setPersonalAuthorizationToken(HttpGet request) {

        RequestUtils.setAuthorizationToken(request, TOKEN);

    }

    // TODO: These should be moved somewhere more generic, only the top 1 is specific to this API
    public static HttpGet createHttpGet(String url) {

        return new HttpGet(url);

    }

    public static CloseableHttpResponse sendRequest(CloseableHttpClient client, HttpGet request) throws IOException {

        return client.execute(request);

    }

    public static void printResponseBody(CloseableHttpResponse response) throws IOException {

        System.out.println(EntityUtils.toString(response.getEntity()));

    }

    public static void printRequestBody(HttpPost request) throws IOException {

        System.out.println(EntityUtils.toString(request.getEntity()));

    }

    public static int getStatusCode(CloseableHttpResponse response) {

        return response.getStatusLine().getStatusCode();

    }

}
