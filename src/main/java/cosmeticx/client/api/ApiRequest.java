package cosmeticx.client.api;

import java.util.HashMap;

public class ApiRequest {

    private HashMap<String, String> headers;
    private HashMap<String, String> body;
    private final boolean post_method;

    private final String URL;

    public ApiRequest(String URL, boolean post_method) {
        this.headers = new HashMap<>();
        this.body = new HashMap<>();
        this.URL = URL;
        this.post_method = post_method;
    }

    public HashMap<String, String> getBody() {
        return body;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public ApiRequest body(String key, String value) {
        this.body.put(key, value);
        return this;
    }

    public ApiRequest header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public String getURL() {
        return URL;
    }

    public boolean isPostMethod() {
        return post_method;
    }
}
