package com.github.goive.steamapi.client;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.github.goive.steamapi.exceptions.SteamApiException;

/**
 * The client that connects to the Steam API to retrieve the data for the given appId.
 * 
 * @author Ivan Antes-Klobucar
 * @version 1.1.1
 */
public class SteamApiClientImpl implements SteamApiClient {

    private String apiUrl = "http://store.steampowered.com/api/appdetails?appids=";
    private ObjectMapper mapper = new ObjectMapper();

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
    private Map<Object, Object> fetchResultMap(String appIds) {
        Map<Object, Object> resultMap = new HashMap<Object, Object>();

        try {
            URL src = new URL(apiUrl + appIds);
            resultMap = mapper.readValue(src, Map.class);
        } catch (IOException e) {
            throw new SteamApiException(e);
        }

        return resultMap;
    }

    @Override
    public Map<Object, Object> retrieveResultBodyMap(long appId) throws SteamApiException {
        Map<Object, Object> resultMap = fetchResultMap(appId + "");

        return resultMap;
    }

    @Override
    public Map<Object, Object> retrieveResultBodyMap(List<Long> appIds) throws SteamApiException {
        if (appIds == null || appIds.isEmpty()) {
            throw new SteamApiException("No appIds given.");
        }

        if (appIds.size() == 1) {
            return retrieveResultBodyMap(appIds.get(0));
        }

        Map<Object, Object> resultMap = new HashMap<Object, Object>();

        String appIdCsvList = StringUtils.join(appIds, ",");
        resultMap = fetchResultMap(appIdCsvList);

        return resultMap;
    }

}
