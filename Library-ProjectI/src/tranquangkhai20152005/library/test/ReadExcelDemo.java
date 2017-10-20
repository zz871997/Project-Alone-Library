package tranquangkhai20152005.library.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.eval.forked.ForkedEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcelDemo {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(new File("C:/Users/User/Music/Desktop/employee.xls"));
		
		// Create workbook Object
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		
		// Get the first sheet
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		// Lay ra cac Iterator cho tat ca cac dong cua sheet hien tai
		Iterator<Row> rowIterator = sheet.iterator();
		
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			// Lay tat ca cellIterator
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				CellType cellType = cell.getCellTypeEnum();
				
				switch (cellType) {
				case _NONE:
					System.out.print("");
					System.out.print("\t");
					break;
				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					System.out.print("\t");
					break;
				case BLANK:
	                System.out.print("");
	                System.out.print("\t");
	                break;
				case FORMULA:
					System.out.print(cell.getCellFormula());
					System.out.print("\t");
					
					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
					// In ra gia tri tu cong thuc
					System.out.print(evaluator.evaluate(cell).getNumberValue());
					break;
				case STRING:
					System.out.print(cell.getStringCellValue());
	                System.out.print("\t");
	                break;
	            case ERROR:
	                System.out.print("!");
	                System.out.print("\t");
	                break;
	            case NUMERIC:
	                System.out.print(cell.getNumericCellValue());
	                System.out.print("\t");
	                break;
				}
			}
			System.out.println("");
		}
		
	}
}
