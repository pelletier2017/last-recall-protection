package com.recall.infobox;

import com.recall.LastRecallLockPlugin;
import net.runelite.client.plugins.Plugin;
import javax.inject.Inject;

import java.awt.*;

public class LockedInfoBox extends BaseInfoBox {

    @Inject
    public LockedInfoBox(LastRecallLockPlugin plugin) {
        super(plugin);
    }

    @Override
    public String getText() {
        return "Lock";
    }

    @Override
    public Color getTextColor() {
        return Color.RED;
    }
}
