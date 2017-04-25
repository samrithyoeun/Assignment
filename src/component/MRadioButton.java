package component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Mister_Brown on 8/8/2016.
 */
public class MRadioButton extends JRadioButton implements ItemListener, MouseListener {
    private static int  theme =1;
    public static int BLUE_THEME =1;
    public static int RED_THEME= 2;

    public MRadioButton() {

        super();
        addItemListener(this);
        setForeground(Color.BLACK);
        setFont(new Font("Consolas", Font.PLAIN, 22));
        setBackground(new Color(236, 236, 236));


        setVerticalTextPosition(BOTTOM);
        setHorizontalTextPosition(CENTER);
        setHorizontalAlignment(CENTER);
        

        addItemListener(this);
        addMouseListener(this);
    }
    
    public MRadioButton(String text){
    	this();
    	setText(text);
    	
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()== ItemEvent.SELECTED) {
            if (theme == BLUE_THEME){
                this.setBackground(new Color(178, 228, 250));
                this.setForeground(Color.BLACK);
            }

            else if (theme == RED_THEME){
                this.setBackground(new Color(250, 227, 235));
                this.setForeground(Color.BLACK);
            }
        }
        else if(e.getStateChange()==ItemEvent.DESELECTED){
            this.setBackground(new Color(236, 236, 236));
            this.setForeground(Color.BLACK);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (theme == BLUE_THEME){
            this.setBackground(Color.CYAN);

        }

        else if (theme == RED_THEME){
            this.setBackground(new Color(244, 151, 190));

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(null);
    }
}
