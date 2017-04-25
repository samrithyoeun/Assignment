package component;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Mister_Brown on 8/18/2016.
 */

public class roundBorder extends AbstractBorder {

    @Override public void paintBorder(
            Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int r = 30;
        int w = width  - 1;
        int h = height - 1;
        Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
        Container parent = c.getParent();
        if(parent!=null) {
            g2.setColor(parent.getBackground());
            Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
            corner.subtract(round);
            g2.fill(corner);
            g2.setBackground(Color.CYAN);
        }

        g2.setPaint(Color.CYAN);
        g2.draw(round);
        g2.dispose();
        
        
    }
    @Override public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
    @Override public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = 8;
        insets.top = insets.bottom = 4;
        return insets;
    }
}