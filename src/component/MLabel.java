package component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mister_Brown on 7/30/2016.
 */
public class MLabel extends JLabel{
    public MLabel(){
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
        setFont(new Font("Consolas", Font.PLAIN, 25));
        setBackground(new Color(236, 236, 236));
        setHorizontalAlignment(JLabel.CENTER);

    }

    public MLabel(String text){

        this();
        setText(text);

    }
    public MLabel(int LocationX, int LocationY, int Width, int Height){
        this();
        setLocation(LocationX,LocationY);
        setSize(Width,Height);
    }

    public MLabel(int x, int y, int Width, int Height, String text) {
        this(x, y, Width, Height);
        setOpaque(true);
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(TOP);
        setText(text);
        setFont(new Font("Consolas", Font.PLAIN, 25));
    }

    public MLabel(int x, int y, int Width, int Height, ImageIcon image){
        this(x,y,Width,Height);
        setOpaque(true);
        setIcon(image);
    }

}
