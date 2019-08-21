package me.alexksysx.randomorg;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.alexksysx.randomorg.request.IntegersRequest;

public class RequestToJson {
    public static String convertToJson(IntegersRequest object) {
        ObjectMapper Obj = new ObjectMapper();
        Obj.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try{
            String jsonStr = Obj.writeValueAsString(object);
            return jsonStr;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
