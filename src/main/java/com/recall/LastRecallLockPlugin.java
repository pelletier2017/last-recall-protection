package com.recall;

import com.recall.ui.overlay.LockedOverlay;
import com.recall.ui.overlay.OverlayMouseListener;
import com.recall.handler.ChatHandler;
import com.recall.handler.InventoryHandler;
import com.recall.teleport.TeleportFilterManager;
import com.recall.handler.LocationTracker;
import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.PluginChanged;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.stretchedmode.StretchedModeConfig;
import net.runelite.client.plugins.stretchedmode.StretchedModePlugin;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import java.util.Arrays;
import java.util.List;

@Slf4j
@PluginDescriptor(
        name = "Last Recall Lock"
)
public class LastRecallLockPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private LastRecallLockConfig config;

    @Inject
    private ChatHandler chatHandler;

    @Inject
    private TeleportFilterManager teleportFilterManager;

    @Inject
    private InfoBoxManager infoBoxManager;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private MouseManager mouseManager;

    // handlers

    @Inject
    private InventoryHandler inventoryHandler;

    @Inject
    private LocationTracker locationTracker;

    @Inject
    private LockedOverlay lockedOverlay;

    @Inject
    private OverlayMouseListener overlayMouseListener;

    // applies after menu entry swapper and other plugins
    private static final int POST_MENU_SORT_PRIORITY = 100;

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(lockedOverlay);
        mouseManager.registerMouseListener(0, overlayMouseListener);

        // effectively restart stretched mode to fix hitbox on overlay
        client.setStretchedEnabled(false);
        client.invalidateStretching(true);
    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(lockedOverlay);
    }

    @Subscribe
    public void onItemContainerChanged(ItemContainerChanged event) {
        inventoryHandler.onItemContainerChanged(event);
    }

    @Subscribe
    public void onPluginChanged(PluginChanged event) {
        updateOverlay();
    }

    @Subscribe
    public void onGameTick(GameTick event) {
        updateOverlay();
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event) {
        updateOverlay();
    }

    @Subscribe
    public void onChatMessage(ChatMessage event) {
        chatHandler.onChatMessage(event);
    }

// Inspired by the official runelite menu entry swapper plugin, with some modification.
    @Subscribe(priority = POST_MENU_SORT_PRIORITY)
    // This will run after the normal menu entry swapper, so it won't interfere with this plugin.
    public void onPostMenuSort(PostMenuSort ignored) {
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

        if (!lockedOverlay.isHidden() && lockedOverlay.isLocked() && wouldOverrideRecall()) {
            MenuEntry[] filteredMenuEntries = teleportFilterManager.filterAll(client.getMenuEntries());
            client.setMenuEntries(filteredMenuEntries);
        }
    }

    private void updateOverlay() {
        boolean hide = config.hideOverlay() || !config.hasOrb();
        lockedOverlay.setHidden(hide);
        lockedOverlay.setWouldResetRecall(wouldOverrideRecall());
    }

    private boolean wouldOverrideRecall() {
        return inventoryHandler.hasCrystalOfMemories() && locationTracker.lastRecallWouldReset() && chatHandler.isLastRecallSaved();
//        return config.hasOrb() && config.hasRecallSaved() && locationTracker.lastRecallWouldReset();
    }

    @Provides
    LastRecallLockConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(LastRecallLockConfig.class);
    }

}
