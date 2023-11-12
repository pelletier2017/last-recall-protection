package com.recall.ui.overlay;

import com.recall.LastRecallLockConfig;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.input.MouseAdapter;

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
//        log.info("mouseClicked");
//        lockedOverlay.setHovered(!lockedOverlay.isHovered());
        if (mouseEvent.getButton() == MouseEvent.BUTTON1 && isOnOverlay(mouseEvent)) {
            lockedOverlay.setLocked(!lockedOverlay.isLocked());
            mouseEvent.consume();
        }
//        log.info("mouse listener isLocked=" + lockedOverlay.isLocked());
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
    public MouseEvent mouseEntered(MouseEvent mouseEvent) {
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseExited(MouseEvent mouseEvent) {
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseDragged(MouseEvent mouseEvent) {
//        log.info("mouseDragged");
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

        // move rectangle to line up hitbox correctly
        Rectangle copy = new Rectangle(rectangle);
        copy.translate(rectangle.height/5, rectangle.height/2);

//        log.info(point.toString());
//        log.info(rectangle.toString());
//        log.info(copy.toString());
        return copy.contains(point);
    }
}
