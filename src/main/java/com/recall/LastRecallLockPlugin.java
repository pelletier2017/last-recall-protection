package com.recall;

import com.recall.infobox.InfoBoxGenerator;
import com.recall.teleport.TeleportFilterManager;
import com.recall.location.LocationHelper;
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
        name = "Last Recall Lock"
)
public class LastRecallLockPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private LastRecallLockConfig config;

    @Inject
    private ChatListener chatListener;

    @Inject
    private TeleportFilterManager teleportFilterManager;

    @Inject
    private InfoBoxManager infoBoxManager;

    private final InfoBoxGenerator infoBoxGenerator = new InfoBoxGenerator(this);

    private InfoBox oldInfoBox;

    // applies after menu entry swapper and other plugins
    private static final int POST_MENU_SORT_PRIORITY = -20;

    @Override
    protected void startUp() throws Exception {
        removeAnyInfoBoxes();
    }

    @Override
    protected void shutDown() throws Exception {
        removeAnyInfoBoxes();
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
        log.debug("isLastRecallSaved=" + chatListener.isLastRecallSaved());
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

        log.info("onPostMenuSort");
        for (MenuEntry entry : filteredEntries) {
            logMenuEntry(entry);
        }
        if (config.isLocked() && wouldOverrideRecall()) {
            MenuEntry[] filteredMenuEntries = teleportFilterManager.filterAll(client.getMenuEntries());
            client.setMenuEntries(filteredMenuEntries);
        }
    }

    private void logMenuEntry(MenuEntry entry) {
        log.info("onMenuOpened option=[" + entry.getOption() + "] menuType=[" + entry.getType() + "] target=[" + entry.getTarget() + "] itemId=[" + entry.getItemId() + "] itemOp=[" + entry.getItemOp() + "]");
    }

    private boolean wouldOverrideRecall() {
        return LocationHelper.lastRecallWouldReset(client) && chatListener.isLastRecallSaved();
    }

    private void updateInfoBox() {
        InfoBox newInfoBox = getCurrentInfoBox();
        if (newInfoBox != oldInfoBox) {
            removeAnyInfoBoxes();
        }
        oldInfoBox = newInfoBox;

        if (hasRecallOrb()) {
            addInfoBox(newInfoBox);
        } else {
            removeAnyInfoBoxes();
        }
    }

    private boolean hasRecallOrb() {
        // TODO replace this with checking if its in your inventory
        return config.hasRecallOrb();
    }

    private InfoBox getCurrentInfoBox() {
        if (!config.isLocked()) {
            return infoBoxGenerator.getUnlockedInfoBox();
        } else if (wouldOverrideRecall()) {
            return infoBoxGenerator.getLockedInfoBox();
        } else {
            return infoBoxGenerator.getSemiLockedInfoBox();
        }
    }

    private void addInfoBox(InfoBox infoBox) {
        if (!infoBoxManager.getInfoBoxes().contains(infoBox)) {
            infoBoxManager.addInfoBox(infoBox);
        }
    }

    private void removeAnyInfoBoxes() {
        for (InfoBox infoBox : infoBoxGenerator.getInfoBoxes()) {
            infoBoxManager.removeInfoBox(infoBox);
        }
    }

    @Provides
    LastRecallLockConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(LastRecallLockConfig.class);
    }

}
