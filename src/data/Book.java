package data;

public class Book {

	int BookID;
    String Title;
    String Isbn;
    String Category;
    String Accesscode;
    String Author;
    String Language;
    String Edition;
    int Qty;

    public Book(){
            super();
    }
    
    public void setBookID(int id){
    	BookID=id;
    }
    
    public int getBookID(){
    	return BookID;
    }
    public Book(int id,String title, String isbn, String category, String accesscode,String a, String language, String edition, int qty) {
    		BookID =id;
            Title = title;
            Isbn = isbn;
            Category = category;
            Accesscode = accesscode;
            Language = language;
            Edition = edition;
            Qty = qty;
            Author=a;
    }

    public String[] GetData(){
            return new String[]{
                    "",
                    Isbn,
                    Title,
                    Category,
                    Accesscode,
                    Author,
                    Language,
                    Edition,
                    Qty+"",
            };
    }


    public String getTitle() {
            return Title;
    }

    public void setTitle(String title) {
            Title = title;
    }

    public String getIsbn() {
            return Isbn;
    }

    public void setIsbn(String isbn) {
            Isbn = isbn;
    }

    public String getCategory() {
            return Category;
    }

    public void setCategory(String category) {
            Category = category;
    }

    public String getAccesscode() {
            return Accesscode;
    }

    public void setAccesscode(String accesscode) {
            Accesscode = accesscode;
    }

    public String getLanguage() {
            return Language;
    }

    public void setLanguage(String language) {
            Language = language;
    }

    public String getEdition() {
            return Edition;
    }

    public void setEdition(String edition) {
            Edition = edition;
    }

    public int getQty() {
            return Qty;
    }

    public void setQty(int qty) {
            Qty = qty;
    }
    public String getAuthor(){
    	return Author;
    }
    public void setAuthor(String a){
    	Author=a;
    }
}   

    
