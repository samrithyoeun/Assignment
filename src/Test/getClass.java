package Test;


import java.text.SimpleDateFormat;

import component.MOptionPane;
import java.awt.*;
import javax.swing.*;

import java.util.Date;
import java.util.Date.*;

public class getClass extends JFrame{
	
	

	    public static void main(String arg[]) {
	    JFrame frame = new JFrame();
	    frame.setLayout(new BorderLayout());

	    JPanel gb = new JPanel(new GridBagLayout());
	    JLabel content = new JLabel("Some text");

	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.anchor = GridBagConstraints.NORTH;
	    gbc.weighty = 1;

	    gb.add(content, gbc); // gbc is containing the GridBagConstraints
	    frame.add(gb, BorderLayout.CENTER);

	    frame.setVisible(true);
	    

	}
}
