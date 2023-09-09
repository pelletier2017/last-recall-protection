package com.example;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("lastRecallProtection")
public interface LastRecallProtectionConfig extends Config {
    @ConfigItem(
            keyName = "hide",
            name = "Hide teleports",
            description = "Will hide all teleports except in an instance"
    )
    default boolean hide() {
        return false;
    }
}
