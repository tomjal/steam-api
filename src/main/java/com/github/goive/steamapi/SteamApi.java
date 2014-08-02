package com.github.goive.steamapi;

import java.util.List;

import com.github.goive.steamapi.data.SteamApp;
import com.github.goive.steamapi.exceptions.SteamApiException;

/**
 * Entry point for the APIs' external use.
 * 
 * @author Ivan Antes-Klobucar
 * @version 1.1.1
 */
public interface SteamApi {

    /**
     * Retrieves a {@link SteamApp} object for the given appId.
     * 
     * @param appId Unique ID of the Steam application (visible in URL on Steam store page)
     * @return {@link SteamApp} object containing information about the given appId.
     * @throws SteamApiException if the retrieving went wrong or the appId is invalid.
     */
    SteamApp retrieveData(long appId) throws SteamApiException;

    /**
     * Retrieves a {@link List} of {@link SteamApp} objects for the given appIds. There will be no results for invalid
     * appIds. The resulting list will only contain valid responses from the Steam API.
     * 
     * @param appIds List of unique IDs of the Steam application (visible in URL on Steam store page)
     * @return {@link List} of {@link SteamApp} object containing information about the given appIds.
     * @throws SteamApiException if the retrieving went wrong or the appId is invalid.
     */
    List<SteamApp> retrieveData(List<Long> appIds) throws SteamApiException;

}
