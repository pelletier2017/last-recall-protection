package com.recall.infobox;

import com.recall.LastRecallLockPlugin;
import net.runelite.client.plugins.Plugin;

import javax.inject.Inject;
import java.awt.*;

public class SemiLockedInfoBox extends BaseInfoBox {

    @Inject
    public SemiLockedInfoBox(LastRecallLockPlugin plugin) {
        super(plugin);
    }

    @Override
    public String getText() {
        return "Lock";
    }

    @Override
    public Color getTextColor() {
        return Color.YELLOW;
    }
}
