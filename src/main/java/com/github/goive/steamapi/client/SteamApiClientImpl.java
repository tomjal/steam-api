package com.github.goive.steamapi.client;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.github.goive.steamapi.exceptions.InvalidAppIdException;
import com.github.goive.steamapi.exceptions.SteamApiException;
import com.github.goive.steamapi.utils.ResultMapUtils;

/**
 * The client that connects to the Steam API to retrieve the data for the given appId.
 * 
 * @author Ivan Antes-Klobucar
 * @version 1.1
 */
public class SteamApiClientImpl implements SteamApiClient {

    private String apiUrl = "http://store.steampowered.com/api/appdetails?appids=";

    public SteamApiClientImpl() {

    }

    /**
     * Creates a new instance.
     * 
     * @param customApiUrl Overrides the standard API url
     */
    public SteamApiClientImpl(String customApiUrl) {
        this.apiUrl = customApiUrl;
    }

    @SuppressWarnings("unchecked")
    public Map<Object, Object> retrieveResultBodyMapForId(long appId) throws SteamApiException {
        Map<Object, Object> resultMap = new HashMap<Object, Object>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            URL src = new URL(apiUrl + appId);
            resultMap = mapper.readValue(src, Map.class);
        } catch (IOException e) {
            throw new SteamApiException(e);
        }

        if (!ResultMapUtils.isSuccessfullyRetrieved(resultMap)) {
            throw new InvalidAppIdException(appId);
        }

        return resultMap;
    }

}
