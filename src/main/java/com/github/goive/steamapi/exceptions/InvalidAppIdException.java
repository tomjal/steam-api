package com.github.goive.steamapi.exceptions;

public class InvalidAppIdException extends SteamApiException {

    private static final long serialVersionUID = 720149258778294094L;

    private long appId;

    public InvalidAppIdException(Long appId) {
        this.appId = appId;
    }

    @Override
    public String getMessage() {
        return "The given appId is invalid: " + appId;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

}
