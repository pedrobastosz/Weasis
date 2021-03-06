package org.weasis.base.explorer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.Icon;

public final class ThumbnailIcon implements Icon {

    private final BufferedImage image;

    public ThumbnailIcon(final BufferedImage image) {
        this.image = image;
    }

    @Override
    public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
        g.drawImage(this.image, x, y, c);
    }

    @Override
    public int getIconWidth() {
        return this.image.getWidth();
    }

    @Override
    public int getIconHeight() {
        return this.image.getHeight();
    }

    public BufferedImage getImage() {
        return this.image;
    }
}