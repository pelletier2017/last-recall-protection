package com.recall.location;

import net.runelite.api.Client;

public class LocationHelper {

    private static final int HOUSE_REGION = 7513;

    private static boolean isInHouse(Client client) {
        int[] regions = client.getMapRegions();
        return client.isInInstancedRegion() && regions.length > 0 && regions[0] == HOUSE_REGION;
    }

    private static boolean isInRecallableInstance(Client client) {
        // TODO some instances will save recall that'll go right outside (ex godwars)
        return false;
    }

    public static boolean lastRecallWouldReset(Client client) {
        return !client.isInInstancedRegion() || isInRecallableInstance(client);
    }
}
