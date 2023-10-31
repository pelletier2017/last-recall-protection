package com.recall.overlay;

import lombok.Getter;
import lombok.Setter;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.components.InfoBoxComponent;

import java.awt.*;

// heavily borrowed from https://github.com/damencs/tob-qol/blob/master/src/main/java/com/tobqol/rooms/nylocas/commons/NyloSelectionBox.java
public class LockedOverlay extends Overlay {

    @Getter
    @Setter
    private boolean isSelected = false;

    @Getter
    @Setter
    private boolean isHovered = false;

    private final InfoBoxComponent component;

    public LockedOverlay(InfoBoxComponent component) {
        this.component = component;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (isSelected) {
            component.setColor(Color.GREEN);
            component.setText("Lock");
        } else {
            component.setColor(Color.RED);
            component.setText("Not");
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
}
