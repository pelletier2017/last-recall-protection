package com.recall;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("lastRecallLock")
public interface LastRecallLockConfig extends Config {

    // just for testing
//    @ConfigItem(
//            keyName = "hasOrb",
//            name = "TEST Has Orb",
//            description = "orby"
//    )
//    default boolean hasOrb() {
//        return false;
//    }
//
//    @ConfigItem(
//            keyName = "recallSaved",
//            name = "TEST Recall Saved",
//            description = "recall saved"
//    )
//    default boolean hasRecallSaved() {
//        return false;
//    }
}
