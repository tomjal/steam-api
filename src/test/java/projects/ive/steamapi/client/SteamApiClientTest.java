package projects.ive.steamapi.client;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import projects.ive.steamapi.exceptions.InvalidAppIdException;
import projects.ive.steamapi.exceptions.SteamApiException;

public class SteamApiClientTest {

    private static final long HALF_LIFE_APP_ID = 70L;
    private static final long NOT_EXISTING_ID = 7099999999999L;

    private SteamApiClient client;

    @Before
    public void setup() throws IOException {
        client = new SteamApiClient();
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
        client = new SteamApiClient("invalidurl");
        client.retrieveResultBodyMapForId(HALF_LIFE_APP_ID);
    }

}
