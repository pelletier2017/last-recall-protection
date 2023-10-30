package com.recall.infobox;

import lombok.Getter;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBox;

import java.util.List;

@Getter
public class InfoBoxGenerator {

    private final InfoBox lockedInfoBox;

    private final InfoBox semiLockedInfoBox;

    private final InfoBox unlockedInfoBox;

    public InfoBoxGenerator(Plugin plugin) {
        lockedInfoBox = new LockedInfoBox(plugin);
        semiLockedInfoBox = new SemiLockedInfoBox(plugin);
        unlockedInfoBox = new UnlockedInfoBox(plugin);
    }

    public List<InfoBox> getInfoBoxes() {
        return List.of(lockedInfoBox, semiLockedInfoBox, unlockedInfoBox);
    }
}
