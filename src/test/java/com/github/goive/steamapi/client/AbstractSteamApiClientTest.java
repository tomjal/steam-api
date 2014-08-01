package com.github.goive.steamapi.client;

import java.io.IOException;

import org.junit.Before;

public abstract class AbstractSteamApiClientTest {

    protected static final long HALF_LIFE_APP_ID = 70L;
    protected static final long NOT_EXISTING_ID = 7099999999999L;

    protected SteamApiClientImpl client;

    @Before
    public void setup() throws IOException {
        client = new SteamApiClientImpl();
    }
}
