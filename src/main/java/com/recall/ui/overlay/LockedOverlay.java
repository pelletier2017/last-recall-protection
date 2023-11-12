package com.recall.ui.overlay;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.components.InfoBoxComponent;
import net.runelite.client.util.ImageUtil;

import javax.inject.Singleton;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

// heavily borrowed from https://github.com/damencs/tob-qol/blob/master/src/main/java/com/tobqol/rooms/nylocas/commons/NyloSelectionBox.java
@Slf4j
@Singleton
public class LockedOverlay extends Overlay {

    private static final String ICON_FILE_NAME = "CrystalMemoir.png";

    private static final int OVERLAY_SIZE = 30;

    private static final BufferedImage imageIcon = resize(ImageUtil.loadImageResource(LockedOverlay.class, ICON_FILE_NAME), OVERLAY_SIZE, OVERLAY_SIZE);

    @Getter
    @Setter
    private boolean isLocked = false;

    @Getter
    @Setter
    private boolean wouldResetRecall = false;

    @Getter
    @Setter
    private boolean isHovered = false;

    @Getter
    @Setter
    private boolean isHidden = false;

    private final InfoBoxComponent component;

    private int loggingCount = 0;

    public LockedOverlay() {
        this.component = new InfoBoxComponent();
        component.setImage(imageIcon);
//        component.setBackgroundColor(Color.BLACK);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        loggingCount++;
        if (loggingCount > 50) {
            loggingCount = 0;
            log.info("isLocked=" + isLocked);
        }

        if (isHidden) {
            return null;
        }

        if (!isLocked) {
            component.setColor(Color.GREEN);
            component.setText("");
        } else if (wouldResetRecall) {
            component.setColor(Color.RED);
            component.setText("Lock");
        } else {
            // locked but would not reset
            component.setColor(Color.YELLOW);
            component.setText("Lock");
        }

        Dimension result = component.render(graphics);

        if (isHovered) {
            Color color = graphics.getColor();
            graphics.setColor(new Color(200, 200, 200));
            graphics.drawRect(component.getBounds().x, component.getBounds().y, component.getBounds().width, component.getBounds().height);
            graphics.setColor(color);
        }

        return result;
    }

    private static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
