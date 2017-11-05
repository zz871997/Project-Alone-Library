package tranquangkhai20152005.library.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiffDay {
	private static void tinhTienPhat (String ngayTra, String ngayHenTra) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
			Date dateStart = format.parse(ngayHenTra);
			Date dateEnd   = format.parse(ngayTra);
			long diffDay = (dateEnd.getTime() - dateStart.getTime()) / (24 * 60 * 60 * 1000);
			System.out.println("diffDay: " + diffDay);
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Loi dinh dang ngay");
		}
		
		
	}
	
	public static void main(String[] args) {
		String ngayTra = "4-1-2018";
		String ngayHenTra = "01-11-2017";
		
		tinhTienPhat(ngayTra, ngayHenTra);
	}
	
//	public static void main(String[] args) {
//
//		String dateStart = "01/14/2012 09:29:58";
//		String dateStop = "01/15/2012 10:31:48";
//
//		//HH converts hour in 24 hours format (0-23), day calculation
//		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//
//		Date d1 = null;
//		Date d2 = null;
//
//		try {
//			d1 = format.parse(dateStart);
//			d2 = format.parse(dateStop);
//
//			//in milliseconds
//			long diff = d2.getTime() - d1.getTime();
//
//			long diffSeconds = diff / 1000 % 60;
//			long diffMinutes = diff / (60 * 1000) % 60;
//			long diffHours = diff / (60 * 60 * 1000) % 24;
//			long diffDays = diff / (24 * 60 * 60 * 1000);
//
//			System.out.print(diffDays + " days, ");
//			System.out.print(diffHours + " hours, ");
//			System.out.print(diffMinutes + " minutes, ");
//			System.out.print(diffSeconds + " seconds.");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

}
