package com.recall.ui;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.input.MouseListener;

import java.awt.event.MouseEvent;

@Slf4j
public class OverlayMouseListener implements MouseListener {

    @Override
    public MouseEvent mouseClicked(MouseEvent mouseEvent) {
        log.info("mouseClicked");
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
        log.info("mouseDragged");
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseMoved(MouseEvent mouseEvent) {
        log.info("mouseMoved");
        return mouseEvent;
    }
}
