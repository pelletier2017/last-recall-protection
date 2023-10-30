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

    // TODO add config to blacklist certain teleports that I may have forgotten
    // TODO add config to whitelist certain teleports to not block them
    // TODO add option to hide right click but default is off
    // TODO add examples to comments for whitelist so people know format, ideally in config panel but last resort goes in README
}
