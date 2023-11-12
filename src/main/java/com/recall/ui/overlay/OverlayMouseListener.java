package com.recall.ui.overlay;

import com.recall.LastRecallLockConfig;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.input.MouseListener;

import javax.inject.Inject;
import java.awt.event.MouseEvent;

@Slf4j
public class OverlayMouseListener implements MouseListener {

    @Inject
    private volatile LockedOverlay lockedOverlay;

    @Inject
    private LastRecallLockConfig config;

    @Override
    public MouseEvent mouseClicked(MouseEvent mouseEvent) {
        log.info("mouseClicked");
//        lockedOverlay.setHovered(!lockedOverlay.isHovered());
        lockedOverlay.setLocked(!lockedOverlay.isLocked());
        log.info("mouse listener isLocked=" + lockedOverlay.isLocked());
        return mouseEvent;
    }

    @Override
    public MouseEvent mousePressed(MouseEvent mouseEvent) {
        log.info("mousePressed");
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseReleased(MouseEvent mouseEvent) {
        log.info("mouseReleased");
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
//        log.info("mouseMoved");
        return mouseEvent;
    }
}
