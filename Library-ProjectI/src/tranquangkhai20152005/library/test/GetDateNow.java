package tranquangkhai20152005.library.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetDateNow {
	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String day = Integer.toString(localDate.getMonthValue());
		System.out.println(dtf.format(localDate) +"   " + day);
	}
}
