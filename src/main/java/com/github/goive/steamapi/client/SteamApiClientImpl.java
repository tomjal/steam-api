package com.github.goive.steamapi.client;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.github.goive.steamapi.enums.CountryCode;
import com.github.goive.steamapi.exceptions.SteamApiException;

/**
 * The client that connects to the Steam API to retrieve the data for the given appId.
 * 
 * @author Ivan Antes-Klobucar
 * @version 2.1
 */
public class SteamApiClientImpl implements SteamApiClient {

    private static final int MAX_APPIDS = 1000;
    private String apiUrl = "http://store.steampowered.com/api/appdetails?appids=";
    private ObjectMapper mapper = new ObjectMapper();
    private CountryCode countryCode;

    public SteamApiClientImpl() {
        countryCode = CountryCode.AT;
    }

    /**
     * Creates a new instance.
     * 
     * @param customApiUrl Overrides the standard API url
     */
    public SteamApiClientImpl(String customApiUrl) {
        this();
        this.apiUrl = customApiUrl;
    }

    /**
     * Creates a new instance.
     * 
     * @param countryCode Overrides the default country code to return the correct currency.
     */
    public SteamApiClientImpl(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    @SuppressWarnings("unchecked")
    private Map<Object, Object> fetchResultMap(String appIds) {
        Map<Object, Object> resultMap = new HashMap<Object, Object>();

        try {
            URL src = new URL(apiUrl + appIds + "&cc=" + countryCode.name());
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

        if (appIds.size() > MAX_APPIDS) {
            throw new SteamApiException("Too many appIds given. Maximum is " + MAX_APPIDS);
        }

        if (appIds.size() == 1) {
            return retrieveResultBodyMap(appIds.get(0));
        }

        Map<Object, Object> resultMap = new HashMap<Object, Object>();

        String appIdCsvList = StringUtils.join(appIds, ",");
        resultMap = fetchResultMap(appIdCsvList);

        return resultMap;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

}
