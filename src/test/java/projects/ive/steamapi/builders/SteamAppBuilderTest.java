package projects.ive.steamapi.builders;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import projects.ive.steamapi.data.Category;
import projects.ive.steamapi.data.Price;
import projects.ive.steamapi.data.SteamApp;
import projects.ive.steamapi.enums.Type;
import projects.ive.steamapi.exceptions.InvalidAppIdException;

public class SteamAppBuilderTest {

    private static final long HALF_LIFE_APP_ID = 70L;

    private Map<Object, Object> halfLifeResultMap;

    @Before
    public void setup() throws IOException {
        initMaps();
    }

    @SuppressWarnings("unchecked")
    private void initMaps() throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();

        halfLifeResultMap = objectMapper.readValue(new File("src/test/resources/app_id_70.json"), Map.class);
    }

    @Test
    public void shouldCreateSteamApp() throws InvalidAppIdException {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);

        Assert.assertNotNull(steamApp);
        Assert.assertTrue(steamApp instanceof SteamApp);
    }

    @Test
    public void shouldContaintCorrectPriceData() throws InvalidAppIdException {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);
        Price price = steamApp.getPrice();

        Assert.assertEquals("Currency not correct", Currency.getInstance("EUR"), price.getCurrency());
        Assert.assertEquals("Initial price not correct", BigDecimal.valueOf(9.99), price.getInitialPrice());
        Assert.assertEquals("Final price not correct", BigDecimal.valueOf(4.99), price.getFinalPrice());
        Assert.assertEquals("Discount percent not correct", 50, price.getDiscountPercent());
    }

    @Test
    public void shouldContaintCorrectSupportedLanguages() throws InvalidAppIdException {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);

        Assert.assertEquals("SupportedLanguages size not correct", 8, steamApp.getSupportedLanguages().size());
        Assert.assertTrue(steamApp.getSupportedLanguages().contains("Italian"));
        Assert.assertTrue(steamApp.getSupportedLanguages().contains("French"));
    }

    @Test
    public void shouldContaintCorrectGenericData() throws InvalidAppIdException {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);

        Assert.assertEquals("AppId not correct", HALF_LIFE_APP_ID, steamApp.getAppId());
        Assert.assertEquals("Type not correct", Type.GAME, steamApp.getType());
        Assert.assertEquals("Name not correct", "Half-Life", steamApp.getName());
        Assert.assertEquals("RequiredAge not correct", 10, steamApp.getRequiredAge());
        Assert.assertTrue("DetailedDescription not correct",
            steamApp.getDetailedDescription().contains("Named Game of the Year by over 50"));
        Assert.assertTrue("AboutTheGame not correct",
            steamApp.getAboutTheGame().contains("Valve's debut title blends action and adventure"));
        Assert.assertEquals("HeaderImage not correct",
            "http://cdn.akamai.steamstatic.com/steam/apps/70/header.jpg?t=1360355165", steamApp.getHeaderImage());
        Assert.assertEquals("Website not correct", "http://www.half-life.com/", steamApp.getWebsite());
    }

    @Test
    public void shouldContainCorrectMarketInfo() {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);

        Assert.assertEquals("Not all developers are present", 1, steamApp.getDevelopers().size());
        Assert.assertTrue(steamApp.getDevelopers().contains("Valve"));

        Assert.assertEquals("Not all publishers are present", 1, steamApp.getPublishers().size());
        Assert.assertTrue(steamApp.getPublishers().contains("Valve"));
    }

    @Test
    public void shouldContainCorrectPlatformData() {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);

        Assert.assertTrue("Linux availability", steamApp.isAvailableForLinux());
        Assert.assertTrue("Windows availability", steamApp.isAvailableForWindows());
        Assert.assertTrue("Mac availability", steamApp.isAvailableForMac());
    }

    @Test
    public void shouldContainCorrectCategories() {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);

        Assert.assertEquals("Incorrect amount of categories", 3, steamApp.getCategories().size());

        Category singlePlayerCategory = new Category(2, "Single-player");
        Assert.assertTrue("Wrong category", steamApp.getCategories().contains(singlePlayerCategory));
    }

}
