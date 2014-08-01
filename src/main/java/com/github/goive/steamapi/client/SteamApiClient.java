package com.github.goive.steamapi.client;

import java.util.Map;

import com.github.goive.steamapi.exceptions.SteamApiException;

/**
 * Specifies a client implementation of the Steam store API.
 * 
 * @author Ivan Antes-Klobucar
 * @version 1.1
 */
public interface SteamApiClient {

    /**
     * Retrieves a {@link Map} representing the JSON structure of the response.
     * 
     * @param appId Unique ID of the Steam application (visible in URL on Steam store page)
     * @return Map representation of the JSON object returned by the API
     * @throws SteamApiException
     */
    Map<Object, Object> retrieveResultBodyMapForId(long appId) throws SteamApiException;

}
