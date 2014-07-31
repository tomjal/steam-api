package com.github.goive.steamapi;

import java.util.Map;

import com.github.goive.steamapi.builders.SteamAppBuilder;
import com.github.goive.steamapi.client.SteamApiClient;
import com.github.goive.steamapi.data.SteamApp;
import com.github.goive.steamapi.exceptions.SteamApiException;

public class SteamApi {

    public static SteamApp retrieveData(long appId) throws SteamApiException {
        SteamApiClient client = new SteamApiClient();

        Map<Object, Object> bodyMapForId = client.retrieveResultBodyMapForId(appId);

        return SteamAppBuilder.createFromResultMap(bodyMapForId);
    }

}
