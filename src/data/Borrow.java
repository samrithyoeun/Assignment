package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Mister_Brown on 4/17/2017.
 */
public class Borrow {
    int BorrowID;
	int BookID;
    int MemberID;
    String BorrowDate;
    String ReturnDate;
    int Qty;

    String CardId ;  
    String MemberName ;
    String Isbn ;
    String BookTitle ;
    String Author ;
    
    public String[] GetData(){
        return new String[]{
        		CardId,	
    			MemberName,
    			Isbn,
    			BookTitle,
    			Author,
    			Qty+"",
    			BorrowDate,
    			ReturnDate,
        };
    }
    
    
    public Borrow(){}
    public int getBorrowID(){
    	return BorrowID;
    } 
    
    public Borrow(int id,int bookID, int memberID, String boorowDate, String returnDate, int qty) {
        BorrowID =id;
    	BookID = bookID;
        MemberID = memberID;
        BorrowDate = boorowDate;
        ReturnDate = returnDate;
        Qty = qty;
    }

    
    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID(int memberID) {
        MemberID = memberID;
    }

    public String getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        BorrowDate = borrowDate;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }
    
    public Borrow(int id,String cardId, String memberName, String isbn, String bookTitle, String author, int qty, String borrowDate, String returnDate) {
        BorrowID = id;
    	CardId = cardId;
        MemberName = memberName;
        Isbn = isbn;
        BookTitle = bookTitle;
        Author = author;
        Qty = qty;
        BorrowDate = borrowDate;
        ReturnDate = returnDate;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
    
    public static String requestDueDate(String dt, int duration){
    		
        	dt = dt.replace("/","-").replace("\\","-");
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Calendar c = Calendar.getInstance();
    		try {
    			c.setTime(sdf.parse(dt));
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		c.add(Calendar.DATE, duration);  // number of days to add
    		dt = sdf.format(c.getTime());  // dt is now the new date
    		return dt;
        }
    
}
