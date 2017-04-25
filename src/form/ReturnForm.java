package form;
import component.*;
import data.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReturnForm {
	Book book;
	Member member;
	Borrow borrow;
	Return returns;
	
	int borrowID;
    public void ReturnBook(JFrame parent, Database data, Setting s){
        int frameWidth =798;
        int frameHeight =700;
        int spacing =75;


//  i used the JDialog ,because when i use Jframe.setDefaultCloseOperation
//  it will close all the all
        JDialog dialog = new JDialog(parent);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(parent);
        JPanel frame = new JPanel();

//        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setBackground(new Color(236, 236, 236));
        frame.setSize( frameWidth,frameHeight);


        dialog.setTitle("RETURNING BOOK(S)");
        MLabel title = new MLabel(0, 175, frame.getWidth(), 50, "RETURNING BOOK");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(255,255,255));
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

        MLabel lblDueDate = new MLabel(55, 10+spacing*2 , 300, 25, "Issue /Due Date");
        MTextField txtDueDate = new MTextField(48, 35+spacing*2 , 300, 40);
        txtDueDate.setEditable(false);

        MLabel lblFine = new MLabel(55, 10+spacing*3 , 300, 25, "Fine(In Riel)");
        MTextField txtFine = new MTextField(48, 35+spacing*3 , 300, 40);
        txtFine.setEditable(false);

        rightPanel.add(lblStudentName);
        rightPanel.add(txtStudentName);
        rightPanel.add(lblSex);
        rightPanel.add(txtSex);
        rightPanel.add(txtDueDate);
        rightPanel.add(lblDueDate);
        rightPanel.add(lblFine);
        rightPanel.add(txtFine);

        MLabel lblStudentID = new MLabel(55, 258 , 300, 25, "Student ID");
        MTextField txtStudentID = new MTextField(48, 282 , 300, 40);

        
        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLocation(frameWidth/2,images.getIconHeight()+80);
        leftPanel.setSize(frameWidth/2,frameHeight-images.getIconHeight()-200);
        leftPanel.setLayout(null);

        MLabel lblQuantity = new MLabel(55, 10+ spacing , 300, 25, "Quantity");
        MTextField txtQuantity = new MTextField(48, 35 +spacing, 300, 40);
        

        MLabel lblBookName = new MLabel(55, 10 , 300, 25, "Book Name");
        MTextField txtBookName = new MTextField(48, 35 , 300, 40);
        txtBookName.setEditable(false);

        MLabel lblReturnDate = new MLabel(55, 10+ spacing *2, 300, 25, "Return YYYY-MM-DD");
        
        MTextField txtReturnDate = new MTextField(48, 35 +spacing*2, 300, 40);
        SimpleDateFormat ssdf = new SimpleDateFormat("yyyy-MM-dd");
		Date cc = new Date();
        txtReturnDate.setText(ssdf.format(cc));

        leftPanel.add(lblQuantity);
        leftPanel.add(txtQuantity);
        leftPanel.add(lblBookName);
        leftPanel.add(txtBookName);
        leftPanel.add(lblReturnDate);
        leftPanel.add(txtReturnDate);

        MLabel lblBookID = new MLabel(460, 258, 300, 25, "ISBN");
        MTextField txtBookID = new MTextField(453, 282, 300, 40);

//       the two button ADD and CANCEL
            MButton btnHistory = new MButton(453, 282 + spacing * 4, 135, 40, "History");
            MButton btnAdd = new MButton(453 + 165, 282 + spacing * 4, 135, 40, "Return");

            btnHistory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	dialog.dispose();
                	ViewReturn(parent,data);
                }
            });


            frame.add(btnAdd);
            frame.add(btnHistory);
            frame.repaint();

            txtBookID.addActionListener(e->{
            	
            });
            
            
            txtBookID.addActionListener(e->{
            	member = data.searchMember(txtStudentID.getText());
            	if(member!=null){
            		frame.add(rightPanel);
            		book = data.searchBook(txtBookID.getText());
            		
                	if(book!=null){
                		
            			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            			Calendar c = Calendar.getInstance();
            			
                		borrow =data.searchBorrow(member, book);
                		
                		
                		if(borrow!=null){
                			
	                		txtStudentName.setText(member.getName());
	                		txtSex.setText(member.getGender());
	                		txtDueDate.setText(borrow.getReturnDate());
	                		
	                		txtBookName.setText(borrow.getBookTitle());
	                		txtQuantity.setText(borrow.getQty()+"");
	                		txtReturnDate.setText(sdf.format(c.getTime()));
	                		txtFine.setText((DiffDay(borrow.getReturnDate(),txtReturnDate.getText())*s.fine)+"");
	                		frame.add(leftPanel).repaint();
                		}
                		else{
                			MOptionPane.show(null, "This user didn't borrow this book");
                		}
                	}
                	else if (book==null && !txtBookID.getText().equals("")){
                		MOptionPane.show(null, "Incorrect ISBN !");
                	}
            	}else{
            		MOptionPane.show(null, "You may type the student ID wrong !");	
            	}
            	
             });
            
            btnAdd.addActionListener(e->{
            	member = data.searchMember(txtStudentID.getText());
            	book = data.searchBook(txtBookID.getText());
            	borrow =data.searchBorrow(member, book);
            	if(borrow==null){
            		MOptionPane.show(null, "borrow is null");
            	}
            	if(borrow!=null){
            		
	            	data.updateBookQty(book.getQty()+Integer.parseInt(txtQuantity.getText()), book.getBookID());
	            	data.Insert(new Return(0, book.getBookID(), member.getMemberID(), borrow.getBorrowDate(), borrow.getReturnDate(), Integer.parseInt(txtQuantity.getText()), Double.parseDouble(txtFine.getText())));
	            	if(borrow.getQty()>Integer.parseInt(txtQuantity.getText())){
	            		borrow.setQty(borrow.getQty()-Integer.parseInt(txtQuantity.getText()));
	            		if(data.Update(borrow)>0){
	            			MOptionPane.show(null, "The book is returned!");
	            		}else{
	            			MOptionPane.show(null, "Cannot connect to the database!");
	            		}
	            	}
	            	else{
	            		MOptionPane.show(null, borrow.getBorrowID()+"");
	            		if(data.Delete(borrow)>0){
	            			MOptionPane.show(null, "The book is returned!");
	            		}else{
	            			MOptionPane.show(null, "Cannot connect to the database!");
	            		}
	            		}
            	}
            	
            });
            

            
// will display for the update and add but just different time
        
        frame.add(title);
        frame.add(rightPanel);
        frame.add(leftPanel);
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


        
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
                dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
            }
        });
    }

    public static void ViewReturn(JFrame parent, Database data){
        int frameWidth =1000;
        int frameHeight =700;
        int spacing =75;
        int adjust =65;

        JDialog dialog = new JDialog(parent);
        dialog.setSize(new Dimension(frameHeight,frameWidth));
        dialog.setLayout(new BorderLayout(20,10));
        dialog.setTitle("VIEW RETURNING HISTORY" );
        
        
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
        			"CARD-ID",	
        			"MEMBER",
        			"ISBN",
        			"BOOK",
        			"AUTHOR",
        			"QTY",
        			"BORROW",
        			"RETURN",
        			"FINE"
        			};

        	ArrayList<Return> list = data.getReturn();
    		MTable tbl = new MTable(columnNames);
    		for(int i=0;i<list.size();i++){
    			tbl.addRow(list.get(i).GetDatas());
    		}
    		tbl.setAutoResizeWidth();
    		
    	tbl.setBorder(new CompoundBorder(
    			new EmptyBorder (20,20,20,20),
    			new MatteBorder(2,2,2,2,Color.BLACK)
    			));
    	
    	
    	
    	
    	dialog.add(tbl,BorderLayout.CENTER);
        dialog.add(topPanel, BorderLayout.PAGE_START);
        dialog.setVisible(true);
    }

    
    public static long DiffDay(String dateStart, String dateStop){
        	
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        	LocalDateTime dateTime1= LocalDateTime.parse(dateStart+" 19:00:00", formatter);
        	LocalDateTime dateTime2= LocalDateTime.parse(dateStop+" 19:00:00", formatter);
        	return java.time.Duration.between(dateTime1, dateTime2).toDays();
        	
    	}

}
