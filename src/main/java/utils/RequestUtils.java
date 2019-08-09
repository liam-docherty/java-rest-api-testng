package utils;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;

public class RequestUtils {

    public static void setAuthorizationToken(HttpGet request, String token) {

        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

    }

}
