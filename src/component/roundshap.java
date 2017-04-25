package component;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Mister_Brown on 8/18/2016.
 */
public class roundshap extends roundBorder {
    public static final int CYAN_THEME=2;
    public static final int YELLOW_THEME=1;
    private static int  theme =1 ;

    @Override public void paintBorder(
            Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int r = 12;
        int w = width  - 1;
        int h = height - 1;
//*/
        Path2D.Float p = new Path2D.Float();
        p.moveTo(x, y + h);
        p.lineTo(x, y + r);
        p.quadTo(x, y, x + r, y);
        p.lineTo(x + w - r, y);
        p.quadTo(x + w, y, x + w, y + r);
        p.lineTo(x + w, y + h);
        p.closePath();
        Area round = new Area(p);
/*/
    Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
    Rectangle b = round.getBounds();
    b.setBounds(b.x, b.y + r, b.width, b.height - r);
    round.add(new Area(b));
//*/
        Container parent = c.getParent();
        if(parent!=null) {
            g2.setColor(parent.getBackground());
            Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
            corner.subtract(round);
            g2.fill(corner);
        }
        g2.setPaint(c.getForeground());
        g2.draw(round);
        g2.dispose();
    }
}
