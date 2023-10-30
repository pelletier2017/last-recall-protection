package com.recall.infobox;

import net.runelite.client.plugins.Plugin;

import java.awt.*;

public class UnlockedInfoBox extends BaseInfoBox {

    public UnlockedInfoBox(Plugin plugin) {
        super(plugin);
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public Color getTextColor() {
        return null;
    }
}
