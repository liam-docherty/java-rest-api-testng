package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RateLimit {

    private int coreLimit;
    private String searchLimit;


    public int getCoreLimit() {
        return coreLimit;
    }

    public String getSearchLimit() {
        return searchLimit;
    }

    // Parameter is simply called resources because that is the name of the parent element of "core" and "search" in the response
    @SuppressWarnings("unchecked")
    @JsonProperty("resources")
    private void unmarshallNested(Map<String, Object> resources) {
        Map<String, Integer> core = (Map<String, Integer>) resources.get("core");
        coreLimit = core.get("limit");

        Map<String, String> search = (Map<String, String>) resources.get("search");
        searchLimit = String.valueOf(search.get("limit"));
    }

}
