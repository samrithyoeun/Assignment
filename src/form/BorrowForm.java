package form;
import javax.swing.*;

import javax.swing.border.*;
import component.*;
import data.*;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BorrowForm {
	static double FINE_IF_LOST = 200000;
	static Book book;
	static Member member;
	static int limited=3;
	static int borrowed;

	
    public static void BorrowBook(JFrame parent,Database data,Setting s){
        int frameWidth =798;
        int frameHeight =700;
        int spacing =75;
        
        
        
//  i used the Jdialog ,because when i use Jframe.setDefaultCloseOperation
//  it will close all the all
        JDialog dialog = new JDialog(parent);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parent);
        JPanel frame = new JPanel();

//        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setBackground(new Color(236, 236, 236));
        frame.setSize( frameWidth,frameHeight);


        dialog.setTitle("BORROWING BOOK(S)");
        MLabel title = new MLabel(0, 190, frame.getWidth(), 50, "BORROWING BOOK(S) FORM");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(248,249,250));
        title.setFont(new Font("Consolas", Font.BOLD, 35));

        ImageIcon images = new ImageIcon("src/picture/upadate.png");
        MLabel image = new MLabel(0, 0, images.getIconWidth(), images.getIconHeight(), images);

        // right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLocation(0,images.getIconHeight()+80);
        rightPanel.setSize(frameWidth/2,frameHeight-images.getIconHeight());
        rightPanel.setLayout(null);

        MLabel lblStudentName = new MLabel(55, 10 , 300, 25, "Student Name");
        MTextField txtStudentName = new MTextField(48, 35 , 300, 40);
        txtStudentName.setEditable(false);

        MLabel lblSex = new MLabel(55, 10+spacing , 300, 25, "Sex");
        MTextField txtSex = new MTextField(48, 35+spacing , 300, 40);
        txtSex.setEditable(false);

        MLabel lblIssueDate = new MLabel(55, 10+spacing*2 , 300, 25, "Issue Date YYYY-MM-DD");
        MTextField txtIssueDate = new MTextField(48, 35+spacing*2 , 300, 40);
        
        

        

        rightPanel.add(lblStudentName);
        rightPanel.add(txtStudentName);
        rightPanel.add(lblSex);
        rightPanel.add(txtSex);
        rightPanel.add(txtIssueDate);
        rightPanel.add(lblIssueDate);
        

        
        MLabel lblStudentID = new MLabel(55, 258 , 300, 25, "Student ID");
        MTextField txtStudentID = new MTextField(48, 282 , 300, 40);
/*
        txtStudentID.addActionListener(e-> frame.add(rightPanel).repaint());
        txtStudentID.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                frame.add(rightPanel).repaint();
            }
        });
*/
        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLocation(frameWidth/2,images.getIconHeight()+80);
        leftPanel.setSize(frameWidth/2,frameHeight-images.getIconHeight()-200);
        leftPanel.setLayout(null);

        MLabel lblQuantity = new MLabel(55, 10+ spacing , 300, 25, "Quantity");
        
        ArrayList<String> ss= new ArrayList<String>();
        
        for(int i=1;i<=s.qty;i++){
        	ss.add(i+"");
        }
        
        String st[] = new String[ss.size()];
        st= ss.toArray(st);
        
        MComboBox txtQuantity = new MComboBox(48, 35 +spacing, 300, 40,st);


        MLabel lblBookName = new MLabel(55, 10 , 300, 25, "Book Name");
        MTextField txtBookName = new MTextField(48, 35 , 300, 40);
        
        

        MLabel lblDueDate = new MLabel(55, 10+ spacing *2, 300, 25, "Due Date YYYY-MM-DD");
        MTextField txtDueDate = new MTextField(48, 35 +spacing*2, 300, 40);

        leftPanel.add(lblQuantity);
        leftPanel.add(txtQuantity);
        leftPanel.add(lblBookName);
        leftPanel.add(txtBookName);
        leftPanel.add(lblDueDate);
        leftPanel.add(txtDueDate);
        

        MLabel lblBookID = new MLabel(460, 258, 300, 25, "ISBN");
        MTextField txtBookID = new MTextField(453, 282, 300, 40);

        //       the two button ADD and History
        MButton btnHistory = new MButton(453, 282 + spacing * 4, 135, 40, "History");
        MButton btnAdd = new MButton(453 + 165, 282 + spacing * 4, 135, 40, "Borrow");

        btnHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            	ViewBorrow(parent,data);
            }
        });


        frame.add(btnAdd);
        frame.add(btnHistory);
        frame.repaint();
        
        txtBookID.addActionListener(e->{
        	book = data.searchBook(txtBookID.getText());
        	if(book!=null){
        		frame.add(leftPanel).repaint();
        		 txtBookName.setText(book.getTitle());
        		 txtBookName.setEditable(false);
        		 txtDueDate.setText(Borrow.requestDueDate(txtIssueDate.getText(),s.duration));
        		 
        	}
        	else if (book==null && !txtBookID.getText().equals("")){
        		MOptionPane.show(null, "No such book in our library ! ");
        	}
        });
        
        
        txtStudentID.addActionListener(e->{
        	if(!txtStudentID.getText().equals("")){
	        	member = data.searchMember(txtStudentID.getText());
	        	borrowed =data.SearchBorrow(member.getMemberID());
	        	
	          	if(member!=null){
	        		
	        		if( borrowed< limited){
	        			frame.add(rightPanel).repaint();
	            		txtStudentName.setText(member.getName());
	            		txtStudentName.setEditable(false);
	            		txtSex.setText(member.getGender());
	            		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	         				Date c = new Date();
	
	         	        txtIssueDate.setText(sdf.format(c));
	
	            		
	        		}else{
	        			MOptionPane.show(null, "this member has reach the limited number of books to be borrowed !");
	        		}
	        		
	        	}
	          	else if (member==null){
	        		MOptionPane.show(null, "Dont have the member in our library");
	        	}
        	}
        	});
        
        txtIssueDate.addActionListener(e->{
        	txtDueDate.setText(Borrow.requestDueDate(txtIssueDate.getText(),s.duration));
        });
        
        btnAdd.addActionListener(e->{
        	if(txtDueDate.getText().equals("") || txtIssueDate.getText().equals("")){
        		MOptionPane.show(null,"Unable to perform 'borrow' Operation !");
        	}else{
        		int qty=0;
        		try{
        			qty = Integer.parseInt(txtQuantity.getSelectedItem().toString().replace(" ",""));
        			Borrow borrow  = new Borrow(0, book.getBookID(), 
            				member.getMemberID(), 
            				txtIssueDate.getText(), 
            				txtDueDate.getText(),
            				qty
            				);
        			if(borrowed+ qty<limited){
	        			if(data.Insert(borrow)>0 && data.updateBookQty(book.getQty()-qty, book.getBookID())>0){
	        				dialog.dispose();
	        				MOptionPane.show(null,"Insert borrowing transaction to the database");
	        			}
	        			}
        			else{
        				MOptionPane.show(null, "Cannot borrow these much books, as the limited number reached!");
        			}
        			}catch(Exception ex){
        				MOptionPane.show(null, "Invalid input at 'Quantity' ");
        			}
        			
        	}

        });
        
        

        

// will display for the update and add but just different time
        frame.add(title);
        frame.add(image);

        frame.add(lblBookID);
        frame.add(txtBookID);
        frame.add(txtStudentID);
        frame.add(lblStudentID);
        frame.setVisible(true);

        dialog.add(frame);
        dialog.setSize(frame.getSize());
        dialog.setLocationRelativeTo(frame);
        dialog.setModal(true);
        dialog.setVisible(true);


        
    }

    public static void ViewBorrow(JFrame parent, Database data){
        int frameWidth =1000;
        int frameHeight =700;
        int spacing =75;
        int adjust =65;

        JDialog dialog = new JDialog(parent);
        dialog.setSize(new Dimension(frameHeight,frameWidth));
        dialog.setLayout(new BorderLayout(20,10));
        dialog.setTitle("VIEW BORROWING HISTORY" );
        
        
        JPanel frame = new JPanel(new BorderLayout());
        frame.setLayout(null);
        frame.setBackground(Color.WHITE);
        dialog.setSize(frameWidth,frameHeight);

        JPanel topPanel = new JPanel();
        MTextField txtSearch = new MTextField();
        txtSearch.setHint("Searching for......");
       
        MComboBox cbType= new MComboBox(StaticData.SearchBook());
        MButton btnSearch = new MButton("Search");
        
        btnSearch.requestFocus(true);
        
        

        topPanel.setBorder(new EmptyBorder(20,0,5,0));
        topPanel.add(txtSearch,BorderLayout.CENTER);
        topPanel.add(cbType,BorderLayout.CENTER);
        topPanel.add(btnSearch,BorderLayout.CENTER);

        cbType.addItem(" Filter By");
        
        String[] columnNames = {
        			"CARD ID",	
        			"MEMBER NAME",
        			"ISBN",
        			"BOOK TITLE",
        			"AUTHOR",
        			"QTY",
        			"BORROW DATE",
        			"RETURN DATE",
        			};

        	ArrayList<Borrow> list = data.getBorrow();
    		MTable tbl = new MTable(columnNames);
    		for(int i=0;i<list.size();i++)
    			tbl.addRow(list.get(i).GetData());
    		tbl.setAutoResizeWidth();
    		
    	tbl.setBorder(new CompoundBorder(
    			new EmptyBorder (20,20,0,20),
    			new MatteBorder(2,2,2,2,Color.BLACK)
    			));
    	
    	JPanel bottomPanel = new JPanel();
		MButton btnDeleteBook= new MButton("LOST BOOK");
			btnDeleteBook.setFont(new Font("arial", Font.BOLD, 18));
			
	bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	bottomPanel.add(btnDeleteBook);
	btnDeleteBook.addActionListener(e->{
		Borrow bo = list.get(tbl.table.getSelectedRow());
		Book book= data.searchBook(list.get(tbl.table.getSelectedRow()).getIsbn());
		Member member = data.searchMember(list.get(tbl.table.getSelectedRow()).getCardId());
		Return re = new Return(0, book.getBookID(), member.getMemberID(), bo.getBorrowDate(),bo.getReturnDate(), bo.getQty(), FINE_IF_LOST);
		
		if(data.Insert(re)>0 && data.Delete(bo)>0){
			
			MOptionPane.show(null, "Recorded the book is lost");
		}else{
			MOptionPane.show(null, "Cannot connect to the database!");
		}
	});
	bottomPanel.setBorder(new EmptyBorder(20,20,20,20));
		
    	dialog.add(tbl,BorderLayout.CENTER);
        dialog.add(topPanel, BorderLayout.PAGE_START);
        dialog.add(bottomPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }


}
