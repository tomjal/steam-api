Java Steam API
=========

[![Build Status](https://drone.io/github.com/go-ive/steam-api/status.png)](https://drone.io/github.com/go-ive/steam-api/latest)

A Java library to retrieve data from Valves Steam platform.

Use it in your own code like this:


	SteamApi steamApi = new SteamApiImpl();
	long appIdOfHalfLife = 70;
	SteamApp halfLife = steamApi.retrieveData(appIdOfHalfLife);
	
	// or
	
	List<Long> appIds = new ArrayList<Long>();
	appIds.add(70L);
	appIds.add(10L);
	List<SteamApp> games = steamApi.retrieveData(appIds);
	
	// then
	
	String gameName = halfLife.getName();
	BigDecimal price = halfLife.getPrice().getFinalPrice();
	... and more

This retrieves almost all available data for the given Steam ID or list of IDs, including prices and discounts.

Maven dependency:

	<dependency>
		<groupId>com.github.go-ive</groupId>
		<artifactId>steam-api</artifactId>
		<version>2.0</version>
	</dependency>