package component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Mister_Brown on 7/30/2016.
 */
public class MFrame extends JFrame{
    public MFrame(){
        setSize(750,375);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        addReduis(20,20);
        setLocation(CenterScreen(this));
        setLayout(null);
        JButton button = new JButton();
        button.setFont(new Font("arial", Font.PLAIN, 16));
        Color color = new Color(141, 141, 141);
        button.setText("<HTML><FONT color=\"#8D8D8D\">X</FONT></HTML>");
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setBackground(Color.WHITE);
        button.setBounds(700,2,80,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setBackground(null);
        add(button);

    }


    void addReduis(int x, int y){
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), x, y));
            }
        });

    }

    public static Point CenterScreen(Component th){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Point dimension = new Point(dim.width/2-th.getSize().width/2, dim.height/2-th.getSize().height/2);
        return dimension;
    }


}
