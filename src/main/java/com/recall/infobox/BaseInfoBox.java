package com.recall.infobox;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

public abstract class BaseInfoBox extends InfoBox {

//    private static final String ICON_FILE_NAME = "crystalPadlockTransparent.png";
    private static final String ICON_FILE_NAME = "CrystalMemoir.png";

    private static final BufferedImage imageIcon = ImageUtil.loadImageResource(BaseInfoBox.class, ICON_FILE_NAME);;

    public BaseInfoBox(Plugin plugin) {
        super(imageIcon, plugin);
    }
}
