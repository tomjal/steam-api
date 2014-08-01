package com.github.goive.steamapi.client;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.goive.steamapi.client.SteamApiClientImpl;
import com.github.goive.steamapi.exceptions.InvalidAppIdException;
import com.github.goive.steamapi.exceptions.SteamApiException;

public class SteamApiClientTest {

    private static final long HALF_LIFE_APP_ID = 70L;
    private static final long NOT_EXISTING_ID = 7099999999999L;

    private SteamApiClientImpl client;

    @Before
    public void setup() throws IOException {
        client = new SteamApiClientImpl();
    }

    @Test
    public void shouldSuccessfullyRetrieveResultBodyMapFromSteam() throws SteamApiException {
        Map<Object, Object> resultBodyMap = client.retrieveResultBodyMapForId(HALF_LIFE_APP_ID);

        Assert.assertNotNull(resultBodyMap);
        Assert.assertTrue(resultBodyMap.containsKey(String.valueOf(HALF_LIFE_APP_ID)));
    }

    @Test(expected = InvalidAppIdException.class)
    public void shouldFailToRetrieveResultBodyMapFromSteam() throws SteamApiException {
        client.retrieveResultBodyMapForId(NOT_EXISTING_ID);
    }

    @Test(expected = SteamApiException.class)
    public void shouldFailToCallSteamApi() throws SteamApiException {
        client = new SteamApiClientImpl("invalidurl");
        client.retrieveResultBodyMapForId(HALF_LIFE_APP_ID);
    }

}
