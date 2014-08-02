package com.github.goive.steamapi.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.github.goive.steamapi.exceptions.SteamApiException;

public class SteamApiClientBatchTest extends AbstractSteamApiClientTest {

    private static final long COUNTER_STRIKE_APP_ID = 10L;

    @Test(expected = SteamApiException.class)
    public void shouldNotCallSteamApiIfNoIds() {
        List<Long> appIds = new ArrayList<Long>();

        client.retrieveResultBodyMap(appIds);
    }

    @Test(expected = SteamApiException.class)
    public void shouldNotCallSteamApiIfNullGiven() {
        client.retrieveResultBodyMap(null);
    }

    @Test
    public void shouldSuccessfullyCallSteamApiWithTwoValidIds() {
        List<Long> appIds = new ArrayList<Long>();
        appIds.add(HALF_LIFE_APP_ID);
        appIds.add(COUNTER_STRIKE_APP_ID);

        Map<Object, Object> resultBodyMap = client.retrieveResultBodyMap(appIds);

        Assert.assertNotNull(resultBodyMap);
        Assert.assertTrue(resultBodyMap.containsKey(HALF_LIFE_APP_ID + ""));
        Assert.assertTrue(resultBodyMap.containsKey(COUNTER_STRIKE_APP_ID + ""));
    }

    @Test
    public void shouldSuccessfullyCallSteamApiWithOneValidId() {
        List<Long> appIds = new ArrayList<Long>();
        appIds.add(HALF_LIFE_APP_ID);

        Map<Object, Object> resultBodyMap = client.retrieveResultBodyMap(appIds);

        Assert.assertNotNull(resultBodyMap);
        Assert.assertTrue(resultBodyMap.containsKey(HALF_LIFE_APP_ID + ""));
    }

    @Test(expected = SteamApiException.class)
    public void shouldNotCallSteamApiIfTooManyIDs() {
        List<Long> appIds = new ArrayList<Long>();

        for (int i = 1; i <= 1001; i++) {
            appIds.add(Long.valueOf(i));
        }

        client.retrieveResultBodyMap(appIds);
    }

}
