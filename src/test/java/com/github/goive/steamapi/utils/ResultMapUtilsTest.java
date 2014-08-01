package com.github.goive.steamapi.utils;

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
        @SuppressWarnings("unchecked")
        Map<Object, Object> innerMap = (Map<Object, Object>)halfLifeResultMap.get("70");

        Assert.assertTrue(ResultMapUtils.isSuccessfullyRetrieved(innerMap));
    }

    @Test
    public void shouldRecognizeFailedRetrievedData() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> innerMap = (Map<Object, Object>)nonSuccessfulResultMap.get("7099999999999");

        Assert.assertFalse(ResultMapUtils.isSuccessfullyRetrieved(innerMap));
    }

}
