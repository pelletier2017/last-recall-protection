package com.recall.ui;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TeleportsLockedInfoBox extends InfoBox {

//    private static final String ICON_FILE_NAME = "crystalPadlockTransparent.png";
    private static final String ICON_FILE_NAME = "CrystalMemoir.png";

    private static final BufferedImage imageIcon = ImageUtil.loadImageResource(TeleportsLockedInfoBox.class, ICON_FILE_NAME);;

    public TeleportsLockedInfoBox(Plugin plugin) {
        super(imageIcon, plugin);
    }

//    private BufferedImage iconImage() {
//        return
//    }

    @Override
    public String getText() {
        return "Lock";
    }

    @Override
    public Color getTextColor() {
        return Color.RED;
    }
}
