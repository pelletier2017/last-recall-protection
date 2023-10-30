package com.recall;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LastRecallLockPluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(LastRecallLockPlugin.class);
        RuneLite.main(args);
    }
}