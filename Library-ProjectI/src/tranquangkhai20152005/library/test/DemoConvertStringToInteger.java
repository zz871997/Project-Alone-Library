package tranquangkhai20152005.library.test;

public class DemoConvertStringToInteger {
	public static void main(String[] args) {
		try{
			String str = "09999999999";
			long number = (long) Long.parseLong(str);
			System.out.println(number);
		}
		catch (NumberFormatException e) {
			System.out.println("Chuoi ko dung format");
		}
		
		
	}
}
