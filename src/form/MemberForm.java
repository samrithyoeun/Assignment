package form;


import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import component.*;
import data.Book;
import data.Database;
import data.Member;
import data.StaticData;

public class MemberForm {
	static ArrayList<Member> list;
	static ArrayList<String[]> found;
    public void addMember(JFrame parent, Database database, Member m){
        int frameWidth =798;
        int frameHeight =700;
        int spacing =75;
        int adjust = 65;
 
        JDialog dialog = new JDialog(parent);
        dialog.setResizable(false);
        JPanel frame = new JPanel();

        frame.setLayout(null);
        frame.setBackground(new Color(236, 236, 236));
        frame.setSize( frameWidth,frameHeight);

        dialog.setTitle("ADDING NEW MEMBER(S)");
        MLabel title = new MLabel(0, 175, frame.getWidth(), 50, "ADDING NEW MEMBER(S)");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(248, 249, 250));
        title.setFont(new Font("Consolas", Font.BOLD, 35));

        ImageIcon images = new ImageIcon("src/picture/adds.png");
        MLabel image = new MLabel(0, 0, images.getIconWidth(), images.getIconHeight(), images);


        //Left side MemberName=International Standard Book Number, Book Name, RegisterDate, Mobile, Quantity
        MLabel lblMemberName = new MLabel(55, 258, 705, 25, "Member Name");
        MTextField txtMemberName = new MTextField(48, 282, 705, 40);


        MLabel lblMemberID = new MLabel(55, 258 + spacing, 300, 25, "Member ID");
        MTextField txtMemberID = new MTextField(48, 282 + spacing, 300, 40);

        MLabel lblRegisterDate = new MLabel(55, 258 + spacing * 2, 300, 25, "Register (YYYY-MM-DD)");
        MTextField txtRegisterDate = new MTextField(48, 282 + spacing * 2, 300, 40);

        MLabel lblMobile = new MLabel(55, 258 + spacing * 3, 300, 25, "Mobile");
        MTextField txtMobile = new MTextField(48, 282 + spacing * 3, 300, 40);

        MLabel lblEmail = new MLabel(55, 258 + spacing * 4, 300, 25, "Email");
        MTextField txtEmail = new MTextField(48, 282 + spacing * 4, 300, 40);



        MLabel lblGender = new MLabel(460, 258 + spacing, 300, 25, "Gender");
        MComboBox txtGender = new MComboBox(453, 282 + spacing, 300, 40,new String[]{" Male"," Female"});

        MLabel lblAddress = new MLabel(460, 258 + spacing * 2, 300, 25, "Address");
        MTextArea txtAddress = new MTextArea(453, 282 + spacing * 2, 300, 120);

        MLabel lblYear = new MLabel(460, 258 + spacing * 3, 300, 25, "Year\\Edition");
        MTextField txtYear = new MTextField(453, 282 + spacing * 3, 300, 40);

        frame.setLocation(MFrame.CenterScreen(frame));


            MLabel lblSearch = new MLabel(55 + adjust, 20, 500, 25, "Enter the MemberID");
            MTextField txtSearch = new MTextField(48 + adjust, 51, 400, 40);

            MButton btnSearch = new MButton(470 + adjust, 51, 135, 40, "Search");
//    the two button




//       the two button ADD and CANCEL
            MButton btnCancel = new MButton(453, 282 + spacing * 4, 135, 40, "Cancel");
            MButton btnAdd = new MButton(453 + 165, 282 + spacing * 4, 135, 40, "Add");

            frame.add(btnAdd);
            frame.add(btnCancel);
            frame.repaint();
        
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Date c = new Date();
    		txtRegisterDate.setText(sdf.format(c));
    		
            if(parent ==null){
                dialog.setTitle("UPDATING MEMBER(S)");
                title.setText("UPDATE THE MEMBER(S)");
                
                txtMemberName.setText(m.getName());
                txtMemberID.setText(m.getCardID());
                txtGender.setSelectedItem(m.getGender());
                txtRegisterDate.setText(m.getRegisterDate());
                txtRegisterDate.setEditable(false);
                txtMobile.setText(m.getMobile());
                txtEmail.setText(m.getEmail());
                txtAddress.setText(m.getAddress());
                btnAdd.setText("Update");
                    
                    }
            
            btnAdd.addActionListener(e->{
                
                Member member =new Member(0,
                        txtMemberName.getText(),
                        txtMemberID.getText(),
                        txtGender.getSelectedItem().toString(),
                        txtRegisterDate.getText(),
                        txtRegisterDate.getText(),
                        txtMobile.getText(),
                        txtEmail.getText(),
                        txtAddress.getText());
                if(btnAdd.getText().equals("Add")){
                if(database.Insert(member)>0){
                    MOptionPane.show(null, "Inserted 1 row to the Database ! ");
                    txtMemberName.setText("");
                    txtMemberID.setText("");
                    txtGender.setSelectedIndex(0);
                    txtRegisterDate.setText("");
                    txtMobile.setText("");
                    txtEmail.setText("");
                    txtAddress.setText("");
                }else{
                    MOptionPane.show(null,"Cannot do the Insert Operation");
                };
                }
                else{
                    member.setMemberID(m.getMemberID());
                     if(database.Update(member)>0){
                            MOptionPane.show(null,"Update 1 rows successfully! ");
                            dialog.dispose();
                            SearchMember(null, database);
                            
                        }else{
                            MOptionPane.show(null,"Unable to do the update Operation");
                        }
                }
                
            });
            
// will display for the update and add but just different time
        frame.add(title);
        frame.add(image);

        frame.add(lblGender);
        frame.add(txtGender);
        frame.add(lblAddress);
        frame.add(txtAddress);
        frame.add(lblYear);
        frame.add(txtYear);
        frame.add(lblMemberName);
        frame.add(txtMobile);
        frame.add(lblMobile);
        frame.add(lblMemberID);
        frame.add(txtRegisterDate);
        frame.add(lblRegisterDate);
        frame.add(txtMemberID);
        frame.add(lblMemberID);
        frame.add(txtMemberName);
        frame.add(lblEmail);
        frame.add(txtEmail);

        frame.setVisible(true);

        dialog.add(frame);
        dialog.setSize(frame.getSize());
        dialog.setLocationRelativeTo(frame);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
    

    public static void SearchMember(JFrame parent, Database database){
        int frameWidth =1000;
        int frameHeight =700;
        int spacing =75;
        int adjust =65;

        JDialog dialog = new JDialog(parent);
        dialog.setSize(new Dimension(frameHeight,frameWidth));
        dialog.setLayout(new BorderLayout(20,10));
        dialog.setTitle("SEARCH/ UPDATE/ DELETE MEMBER(S)" );
        dialog.setSize(frameWidth,frameHeight);

        JPanel frame = new JPanel(new BorderLayout());
        frame.setLayout(null);
        frame.setBackground(Color.WHITE);
        

        JPanel topPanel = new JPanel();
        MTextField txtSearch = new MTextField();
        txtSearch.setHint("Search for ....");
        MComboBox cbType= new MComboBox(StaticData.SearchMember());
        MButton btnSearch = new MButton("Search");

        topPanel.setBorder(new EmptyBorder(20,0,5,0));
        topPanel.add(txtSearch,BorderLayout.CENTER);
        topPanel.add(cbType,BorderLayout.CENTER);
        topPanel.add(btnSearch,BorderLayout.CENTER);

        
        
        String[] columnNames = {
                "No.",
                "NAME",
                "CARD-ID",
                "SEX",
                "REGISTER",
                "EXPIRE",
                "PHONE",
                "EMAIL",
                "ADDRESS"};

        MTable tbl = new MTable(columnNames);
        list = new ArrayList<Member>();
        list = database.getMember();
        for(int i =0;i<list.size();i++){
            
            tbl.addRow(list.get(i).GetData());
            tbl.model.setValueAt(" "+(i+1)+" ", i,0);
            
        }
        tbl.setAutoResizeWidth();
            
        tbl.setBorder(new CompoundBorder(
                new EmptyBorder (20,20,0,20),
                new MatteBorder(2,2,2,2,Color.BLACK)
                ));
        /*
	        " Filter By: ",
			" Name",
			" ID",
			" Gender",
			" Register Date",
			" Expire Date"
		*/
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
        	
        	
        	
        	found.clear();
        	
        	switch(cbType.getSelectedIndex()){
            case 0:
                MOptionPane.show(null,"Please select one of the item in the Combobox");
                break;
            case 1:
                //search by name
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getName().contains(txtSearch.getText()))
                        found.add(list.get(i).GetData());
                }
                break;
            case 2:
                //search by id
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getCardID().contains(txtSearch.getText()))
                        found.add(list.get(i).GetData());
                    }
                
                break;
            case 3:
                //search by gender
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getGender().contains(txtSearch.getText()))
                        found.add(list.get(i).GetData());
                }
                break;
            case 4:
                //search by register date
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getRegisterDate().contains(txtSearch.getText()))
                        found.add(list.get(i).GetData());
                }
                break;
            case 5:
                //search by expire date
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getExpireDate().contains(txtSearch.getText()))
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
        JPanel bottomPanel = new JPanel();
            MButton btnUpdate= new MButton("UPDATE");
                btnUpdate.setFont(new Font("arial", Font.BOLD, 18));
            MButton btnDelete= new MButton("DELETE");
                btnDelete.setFont(new Font("arial", Font.BOLD, 18));
        
                
            btnUpdate.addActionListener(e->{
            	MemberForm member = new MemberForm();
        		int row =tbl.table.getSelectedRow();
        		if(row<0){
        			MOptionPane.show(null, "No row is Selected");
        		}else{
        			dialog.dispose();
        			member.addMember(null,database,list.get(row));	
        			
        		}
            });  
            
            btnDelete.addActionListener(e->{
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
                
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnUpdate);
        bottomPanel.add(btnDelete);
        bottomPanel.setBorder(new EmptyBorder(20,20,20,20));
        
        dialog.add(tbl,BorderLayout.CENTER);
        dialog.add(bottomPanel,BorderLayout.SOUTH);
        dialog.add(topPanel, BorderLayout.PAGE_START);
        dialog.setVisible(true);
    }

    public static String ExpireDate(String dt){
    	dt = dt.replace("/","-").replace("\\","-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 365);  // number of days to add
		dt = sdf.format(c.getTime());  // dt is now the new date
		return dt;
    }
    
}
