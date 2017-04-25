package form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import component.*;
import data.Database;
import data.Setting;

public class Mainscreen {
	MLabel title;
    ImageIcon libraryLogo;
    ImageIcon book;
    ImageIcon borrow;
    ImageIcon returns;
    ImageIcon student;
    ImageIcon addBookLogo;
    ImageIcon viewBookLogo;
    ImageIcon addMemberLogo;
    ImageIcon viewMemberLogo;
    JPanel center;
    JPanel south;
    MLabel mainMenu;
    CardLayout cardLayout;
    JPanel mainPanel;
    JPanel memberPanel;
    JPanel bookPanel;
    MRadioButton btnBook; 
    MRadioButton btnMember;
    MRadioButton btnSetting;
    MRadioButton btnBorrow;
    MRadioButton btnReturn;
    MButton about;
    MRadioButton btnAddBook;
    MRadioButton btnViewBook;
    MRadioButton btnAddMember;
    MRadioButton btnViewMember;
    JPanel cardPanel;
    
	
	
	public void show(){
	
		JFrame frame = new JFrame();
		frame.setSize(1200,600);
		frame.setLocationRelativeTo(null);
		frame.setMinimumSize(new Dimension(1200,600));
		frame.setLayout(new BorderLayout());
		frame.setTitle("LIBRARY MANAGEMENT SYSTEM");
		title = new MLabel("<html><strong>LIBRARY MANAGEMENT SYSTEM</strong><br>powered by group5 of scholarship class</html>");
		title.setHorizontalAlignment(JLabel.CENTER);
		libraryLogo = new ImageIcon("src/picture/lmslogo.png");
		title.setIcon(libraryLogo);
		title.setIconTextGap(30);
		title.setFont(new Font("Consolas", Font.PLAIN, 25));
		
		center = new JPanel();
		center.setLayout(new BorderLayout());
		mainMenu = new MLabel("<html><strong><u>MAIN-MENU</u></strong><br></html>");
		mainMenu.setFont(new Font("Consolas", Font.PLAIN, 40));
		
		
		center.add(mainMenu,BorderLayout.NORTH);
		
		south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.RIGHT));
		about = new MButton("ABOUT US");
		south.setBorder(new EmptyBorder(15,15,15,30));
		south.add(about);
		
		/*initMenu*/
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,5));
		btnBook = new MRadioButton("Book Management");
		book = new ImageIcon("src/picture/book.png");
		btnBook.setIcon(book);
		btnBook.setBorder(new EmptyBorder(15,15,15,15));
		mainPanel.add(btnBook);
		
		btnMember = new MRadioButton("Member Management");
		student = new ImageIcon("src/picture/student.png");
		btnMember.setIcon(student);
		btnMember.setBorder(new EmptyBorder(15,15,15,15));
		mainPanel.add(btnMember);
		
		btnBorrow = new MRadioButton("Borrowing Books");
		borrow = new ImageIcon("src/picture/borrow.png");
		btnBorrow.setIcon(borrow);
		btnBorrow.setBorder(new EmptyBorder(15,15,15,15));
		mainPanel.add(btnBorrow);
		
		btnReturn = new MRadioButton("return Books");
		returns = new ImageIcon("src/picture/return.png");
		btnReturn.setBorder(new EmptyBorder(15,15,15,15));
		btnReturn.setIcon(returns);
		mainPanel.add(btnReturn);
		
		btnSetting = new MRadioButton("Setting");
		ImageIcon setting = new ImageIcon("src/picture/se.png");
		btnSetting.setBorder(new EmptyBorder(15,15,15,15));
		btnSetting.setIcon(setting);
		mainPanel.add(btnSetting);
		
//		MRadioButton btnExit = new MRadioButton("Log-out");
//		ImageIcon exit = new ImageIcon("src/picture/exit.png");
//		btnExit.setBorder(new EmptyBorder(15,15,15,15));
//		btnExit.setIcon(exit);
//		mainPanel.add(btnExit);
//		

		
	
		/*memberPanel*/
		memberPanel = new JPanel(new GridLayout(1,2));
		btnAddBook = new MRadioButton("Add Book(s)");
		addBookLogo = new ImageIcon("src/picture/book.png");
		btnAddBook.setIcon(addBookLogo);
		memberPanel.add(btnAddBook);

		btnViewBook = new MRadioButton("Search/Update/Delete Book(s)");
		viewBookLogo = new ImageIcon("src/picture/book.png");
		btnAddBook.setIcon(viewBookLogo);
		memberPanel.add(btnViewBook);
		memberPanel.setBorder(new EmptyBorder(40,40,40,40));
						
		
		/*bookPanel*/
		bookPanel = new JPanel(new GridLayout(1,2));
		btnAddBook = new MRadioButton("Add Book(s)");
		addBookLogo = new ImageIcon("src/picture/addbook.png");
		btnAddBook.setIcon(addBookLogo);
		bookPanel.add(btnAddBook);

		btnViewBook = new MRadioButton("Search/Update/Delete Book(s)");
		viewBookLogo = new ImageIcon("src/picture/viewbook.png");
		btnViewBook.setIcon(viewBookLogo);
		bookPanel.add(btnViewBook);
		bookPanel.setBorder(new EmptyBorder(40,40,40,40));		

		/* Setting Panel */		
		JPanel SettingPanel = new JPanel(new GridLayout(1,2));
		MRadioButton btnSettting = new MRadioButton("System Setting");
		ImageIcon settingimage = new ImageIcon("src/picture/setting.png");
		btnSettting.setIcon(settingimage);
		SettingPanel.add(btnSettting);

		MRadioButton btnSummary = new MRadioButton("Summaries");
		ImageIcon summaries = new ImageIcon("src/picture/report.png");
		btnSummary.setIcon(summaries);
		SettingPanel.add(btnSummary);
		SettingPanel.setBorder(new EmptyBorder(40,40,40,40));		
		
		/*memberPanel*/
		
		memberPanel = new JPanel(new GridLayout(1,2));
		btnAddMember = new MRadioButton("Add Member(s)");
		addMemberLogo = new ImageIcon("src/picture/addmember.png");
		btnAddMember.setIcon(addMemberLogo);
		memberPanel.add(btnAddMember);

		btnViewMember = new MRadioButton("Search/Update/Delete Member(s)");
		viewMemberLogo = new ImageIcon("src/picture/viewmember.png");
		btnViewMember.setIcon(viewMemberLogo);
		memberPanel.add(btnViewMember);
		memberPanel.setBorder(new EmptyBorder(40,40,40,40));		
		
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		cardPanel.setBackground(Color.black);
		cardPanel.add(mainPanel,"mainPanel");
		cardPanel.add(memberPanel,"memberPanel");
		cardPanel.add(bookPanel,"bookPanel");
		cardPanel.add(SettingPanel, "SettingPanel");
		center.add(cardPanel, BorderLayout.CENTER);
		
		btnMember.addActionListener(e->{
			cardLayout.show(cardPanel, "memberPanel");
			mainMenu.setText("<html><strong><u>MEMBER-MANAGEMENT</u></strong></html>");
			about.setText("GO BACK");
		});
		
		btnBook.addActionListener(e->{
			cardLayout.show(cardPanel, "bookPanel");
			mainMenu.setText("<html><strong><u>BOOK-MANAGEMENT</u></strong></html>");
			about.setText("GO BACK");
		});
		
		btnSetting.addActionListener(e->{
			cardLayout.show(cardPanel, "SettingPanel");
			mainMenu.setText("<html><strong><u>SETTING</u></strong></html>");
			about.setText("GO BACK");
		});
		
		
		
		
		/*All Object*/
		BookForm bf = new BookForm();
		ReturnForm rf = new ReturnForm();
		BorrowForm bof = new BorrowForm();
		MemberForm mf = new MemberForm();
		MOptionPane op = new MOptionPane();
		Database data = new Database();
		
		
		data.connect();
		Setting s=data.getSetting();
		
		about.addActionListener(e->{
			if(about.getText().contains("GO BACK")){
				cardLayout.show(cardPanel, "mainPanel");
				about.setText("ABOUT US");
				mainMenu.setText("<html><strong><u>MAIN-MENU</u></strong></html>");
			}
			else{
				op.show(frame,"ABOUT US!","CREATED BY SAMRITH-YOEUN");
				
				
			}
		});
		
		btnAddMember.addActionListener(e->{
			mf.addMember(frame,data,null);
			
		});
		
		btnAddBook.addActionListener(e->{
			
			bf.AddBooks(frame,data,null);
		});
		
		btnBorrow.addActionListener(e->{
			
			bof.BorrowBook(frame,data,s);
		});
		
		btnReturn.addActionListener(e->{
			
			rf.ReturnBook(frame,data,s);
		});
		
		btnViewBook.addActionListener(e->{
			bf.SearchBook(frame,data);
		});
		
		
		btnViewMember.addActionListener(e->{
			mf.SearchMember(frame,data);
		});
	
		btnSettting.addActionListener(e->{
			SettingForm.Setting(frame,data,s);
		});
		
		btnSummary.addActionListener(e->{
			SettingForm.Summary(frame,data);
		});
		frame.add(south,BorderLayout.SOUTH);
		frame.add(center,BorderLayout.CENTER);
		frame.add(title,BorderLayout.NORTH);
		frame.setVisible(true);
		
		
		
	}
	
		
	
	
	public static void main(String arg[]){
		Login login = new Login();
		login.login();
		
	}


	
}
