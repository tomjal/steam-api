package com.github.goive.steamapi.builders;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.github.goive.steamapi.data.Category;
import com.github.goive.steamapi.data.Price;
import com.github.goive.steamapi.data.SteamApp;
import com.github.goive.steamapi.enums.Type;
import com.github.goive.steamapi.exceptions.InvalidAppIdException;

public class SteamAppBuilderSingleTest extends AbstractSteamAppBuilderTest {

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

    @Test
    public void shouldContainReleaseDataWhenThreeFields() throws ParseException {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date releaseDate = sdf.parse("8/11/1998");

        Assert.assertEquals("Release date not correct", releaseDate, steamApp.getReleaseDate());
    }

    @Test
    public void shouldContainReleaseDataWhenTwoFields() throws ParseException {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMapWithTwoFieldReleaseDate);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        Date releaseDate = sdf.parse("11/1998");

        Assert.assertEquals("Release date not correct", releaseDate, steamApp.getReleaseDate());
    }

    @Test
    public void shouldHandleFreeToPlayGame() {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(freeToPlayResultMap);

        Assert.assertNull(steamApp.getPrice());
    }

    @Test
    public void shouldHandleOneDigitReleaseDate() {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(oneDigitReleaseDayResultMap);

        Assert.assertNotNull(steamApp.getReleaseDate());
    }

    @Test
    public void shouldContainMetacriticData() {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);

        Assert.assertEquals("Metacritic score not correct", Integer.valueOf(96), steamApp.getMetacriticScore());
        Assert.assertEquals("Metacritic url not correct", "http://www.metacritic.com/game/pc/half-life",
            steamApp.getMetacriticUrl());
    }

    @Test
    public void shouldContainSupportInfo() {
        SteamApp steamApp = SteamAppBuilder.createFromResultMap(halfLifeResultMap);

        Assert.assertNotNull(steamApp.getSupportInfo());
        Assert.assertEquals("URL not correct", "http://www.google.com", steamApp.getSupportInfo().getUrl().toString());
    }
}
