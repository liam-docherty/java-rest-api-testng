package todoist;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.RequestUtils;

import java.io.IOException;

public class BaseClass {

    // If these endpoints changed per environment they should instead be held in an environments file. OK for this demo
    public static final String BASE_ENDPOINT = "https://api.todoist.com/rest/v1";
    public static final String PROJECTS_ENDPOINT = BASE_ENDPOINT + "/projects";
    public static final String TOKEN = "a72e91d0ab45952b4a22ac4aef77041daf569a9b";

    public CloseableHttpClient client;
    public CloseableHttpResponse response;
    public HttpGet getRequest;
    public HttpPost postRequest;
    public HttpDelete deleteRequest;

    @BeforeMethod
    public void buildClient() {

        client = HttpClientBuilder.create().build();

    }

    @AfterMethod
    public void closeResources() throws IOException {

        client.close();
        response.close();

    }

    // TODO: These should be moved somewhere more generic, only the Personal Authorization Token one is specific to this API


    // TODO: Need to work out how to re-write these so there is one common method
    public static void setPersonalAuthorizationToken(HttpGet request) {

        RequestUtils.setAuthorizationToken(request, TOKEN);

    }

    public static void setPersonalAuthorizationToken(HttpPost request) {

        RequestUtils.setAuthorizationToken(request, TOKEN);

    }

    public static void setPersonalAuthorizationToken(HttpDelete request) {

        RequestUtils.setAuthorizationToken(request, TOKEN);

    }

    public static HttpGet createHttpGet(String url) {

        return new HttpGet(url);

    }

    public static HttpPost createHttpPost(String url) {

        return new HttpPost(url);

    }

    public static HttpDelete createHttpDelete(String url) {

        return new HttpDelete(url);

    }


    // TODO: This parameter is specific to one method. This may need to be more generic e.g. for PUT
    public static void setApplicationJsonContentType(HttpPost request) {

        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

    }


    // TODO: Need to work out how to re-write these so there is one common method
    // TODO: Do we need to pass the client in here. Should we instead move the before each and after each into these methods
    public static CloseableHttpResponse sendRequest(CloseableHttpClient client, HttpGet request) throws IOException {

        return client.execute(request);

    }

    public static CloseableHttpResponse sendRequest(CloseableHttpClient client, HttpPost request) throws IOException {

        return client.execute(request);

    }

    public static CloseableHttpResponse sendRequest(CloseableHttpClient client, HttpDelete request) throws IOException {

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
