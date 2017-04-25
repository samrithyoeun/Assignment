package data;

public class MemberCount {
	public int qty;
	public String memberType;
	
	double amount;
	public MemberCount(int q, String t){
		qty =	q;
		memberType =t;
	}
	
	public MemberCount(double b, String t){
		amount =b;
		memberType =t;
	}

	
}
