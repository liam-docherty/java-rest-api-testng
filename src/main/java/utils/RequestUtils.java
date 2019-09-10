package utils;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

public class RequestUtils {

    public static void setAuthorizationToken(HttpGet request, String token) {

        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

    }

    public static void setAuthorizationToken(HttpPost request, String token) {

        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

    }

    public static void setAuthorizationToken(HttpDelete request, String token) {

        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

    }

}
