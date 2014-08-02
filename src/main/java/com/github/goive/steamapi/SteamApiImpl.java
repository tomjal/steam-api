package com.github.goive.steamapi;

import java.util.List;
import java.util.Map;

import com.github.goive.steamapi.builders.SteamAppBuilder;
import com.github.goive.steamapi.client.SteamApiClient;
import com.github.goive.steamapi.client.SteamApiClientImpl;
import com.github.goive.steamapi.data.SteamApp;
import com.github.goive.steamapi.exceptions.SteamApiException;

public class SteamApiImpl implements SteamApi {

    public SteamApp retrieveData(long appId) throws SteamApiException {
        SteamApiClient client = new SteamApiClientImpl();

        Map<Object, Object> bodyMapForId = client.retrieveResultBodyMap(appId);

        return SteamAppBuilder.createFromResultMap(bodyMapForId);
    }

    public List<SteamApp> retrieveData(List<Long> appIds) throws SteamApiException {
        SteamApiClient client = new SteamApiClientImpl();

        Map<Object, Object> resultBodyMap = client.retrieveResultBodyMap(appIds);

        return SteamAppBuilder.createListOfResultMaps(resultBodyMap);
    }

}
