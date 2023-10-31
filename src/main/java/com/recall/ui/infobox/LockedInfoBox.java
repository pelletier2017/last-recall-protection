package com.recall.ui.infobox;

import com.recall.LastRecallLockPlugin;

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
