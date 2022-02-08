package pages;

import org.junit.Assert;

public class UserApiService {

    public void checkResponse(String existingValue, String expectedValue) {
        Assert.assertEquals("Value does not match.", existingValue, expectedValue);
    }
}
