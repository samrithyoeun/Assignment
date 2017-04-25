package form;

import javax.swing.JDialog;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import component.*;
import data.*;

public class SettingForm {
	 public static void Setting(JFrame parent, Database data, Setting s){
	        int frameWidth =434;
	        int frameHeight =700;
	        int spacing =90;
	        
	        
	        JDialog dialog = new JDialog(parent);
	        dialog.setTitle("Setting");
	        dialog.setSize(frameWidth,frameHeight);

	        JPanel frame = new JPanel();
	        frame.setLayout(null);
	        frame.setBackground(new Color(236, 236, 236));
	        frame.setSize(frameWidth,frameHeight);


	        MLabel title = new MLabel(0, 175, frame.getWidth(), 50, "SETTING(S)");
	        title.setHorizontalAlignment(SwingConstants.CENTER);
	        title.setBackground(new Color(248, 249, 250));
	        title.setFont(new Font("Consolas", Font.BOLD, 35));
	        frame.add(title);

	        ImageIcon images = new ImageIcon("src/picture/settingcover.png");
	        MLabel image = new MLabel(0, 0, images.getIconWidth(), images.getIconHeight(), images);
	        frame.add(image);

	        MLabel lblFine = new MLabel(55, 258 , 300, 25, "Fine Per Day");
	        MTextField txtFine = new MTextField(48, 282 , 300, 40);
	        txtFine.setText(s.fine+"");
	        frame.add(lblFine);
	        frame.add(txtFine);

	        MLabel lblDay = new MLabel(55, 258+spacing , 300, 25, "Duration of Borrowing");
	        MTextField txtDay = new MTextField(48, 282 + spacing, 300, 40);
	        txtDay.setText(s.duration+"");
	        frame.add(lblDay);
	        frame.add(txtDay);

	        MLabel lblQty = new MLabel(55, 258+spacing *2 , 300, 25, "Quantity of Borrowing");
	        MTextField txtQty = new MTextField(48, 282+spacing *2 , 300, 40);
	        txtQty.setText(s.qty+"");
	        frame.add(lblQty);
	        frame.add(txtQty);

	        MButton btnSave = new MButton(48, 258+spacing *3, 120, 40, "SAVE");
	        MButton btnCancel = new MButton(210, 258+spacing *3, 140, 40, "CANCEL");

	        
	        btnSave.addActionListener(e->{
	        	int row =data.Update(new data.Setting(
	        			Integer.parseInt(txtQty.getText()),
	        			Integer.parseInt(txtDay.getText()),
	        			Double.parseDouble(txtFine.getText())
	        			));
	        	
	        	if(row<0)MOptionPane.show(null, "UNABLE TO UPDATE");
	        	else MOptionPane.show(null, "Successfully update");
	        	
	        });
	        
	        btnCancel.addActionListener(e->{
	        	dialog.dispose();
	        });
	        frame.add(btnSave);
	        frame.add(btnCancel);

	        dialog.add(frame);
	        dialog.setModal(true);
	        dialog.setLocation(MFrame.CenterScreen(frame));
	        dialog.setVisible(true);






	    }

	 public static void Summary(JFrame parent,Database data){
       int totalMember =40;
       int male = 20;
       int female = totalMember-male;
       int totalTeacher =20;
       int maleTeacher =2;
       int totalStudent =20;
       int maleStudent =3;


       
       int bookQty =23;

       int frameWidth =798;
       int frameHeight =700;
       int spacing =75;
       Color background =new Color(236, 236, 236);

       JDialog dialog = new JDialog(parent);
       dialog.setResizable(false);
       dialog.setTitle("ADDING NEW BOOK(S)");
       dialog.setSize(frameWidth,frameHeight);

       JPanel frame = new JPanel();
       frame.setLayout(null);
       frame.setLocation(MFrame.CenterScreen(frame));
       frame.setBackground(new Color(236, 236, 236));
       frame.setSize(frameWidth,frameHeight);

       MLabel title = new MLabel(0, 175, frame.getWidth(), 50, "Summary");
       title.setHorizontalAlignment(SwingConstants.CENTER);
       title.setBackground(new Color(245, 213, 65));
       title.setFont(new Font("Consolas", Font.BOLD, 35));
       frame.add(title);

       ImageIcon images = new ImageIcon("src/picture/summarycover.jpg");
       MLabel image = new MLabel(0, 0, images.getIconWidth(), images.getIconHeight(), images);
       frame.add(image);

       MLabel lblBook = new MLabel(37, 258, 200, 40, "<HTML> + <U>BOOK(S)</U></HTML>");
       lblBook.setBackground(background);
       frame.add(lblBook);

       String line ="";
       for (int i=0;i<20;i++){
           line +="|";
       }
       MTextArea lines = new MTextArea(frameWidth/2,258,1,frameHeight-image.getHeight());
       lines.setBackground(Color.black);
       frame.add(lines);

       MTextArea lblStudentID = new MTextArea(55, 258+spacing/2 , 350, frameHeight-image.getHeight());
       lblStudentID.setBackground(background);
       lblStudentID.setFont(new Font("Arial", Font.PLAIN, 19));
       

       MLabel lblMember = new MLabel(460-17, 258, 300, 40, "<HTML> + <U>MEMBER(S)</U></HTML>");
       lblMember.setBackground(background);
       frame.add(lblMember);


       MTextArea taMember = new MTextArea(410, 258+spacing/2 , 500, 300);
       taMember.setBackground(background);
       taMember.setFont(new Font("Arial", Font.PLAIN, 19));
       
       frame.add(taMember);

       frame.add(lblStudentID);
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date c = new Date();

       MLabel info = new MLabel(frameWidth/2, frameHeight-70, frameWidth/2-50, 40, "<HTML><I><U>this summary is done on "+sdf.format(c)+"</U></i></HTML>");
       info.setHorizontalAlignment(JLabel.RIGHT);
       info.setFont(new Font("Arial", Font.PLAIN, 15));
       frame.add(info);

       
       ArrayList<BookCount> bookCount = data.getCountBook();
       ArrayList<MemberCount> memberCount = data.getMemberCount();
       MemberCount moneyEarned = data.getMoneyEarned();
       
       for(int i =0;i<memberCount.size();i++){
    	   taMember.append(" - "+memberCount.get(i).memberType + "\t" + memberCount.get(i).qty+"\n");
       }
       taMember.append("\n");
       
       memberCount = data.getNewMemberCount();
       for(int i =0;i<memberCount.size();i++){
    	   taMember.append(" - "+memberCount.get(i).memberType + "\t" + memberCount.get(i).qty+"\n");
    	   
       }
       taMember.append("\n - Money earned :\n");
       taMember.append(" - From new members "+"\t"+memberCount.get(2).qty*80000+" R");
       taMember.append("\n - From return fine "+"\t"+ moneyEarned.qty+" R");
       taMember.append("\n - Total Money :"+"\t"+ (moneyEarned.qty+memberCount.get(2).qty*80000)+" R");
       
       
       lblStudentID.setText("");
       int total =0;
       for(int i=0;i<bookCount.size();i++){
    	   lblStudentID.append(" - "+bookCount.get(i).name + "\t" + bookCount.get(i).qty+"\n");
    	   total +=bookCount.get(i).qty;
       }
       
       lblStudentID.append("TOTAL BOOKS : "+total);
       
       dialog.add(frame);
       dialog.setModal(true);
       dialog.setLocation(MFrame.CenterScreen(frame));
       dialog.setVisible(true);
   }
}
