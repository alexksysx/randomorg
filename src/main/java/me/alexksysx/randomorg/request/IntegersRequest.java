package me.alexksysx.randomorg.request;

import java.util.HashMap;
import java.util.Map;

public class IntegersRequest {
    private String jsonrpc = "2.0";
    private String method = "generateIntegers";
    private int id;
    private Map<String, Object> params = new HashMap<>();

    public IntegersRequest(String apiKey, int n, int min, int max) {
        this(apiKey, n, min, max, 1);
    }

    public IntegersRequest(String apiKey, int n, int min, int max, int id) {
        params.put("apiKey", apiKey);
        params.put("n", n);
        params.put("min", min);
        params.put("max", max);
    }
}
