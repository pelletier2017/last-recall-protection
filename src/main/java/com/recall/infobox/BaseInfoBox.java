package com.recall.infobox;

import com.recall.LastRecallLockPlugin;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.image.BufferedImage;

public abstract class BaseInfoBox extends InfoBox {

    private static final String ICON_FILE_NAME = "CrystalMemoir.png";

    private static final BufferedImage imageIcon = ImageUtil.loadImageResource(BaseInfoBox.class, ICON_FILE_NAME);;

    @Inject
    public BaseInfoBox(LastRecallLockPlugin plugin) {
        super(imageIcon, plugin);
    }
}
