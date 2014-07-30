Java Steam API
=========

A Java library to retrieve data from Valves Steam platform.

Use it in your own code like this:

    long appIdOfHalfLife = 70;
    SteamApp halfLife = SteamApi.retrieveData(appIdOfHalfLife);

This retrieves almost all available data for the given Steam ID product, including prices and discounts.
