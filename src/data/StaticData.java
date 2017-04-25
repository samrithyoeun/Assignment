package data;

public class StaticData {
	
	public static String[] BookCategories(){
			
		 return new String[]
						{       " Book Categories",
								" Real Science    ",
								" Social Science  ",
								" Lifestyle + Sport ",
								" Art + Megazine    ",
								" Khmer + History   ",
								" World's History ",
								" Story + Humor     ",
								" Law + Knowledge   ",
								" Biography       ",
								" Journal + News    ",
								" Reference       ",
								" Foriegn Language",
								" Maths + Physic    ",
								" Chemisty + Biology" };
	}
	
	public static String[] SearchBook(){
		
		return new String[]
				{       " Filter By: ",
						" Title",
						" ISBN",
						" Category",
						" Author"
						};
	}
	
public static String[] SearchMember(){
		
		return new String[]
				{       " Filter By: ",
						" Name",
						" Card-ID",
						" Gender",
						" Register Date",
						" Expire Date"
						};
	}
}
