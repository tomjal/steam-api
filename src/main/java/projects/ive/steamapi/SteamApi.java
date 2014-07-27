package projects.ive.steamapi;

import java.util.Map;

import projects.ive.steamapi.builders.SteamAppBuilder;
import projects.ive.steamapi.client.SteamApiClient;
import projects.ive.steamapi.data.SteamApp;
import projects.ive.steamapi.exceptions.SteamApiException;

public class SteamApi {

    public static SteamApp retrieveData(long appId) throws SteamApiException {
        SteamApiClient client = new SteamApiClient();

        Map<Object, Object> bodyMapForId = client.retrieveResultBodyMapForId(appId);

        return SteamAppBuilder.createFromResultMap(bodyMapForId);
    }

}
