package component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mister_Brown on 8/2/2016.
 */
public class MOptionPane extends JOptionPane {


    public static void show(Component parent, String Text){
        MLabel message = new MLabel(Text);
        message.setFont(new Font("Consolas", Font.PLAIN, 20));
        showMessageDialog(parent,message);
        
    }

    public static void show(Component parent, String Title,String Text ){
        MLabel message = new MLabel(Text);
        message.setFont(new Font("Consolas", Font.PLAIN, 20));
        showMessageDialog(parent,message,Title,INFORMATION_MESSAGE);
        



    }
}
