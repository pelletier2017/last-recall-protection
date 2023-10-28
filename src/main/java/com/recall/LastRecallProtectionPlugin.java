package com.recall;

import com.recall.teleport.TeleportFilterManager;
import com.recall.location.LocationHelper;
import com.recall.ui.TeleportsLockedInfoBox;
import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.PostMenuSort;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.PluginChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import java.util.Arrays;
import java.util.List;

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

    @Inject
    private TeleportFilterManager teleportFilterManager;

    @Inject
    private InfoBoxManager infoBoxManager;

    private boolean lastRecallWouldReset = false;

    private boolean isLastRecallSaved = false;

    boolean showingInfoBox = false;

    private InfoBox infoBox = new TeleportsLockedInfoBox(this);

    // applies after menu entry swapper and other plugins
    private static final int POST_MENU_SORT_PRIORITY = -20;

    @Override
    protected void startUp() throws Exception {
        infoBoxManager.removeInfoBox(infoBox);
    }

    @Override
    protected void shutDown() throws Exception {
        infoBoxManager.removeInfoBox(infoBox);
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event) {
        log.debug("onGameStateChanged");
    }

    @Subscribe
    public void onPluginChanged(PluginChanged event) {
        log.debug("onPluginChanged");
        updateInfoBox();
    }

    @Subscribe
    public void onGameTick(GameTick event) {
        updateInfoBox();
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event) {
        updateInfoBox();
    }

    @Subscribe
    public void onChatMessage(ChatMessage event) {
        chatListener.onChatMessage(event);
        isLastRecallSaved = chatListener.isLastRecallSaved();
        log.debug("isLastRecallSaved=" + isLastRecallSaved);
    }

// Inspired by the official runelite menu entry swapper plugin, with some modification.
    @Subscribe(priority = POST_MENU_SORT_PRIORITY)
    // This will run after the normal menu entry swapper, so it won't interfere with this plugin.
    public void onPostMenuSort(PostMenuSort event) {
        List<MenuAction> menuActionsToIgnore = List.of(
                MenuAction.CANCEL,
                MenuAction.WALK
        );

        MenuEntry[] filteredEntries = Arrays.stream(client.getMenuEntries())
                .filter(menuEntry -> !menuActionsToIgnore.contains(menuEntry.getType()))
                .toArray(MenuEntry[]::new);
        if (filteredEntries.length == 0) {
            return;
        }

        log.info("onPostMenuSort");
        for (MenuEntry entry : filteredEntries) {
            logMenuEntry(entry);
        }
        if (isLockingRecall()) {
//            log.debug("Filtering teleports");
            MenuEntry[] filteredMenuEntries = teleportFilterManager.filterAll(client.getMenuEntries());
            client.setMenuEntries(filteredMenuEntries);
        }
    }

    private boolean isLockingRecall() {
        return config.lockRecall() && LocationHelper.lastRecallWouldReset(client) && chatListener.isLastRecallSaved();
    }

    private void updateInfoBox() {
        if (isLockingRecall()) {
            if (!infoBoxManager.getInfoBoxes().contains(infoBox)) {
                infoBoxManager.addInfoBox(infoBox);
            }
        } else {
            infoBoxManager.removeInfoBox(infoBox);
        }
    }

    private void logMenuEntry(MenuEntry entry) {
        log.info("onMenuOpened option=[" + entry.getOption() + "] menuType=[" + entry.getType() + "] target=[" + entry.getTarget() + "] itemId=[" + entry.getItemId() + "] itemOp=[" + entry.getItemOp() + "]");
    }

    @Provides
    LastRecallProtectionConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(LastRecallProtectionConfig.class);
    }

}
