package com.recall;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LastRecallProtectionPluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(LastRecallProtectionPlugin.class);
        RuneLite.main(args);
    }
}