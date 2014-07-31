package com.github.goive.steamapi.builders;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.github.goive.steamapi.data.Category;
import com.github.goive.steamapi.data.Price;
import com.github.goive.steamapi.data.SteamApp;
import com.github.goive.steamapi.data.SupportInfo;
import com.github.goive.steamapi.enums.Type;

public class SteamAppBuilder {

    private static Logger logger = Logger.getLogger(SteamAppBuilder.class);

    private static final String MAC = "mac";
    private static final String LINUX = "linux";
    private static final String WINDOWS = "windows";
    private static final String PLATFORMS = "platforms";
    private static final String PUBLISHERS = "publishers";
    private static final String DEVELOPERS = "developers";
    private static final String PRICE_OVERVIEW = "price_overview";
    private static final String CURRENCY = "currency";
    private static final String INITIAL = "initial";
    private static final String FINAL = "final";
    private static final String DISCOUNT_PERCENT = "discount_percent";
    private static final String DATE = "date";
    private static final String RELEASE_DATE = "release_date";
    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final String CATEGORIES = "categories";
    private static final String SCORE = "score";
    private static final String METACRITIC = "metacritic";
    private static final String EMAIL = "email";
    private static final String URL = "url";
    private static final String SUPPORT_INFO = "support_info";

    private static final String WEBSITE = "website";
    private static final String HEADER_IMAGE = "header_image";
    private static final String SUPPORTED_LANGUAGES = "supported_languages";
    private static final String ABOUT_THE_GAME = "about_the_game";
    private static final String DETAILED_DESCRIPTION = "detailed_description";
    private static final String REQUIRED_AGE = "required_age";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String DATA = "data";

    private static final String UNCHECKED = "unchecked";

    @SuppressWarnings(UNCHECKED)
    public static SteamApp createFromResultMap(Map<Object, Object> resultMap) {
        SteamApp steamApp = new SteamApp();
        Long appId = null;

        Set<Object> keySet = resultMap.keySet();
        for (Object object : keySet) {
            appId = Long.parseLong((String)object);
            steamApp.setAppId(appId);
        }

        Map<Object, Object> innerMap = (Map<Object, Object>)resultMap.get(appId.toString());
        Map<Object, Object> dataMap = (Map<Object, Object>)innerMap.get(DATA);

        fillFields(steamApp, dataMap);

        return steamApp;
    }

    private static void fillFields(SteamApp steamApp, Map<Object, Object> dataMap) {
        parseGenericData(steamApp, dataMap);
        parsePriceData(steamApp, dataMap);
        parseMarketData(steamApp, dataMap);
        parsePlatformData(steamApp, dataMap);
        parseCategorieData(steamApp, dataMap);
        parseReleaseData(steamApp, dataMap);
        parseMetacriticData(steamApp, dataMap);
        parseSupportInfo(steamApp, dataMap);
    }

    @SuppressWarnings(UNCHECKED)
    private static void parseSupportInfo(SteamApp steamApp, Map<Object, Object> dataMap) {
        Map<Object, Object> supportInfoMap = (Map<Object, Object>)dataMap.get(SUPPORT_INFO);
        SupportInfo supportInfo = new SupportInfo();

        String url = (String)supportInfoMap.get(URL);
        String email = (String)supportInfoMap.get(EMAIL);

        try {
            supportInfo.setUrl(new URL(url));
        } catch (MalformedURLException e) {
            logger.debug("Could not parse url " + url, e);
        }

        supportInfo.setEmail(email);

        steamApp.setSupportInfo(supportInfo);
    }

    @SuppressWarnings(UNCHECKED)
    private static void parseMetacriticData(SteamApp steamApp, Map<Object, Object> dataMap) {
        Map<Object, Object> metacriticMap = (Map<Object, Object>)dataMap.get(METACRITIC);

        if (metacriticMap == null) {
            logger.info("No metacritic data found for " + steamApp.getAppId() + " - " + steamApp.getName());
            return;
        }

        Integer metacriticScore = (Integer)metacriticMap.get(SCORE);
        steamApp.setMetacriticScore(metacriticScore);

        String url = (String)metacriticMap.get(URL);
        steamApp.setMetacriticUrl(url);
    }

    @SuppressWarnings(UNCHECKED)
    private static void parseCategorieData(SteamApp steamApp, Map<Object, Object> dataMap) {
        List<Category> categories = new ArrayList<Category>();

        List<Object> categoriesMap = (List<Object>)dataMap.get(CATEGORIES);

        if (categoriesMap == null) {
            logger.info("No categories data found for " + steamApp.getAppId() + " - " + steamApp.getName());
            return;
        }

        for (Object mapObject : categoriesMap) {
            Map<Object, Object> categoryItemMap = (Map<Object, Object>)mapObject;

            String description = (String)categoryItemMap.get(DESCRIPTION);
            int id = Integer.parseInt((String)categoryItemMap.get(ID));

            Category category = new Category(id, description);

            categories.add(category);
        }

        steamApp.setCategories(categories);
    }

    @SuppressWarnings(UNCHECKED)
    private static void parsePlatformData(SteamApp steamApp, Map<Object, Object> dataMap) {
        Map<Object, Object> platformsMap = (Map<Object, Object>)dataMap.get(PLATFORMS);

        Boolean windows = (Boolean)platformsMap.get(WINDOWS);
        Boolean linux = (Boolean)platformsMap.get(LINUX);
        Boolean mac = (Boolean)platformsMap.get(MAC);

        steamApp.setAvailableForLinux(linux);
        steamApp.setAvailableForMac(mac);
        steamApp.setAvailableForWindows(windows);
    }

    @SuppressWarnings(UNCHECKED)
    private static void parseMarketData(SteamApp steamApp, Map<Object, Object> dataMap) {
        List<String> developers = (List<String>)dataMap.get(DEVELOPERS);
        steamApp.setDevelopers(developers);

        List<String> publishers = (List<String>)dataMap.get(PUBLISHERS);
        steamApp.setPublishers(publishers);
    }

    @SuppressWarnings(UNCHECKED)
    private static void parseReleaseData(SteamApp steamApp, Map<Object, Object> dataMap) {
        Map<Object, Object> releaseMap = (Map<Object, Object>)dataMap.get(RELEASE_DATE);
        String dateString = (String)releaseMap.get(DATE);

        int numFields = dateString.split(" ").length;
        switch (numFields) {
        case 2:
            SimpleDateFormat sdf2 = new SimpleDateFormat("MMM yyyy", Locale.US);

            try {
                Date releaseDate = sdf2.parse(dateString);
                steamApp.setReleaseDate(releaseDate);
            } catch (ParseException e) {
                logger.error("Could not parse release date for appId " + steamApp.getAppId(), e);
            }

            break;
        case 3:
            SimpleDateFormat sdf3 = new SimpleDateFormat("d MMM yyyy", Locale.US);

            try {
                Date releaseDate = sdf3.parse(dateString);
                steamApp.setReleaseDate(releaseDate);
            } catch (ParseException e) {
                logger.error("Could not parse release date for appId " + steamApp.getAppId(), e);
            }

            break;
        default:
            logger.error("Unknown date pattern for " + steamApp.getAppId() + ". " + dateString);
        }
    }

    private static void parseGenericData(SteamApp steamApp, Map<Object, Object> dataMap) {
        String type = (String)dataMap.get(TYPE);
        Type appType = assignType(type);
        steamApp.setType(appType);

        String name = (String)dataMap.get(NAME);
        steamApp.setName(name);

        Integer requiredAge = null;

        try {
            requiredAge = (Integer)dataMap.get(REQUIRED_AGE);
        } catch (ClassCastException e) {
            logger.debug("Could not parse required age for appId " + steamApp.getAppId()
                + " as integer. Trying string...", e);
            requiredAge = Integer.valueOf((String)dataMap.get(REQUIRED_AGE));
        }

        steamApp.setRequiredAge(requiredAge);

        String detailedDescription = (String)dataMap.get(DETAILED_DESCRIPTION);
        steamApp.setDetailedDescription(detailedDescription);

        String aboutTheGame = (String)dataMap.get(ABOUT_THE_GAME);
        steamApp.setAboutTheGame(aboutTheGame);

        String supportedLanguagesRaw = (String)dataMap.get(SUPPORTED_LANGUAGES);
        if (supportedLanguagesRaw != null) {
            List<String> supportedLanguages = Arrays.asList(supportedLanguagesRaw.split("\\s*,\\s*"));
            steamApp.setSupportedLanguages(supportedLanguages);
        }

        String headerImage = (String)dataMap.get(HEADER_IMAGE);
        steamApp.setHeaderImage(headerImage);

        String website = (String)dataMap.get(WEBSITE);
        steamApp.setWebsite(website);
    }

    @SuppressWarnings(UNCHECKED)
    private static void parsePriceData(SteamApp steamApp, Map<Object, Object> dataMap) {
        Map<Object, Object> priceOverview = (Map<Object, Object>)dataMap.get(PRICE_OVERVIEW);
        Price price = new Price();
        steamApp.setPrice(price);

        if (priceOverview == null) {
            logger.info("No price data found. Assuming " + steamApp.getAppId() + " is free to play.");
            return;
        }

        String currencyString = (String)priceOverview.get(CURRENCY);
        price.setCurrency(Currency.getInstance(currencyString));

        Integer initialPrice = (Integer)priceOverview.get(INITIAL);
        price.setInitialPrice(new BigDecimal(String.valueOf(initialPrice)).divide(new BigDecimal(100)));

        Integer finalPrice = (Integer)priceOverview.get(FINAL);
        price.setFinalPrice(new BigDecimal(String.valueOf(finalPrice)).divide(new BigDecimal(100)));

        Integer discountPercent = (Integer)priceOverview.get(DISCOUNT_PERCENT);
        price.setDiscountPercent(discountPercent);

    }

    private static Type assignType(String typeValue) {
        for (Type type : Type.values()) {
            if (type.getValue().equalsIgnoreCase(typeValue)) {
                return type;
            }
        }

        logger.warn("No type specified for " + typeValue);
        return Type.UNDEFINED;
    }

}
