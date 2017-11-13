package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import tranquangkhai20152005.library.model.Detail;
import tranquangkhai20152005.library.model.DetailDB;
import tranquangkhai20152005.library.model.LoanBook;
import tranquangkhai20152005.library.model.LoanBookDB;
import tranquangkhai20152005.library.model.PersonDB;
import tranquangkhai20152005.library.view.DetailInformation;
import tranquangkhai20152005.library.view.DetailView;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBorrowView;
import tranquangkhai20152005.library.view.TableDetailVew;

public class ShowDetailInformation {
	private MainUI mainUI;
	private Detail detail;
	private LoanBook loanBook;
	private DetailDB detailDB;
	private LoanBookDB loanBookDB;
	
	private DetailView detailView;
	private JButton btnShowDetail;
	
	private DetailInformation detailInformation;
	private TableDetailVew tableDetailVew;
	private TableBorrowView tableBorrowView;
	
	/* Initialize */
	public ShowDetailInformation (MainUI mainUI) {
		this.mainUI = mainUI;
		detailDB    = new DetailDB();
		loanBookDB  = new LoanBookDB();
		
		tableBorrowView = mainUI.getTableBorrowView();	
		btnShowDetail   = mainUI.getManagerView().getBtnViewDetail();
		
		btnShowDetailEvent();
	}
	
	private void btnShowDetailEvent() {
		btnShowDetail.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				detailView        = new DetailView(mainUI);
				detailInformation = detailView.getDetailInformation();
				
				int index = findIndexOfData();
				if (index >= 0) {
					detailView.setVisible(true);
					String maMT = getMaMT(index, 0);
					loadInfor(maMT);
					setActions();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 mượn trả để xem chi tiết");
				}
			}
		});
	}
	
	/* Find index of row is selected */
	private int findIndexOfData() {
		int index = tableBorrowView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get maMT of row is selected */
	private String getMaMT (int indexRow, int indexCol) {
		JTable table = tableBorrowView.getTable();
		String maMT  = table.getModel().getValueAt(indexRow, indexCol).toString();
		return maMT;
	}
	
	/* Load detail information */
	private void loadInfor(String maMT) {
		loanBook = loanBookDB.getLoanBook(maMT);
		ArrayList<Detail> listDetail = detailDB.getAllDetailWithID(maMT);
		
		detailInformation.getLbMaMT().setText(maMT);
		detailInformation.getLbMaDG().setText(loanBook.getMaDG());
		detailInformation.getLbMaNV().setText(loanBook.getMaNV());
		detailInformation.getLbHoTenDG().setText(new PersonDB().getPerson("docgia", loanBook.getMaDG()).getName());
		detailInformation.getLbHoTenNV().setText(new PersonDB().getPerson("nhanvien", loanBook.getMaNV()).getName());
		detailInformation.getLbNgayMuon().setText(loanBook.getNgayMuon());
		detailInformation.getLbNgayHenTra().setText(loanBook.getNgayHenTra());
		detailInformation.getLbSoTienCoc().setText(loanBook.getTienCoc() + "");
		detailInformation.getLbNgayTra().setText("");
		detailInformation.getLbTrangThai().setText("");
		detailInformation.getLbSoTienPhat().setText("");
		//-----------------------------------------
		detailInformation.getLbTongTienPhat().setText(detailDB.tinhTongPhat(maMT) + "");
		
		/* Update table list book is loan */
		String[][] listBook = new String[listDetail.size()][3];
		for (int i = 0; i < listDetail.size(); i++) {
			listBook[i][0] = listDetail.get(i).getMaSach();
			listBook[i][1] = new BookDB().getBook(listDetail.get(i).getMaSach()).getTenSach();
			listBook[i][2] = listDetail.get(i).getSoLuong() + "";
		}
		detailInformation.getTableDetailVew().updateTable(listBook);
	}
	
	/* Set actions for all buttons in DetailInformatio View*/
	
	private void setActions() {
		JTable tableDetail = detailInformation.getTableDetailVew().getTable();
		JButton btnPay     = detailView.getBtnPay();
		btnPay.setEnabled(false);
		JButton btnPayAll  = detailView.getBtnPayAll();
		JButton btnPrint   = detailView.getBtnPrint();
		JButton btnCancel  = detailView.getBtnCancel();
		
		/* When a row in table (list Book is loan) is selected */
		tableDetail.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String maMT       = detailInformation.getLbMaMT().getText().trim().toString();
				String maSachMuon = tableDetail.getModel().getValueAt(tableDetail.getSelectedRow(), 0).toString();
				Detail detail     = detailDB.getDetail(maMT, maSachMuon);
				
				if (detail.getNgayTra().equals("") == true) {
					btnPay.setEnabled(true);
				}
				else {
					btnPay.setEnabled(false);
				}
				
				detailInformation.getLbNgayTra().setText(detail.getNgayTra());
				detailInformation.getLbSoTienPhat().setText(detail.getSoTienPhat() + "");
				
				if (detailInformation.getLbNgayTra().getText().trim().toString().equals("")) {
					detailInformation.getLbTrangThai().setText("Chưa trả");
				}
				else {
					detailInformation.getLbTrangThai().setText("Đã trả");
				}
			}
		});
		
		/* Pay a book */
		btnPay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int indexOfRow = tableDetail.getSelectedRow();
				String maMT = detailInformation.getLbMaMT().getText().trim().toString();
				String maSachMuon = tableDetail.getModel().getValueAt(indexOfRow, 0).toString();
				int soLuongMuon = Integer.parseInt(tableDetail.getModel().getValueAt(indexOfRow, 2).toString());
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDate localDate   = LocalDate.now();
				String ngayTraHT      = Integer.toString(localDate.getDayOfMonth());
				String thangTraHT     = Integer.toString(localDate.getMonthValue());
				String namTraHT       = Integer.toString(localDate.getYear());
				String ngayTra        = ngayTraHT + "-" + thangTraHT + "-" + namTraHT;
				double tienPhat       = tinhTienPhat(ngayTra, detailInformation.getLbNgayHenTra().getText().toString());
				
				// Update ngayTra, tienPhat 
				Detail detail = detailDB.getDetail(maMT, maSachMuon);
				detailDB.updateDetail(detail, ngayTra, tienPhat);
				
				// Update number of book
				Book bookIsLoan = new BookDB().getBook(maSachMuon);
				new BookDB().updateBook(bookIsLoan, bookIsLoan.getSoLuong() + soLuongMuon);
				mainUI.getTableBookView().updateTable(new BookDB().getAllBooks());
				
				// Update View
				detailInformation.getLbTrangThai().setText("Đã trả");
				detailInformation.getLbNgayTra().setText(ngayTra);
				detailInformation.getLbSoTienPhat().setText(tienPhat + "");
				detailInformation.getLbTongTienPhat().setText(detailDB.tinhTongPhat(maMT) + "");
				
				// Disable this btnPay of this book
				btnPay.setEnabled(false);
			}
		});
		
		/* Pay all books */
		btnPayAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String            maMT       = detailInformation.getLbMaMT().getText().trim().toString();
				ArrayList<Detail> listDetail = detailDB.getAllDetailWithID(maMT);
				for (int i = 0; i < listDetail.size(); i++) {
					Detail aDetail = listDetail.get(i);
					if (aDetail.getNgayTra().equals("") == false) continue;
					else {
						String maSachMuon = aDetail.getMaSach();
						int soLuongMuon   = aDetail.getSoLuong();
						
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
						LocalDate localDate   = LocalDate.now();
						String ngayTraHT      = Integer.toString(localDate.getDayOfMonth());
						String thangTraHT     = Integer.toString(localDate.getMonthValue());
						String namTraHT       = Integer.toString(localDate.getYear());
						String ngayTra        = ngayTraHT + "-" + thangTraHT + "-" + namTraHT;
						double tienPhat       = tinhTienPhat(ngayTra, detailInformation.getLbNgayHenTra().getText().toString());
						detailDB.updateDetail(aDetail, ngayTra, tienPhat);
						
						// Update number of book
						Book bookIsLoan = new BookDB().getBook(maSachMuon);
						new BookDB().updateBook(bookIsLoan, bookIsLoan.getSoLuong() + soLuongMuon);
						mainUI.getTableBookView().updateTable(new BookDB().getAllBooks());
					}
				}

				detailInformation.getLbTongTienPhat().setText(detailDB.tinhTongPhat(maMT) + "");
				JOptionPane.showMessageDialog(new JDialog(), "Tất cả sách còn lại đã được trả");
				btnPayAll.setEnabled(false);
			}
		});
		
		/* Print bill */
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String [][] data = convertData();
				printBill(data);
			}
		});
		
		/* Close this dialog */
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
	}
	
	private void huy() {
		this.detailView.setVisible(false);
	}
	
	/* Calculating the different day(s) between ngayTra and ngayHenTra */
	/* Then calculating forfeit */
	private double tinhTienPhat (String ngayTra, String ngayHenTra) {
		double tienPhat = 0.0;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
			Date dateStart = format.parse(ngayHenTra);
			Date dateEnd   = format.parse(ngayTra);
			long diffDay = (dateEnd.getTime() - dateStart.getTime()) / (24 * 60 * 60 * 1000);
			System.out.println("diffDay: " + diffDay);
			if (diffDay > 0) {
				tienPhat = diffDay * 2000;
			}
			else {
				tienPhat = 0.0;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Loi dinh dang ngay");
		}
		return tienPhat;
	}
	
	/* Print bill with data */
	private void printBill(String[][] data) {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.detailView);
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
			saveBillExcelTo(saveFilePath, data);
		}
	}
	
	/* Convert table to data */
	private String[][] convertData() {
		String            maMT       = detailInformation.getLbMaMT().getText().trim().toString();
		ArrayList<Detail> listDetail = detailDB.getAllDetailWithID(maMT);
		String data[][] = new String[listDetail.size()][5];
		
		for (int i = 0; i < listDetail.size(); i++) {
			Detail aDetail = listDetail.get(i);
			data[i][0] = Integer.toString(i+1);
			data[i][1] = aDetail.getMaSach();
			data[i][2] = detailInformation.getTableDetailVew().getTable().getModel().getValueAt(i, 1).toString();
			data[i][3] = aDetail.getNgayTra();
			data[i][4] = aDetail.getSoTienPhat() + "";
		}
		return data;
	}
	
	private void saveBillExcelTo (String path, String data[][]) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("PheuMuonTra");
			sheet.setColumnWidth(0, 4300);
			sheet.setColumnWidth(1, 4300);
			sheet.setColumnWidth(2, 6000);
			sheet.setColumnWidth(3, 4300);
			sheet.setColumnWidth(4, 4300);
			
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));
			
			row = sheet.createRow(1);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Thư viện Tạ Quang Bửu");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 4));
			
			row = sheet.createRow(2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Trần Quang Khải - 20152005");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
		
			printImage(workbook, sheet);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate   = LocalDate.now();
			String ngayHT         = Integer.toString(localDate.getDayOfMonth());
			String thangHT        = Integer.toString(localDate.getMonthValue());
			String namHT          = Integer.toString(localDate.getYear());
			String now            = "Ngày " + ngayHT + "-" + thangHT + "-" + namHT;
			row = sheet.createRow(3);  
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(now);
			cell.setCellStyle(createStyleForDate(workbook));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 4));

			row = sheet.createRow(7);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("PHIẾU MƯỢN TRẢ");
			cell.setCellStyle(createStyleForTitle(workbook));
			sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 4));
			
			row = sheet.createRow(9);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã mượn trả");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(detailInformation.getLbMaMT().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(10);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã độc giả");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(detailInformation.getLbMaDG().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tên độc giả");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(detailInformation.getLbHoTenDG().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(11);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã nhân viên");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(detailInformation.getLbMaNV().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tên nhân viên");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(detailInformation.getLbHoTenNV().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(12);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Ngày mượn");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(detailInformation.getLbNgayMuon().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Ngày hẹn trả");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(detailInformation.getLbNgayHenTra().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			
			row = sheet.createRow(14);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("STT");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("MÃ SÁCH MƯỢN");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("TÊN SÁCH");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("NGÀY TRẢ");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("TIỀN PHẠT");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			
			int rowNum = 15;
			for (int i = 0; i < data.length; i++) {
				row = sheet.createRow(rowNum);
				for (int j = 0; j < 5; j++) {
					cell = row.createCell(j, CellType.STRING);
					cell.setCellValue(data[i][j]);
					cell.setCellStyle(createStyleDataTable(workbook));
				}
				rowNum++;
			}
			
			row = sheet.createRow(rowNum + 1);
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tổng tiền phạt");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(detailInformation.getLbTongTienPhat().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(rowNum + 2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Người mượn");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 0, 1));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Nhân viên cho mượn");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 3, 4));
			
			row = sheet.createRow(rowNum + 3);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(detailInformation.getLbHoTenDG().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 3, rowNum + 3, 0, 1));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(detailInformation.getLbHoTenNV().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 3, rowNum + 3, 3, 4));
				
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
			 InputStream inputStream = Files.newInputStream(imagePath);
			 byte[] bytes = IOUtils.toByteArray(inputStream);
			 int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			 inputStream.close();
			 CreationHelper helper = wb.getCreationHelper();
			 Drawing drawing = sheet.createDrawingPatriarch();
			 ClientAnchor anchor = helper.createClientAnchor();
			 anchor.setCol1(1);
			 anchor.setRow1(3);
			 Picture pict = drawing.createPicture(anchor, pictureIdx);
			 pict.resize();   
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
