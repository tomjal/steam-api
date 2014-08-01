package com.github.goive.steamapi.exceptions;

public class InvalidAppIdException extends SteamApiException {

    private static final long serialVersionUID = 720149258778294094L;

    private String appId;

    public InvalidAppIdException(String appId) {
        this.appId = appId;
    }

    @Override
    public String getMessage() {
        return "The given appId is invalid: " + appId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}
