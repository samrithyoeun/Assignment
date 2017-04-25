package component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.prompt.PromptSupport;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Mister_Brown on 7/30/2016.
 */
public class MTextField extends JTextField {
    private Border rounded = new LineBorder(new Color(210,210,210), 1, true);
    private Border empty = new EmptyBorder(0, 10, 0, 0);


   
    public MTextField(){
//        setForeground(new Color(0, 39, 186));
        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setFont(new Font("Consolas", Font.PLAIN, 22));
        setBorder(rounded);
        Border border = new CompoundBorder(rounded, empty);
        setBorder(border);
       
        Dimension w=new Dimension(400,40);
        setPreferredSize(w);
        setBackground(Color.CYAN);

    }

    @Override
    public void setEditable(boolean b) {
        super.setEditable(b);
        
        if(b==false){
        	this.setForeground(Color.decode("#FF4081"));
        	
        }
        
    }
    
    
    public MTextField(int x, int y, int width, int hieght ) {
        this();
        setBounds(new Rectangle(x,y,width,hieght));

    }

    public void setHint(String hint){
    	PromptSupport.setForeground(Color.decode("#FF4081"), this);
        PromptSupport.setPrompt(hint,this);
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
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
    

    
}
