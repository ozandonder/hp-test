package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

import static java.lang.System.out;

public class JsonUtils {
    private static JSONObject jsonObject;

    public JsonUtils() {
    }

    public static JSONObject getJson(String jsonPath) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(jsonPath));

            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            out.println(e);
        }
        return jsonObject;
    }

    public JSONObject getJson() {
        return jsonObject;
    }

    public void setJson(JSONObject jsonObj) {
        jsonObject = jsonObj;
    }
}
