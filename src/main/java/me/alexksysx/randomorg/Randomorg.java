package me.alexksysx.randomorg;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.alexksysx.randomorg.request.IntegersRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Randomorg {
    private URL url = new URL("https://api.random.org/json-rpc/2/invoke");
    private final String API_KEY;

    public Randomorg(String api_key) throws MalformedURLException {
        API_KEY = api_key;
    }

    public ArrayList<Integer>  generateIntegers(int n, int min, int max) throws IOException {
        IntegersRequest req = new IntegersRequest(API_KEY, n, min, max);
        String jsonInputString = RequestToJson.convertToJson(req);
        String result = Connection.post(url, jsonInputString);
        ArrayList<Integer> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(result);
        JsonNode res = actualObj.get("result");
        JsonNode random = res.get("random");
        JsonNode data = random.get("data");
        if(data.isArray()) {
            for (JsonNode node : data) {
                list.add(node.asInt());
            }
        }
        return list;
    }
}
