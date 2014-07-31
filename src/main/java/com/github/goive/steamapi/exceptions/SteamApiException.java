package com.github.goive.steamapi.exceptions;

public class SteamApiException extends Exception {

    private static final long serialVersionUID = -3863510151151588226L;

    public SteamApiException(Throwable t) {
        super(t);
    }
    
    public SteamApiException(){
        
    }

}
