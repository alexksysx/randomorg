package me.alexksysx.randomorg;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.alexksysx.randomorg.exception.RandomorgException;
import me.alexksysx.randomorg.request.IntegersRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Randomorg {
    private URL url = new URL("https://api.random.org/json-rpc/2/invoke");
    private final String API_KEY;
    private int bitsLeft;
    private int requestsLeft;

    public Randomorg(String api_key) throws MalformedURLException {
        API_KEY = api_key;
    }

    private void updateBits(int bits) {
        bitsLeft = bits;
    }

    private void updateRequests(int requests) {
        requestsLeft = requests;
    }

    public int getBitsLeft() {
        return bitsLeft;
    }

    public int getRequestsLeft() {
        return requestsLeft;
    }

    public ArrayList<Integer> generateIntegers(int n, int min, int max) throws IOException, RandomorgException {
        IntegersRequest req = new IntegersRequest(API_KEY, n, min, max);
        String jsonInputString = RequestToJson.convertToJson(req);
        assert jsonInputString != null;
        String result = Connection.post(url, jsonInputString);
        ArrayList<Integer> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(result);
        if (actualObj.has("error")) {
            JsonNode error = actualObj.get("error");
            int errorCode = error.get("code").asInt();
            String message = error.get("message").asText();
            throw new RandomorgException(errorCode, message);
        }
        updateBits(actualObj.get("result").get("bitsLeft").asInt());
        updateRequests(actualObj.get("result").get("requestsLeft").asInt());
        JsonNode data = actualObj.get("result").get("random").get("data");
        if (data.isArray()) {
            for (JsonNode node : data) {
                list.add(node.asInt());
            }
        }
        return list;
    }
}
