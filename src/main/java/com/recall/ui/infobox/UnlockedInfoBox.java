package com.recall.ui.infobox;

import com.recall.LastRecallLockPlugin;

import javax.inject.Inject;
import java.awt.*;

public class UnlockedInfoBox extends BaseInfoBox {

    @Inject
    public UnlockedInfoBox(LastRecallLockPlugin plugin) {
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
