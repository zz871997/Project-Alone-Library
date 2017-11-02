package tranquangkhai20152005.library.test;

import java.util.ArrayList;

import tranquangkhai20152005.library.model.BookDB;

public class ThongKe {
	private static BookDB bookDB = new BookDB();
	static ArrayList<ArrayList<String>> result = bookDB.thongKeSach("NXB");
	
	
	public static String[][] convertToString (ArrayList<ArrayList<String>> arr) {
		String[][] convertResult = new String[result.size()][2];
		for (int i = 0; i < arr.size(); i ++) {
			convertResult[i][0] = arr.get(i).get(0);
			System.out.println("0:" + convertResult[i][0]);
			
			convertResult[i][1] = arr.get(i).get(1);
			System.out.println("1:" + convertResult[i][1]);
		}
		
		return convertResult;
	}
	
	public static void main(String[] args) {
		System.out.println("Test Thong Ke");
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		ArrayList<String> one = new ArrayList<>();
		one.add("1");
		one.add("one");
		one.add("1");
		one.add("two");
		
		ArrayList<String> two = new ArrayList<>();
		two.add("2");
		two.add("one");
		two.add("2");
		two.add("two");
		
		arr.add(one);
		arr.add(two);
		
		String[][] dm = convertToString(result);
		System.out.println(result.size());
	}
}
