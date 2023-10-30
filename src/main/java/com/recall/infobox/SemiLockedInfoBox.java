package com.recall.infobox;

import net.runelite.client.plugins.Plugin;

import java.awt.*;

public class SemiLockedInfoBox extends BaseInfoBox {

    public SemiLockedInfoBox(Plugin plugin) {
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
