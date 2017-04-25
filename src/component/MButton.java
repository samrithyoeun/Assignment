package component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Mister_Brown on 7/30/2016.
 */
public class MButton extends JButton{
    public MButton(){
        setOpaque(false);
        setForeground(Color.white);
        setBackground(Color.RED);
        setFont(new Font("arial", Font.BOLD, 23));
        setFocusPainted(false);

    }

    public MButton(String text){
        this();
        setText(text);
    }

    public MButton(int x, int y, int width, int height, String text){
        this();
        setLocation(x,y);
        setSize(width,height);
        setText(text);

    }

    private Color hoverBackgroundColor = Color.decode("#FF80AB");
    private Color pressedBackgroundColor = Color.decode("#D50000");
;

    public Color getHoverBackgroundColor() {
        return hoverBackgroundColor;
    }

    public void setHoverBackgroundColor(Color hoverBackgroundColor) {
        this.hoverBackgroundColor = hoverBackgroundColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    //
    private Shape shape;

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
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
