package com.github.goive.steamapi.utils;

import java.util.Map;

import com.github.goive.steamapi.SteamApi;

/**
 * Utility class to work with result maps from {@link SteamApi}.
 * 
 * @author Ivan Antes-Klobucar
 * @version 1.1
 */
public class ResultMapUtils {

    public static boolean isSuccessfullyRetrieved(Map<Object, Object> innerMap) {
        return (Boolean)innerMap.get("success");
    }

}
