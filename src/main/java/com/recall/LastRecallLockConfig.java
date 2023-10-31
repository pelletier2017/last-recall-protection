package com.recall;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("lastRecallLock")
public interface LastRecallLockConfig extends Config {
    @ConfigItem(
            keyName = "lock",
            name = "Lock",
            description = "Will remove teleport options to avoid overriding recall teleport"
    )
    default boolean isLocked() {
        return false;
    }

    @ConfigItem(
            keyName = "hideOverlay",
            name = "Hide Overlay",
            description = "Hides overlay"
    )
    default boolean hideOverlay() {
        return false;
    }

    // TODO remove config items below this, only for testing
    @ConfigItem(
            keyName = "hasOrb",
            name = "TEST Has Orb",
            description = "orby"
    )
    default boolean hasOrb() {
        return false;
    }

    @ConfigItem(
            keyName = "recallSaved",
            name = "TEST Recall Saved",
            description = "recall saved"
    )
    default boolean hasRecallSaved() {
        return false;
    }

    // TODO add config to blacklist certain teleports that I may have forgotten
    // TODO add config to whitelist certain teleports to not block them
    // TODO add examples to comments for whitelist so people know format, ideally in config panel but last resort goes in README
}
