Java Steam API
=========

[![Build Status](https://drone.io/github.com/go-ive/steam-api/status.png)](https://drone.io/github.com/go-ive/steam-api/latest)

A Java library to retrieve data from Valves Steam platform.

Use it in your own code like this:

    long appIdOfHalfLife = 70;
    SteamApp halfLife = SteamApi.retrieveData(appIdOfHalfLife);

This retrieves almost all available data for the given Steam ID product, including prices and discounts.

Include it as a Maven dependency:

    <dependency>
      <groupId>com.github.go-ive</groupId>
      <artifactId>steam-api</artifactId>
      <version>1.1.0</version>
    </dependency>
