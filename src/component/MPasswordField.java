package component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Mister_Brown on 7/30/2016.
 */
public class MPasswordField extends JPasswordField {
    public MPasswordField(int x, int y, int width, int hieght ) {
        setOpaque(false);
        setBounds(new Rectangle(x,y,width,hieght));
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setBackground(Color.CYAN);
        setFont(new Font("Consolas", Font.PLAIN, 25));
        Border rounded = new LineBorder(new Color(210,210,210), 1, true);
        Border empty = new EmptyBorder(0, 10, 0, 0);
        setBorder(rounded);
        Border border = new CompoundBorder(rounded, empty);
        setBorder(border);
    }

    private Shape shape;

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {

        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 30, 30);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 30, 30);
        }
        return shape.contains(x, y);
    }
}
