package data;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import component.MOptionPane;

public class Database {
	Connection connection;
	PreparedStatement ps;
	Statement stm;
	ResultSet rss;
	
	static int limitedBook;
	
	public static void main(String arg[]){
		Database data = new Database();
		Member member =new Member(4,"YOEUN SAMRITH","1292","Male","2017-05-05","2018-05-05","010532524","samrith.yoeun@gmail.com","Chbar Ampov, Phnom penh" );
		Book book = new Book(10,"C# programming","22","A201","samrith yoeun","hell","english","2nd edition",10);
		Borrow borrow = new Borrow(0,1,1,"2017-04-03","2017-04-10",1);
		Return returns = new Return(0,1,1,"2017-04-03","2017-04-10",1,0.3);
		
		Member member1 =new Member(1,"XXYOEUN SAMRITH","12345678","Male","2017-05-05","2018-05-05","010532524","samrith.yoeun@gmail.com","Chbar Ampov, Phnom penh" );
		Book book1 = new Book(1,"XXC# programming","234568780","A201","samrith yoeun","hell","english","2nd edition",10);
		Borrow borrow1 = new Borrow(1,1,1,"2017-04-03","2017-04-10",33);
		Return returns1 = new Return(1,1,1,"2017-04-03","2017-04-10",33,0.3);
		
		data.connect();
		System.out.print(data.getMemberCount().get(1).qty+"");	
	}
	
	public boolean connect(){

		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/assignmentjava","root","");
			return true;
		}catch(Exception e){
			MOptionPane.showMessageDialog(null,"Sorry! There is database connection problem. Make sure your server is reachable !");
			return false;
		}
		
	}

	public MemberCount getMoneyEarned(){
		MemberCount m = null;
		try{
			
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT SUM(Fine) AS q FROM returns WHERE MONTH(ReturnDate )>= MONTH(CURDATE())";
			rss =stm.executeQuery(sql);			
			while(rss.next()){
				m = new MemberCount(
							rss.getDouble("q"),
							"Return Earned"
						);				
				
			}
			rss.close();
			
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		return m; 

	}
	public ArrayList<MemberCount> getNewMemberCount(){
		ArrayList<MemberCount> list = new ArrayList<MemberCount>();
		try{
			
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT count(MemberID) AS q FROM members WHERE MONTH(RegisterDate )>= MONTH(CURDATE()) && Gender = 'Male'";
			rss =stm.executeQuery(sql);			
			while(rss.next()){
				list.add(new MemberCount(
							rss.getInt("q"),
							"New Male Members"
						));				
				
			}
			rss.close();
			
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sqlq = "SELECT count(MemberID) AS q FROM members WHERE MONTH(RegisterDate )>= MONTH(CURDATE()) && Gender = 'Female'";
			rss =stm.executeQuery(sqlq);			
			while(rss.next()){
				list.add(new MemberCount(
							rss.getInt("q"),
							"New Female Members"
						));				
				
			}
			
			list.add(new MemberCount(list.get(0).qty+list.get(1).qty,"Total New Members"));
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		return list; 

	} 

	public ArrayList<MemberCount> getMemberCount(){
		ArrayList<MemberCount> list = new ArrayList<MemberCount>();
		try{
			
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT count(MemberID) AS q FROM members WHERE Gender = 'Male'";
			rss =stm.executeQuery(sql);			
			while(rss.next()){
				list.add(new MemberCount(
							rss.getInt("q"),
							"Male Members"
						));				
				
			}
			rss.close();
			
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sqlq = "SELECT count(MemberID) AS q FROM members WHERE Gender = 'Female'";
			rss =stm.executeQuery(sqlq);			
			while(rss.next()){
				list.add(new MemberCount(
							rss.getInt("q"),
							"Female Members"
						));				
				
			}
			
			list.add(new MemberCount(list.get(0).qty+list.get(1).qty,"Total Members"));
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		return list; 

	} 

	public ArrayList<BookCount> getCountBook(){
		ArrayList<BookCount> list = new ArrayList<BookCount>();  
		try{
			
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT SUM(Qty) As q , Category FROM books GROUP BY (Category)";
			rss =stm.executeQuery(sql);			
			while(rss.next()){
				list.add(new BookCount(
							rss.getInt("q"),
							rss.getString("Category")
						));				
				
			}
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		return list; 
	}
	 public Setting getSetting(){
		 Setting s =null;	
		 
		 String sql = "Select * from settings";
			try{
				stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rss =stm.executeQuery(sql);			
				while(rss.next()){
					 s = new Setting( 
							rss.getInt("qty"),
							rss.getInt("duration"),
							rss.getDouble("fine")
							);
			
			}
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		 return s;

		 
	 } 
	 public int Insert(Object object){
		 int rows = 0;
			try {				
				 PreparedStatement stmt = null;
				 String sql;
				 if(connection==null) return 0;
				 
			     
				 if (object.getClass()==Book.class){
					 Book book = Book.class.cast(object);
					 sql = "INSERT INTO books VALUES (?,?,?,?,?,?,?,?,?)";
				      stmt = connection.prepareStatement(sql);
				      stmt.setInt(1, 0);
				      stmt.setString(2,book.getTitle());
				      stmt.setString(3,book.getIsbn());
				      stmt.setString(4,book.getCategory());
				      stmt.setString(5,book.getAccesscode());
				      stmt.setString(6,book.getAuthor());
				      stmt.setString(7,book.getLanguage());
				      stmt.setString(8,book.getEdition());
				      stmt.setInt(9, book.getQty());
				     
			     }
				 
				 else if(object.getClass()==Member.class){
					 Member member = (Member)object;
					  sql = "INSERT INTO members VALUES (?,?,?,?,?,?,?,?,?)";
					  stmt = connection.prepareStatement(sql);
				      stmt.setInt(1, 0);
				      stmt.setString(2,member.getName());
				      stmt.setString(3,member.getCardID());
				      stmt.setString(4,member.getGender());
				      stmt.setString(5,member.getRegisterDate());
				      stmt.setString(6,member.getExpireDate());
				      stmt.setString(7,member.getMobile());
				   	  stmt.setString(8,member.getEmail());
				   	  stmt.setString(9, member.getAddress());
				 }
				 
				 else if (object.getClass()==Return.class){
					 Return returns = (Return)object;
                     sql = "INSERT INTO returns VALUES (?,?,?,?,?,?,?)";
                       stmt = connection.prepareStatement(sql);
                       stmt.setInt(1, 0);
                       stmt.setInt(2,returns.getBookID());
                       stmt.setInt(3,returns.getMemberID());
                       stmt.setString(4,returns.getBorrowDate());
                       stmt.setString(5,returns.getReturnDate());
                       stmt.setInt(6,returns.getQty());
                       stmt.setDouble(7,returns.getFine());
				 }
				 
				 else if (object.getClass()==Borrow.class){
	                    Borrow borrow = (Borrow)object;
	                    sql = "INSERT INTO borrows VALUES (?,?,?,?,?,?)";
	                      stmt = connection.prepareStatement(sql);
	                      stmt.setInt(1, 0);
	                      stmt.setInt(2,borrow.getBookID());
	                      stmt.setInt(3,borrow.getMemberID());
	                      stmt.setString(4,borrow.getBorrowDate());
	                      stmt.setString(5,borrow.getReturnDate());
	                      stmt.setInt(6,borrow.getQty());        
				 }
				 
				  
			      rows = stmt.executeUpdate();
			      
			      
				 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rows;
	    }
	    
	 public int Update(Object object){
		int rows=0;	
		 try {				
				 PreparedStatement stmt = null;
				 String sql;
				 if(connection==null) return 0;
			     
				 if (object.getClass()==Book.class){
					 Book book = (Book)object;
					 sql = "UPDATE books SET Title =?, Isbn=?, Category=?, Accesscode=?, Author=?, Languages=?, Edition=?, Qty=? WHERE BookID =?";
				      stmt = connection.prepareStatement(sql);
				      stmt.setString(1,book.getTitle());
				      stmt.setString(2,book.getIsbn());
				      stmt.setString(3,book.getCategory());
				      stmt.setString(4,book.getAccesscode());
				      stmt.setString(5,book.getAuthor());
				      stmt.setString(6,book.getLanguage());
				      stmt.setString(7,book.getEdition());
				      stmt.setInt(8, book.getQty());
				      stmt.setInt(9, book.getBookID());
				     
			     }
				 
				 else if(object.getClass()==Member.class){
					 Member member = (Member)object;
					  sql = "UPDATE members SET Name=?, CardID=?, Gender=?, RegisterDate=?, ExpireDate=?, Mobile=?, Email=?,Address=? WHERE MemberID=?"; 
				      stmt = connection.prepareStatement(sql);
				      
				      stmt.setString(1,member.getName());
				      stmt.setString(2,member.getCardID());
				      stmt.setString(3,member.getGender());
				      stmt.setString(4,member.getRegisterDate());
				      stmt.setString(5,member.getExpireDate());
				      stmt.setString(6,member.getMobile());
				   	  stmt.setString(7,member.getEmail());
				   	  stmt.setString(8, member.getAddress());
				   	  stmt.setInt(9, member.getMemberID());
				 }
				 
				 else if (object.getClass()==Return.class){
					 Return returns = (Return)object;
                    sql = "UPDATE returns SET BookID=?, MemberID=?, BorrowDate=?, ReturnDate=?, Qty=?, Fine=? WHERE ReturnID=?";
                      stmt = connection.prepareStatement(sql);
                      stmt.setInt(1,returns.getBookID());
                      stmt.setInt(2,returns.getMemberID());
                      stmt.setString(3,returns.getBorrowDate());
                      stmt.setString(4,returns.getReturnDate());
                      stmt.setInt(5,returns.getQty());
                      stmt.setDouble(6,returns.getFine());
                      stmt.setInt(7,returns.getReturnID());
				 }
				 
				 else if (object.getClass()==Borrow.class){
	                    Borrow borrow = (Borrow)object;
	                    sql = "UPDATE borrows SET BookID=?, MemberID=?, BorrowDate=?, ReturnDate=?, Qty=? WHERE BorrowID=?";
	                      stmt = connection.prepareStatement(sql);
	                      stmt.setInt(1,borrow.getBookID());
	                      stmt.setInt(2,borrow.getMemberID());
	                      stmt.setString(3,borrow.getBorrowDate());
	                      stmt.setString(4,borrow.getReturnDate());
	                      stmt.setInt(5,borrow.getQty());
	                      stmt.setInt(6, borrow.getBorrowID());
				 }
				 else if (object.getClass()==Setting.class){
	                    Setting s = (Setting)object;
	                    sql = "UPDATE settings SET qty=?, duration=?, fine=? WHERE sid=?";
	                      stmt = connection.prepareStatement(sql);
	                      stmt.setInt(1,s.qty);
	                      stmt.setInt(2,s.duration);
	                      stmt.setDouble(3, s.fine);
	                      stmt.setInt(4,1);
				 }
				 
				  
			       rows = stmt.executeUpdate();
			      
			      
				 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			return rows;
		 }
	 
	 public int Delete(Object object){
		 int rows = 0;
			try {				
				 PreparedStatement stmt = null;
				 String sql;
				 if(connection==null) return 0;
			     
				 if (object.getClass()==Book.class){
					 Book book = (Book)object;
					 sql = "DELETE FROM books WHERE BookID = ?";
				      stmt = connection.prepareStatement(sql);
				      stmt.setInt(1,book.getBookID());
			     }
				 
				 else if(object.getClass()==Member.class){
					 Member member = (Member)object;
					 sql = "DELETE FROM members WHERE MemberID = ?";
				      stmt = connection.prepareStatement(sql);
				      stmt.setInt(1,member.getMemberID());
				 }
				 else if(object.getClass()==Borrow.class){
					 Borrow borrow = (Borrow)object;
					 sql = "DELETE FROM borrows WHERE BorrowID = ?";
				      stmt = connection.prepareStatement(sql);
				      stmt.setInt(1,borrow.getBorrowID());
				 }
				 
			      rows = stmt.executeUpdate();
			      
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rows;
	 } 

	 public ArrayList<Book> getBooks(){
		 ArrayList<Book> list  = new ArrayList<Book>();
		 
		 try{
			
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "Select * from books";
			rss =stm.executeQuery(sql);			
			while(rss.next()){
				list.add(new Book(
							rss.getInt("BookID"),
							rss.getString("Title"),
							rss.getString("Isbn"),
							rss.getString("Category"),
							rss.getString("Accesscode"),
							rss.getString("Author"),
							rss.getString("Languages"),
							rss.getString("Edition"),
							rss.getInt("Qty")
						));				
				
			}
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		 return list;
	 }
	 
	 public ArrayList<Member> getMember(){
		 ArrayList<Member> list  = new ArrayList<Member>();

		 try{		 
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "Select * from members";
			rss =stm.executeQuery(sql);			
			while(rss.next()){
				 DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
				 String RegisterDate=df1.format(rss.getDate("RegisterDate"));
				 String ExpireDate=df1.format(rss.getDate("ExpireDate"));

				
				list.add(new Member(
							rss.getInt("MemberID"),
							rss.getString("Name"),
							rss.getString("CardID"),
							rss.getString("Gender"),
							RegisterDate,
							ExpireDate,
							rss.getString("Mobile"),
							rss.getString("Email"),
							rss.getString("Address")
							));				
			}
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		 return list;
	 }

	 public Member searchMember(String cardID){
		 Member member = null; 
		 try{
			 PreparedStatement stmt = null;
			 String sql;
			 sql = "SELECT * FROM members WHERE CardID =?";
		      stmt = connection.prepareStatement(sql);
		      stmt.setString(1,cardID);
		      rss = stmt.executeQuery();
				while(rss.next()){
						
						DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
						 String RegisterDate=df1.format(rss.getDate("RegisterDate"));
						 String ExpireDate=df1.format(rss.getDate("ExpireDate"));
						member = new Member(
								rss.getInt("MemberID"),
								rss.getString("Name"),
								rss.getString("CardID"),
								rss.getString("Gender"),
								RegisterDate,
								ExpireDate,
								rss.getString("Mobile"),
								rss.getString("Email"),
								rss.getString("Address")
								);
					}			
									
				
				}
	catch(Exception e){
			 		e.printStackTrace();
			 	}
		 return member;
	 }
	 
	 public Book searchBook(String isbn){
		 Book book = null; 
		 try{
			 PreparedStatement stmt = null;
			 String sql;
			 sql = "SELECT * FROM books WHERE Isbn =?";
		      stmt = connection.prepareStatement(sql);
		      stmt.setString(1,isbn);
		      rss = stmt.executeQuery();

				while(rss.next()){
					if(rss.getString("Isbn").equals(isbn)){
						book = new Book(
								rss.getInt("BookID"),
								rss.getString("Title"),
								rss.getString("Isbn"),
								rss.getString("Category"),
								rss.getString("Accesscode"),
								rss.getString("Author"),
								rss.getString("Languages"),
								rss.getString("Edition"),
								rss.getInt("Qty")
								);
					}			
									
				}
				}
			 	catch(Exception e){
			 		e.printStackTrace();
			 	}
		 return book;
	 }
	 
	 public int SearchBorrow(int memberID){
		 int borrowed=0;
		 try{
			 PreparedStatement stmt = null;
			 String sql;
			 sql = "SELECT * FROM borrows WHERE MemberID = ?";
		      stmt = connection.prepareStatement(sql);
		      stmt.setInt(1,memberID);
		      rss = stmt.executeQuery();

				while(rss.next()){
					borrowed++;			
									
				}
				}
			 	catch(Exception e){
			 		e.printStackTrace();
			 	}
		 return borrowed;
	 }
	
	 public ArrayList<Borrow> getBorrow(){
		 ArrayList<Borrow> list  = new ArrayList<Borrow>();

		 try{		 
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT Bo.BorrowID, M.CardID, M.Name, B.Isbn, B.Title, B.Author, Bo.Qty, Bo.BorrowDate, Bo.ReturnDate FROM (books as B INNER JOIN borrows as Bo ON B.BookID = Bo.BookID) INNER JOIN members AS M ON M.MemberID = Bo.MemberID";
			rss =stm.executeQuery(sql);			
			while(rss.next()){
				
				list.add(new Borrow(							
						rss.getInt("BorrowID"),
							rss.getString("CardID"),
							rss.getString("Name"),
							rss.getString("Isbn"),
							rss.getString("Title"),
							rss.getString("Author"),
							rss.getInt("Qty"),
							rss.getDate("BorrowDate").toString(),
							rss.getDate("ReturnDate").toString()));				
			}
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		 return list;

	 }
	 
	 public ArrayList<Return> getReturn(){
		 ArrayList<Return> list  = new ArrayList<Return>();

		 try{		 
			stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT Bo.ReturnID, M.CardID, M.Name, B.Isbn, B.Title, B.Author, B.Qty, Bo.BorrowDate, Bo.ReturnDate, Bo.Fine FROM (books as B INNER JOIN returns as Bo ON B.BookID = Bo.BookID) INNER JOIN members AS M ON M.MemberID = Bo.MemberID";
			rss =stm.executeQuery(sql);			
			while(rss.next()){
				
				list.add(new Return(							
						rss.getInt("ReturnID"),
							rss.getString("CardID"),
							rss.getString("Name"),
							rss.getString("Isbn"),
							rss.getString("Title"),
							rss.getString("Author"),
							rss.getInt("Qty"),
							rss.getDate("BorrowDate").toString(),
							rss.getDate("ReturnDate").toString(),
							rss.getDouble("Fine")));
			}
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		 return list;

	 }

	public int updateBookQty(int qty, int BookID){
		int rows=0;	
		 try {				
				 PreparedStatement stmt = null;
				 String sql;
				 if(connection==null) return 0;
				 sql = "UPDATE books SET Qty=? WHERE BookID =?";
			      stmt = connection.prepareStatement(sql);
			      stmt.setInt(1,qty);
			      stmt.setInt(2,BookID);
			      rows = stmt.executeUpdate();
			      
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rows;
		}
	
	 public Borrow searchBorrow(Member member,Book book){
		 
		 Borrow bo= null;
		 try{		 
			 
			 PreparedStatement stmt = null;
			 String sql;
			 sql = "SELECT Bo.BorrowID, B.Author, Bo.BorrowDate,M.Name, B.Title , M.Gender, B.Title, Bo.Qty, Bo.ReturnDate FROM (books as B INNER JOIN borrows as Bo ON B.BookID = Bo.BookID) INNER JOIN members AS M ON M.MemberID = Bo.MemberID WHERE M.CardID = ? AND B.Isbn = ?";
		      stmt = connection.prepareStatement(sql);
		      stmt.setString(1,member.getCardID());
		      stmt.setString(2,book.getIsbn());
		      rss = stmt.executeQuery();
			while(rss.next()){
				
				bo =new Borrow(							
							rss.getInt("BorrowID"),
							member.getCardID(),
							rss.getString("Name"),
							book.getIsbn(),
							rss.getString("Title"),
							rss.getString("Author"),
							rss.getInt("Qty"),
							rss.getDate("BorrowDate").toString(),
							rss.getDate("ReturnDate").toString());				
			}
			}
		 	catch(Exception e){
		 		e.printStackTrace();
		 	}

		 return bo;

	 }
	 
	 public User getUser(String username, String password){
		 User u=null;
		 try{
			 PreparedStatement stmt = null;
			 String sql;
			 sql = "SELECT * FROM users WHERE Username=? AND Passwords=?";
		      stmt = connection.prepareStatement(sql);
		      stmt.setString(1,username);
		      stmt.setString(2,password);
		      rss = stmt.executeQuery();

				while(rss.next()){
					u=new User();
					u.type= rss.getString("Type");
										
				}
				}
			 	catch(Exception e){
			 		e.printStackTrace();
			 	}
		 return u;
		 
	 }

}


