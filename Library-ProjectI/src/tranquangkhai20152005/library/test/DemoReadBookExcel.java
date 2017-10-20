package tranquangkhai20152005.library.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DemoReadBookExcel {
	private static XSSFWorkbook workbook;

	public static void main(String[] args) throws IOException {
		//System.out.println("Trần Quang Khải");
		FileInputStream fis = new FileInputStream(new File("C:/Users/User/Music/Desktop/books.xlsx"));
		
		workbook = new XSSFWorkbook(fis);
		
		// Create
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		// Lay ra cac Iterator cho tat ca cac dong cua sheet hien tai
		Iterator<Row> rowIterator = sheet.iterator();
		List<Row> rowList = IteratorUtils.toList(rowIterator);
		
		for (int i = 2; i < rowList.size(); i++) {
			XSSFRow row = (XSSFRow) rowList.get(i);
			Iterator<Cell> cellIterator = row.cellIterator();
			List<Cell> cellList = IteratorUtils.toList(cellIterator);
			
			for (int j = 0; j < cellList.size(); j++) {
				Cell cell = cellList.get(j);
				CellType cellType = cell.getCellTypeEnum();
				
				switch(cellType) {
				case STRING:
					System.out.print(cell.getStringCellValue());
					System.out.print("\t");
					break;
//				case NUMERIC:
//					System.out.print(cell.getNumericCellValue());
//					System.out.print("\t");
//					break;
				default:
					System.out.print("");
					System.out.print("\t");
					break;
				}
			}
			System.out.println("");
		}
		
//		while (rowIterator.hasNext()) {
//			XSSFRow row = (XSSFRow) rowIterator.next();
//			
//			// lay tat ca cellIterator
//			Iterator<Cell> cellIterator = row.cellIterator();
//			while (cellIterator.hasNext()) {
//				Cell cell = cellIterator.next();
//				CellType cellType = cell.getCellTypeEnum();
//				
//				switch(cellType) {
//				case STRING:
//					System.out.print(cell.getStringCellValue());
//					System.out.print("\t");
//					break;
//				case NUMERIC:
//					System.out.print(cell.getNumericCellValue());
//					System.out.print("\t");
//					break;
//				default:
//					System.out.print("");
//					System.out.print("\t");
//					break;
//				}
//				
//			}
//			System.out.println("");
//			
//		}
		
	}
	
}
