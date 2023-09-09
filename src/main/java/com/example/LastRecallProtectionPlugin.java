package com.example;

import com.example.location.LocationHelper;
import com.example.teleport.TeleportFilter;
import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.PostMenuSort;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
        name = "Last Recall Protection"
)
public class LastRecallProtectionPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private LastRecallProtectionConfig config;

    @Inject
    private ChatListener chatListener;

    boolean lastRecallWouldReset = false;

    boolean isLastRecallSaved = false;

    // applies after menu entry swapper and other plugins
    private static final int PRIORITY = -20;

    @Subscribe
    public void onGameTick(GameTick event) {
        lastRecallWouldReset = LocationHelper.lastRecallWouldReset(client);
        log.debug("lastRecallWouldReset=" + lastRecallWouldReset);
    }

    @Subscribe
    public void onChatMessage(ChatMessage event) {
        chatListener.onChatMessage(event);
        isLastRecallSaved = chatListener.isLastRecallSaved();
        log.debug("isLastRecallSaved=" + isLastRecallSaved);
    }

    @Provides
    LastRecallProtectionConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(LastRecallProtectionConfig.class);
    }

//     Copy-pasted from the official runelite menu entry swapper plugin, with some modification.
    @Subscribe(priority = PRIORITY)
    // This will run after the normal menu entry swapper, so it won't interfere with this plugin.
    public void onPostMenuSort(PostMenuSort event) {
//        List<MenuAction> menuActionsToIgnore = List.of(
//                MenuAction.CANCEL,
//                MenuAction.WALK
//        );
//
//        MenuEntry[] filteredEntries = Arrays.stream(client.getMenuEntries())
//                .filter(menuEntry -> !menuActionsToIgnore.contains(menuEntry.getType()))
//                .toArray(MenuEntry[]::new);
//        if (filteredEntries.length == 0) {
//            return;
//        }
//
//        log.info("onPostMenuSort");
//        for (MenuEntry entry : filteredEntries) {
//            logMenuEntry(entry);
//        }
        if (config.hide() && isLastRecallSaved && lastRecallWouldReset) {
            log.debug("Filtering teleports");
            MenuEntry[] filteredMenuEntries = TeleportFilter.filterAll(client.getMenuEntries());
            client.setMenuEntries(filteredMenuEntries);
        }
    }

    private void logMenuEntry(MenuEntry entry) {
        log.info("onMenuOpened option=[" + entry.getOption() + "] menuType=[" + entry.getType() + "] target=[" + entry.getTarget() + "] itemId=[" + entry.getItemId() + "] itemOp=[" + entry.getItemOp() + "]");
    }

}
