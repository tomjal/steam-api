package com.github.goive.steamapi.data;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.github.goive.steamapi.enums.Type;

/**
 * Represents an application entry from the Steam database. Fields may be empty if there was no entry in the
 * corresponding JSON object.
 * 
 * @author Ivan Antes-Klobucar
 * @version 2.1
 */
public class SteamApp implements Comparable<SteamApp> {

    private long appId;
    private Type type;
    private String name;
    private int requiredAge;
    private String detailedDescription;
    private String aboutTheGame;
    private List<String> supportedLanguages;
    private String headerImage;
    private String website;
    private Price price;
    private List<String> developers;
    private List<String> publishers;
    private boolean availableForLinux;
    private boolean availableForWindows;
    private boolean availableForMac;
    private List<Category> categories;
    private Date releaseDate;
    private Integer metacriticScore;
    private String metacriticUrl;
    private SupportInfo supportInfo;

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequiredAge() {
        return requiredAge;
    }

    public void setRequiredAge(int requiredAge) {
        this.requiredAge = requiredAge;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getAboutTheGame() {
        return aboutTheGame;
    }

    public void setAboutTheGame(String aboutTheGame) {
        this.aboutTheGame = aboutTheGame;
    }

    public List<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    public void setSupportedLanguages(List<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Returns pricing information of the application.
     * 
     * @return {@link Price} containing further pricing information. If empty, the application is free.
     */
    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public boolean isAvailableForLinux() {
        return availableForLinux;
    }

    public void setAvailableForLinux(boolean availableForLinux) {
        this.availableForLinux = availableForLinux;
    }

    public boolean isAvailableForWindows() {
        return availableForWindows;
    }

    public void setAvailableForWindows(boolean availableForWindows) {
        this.availableForWindows = availableForWindows;
    }

    public boolean isAvailableForMac() {
        return availableForMac;
    }

    public void setAvailableForMac(boolean availableForMac) {
        this.availableForMac = availableForMac;
    }

    /**
     * Returns a list of categories for the application.
     * 
     * @return A list of {@link Category} objects containing categories like "single player".
     */
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getMetacriticScore() {
        return metacriticScore;
    }

    public void setMetacriticScore(Integer metacriticScore) {
        this.metacriticScore = metacriticScore;
    }

    public String getMetacriticUrl() {
        return metacriticUrl;
    }

    public void setMetacriticUrl(String metacriticUrl) {
        this.metacriticUrl = metacriticUrl;
    }

    /**
     * Returns support information for the applications like e-mail address and url.
     * 
     * @return {@link SupportInfo} object containing data about the application.
     */
    public SupportInfo getSupportInfo() {
        return supportInfo;
    }

    public void setSupportInfo(SupportInfo supportInfo) {
        this.supportInfo = supportInfo;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
            .append(appId) //
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof SteamApp)) {
            return false;
        }

        SteamApp other = (SteamApp)obj;

        return new EqualsBuilder() //
            .append(appId, other.appId) //
            .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(appId) //
            .append(type) //
            .append(name) //
            .append(requiredAge) //
            .append(detailedDescription) //
            .append(aboutTheGame) //
            .append(supportedLanguages) //
            .append(headerImage) //
            .append(website) //
            .append(price) //
            .append(developers) //
            .append(publishers) //
            .append(availableForLinux) //
            .append(availableForWindows) //
            .append(availableForMac) //
            .append(categories) //
            .append(releaseDate) //
            .append(metacriticScore) //
            .append(metacriticUrl) //
            .append(supportInfo) //
            .toString();
    }

    @Override
    public int compareTo(SteamApp other) {
        return (int)(this.appId - other.appId);
    }

}
