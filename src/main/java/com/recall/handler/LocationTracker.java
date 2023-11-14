package com.recall.handler;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;

import javax.inject.Inject;

@Slf4j
public class LocationTracker {

    private static final int HOUSE_REGION = 7769;

    private final Client client;

    @Inject
    public LocationTracker(Client client) {
        this.client = client;
    }

    private boolean isInHouse() {
        int[] regions = client.getMapRegions();
        return client.isInInstancedRegion() && regions.length > 0 && regions[0] == HOUSE_REGION;
    }

    public boolean lastRecallWouldReset() {
        // TODO improve to include other instances that will not reset recall orb
        //      many boss instances would override recall because they go outside the room
        return !isInHouse();
    }
}
