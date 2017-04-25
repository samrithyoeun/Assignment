package component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.prompt.PromptSupport;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Mister_Brown on 9/10/2016.
 */
public class MTextArea extends JTextArea implements FocusListener {
    private Border rounded = new LineBorder(new Color(210,210,210), 1, true);
    private Border empty = new EmptyBorder(0, 10, 0, 0);

    public MTextArea(){
        super();
        setFont(new Font("Consolas", Font.PLAIN, 22));
        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setBorder(rounded);
        Border border = new CompoundBorder(rounded, empty);
        setBorder(border);
        requestFocusInWindow();
        Dimension w=new Dimension(200,30);
        setPreferredSize(w);
        setBackground(Color.CYAN);
        setWrapStyleWord(true);
        setLineWrap(true);

    }

    public MTextArea(int x, int y, int width, int hieght ) {
        this();
        setBounds(new Rectangle(x,y,width,hieght));

    }
    
    public MTextArea(int x, int y, int width, int hieght,String hint ) {
        this(x,y,width,hieght);
        PromptSupport.setForeground(Color.decode("#616161"), this);
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

	@Override
	public void focusGained(FocusEvent arg0) {
		new borderFocus();
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		new roundBorder();
		
	}


  

}
