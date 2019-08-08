import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getHeader(CloseableHttpResponse response, String headerName) {

        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        Header matchedHeader = httpHeaders.stream()
                .filter(header -> header.getName().equals(headerName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Did not find header: '" + headerName + "'"));

        return matchedHeader.getValue();
    }

    public static boolean isHeaderPresent(CloseableHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());

        return httpHeaders.stream()
                .anyMatch(header -> header.getName().equals(headerName));
    }

    public static User unmarshall(CloseableHttpResponse response, Class<User> clazz) throws IOException {

        String body = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                // TODO: This is only required while when we have not declared properties for each field in response
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(body, clazz);

    }

    public static <T> T unmarshallGeneric(CloseableHttpResponse response, Class<T> clazz) throws IOException {

        String body = EntityUtils.toString(response.getEntity());

        return new ObjectMapper()
                // TODO: This is only required while when we have not declared properties for each field in response
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(body, clazz);

    }
}
