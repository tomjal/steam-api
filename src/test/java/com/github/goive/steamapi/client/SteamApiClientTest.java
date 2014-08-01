package com.github.goive.steamapi.client;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.github.goive.steamapi.exceptions.InvalidAppIdException;
import com.github.goive.steamapi.exceptions.SteamApiException;

public class SteamApiClientTest extends AbstractSteamApiClientTest {

    @Test
    public void shouldSuccessfullyRetrieveResultBodyMapFromSteamWithOneId() throws SteamApiException {
        Map<Object, Object> resultBodyMap = client.retrieveResultBodyMap(HALF_LIFE_APP_ID);

        Assert.assertNotNull(resultBodyMap);
        Assert.assertTrue(resultBodyMap.containsKey(String.valueOf(HALF_LIFE_APP_ID)));
    }

    @Test(expected = InvalidAppIdException.class)
    public void shouldFailToRetrieveResultBodyMapFromSteamWithOneId() throws SteamApiException {
        client.retrieveResultBodyMap(NOT_EXISTING_ID);
    }

    @Test(expected = SteamApiException.class)
    public void shouldFailToCallSteamApi() throws SteamApiException {
        client = new SteamApiClientImpl("invalidurl");
        client.retrieveResultBodyMap(HALF_LIFE_APP_ID);
    }

}
