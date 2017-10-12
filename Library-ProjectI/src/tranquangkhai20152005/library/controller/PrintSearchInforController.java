package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import tranquangkhai20152005.library.model.Book;
import tranquangkhai20152005.library.model.BookDB;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.SearchBookView;

public class PrintSearchInforController {
	private String saveFilePath;
	
	private MainUI mainUI;
	
	private BookDB bookDB;
	
	private SearchBookView searchBookView;
	
	private ArrayList<Book> resultSearch;
	
	// Constructor
	public PrintSearchInforController (MainUI mainUI) {
		this.mainUI = mainUI;
		bookDB = new BookDB();
		JButton btnPrint = mainUI.getSearchBookView().getBtnPrint();
		resultSearch = new SearchBookController(mainUI).getResultSearch();
		
		
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setAction();
				} catch (UnsupportedEncodingException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		//setAction();
	}
	
	
	/*----------------Set Action------------------- */
	private void setAction() throws IOException, InvalidFormatException, URISyntaxException {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.mainUI);
		
		if(select == JFileChooser.APPROVE_OPTION) {
			saveFilePath = fileChooser.getCurrentDirectory().toString() 
					       + "\\" + fileChooser.getSelectedFile().getName();
			
			System.out.println(saveFilePath);
			printToWord(saveFilePath);
			
		}
	}
	
	// Print data to MS Word
	private void printToWord(String path) throws IOException, InvalidFormatException, URISyntaxException {
		XWPFDocument document = new XWPFDocument();
		printHeaderOfDoc(path, document);
		printTitleOfDoc(path, document);
		printTableBookOfDoc(path, document);
		
		XWPFRun paraRun = document.createParagraph().createRun();
		paraRun.addBreak();
		
		printFooterOfDoc(path, document);
	}
	
	//Print Header of Document
	private void printHeaderOfDoc (String path, XWPFDocument document) throws IOException, URISyntaxException, InvalidFormatException {		
		XWPFTable table  = document.createTable();
		XWPFTableRow row = table.getRow(0);
		
		// Set data for cell1
		row.getCell(0).removeParagraph(0);
		XWPFRun cellRun1 = row.getCell(0).addParagraph().createRun();
		cellRun1.setText("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
	    cellRun1.addBreak();
	    cellRun1.setText("Thư viện Tạ Quang Bửu");
	    cellRun1.addBreak();
	    cellRun1.setText("Trần Quang Khải - 20152005");
	    cellRun1.addBreak();
	    cellRun1.setFontSize(12);
	    cellRun1.setBold(true);
	    cellRun1.setTextPosition(10);
	    	//Insert image
	    Path imagePath = Paths.get(ClassLoader.getSystemResource("bachkhoa.png").toURI());
	    cellRun1.addPicture(Files.newInputStream(imagePath),
	    					XWPFDocument.PICTURE_TYPE_PNG, imagePath.getFileName().toString(), 
	    					Units.toEMU(40), Units.toEMU(60));
		XWPFTableCell cell1 = row.getCell(0);
		XWPFParagraph paragraph1 = cell1.getParagraphs().get(0);
	    paragraph1.setAlignment(ParagraphAlignment.CENTER);
		
	    // Set data for cell2
	    XWPFRun cellRun2 = row.addNewTableCell().addParagraph().createRun();
	    row.getCell(1).removeParagraph(0);
	    cellRun2.setText("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");
	    cellRun2.addBreak();
	    cellRun2.setText("Độc lập - Tự do - Hạnh phúc");
	    cellRun2.addBreak();
	    cellRun2.setText("-------------------------");
	    cellRun2.addBreak();
	    cellRun2.addBreak();
	    cellRun2.addBreak();
	    
	    String[] dmy = getDayMonthYear();
	    cellRun2.setText("Ngày " + dmy[0] + " tháng " + dmy[1] + " năm " + dmy[2]);
	    cellRun2.setFontSize(12);
	    cellRun2.setBold(true);
	    cellRun2.setTextPosition(10);
	    
	    XWPFTableCell cell2 = row.getCell(1);
	    XWPFParagraph paragraph2 = cell2.getParagraphs().get(0);
	    paragraph2.setAlignment(ParagraphAlignment.CENTER);
	    
	    
	    // Set Width of table
	    table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
		
		FileOutputStream fos = new FileOutputStream(new File(path));
		
		document.write(fos);
		fos.close();
	}
	
	// Print title of Document
	private void printTitleOfDoc(String path, XWPFDocument document) {
		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun titleRun = title.createRun();
		titleRun.setText("THÔNG TIN SÁCH CÓ TRONG THƯ VIỆN");
		titleRun.setFontSize(20);
		titleRun.setBold(true);
		titleRun.setTextPosition(20);
	}
	
	// Print Footer of Document
	private void printFooterOfDoc (String path, XWPFDocument document) throws IOException, URISyntaxException, InvalidFormatException {		
		XWPFTable table  = document.createTable();
		XWPFTableRow row = table.getRow(0);
		
		// Set data for cell1
		row.getCell(0).removeParagraph(0);
		XWPFRun cellRun1 = row.getCell(0).addParagraph().createRun();
		cellRun1.setText("Người lập");
	    cellRun1.addBreak();
	    cellRun1.setText("(Ký và ghi rõ họ tên)");
	    // Set text of current employment
	    //.....................................
	     
	    cellRun1.setFontSize(12);
	    cellRun1.setBold(true);
	    cellRun1.setTextPosition(10);
	    
		XWPFTableCell cell1 = row.getCell(0);
		XWPFParagraph paragraph1 = cell1.getParagraphs().get(0);
	    paragraph1.setAlignment(ParagraphAlignment.CENTER);
		
	    // Set data for cell2
	    XWPFRun cellRun2 = row.addNewTableCell().addParagraph().createRun();
	    row.getCell(1).removeParagraph(0);
	    cellRun2.setText("Xác nhận của thủ thư");
	    cellRun2.addBreak();
	    cellRun2.setText("(Kí và ghi rõ họ tên)");
	    
	    cellRun2.setFontSize(12);
	    cellRun2.setBold(true);
	    cellRun2.setTextPosition(10);
	    
	    XWPFTableCell cell2 = row.getCell(1);
	    XWPFParagraph paragraph2 = cell2.getParagraphs().get(0);
	    paragraph2.setAlignment(ParagraphAlignment.CENTER);
	    
	    
	    // Set Width of table
	    table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
		
		FileOutputStream fos = new FileOutputStream(new File(path));
		
		document.write(fos);
		fos.close();
	}
	
	// Print Table Information of All Books
	private void printTableBookOfDoc(String path, XWPFDocument document) {
		Book aBook = null;
		int nCols = 8;
		int nRows;
		if (resultSearch.size() == 0 || resultSearch.equals(null)) {	// if data of search is not exist
			resultSearch = bookDB.getAllBooks();
		}
		
		nRows = resultSearch.size() + 1;
		
		XWPFTable table = document.createTable(nRows, nCols);
		table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(10000));
		
		ArrayList<XWPFTableRow> rows = (ArrayList<XWPFTableRow>) table.getRows();
		for(int i = 0; i < rows.size(); i++) {
			XWPFTableRow row = rows.get(i);
			ArrayList<XWPFTableCell> cells = (ArrayList<XWPFTableCell>) row.getTableCells();
			
			if (i != 0) aBook = resultSearch.get(i-1);
			
			for (int j = 0; j < cells.size(); j++) {
				XWPFTableCell cell = cells.get(j);
				if (i == 0) {
					if(j == 0) setTextCenter("TT", cell);
					if(j == 1) setTextCenter("Mã sách", cell);
					if(j == 2) setTextCenter("Tên sách", cell);
					if(j == 3) setTextCenter("Tác giả", cell);
					if(j == 4) setTextCenter("Nhà xuất bản", cell);
					if(j == 5) setTextCenter("Thể loại", cell);
					if(j == 6) setTextCenter("Năm xuất bản", cell);
					if(j == 7) setTextCenter("Số lượng", cell);
				}
				
				// Else i != 0
				else {
					if(j == 0) cell.setText(Integer.toString(i));
					if(j == 1) cell.setText(aBook.getMaSach());
					if(j == 2) cell.setText(aBook.getTenSach());
					if(j == 3) cell.setText(aBook.getTacGia());
					if(j == 4) cell.setText(aBook.getNXB());
					if(j == 5) cell.setText(aBook.getTheLoai());
					if(j == 6) cell.setText(aBook.getNamXB());
					if(j == 7) cell.setText(Integer.toString(aBook.getSoLuong()));
				}
				
				
			}
			
		}
		
	}
	
	// Set text with alignment "CENTER" and style
	private void setTextCenter(String text, XWPFTableCell cell) {
		XWPFParagraph paragraph1 = cell.getParagraphs().get(0);
		paragraph1.setAlignment(ParagraphAlignment.CENTER);
 		XWPFRun cellRun = paragraph1.createRun();
 		cellRun.setText(text);
 		cellRun.setBold(true);
	}
	
	
	// Get Current Day, Month, Year 
	private String[] getDayMonthYear() {
		String dmy[] = new String[3];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		dmy[0] = Integer.toString(localDate.getDayOfMonth());
		dmy[1] = Integer.toString(localDate.getMonthValue());
		dmy[2] = Integer.toString(localDate.getYear());
		return dmy;
	}
}
