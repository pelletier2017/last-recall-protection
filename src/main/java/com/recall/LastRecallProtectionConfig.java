package com.recall;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("lastRecallProtection")
public interface LastRecallProtectionConfig extends Config {
    @ConfigItem(
            keyName = "lockRecall",
            name = "Lock Recall",
            description = "Will remove teleport options to avoid overriding recall teleport"
    )
    default boolean lockRecall() {
        return false;
    }

    // TODO add config to whitelist certain teleports to not block them
    // TODO add option to hide right click but default is off
    // TODO add examples to comments for whitelist so people know format, ideally in config panel but last resort goes in README
}
