package data;

/**
 * Created by Mister_Brown on 4/17/2017.
 */
public class Return {
    int ReturnID;
	int BookID;
    int MemberID;
    String BorrowDate;
    String ReturnDate;
    int Qty;
    Double Fine;
    
    String CardId ;  
    String MemberName ;
    String Isbn ;
    String BookTitle ;
    String Author ;

    public String[] GetDatas(){
        return new String[]{
        		CardId,	
    			MemberName,
    			Isbn,
    			BookTitle,
    			Author,
    			Qty+"",
    			BorrowDate,
    			ReturnDate,
    			Fine+""
    			
        };
        }
        
        public Return(int id,String cardId, String memberName, String isbn, String bookTitle, String author, int qty, String borrowDate, String returnDate, Double fine) {
            ReturnID = id;
        	CardId = cardId;
            MemberName = memberName;
            Isbn = isbn;
            BookTitle = bookTitle;
            Author = author;
            Qty = qty;
            BorrowDate = borrowDate;
            ReturnDate = returnDate;
            Fine= fine;
        }
    

    
    public Return(){
    	super();
    }

    public Return(int ID,int bookID, int memberID, String borrowDate, String returnDate, int qty, Double fine) {
        BookID = bookID;
        ReturnID=ID;
        MemberID = memberID;
        BorrowDate = borrowDate;
        ReturnDate = returnDate;
        Qty = qty;
        Fine = fine;
    }

    public void setReturnID(int id){
    	ReturnID=id;
    }
    
    public int getReturnID(){
    	return ReturnID;
    }
    
    public String[] GetData(){
        return new String[]{
                BookID+"",
                MemberID+"",
                BorrowDate,
                ReturnDate,
                Qty+"",
                Fine+""
        };
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

    public Double getFine() {
        return Fine;
    }

    public void setFine(Double fine) {
        Fine = fine;
    }
}
