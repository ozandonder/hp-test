package utils;

import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import restapi.models.Buyer;

import java.util.HashMap;
import java.util.Map;

public class DataHooks {

    private static Map<Thread, Buyer> mapList = new HashMap<>();
    private static String strValue;
    private Logger logger = LogManager.getLogger(getClass().getName());
    private static ValidatableResponse response;

    public Buyer getBuyer() {
        return mapList.get(Thread.currentThread());
    }

    public void setBuyer(Buyer buyer) {
        mapList.put(Thread.currentThread(), buyer);
        logger.info("Buyer ------> " + buyer);
    }

    public String getValue() {
        return strValue;
    }

    public void setValue(String value) {
        strValue = value;
    }

    public ValidatableResponse getResponse() {
        return response;
    }

    public void setResponse(ValidatableResponse apiResponse) {
        response = apiResponse;
    }
}
