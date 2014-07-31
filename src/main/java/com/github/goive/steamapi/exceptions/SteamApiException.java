package com.github.goive.steamapi.exceptions;

public class SteamApiException extends RuntimeException {

    private static final long serialVersionUID = -3863510151151588226L;
    private String message;

    public SteamApiException() {

    }

    public SteamApiException(Throwable t) {
        super(t);
    }

    public SteamApiException(String message) {
        this.message = message;
    }

    public SteamApiException(String message, Throwable t) {
        this(t);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
