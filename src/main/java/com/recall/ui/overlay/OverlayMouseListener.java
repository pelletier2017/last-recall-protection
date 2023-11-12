package com.recall.ui.overlay;

import com.recall.LastRecallLockConfig;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.input.MouseAdapter;
import net.runelite.client.plugins.stretchedmode.StretchedModePlugin;

import javax.inject.Inject;
import java.awt.*;
import java.awt.event.MouseEvent;

@Slf4j
public class OverlayMouseListener extends MouseAdapter {

    @Inject
    private LockedOverlay lockedOverlay;

    @Inject
    private LastRecallLockConfig config;

    @Override
    public MouseEvent mouseClicked(MouseEvent mouseEvent) {
        if (lockedOverlay.isHidden() || mouseEvent.isAltDown()) {
            return mouseEvent;
        }
        if (mouseEvent.getButton() == MouseEvent.BUTTON1 && isOnOverlay(mouseEvent)) {
            lockedOverlay.setLocked(!lockedOverlay.isLocked());
            mouseEvent.consume();
        }
        return mouseEvent;
    }

    @Override
    public MouseEvent mousePressed(MouseEvent mouseEvent) {
        if (lockedOverlay.isHidden() || mouseEvent.isAltDown()) {
            return mouseEvent;
        }

        if (mouseEvent.getButton() == MouseEvent.BUTTON1 && isOnOverlay(mouseEvent)) {
            mouseEvent.consume();
        }
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseReleased(MouseEvent mouseEvent) {
        if (lockedOverlay.isHidden() || mouseEvent.isAltDown()) {
            return mouseEvent;
        }

        if (mouseEvent.getButton() == MouseEvent.BUTTON1 && isOnOverlay(mouseEvent)) {
            mouseEvent.consume();
        }
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseMoved(MouseEvent mouseEvent) {
        if (mouseEvent.isAltDown()) {
            lockedOverlay.setHovered(false);
        } else {
            lockedOverlay.setHovered(isOnOverlay(mouseEvent));
        }
        return mouseEvent;
    }

    private boolean isOnOverlay(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        Rectangle rectangle = lockedOverlay.getBounds();
        return rectangle.contains(point);
    }
}
