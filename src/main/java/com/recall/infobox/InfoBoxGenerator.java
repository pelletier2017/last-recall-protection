package com.recall.infobox;

import lombok.Getter;
import net.runelite.client.ui.overlay.infobox.InfoBox;

import javax.inject.Inject;
import java.util.List;

@Getter
public class InfoBoxGenerator {

    @Inject
    private LockedInfoBox lockedInfoBox;

    @Inject
    private SemiLockedInfoBox semiLockedInfoBox;

    @Inject
    private UnlockedInfoBox unlockedInfoBox;

    public List<InfoBox> getInfoBoxes() {
        return List.of(lockedInfoBox, semiLockedInfoBox, unlockedInfoBox);
    }
}
