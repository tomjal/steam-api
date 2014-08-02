package com.github.goive.steamapi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.goive.steamapi.client.SteamApiClient;
import com.github.goive.steamapi.data.SteamApp;

@RunWith(MockitoJUnitRunner.class)
public class SteamApiTest {

    private Map<Object, Object> halfLifeResultMap;
    private Map<Object, Object> twoAppsBothSuccessResultMap;

    @Mock
    private SteamApiClient steamApiClient;

    @InjectMocks
    private SteamApiImpl steamApiImpl;

    @SuppressWarnings("unchecked")
    @Before
    public void setup() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        halfLifeResultMap = objectMapper.readValue(new File("src/test/resources/app_id_70.json"), Map.class);
        twoAppsBothSuccessResultMap = objectMapper.readValue(new File("src/test/resources/two_apps_success.json"),
            Map.class);
    }

    @Test
    public void shouldRetrieveDataForOneId() {
        Mockito.when(steamApiClient.retrieveResultBodyMap(70L)).thenReturn(halfLifeResultMap);

        SteamApp halfLife = steamApiImpl.retrieveData(70L);

        Assert.assertEquals("Name not correct", "Half-Life", halfLife.getName());
    }

    @Test
    public void shouldRetrieveDataForMultipleIds() {
        List<Long> appIds = new ArrayList<Long>();
        appIds.add(10L);
        appIds.add(20L);

        Mockito.when(steamApiClient.retrieveResultBodyMap(appIds)).thenReturn(twoAppsBothSuccessResultMap);

        List<SteamApp> steamApps = steamApiImpl.retrieveData(appIds);

        Assert.assertEquals("Name not correct", "Counter-Strike", steamApps.get(0).getName());
        Assert.assertEquals("Name not correct", "Team Fortress Classic", steamApps.get(1).getName());
    }

}
