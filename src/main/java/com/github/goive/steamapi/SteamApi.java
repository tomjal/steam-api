package com.github.goive.steamapi;

import java.util.Map;

import com.github.goive.steamapi.builders.SteamAppBuilder;
import com.github.goive.steamapi.client.SteamApiClient;
import com.github.goive.steamapi.client.SteamApiClientImpl;
import com.github.goive.steamapi.data.SteamApp;
import com.github.goive.steamapi.exceptions.SteamApiException;

/**
 * Entry point for the APIs' external use.
 * 
 * @author Ivan Antes-Klobucar
 * @version 1.1
 */
public class SteamApi {

    /**
     * Retrieves a {@link SteamApp} object for the given appId.
     * 
     * @param appId Unique ID of the Steam application (visible in URL on Steam store page)
     * @return {@link SteamApp} object containing information about the given appId.
     * @throws SteamApiException if the retrieving went wrong or the appId is invalid.
     */
    public static SteamApp retrieveData(long appId) throws SteamApiException {
        SteamApiClient client = new SteamApiClientImpl();

        Map<Object, Object> bodyMapForId = client.retrieveResultBodyMap(appId);

        return SteamAppBuilder.createFromResultMap(bodyMapForId);
    }

}
