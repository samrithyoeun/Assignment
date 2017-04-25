package component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Mister_Brown on 8/18/2016.
 */
public class MComboBox extends JComboBox implements MouseListener{
	 

	public MComboBox(int LocationX, int LocationY, int Width, int Height, String[] arrayString){
		
        this(arrayString);
        setLocation(LocationX,LocationY);
        setSize(Width,Height);
    }
	
    public MComboBox(String[] arrayString){
        super(arrayString);
    	setOpaque(false);
        setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setFont(new Font("Consolas", Font.PLAIN, 22));
        requestFocusInWindow();
        setPreferredSize(new Dimension(150,40));
        setBorder(new roundBorder());
        setBackground(Color.CYAN);
        setUI(new BasicComboBoxUI() {
            protected JButton createArrowButton() {
                return new JButton() {
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        addMouseListener(this);
    }
    
    public MComboBox(){
    	this(new String[]{"",""});
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        setBorder(new borderFocus());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBorder(new borderFocus());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setBorder(new roundBorder());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(new borderFocus());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(new roundBorder());
    }
}
