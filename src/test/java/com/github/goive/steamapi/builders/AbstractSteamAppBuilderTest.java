package com.github.goive.steamapi.builders;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;

public abstract class AbstractSteamAppBuilderTest {

    protected static final long HALF_LIFE_APP_ID = 70L;

    protected Map<Object, Object> halfLifeResultMap;
    protected Map<Object, Object> halfLifeResultMapWithTwoFieldReleaseDate;
    protected Map<Object, Object> freeToPlayResultMap;
    protected Map<Object, Object> oneDigitReleaseDayResultMap;
    protected Map<Object, Object> twoAppsBothSuccessResultMap;
    protected Map<Object, Object> twoAppsOneSuccessResultMap;

    @Before
    public void setup() throws IOException {
        initMaps();
    }

    @SuppressWarnings("unchecked")
    private void initMaps() throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();

        halfLifeResultMap = objectMapper.readValue(new File("src/test/resources/app_id_70.json"), Map.class);
        halfLifeResultMapWithTwoFieldReleaseDate = objectMapper.readValue(new File(
            "src/test/resources/app_id_70_2_field_release_date.json"), Map.class);
        freeToPlayResultMap = objectMapper.readValue(new File("src/test/resources/f2p_game.json"), Map.class);
        oneDigitReleaseDayResultMap = objectMapper.readValue(new File("src/test/resources/one_digit_release_day.json"),
            Map.class);
        twoAppsBothSuccessResultMap = objectMapper.readValue(new File("src/test/resources/two_apps_success.json"),
            Map.class);
        twoAppsOneSuccessResultMap = objectMapper.readValue(new File("src/test/resources/two_apps_one_success.json"),
            Map.class);
    }

}
