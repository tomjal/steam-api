package projects.ive.steamapi.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResultMapUtilsTest {

    private Map<Object, Object> halfLifeResultMap;
    private Map<Object, Object> nonSuccessfulResultMap;

    @Before
    @SuppressWarnings("unchecked")
    public void setup() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        halfLifeResultMap = objectMapper.readValue(new File("src/test/resources/app_id_70.json"), Map.class);
        nonSuccessfulResultMap = objectMapper.readValue(new File("src/test/resources/app_id_fail.json"), Map.class);
    }

    @Test
    public void shouldRecognizeSuccesfullyRetrievedData() {
        Assert.assertTrue(ResultMapUtils.isSuccessfullyRetrieved(halfLifeResultMap));
    }

    @Test
    public void shouldRecognizeFailedRetrievedData() {
        Assert.assertFalse(ResultMapUtils.isSuccessfullyRetrieved(nonSuccessfulResultMap));
    }

    @Test
    public void shouldExtractAppIdFromMap() {
        long appId = ResultMapUtils.extractAppId(halfLifeResultMap);

        Assert.assertEquals("Failed to extract appId", 70, appId);
    }

}
