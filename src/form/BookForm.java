package form;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import org.jdesktop.swingx.prompt.PromptSupport;

import component.*;
import data.Book;
import data.Database;
import data.StaticData;

import java.awt.*;
import java.util.ArrayList;

public class BookForm {
	public MTextField txtBookName;
	public MTextField txtISBN;
	public MTextField txtAccessionCode;
	public MTextField txtLanguage;
	public MTextField txtQuantity;
	public MComboBox txtCategory;
	public MTextField txtAuthor;
	public MTextField txtYear;
	
	static ArrayList<Book> list;
	static ArrayList<String[]> found;
	
	
	public void AddBooks(JFrame parent, Database data, Book b){
        int frameWidth =798;
        int frameHeight =700;
        int spacing =75;
      
        JDialog dialog = new JDialog(parent);
        dialog.setResizable(false);
        dialog.setTitle("ADDING NEW BOOK(S)");
        JPanel frame = new JPanel();
        
        frame.setLayout(null);
        frame.setBackground(new Color(236, 236, 236));
        frame.setSize(frameWidth,frameHeight);

        MLabel title = new MLabel(0, 175, frame.getWidth(), 50, "ADDING NEW BOOK(S)");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(248, 249, 250));
        title.setFont(new Font("Consolas", Font.BOLD, 35));

        ImageIcon images = new ImageIcon("src/picture/adds.png");
        MLabel image = new MLabel(0, 0, images.getIconWidth(), images.getIconHeight(), images);

        MLabel lblBookName = new MLabel(55, 258, 705, 25, "Book Title");
        txtBookName = new MTextField(48, 282, 705, 40);
        

        MLabel lblISBN = new MLabel(55, 258 + spacing, 300, 25, "ISBN");
         txtISBN = new MTextField(48, 282 + spacing, 300, 40);

        MLabel lblAccessionCode = new MLabel(55, 258 + spacing * 2, 300, 25, "Accession Code");
         txtAccessionCode = new MTextField(48, 282 + spacing * 2, 300, 40);

        MLabel lblLanguage = new MLabel(55, 258 + spacing * 3, 300, 25, "Language");
         txtLanguage = new MTextField(48, 282 + spacing * 3, 300, 40);

        MLabel lblQuantity = new MLabel(55, 258 + spacing * 4, 300, 25, "Quantity");
         txtQuantity = new MTextField(48, 282 + spacing * 4, 300, 40);



        MLabel lblCategory = new MLabel(460, 258 + spacing, 300, 25, "Category");
        txtCategory = new MComboBox(453, 284 + spacing, 300, 40,StaticData.BookCategories());

        MLabel lblAuthor = new MLabel(460, 258 + spacing * 2, 300, 25, "Author");
        txtAuthor = new MTextField(453, 284 + spacing * 2, 300, 40);

        MLabel lblYear = new MLabel(460, 258 + spacing * 3, 300, 25, "Year\\Edition");
        txtYear = new MTextField(453, 282 + spacing * 3, 300, 40);

        frame.setLocation(MFrame.CenterScreen(frame));

 
//       the two button ADD and CANCEL
         MButton btnCancel = new MButton(453, 282 + spacing * 4, 135, 40, "Cancel");
         MButton btnAdd = new MButton(453 + 165, 282 + spacing * 4, 135, 40, "Add");
         
         if(parent ==null){
          	dialog.setTitle("UPDATING BOOK(S)");
          	title.setText("UPDATE THE BOOK(S)");
          	btnAdd.setText("Update");
          	
          	txtBookName.setText(b.getTitle());
          	txtQuantity.setText(b.getQty()+"");
          	txtCategory.setSelectedItem(b.getCategory());
          	txtAuthor.setText(b.getAuthor());
          	txtYear.setText(b.getEdition());
          	txtLanguage.setText(b.getLanguage());
          	txtAccessionCode.setText(b.getAccesscode());
          	txtISBN.setText(b.getIsbn());
  		        }
  
         btnAdd.addActionListener(e->{
        	 
        	 
        	 int qty=0;
        	 try{
    			 qty = Integer.parseInt(txtQuantity.getText()) ;
    		 }catch(Exception ex){
    			 MOptionPane.showMessageDialog(dialog, "Invalid Input at Integer");
    		 }
        	 Book book = new Book(
			        		 0,//id
			        		 txtBookName.getText(),
			        		 txtISBN.getText(),
			        		 txtCategory.getSelectedItem().toString(),
			        		 txtAccessionCode.getText(),
			        		 
			        		 txtAuthor.getText(),
			        		 txtLanguage.getText(),
			        		 txtYear.getText(),
			        		 qty
			        	 );
        	 if(btnAdd.getText().equals("Add")){
        		 if(!txtBookName.getText().equals("")|| !txtISBN.getText().equals("")){
        	 			if(data.Insert(book)>0){
        	 				MOptionPane.show(null, "Insert 1 rows to Database ! ");
        	 				txtBookName.setText("");
                            txtISBN.setText("");
                            txtAccessionCode.setText("");
                            txtCategory.setSelectedIndex(0);
                            txtAuthor.setText("");
                           
                            txtLanguage.setText("");
                            txtYear.setText("");
                            txtQuantity.setText("");
        	 			}
        	 			else{
        	 				MOptionPane.show(null, "Cannot do the Insert Operation !");
        	 			}
        	 			}
        		 }
        	 else if(btnAdd.getText().equals("Update")){
        		 data.connect();
        		 book.setBookID(b.getBookID());
        		 if(data.Update(book)>0){
        		 		MOptionPane.show(null,"Update 1 rows successfully! ");
        		 		dialog.dispose();
        		 		SearchBook(null, data);
        		 		
        		 	}else{
        		 		MOptionPane.show(null,"Unable to do the update Operation");
        		 	}
        		 
        	 }
         });
         
         btnCancel.addActionListener(e->{
        	 dialog.dispose();
         });
         
             frame.add(btnAdd);
             frame.add(btnCancel);
         
            
        frame.add(title);
        frame.add(image);

        frame.add(lblCategory);
        frame.add(txtQuantity);
        frame.add(lblQuantity);
        frame.add(txtCategory);
        frame.add(lblAuthor);
        frame.add(txtAuthor);
        frame.add(lblYear);
        frame.add(txtYear);
        frame.add(lblBookName);
        frame.add(txtLanguage);
        frame.add(lblLanguage);
        frame.add(lblISBN);
        frame.add(txtAccessionCode);
        frame.add(lblAccessionCode);
        frame.add(txtISBN);
        frame.add(lblISBN);
        frame.add(txtBookName);

        frame.setVisible(true);
        dialog.add(frame);
        dialog.setSize(frame.getSize());
        dialog.setLocationRelativeTo(frame);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
	
    @SuppressWarnings("deprecation")
	public static void SearchBook(JFrame parent,Database database){
        int frameWidth =1000;
        int frameHeight =700;
        int spacing =75;
        int adjust =65;

        JDialog dialog = new JDialog(parent);
        dialog.setSize(new Dimension(frameHeight,frameWidth));
        dialog.setLayout(new BorderLayout(20,10));
        dialog.setTitle("SEARCH/ UPDATE/ DELETE BOOK(S)" );
        
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
        			"No",
        			"ISBN",
        			"TITLE",
        			"ACCESS CODE",
        			"CATEGORY",
        			"AUTHOR",
        			"LANGUAGE",
        			"EDITION",
        			"QTY"};

        
        MTable tbl = new MTable(columnNames);
        list = new ArrayList<Book>();
        list = database.getBooks();
        for(int i =0;i<list.size();i++){
        	
        	tbl.addRow(list.get(i).GetData());
        	tbl.model.setValueAt(" "+(i+1), i,0);
        	
        }
        tbl.setAutoResizeWidth();
       
    	tbl.setBorder(new CompoundBorder(
    			new EmptyBorder (20,20,0,20),
    			new MatteBorder(2,2,2,2,Color.BLACK)
    			));
    	
    	
    	
    	JPanel bottomPanel = new JPanel();
    		MButton btnUpdateBook= new MButton("UPDATE");
    			btnUpdateBook.setFont(new Font("arial", Font.BOLD, 18));
    		MButton btnDeleteBook= new MButton("DELETE");
    			btnDeleteBook.setFont(new Font("arial", Font.BOLD, 18));
    			
    	bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	bottomPanel.add(btnUpdateBook);
    	bottomPanel.add(btnDeleteBook);
    	bottomPanel.setBorder(new EmptyBorder(20,20,20,20));
    	
    	found = new ArrayList<String[]>();
        btnSearch.addActionListener(e->{
        	if(btnSearch.getText().equals("Show All")){
        		tbl.removeAll();
        		for(int i =0;i<list.size();i++){
                    tbl.addRow(list.get(i).GetData());
                    tbl.model.setValueAt(" "+(i+1)+" ",i,0);   
                    btnSearch.setText("Search");
                }
        	}else{
        	if(txtSearch.getText().equals("")){
        		MOptionPane.show(null, "Please type something in order to start searching !");
        	}
        	
        	/*
				" Title",
				" ISBN",
				" Category",
				" Author"
        	*/
        	found.clear();
        	
        	switch(cbType.getSelectedIndex()){
            case 0:
                MOptionPane.show(null,"Please select one of the item in the Combobox");
                break;
            case 1:
                //search by title
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getTitle().contains(txtSearch.getText()))
                        found.add(list.get(i).GetData());
                }
                break;
            case 2:
                //search by ISBN
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getIsbn().contains(txtSearch.getText()))
                        found.add(list.get(i).GetData());
                    }
                
                break;
            case 3:
                //search by Category
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getCategory().contains(txtSearch.getText()))
                        found.add(list.get(i).GetData());
                }
                break;
            case 4:
                //search by Author
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getAuthor().contains(txtSearch.getText()))
                        found.add(list.get(i).GetData());
                }
                break;
            
        }
        	
        	if(found.size()>0){
	        	tbl.removeAll();
	        	tbl.addRow(found);
	        	btnSearch.setText("Show All");
        	}else{
        		if(cbType.getSelectedIndex()!=0)
        			MOptionPane.show(null, "NOTHING FOUND !");
        	}
        		
        }	
        });
    	
    	btnUpdateBook.addActionListener(e->{
    		BookForm book = new BookForm();
    		int row =tbl.table.getSelectedRow();
    		if(row<0){
    			MOptionPane.show(null, "No row is Selected");
    		}else{
    			dialog.dispose();
    			book.AddBooks(null,database,list.get(row));	
    			
    		}
    	});
    	
	    btnDeleteBook.addActionListener(e->{
	    	int row =tbl.table.getSelectedRow();
    		if(row<0){
    			MOptionPane.show(null, "No row is Selected");
    		}else{
    			if(database.Delete(list.get(row))>0){
    				MOptionPane.show(null, "Deleted 1 row! ");;
    				tbl.model.removeRow(row);
    				}
    			else
    				MOptionPane.show(null,"Unable to delete this row due to the database operation error!");
    			
    		}
	    });
    	
    	dialog.add(tbl,BorderLayout.CENTER);
    	dialog.add(bottomPanel,BorderLayout.SOUTH);
        dialog.add(topPanel, BorderLayout.PAGE_START);
        dialog.setVisible(true);
    }
}
