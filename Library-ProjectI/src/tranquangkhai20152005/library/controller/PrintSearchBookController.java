package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tranquangkhai20152005.library.model.Book;
import tranquangkhai20152005.library.model.BookDB;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.SearchBookView;

public class PrintSearchBookController {
	private MainUI mainUI;
	private BookDB bookDB;
	private SearchBookView searchBookView;
	private ArrayList<Book> resultSearch;
	private JComboBox<String> cbSearch;
	private JTextField tfSearch;
	
	public PrintSearchBookController(MainUI mainUI) {
		this.mainUI = mainUI;
		bookDB = new BookDB();
		cbSearch = mainUI.getSearchBookView().getCbSearch();
		tfSearch = mainUI.getSearchBookView().getTfSearch();
		JButton btnPrint = mainUI.getSearchBookView().getBtnPrint();
		resultSearch = new SearchBookController(mainUI).getResultSearch();
		
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] data = convertData(resultSearch);
				setAction(data);
			}
		});
	}
	
	/* Convert Data */
	private String[][] convertData(ArrayList<Book> resultSearch) {
		String data[][] = new String[resultSearch.size()][7];
		
		for (int i = 0; i < resultSearch.size(); i++) {
			Book book = resultSearch.get(i);
			data[i][0] = book.getMaSach();
			data[i][1] = book.getTenSach();
			data[i][2] = book.getTacGia();
			data[i][3] = book.getNXB();
			data[i][4] = book.getTheLoai();
			data[i][5] = book.getNamXB();
			data[i][6] = book.getSoLuong() + "";
		}
		return data;
	}
	
	/* ---------- Set Action --------------*/
	private void setAction(String[][] data) {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.mainUI);
		String saveFilePath = "";
		
		if (select == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getCurrentDirectory().toString() 
			       	   + "\\" + fileChooser.getSelectedFile().getName();
			if(path.indexOf(".xlsx") >= 0) {
				saveFilePath = path;
			}
			else {
				saveFilePath = path + ".xlsx";
			}
			System.out.println("Save file to: " + saveFilePath);
			saveSearchExcelTo(saveFilePath, data);
		}
	}
	
	private void saveSearchExcelTo(String path, String[][] data) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("PheuTimKiem");
			sheet.setColumnWidth(0, 4000);
			sheet.setColumnWidth(1, 4000);
			sheet.setColumnWidth(2, 5000);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 4000);
			sheet.setColumnWidth(6, 4000);
			
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 6));
			
			row = sheet.createRow(1);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Thư viện Tạ Quang Bửu");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 6));
			
			row = sheet.createRow(2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Trần Quang Khải - 20152005");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
			
			printImage(workbook, sheet);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate   = LocalDate.now();
			String ngayHT         = Integer.toString(localDate.getDayOfMonth());
			String thangHT        = Integer.toString(localDate.getMonthValue());
			String namHT          = Integer.toString(localDate.getYear());
			String now            = "Ngày " + ngayHT + "-" + thangHT + "-" + namHT;
			
			row = sheet.createRow(3);  
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(now);
			cell.setCellStyle(createStyleForDate(workbook));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 4, 6));
			
			row = sheet.createRow(7);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("TÌM KIẾM SÁCH THEO " + cbSearch.getSelectedItem().toString().toUpperCase() +": " 
							   + tfSearch.getText().toString());
			cell.setCellStyle(createStyleForTitle(workbook));
			sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 6));
			
			row = sheet.createRow(9);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã sách");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Tên sách");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Tác giả");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Nhà xuất bản");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Thể loại");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Năm xuất bản");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("Số lượng");
			cell.setCellStyle(createStyleForTableTitle(workbook));
		
			int rowNum = 10;
			for (int i = 0; i < data.length; i++) {
				row = sheet.createRow(rowNum);
				for (int j = 0; j < 7; j++) {
					cell = row.createCell(j, CellType.STRING);
					cell.setCellValue(data[i][j]);
					cell.setCellStyle(createStyleDataTable(workbook));
				}
				rowNum++;
			}
			
			row = sheet.createRow(rowNum + 2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Người lập");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 0, 2));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Chữ kí thủ thư");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 4, 6));
			
			row = sheet.createRow(rowNum + 3);
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Admin");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 3, rowNum + 3, 4, 6));
			
			workbook.write(fos);
			System.out.println("Create file: " + path);
			fos.close();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Lỗi File");
			e.printStackTrace();
		}
	}
	
	/* Style for date in excel file */
	private XSSFCellStyle createStyleForDate (XSSFWorkbook workbook) {
		XSSFCellStyle styleDate = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setItalic(true);
		styleDate.setFont(font);
		styleDate.setAlignment(HorizontalAlignment.CENTER);
		return styleDate;
	}
	
	/* Style for title */
	private XSSFCellStyle createStyleForTitle (XSSFWorkbook workbook) {
		XSSFCellStyle styleTitle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.BLUE.index);
		font.setFontHeight(18);
		styleTitle.setFont(font);
		styleTitle.setAlignment(HorizontalAlignment.CENTER);
		return styleTitle;
	}
	
	/* Style for table title */
	private XSSFCellStyle createStyleForTableTitle (XSSFWorkbook workbook) {
		XSSFCellStyle styleTableTitle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(12);
		styleTableTitle.setFont(font);
		styleTableTitle.setAlignment(HorizontalAlignment.CENTER);
		styleTableTitle.setAlignment(HorizontalAlignment.CENTER);
		styleTableTitle.setBorderBottom(BorderStyle.MEDIUM);
		styleTableTitle.setBorderTop(BorderStyle.MEDIUM);
		styleTableTitle.setBorderRight(BorderStyle.MEDIUM);
		styleTableTitle.setBorderLeft(BorderStyle.MEDIUM);
		return styleTableTitle;
	}
	
	/* Default style */
	private XSSFCellStyle createStyleDefault(XSSFWorkbook workbook) {
		XSSFCellStyle styleDefault = workbook.createCellStyle();
		styleDefault.setAlignment(HorizontalAlignment.CENTER);
		return styleDefault;
	}
	
	/* Style for cell in table */
	private XSSFCellStyle createStyleDataTable(XSSFWorkbook workbook) {
		XSSFCellStyle styleData = workbook.createCellStyle();
		styleData.setAlignment(HorizontalAlignment.CENTER);
		styleData.setBorderBottom(BorderStyle.MEDIUM);
		styleData.setBorderTop(BorderStyle.MEDIUM);
		styleData.setBorderRight(BorderStyle.MEDIUM);
		styleData.setBorderLeft(BorderStyle.MEDIUM);
		return styleData;
	}
	
	/*Print image */
	private void printImage (Workbook wb, Sheet sheet) {
		 try {
			 Path imagePath = Paths.get(ClassLoader.getSystemResource("bachkhoa.png").toURI()); 
			 //FileInputStream obtains input bytes from the image file
			 InputStream inputStream = Files.newInputStream(imagePath);
			 //Get the contents of an InputStream as a byte[].
			 byte[] bytes = IOUtils.toByteArray(inputStream);
			 //Adds a picture to the workbook
			 int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			 //close the input stream
			 inputStream.close();
			 //Returns an object that handles instantiating concrete classes
			 CreationHelper helper = wb.getCreationHelper();
			 //Creates the top-level drawing patriarch.
			 Drawing drawing = sheet.createDrawingPatriarch();
			 //Create an anchor that is attached to the worksheet
			 ClientAnchor anchor = helper.createClientAnchor();
			 //set top-left corner for the image
			 anchor.setCol1(1);
			 anchor.setRow1(3);
			 //Creates a picture
			 Picture pict = drawing.createPicture(anchor, pictureIdx);
			 //Reset the image to the original size
			 pict.resize();	 
		 }
		 catch (Exception e) {
			 System.out.println(e);
		 }
	}
}
