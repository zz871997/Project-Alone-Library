package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tranquangkhai20152005.library.model.Book;
import tranquangkhai20152005.library.model.BookDB;
import tranquangkhai20152005.library.view.AddBookView;
import tranquangkhai20152005.library.view.BookInformation;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBookView;

public class AddBookController {
	private MainUI mainUI;
	private Book book;
	private BookDB bookDB;
	
	private AddBookView addBookView;
	private BookInformation bookInformation;
	private TableBookView tableBookView;
	
	/* Constructor */
	public AddBookController(MainUI mainUI){
		this.mainUI = mainUI;
		bookDB = new BookDB();
		JButton btnAddBookManager = mainUI.getManagerView().getBtnAddBook();
		JButton btnAddFromExcel   = mainUI.getManagerView().getBtnAddFromExcel();
		
		/* Update table book view */
		tableBookView = mainUI.getTableBookView();
		tableBookView.updateTable(bookDB.getAllBooks());
		
		/* BtnAddBook's event */
		btnAddBookManager.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBookView     = new AddBookView(mainUI);
				bookInformation = addBookView.getBookInformation();
				addBookView.setVisible(true);
				/* Set actions on AddBook View (Dialog) */
				setActions();
			}
		});
		
		/* btnAddFromExcel's Event */
		btnAddFromExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFromExcel();
			}
		});
	}
	
	/*------------------------------Actions on AddBookView----------------------*/
	/* Check format of text field */
	private boolean checkInfor(BookInformation bookInformation) {
		/* Are text fields empty? */
		if (bookInformation.getTfMaSach().getText().toString().trim().equals("")  ||
			bookInformation.getTfTenSach().getText().toString().trim().equals("") ||
			bookInformation.getTfTacGia().getText().toString().trim().equals("")  ||
			bookInformation.getTfNXB().getText().toString().trim().equals("")     ||
			bookInformation.getTfNamXB().getText().toString().trim().equals("")   ||
			bookInformation.getTfTheLoai().getText().toString().trim().equals("") ||
			bookInformation.getTfSoLuong().getText().toString().trim().equals("")) {
			System.out.println("Khong de trong cac truong du lieu");
			JOptionPane.showMessageDialog(this.addBookView, "Các trường dữ liệu không được để trống");
			return false;
		}
		/* Check namXB and soLuong are integer? */
		try {
			int namXB = Integer.parseInt(bookInformation.getTfNamXB().getText().trim().toString());
			int soLuong = Integer.parseInt(bookInformation.getTfSoLuong().getText().toString().trim());
			/* Test < 0 ????  */
			if(namXB < 0 || soLuong <0 || namXB > 9999 || namXB < 1000) {
				JOptionPane.showMessageDialog(this.addBookView, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.addBookView, "Năm xuất bản và số lượng phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		/* Check if maSach is exist*/
		if (!checkID(bookInformation.getTfMaSach().getText().toString())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã sách đã tồn tại - Hãy nhập lại");
			return false;
		}
		return true;
	}
	
	/* CheckID - Check if maSach is exist - Loop all maSach and check */
	private boolean checkID(String id) {
		ArrayList<Book> listBook = bookDB.getAllBooks();
		for (int i = 0; i < listBook.size(); i++) {
			String maSachFromDB = listBook.get(i).getMaSach();
			if (id.equals(maSachFromDB)) return false;
		}
		return true;
	}
	
	/* Set Actions on Dialog */
	private void setActions() {
		JButton btnThem = addBookView.getBtnAdd();
		JButton btnTaoLai = addBookView.getBtnReset();
		JButton btnHuy = addBookView.getBtnCancel();
		
		/* Set actions for buttons */
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				themSach();
			}
		});
		
		btnTaoLai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taoLai();
	
			}
		});
		
		btnHuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				huy();
			}
		});
	}
	
	/*Add Book From Excel - Show JFileChooser */
	private void addFromExcel() {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showOpenDialog(this.mainUI);
		String openFilePath = "";
		
		if (select == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getCurrentDirectory().toString() 
			       	   + "\\" + fileChooser.getSelectedFile().getName();
			if(path.indexOf(".xlsx") >= 0) {
				openFilePath = path;
			}
			else {
				openFilePath = path + ".xlsx";
			}
			System.out.println(openFilePath);
			addBookFromExcelFile(openFilePath);
			
			JOptionPane.showMessageDialog(new JDialog(), "Đã thêm từ Excel");
		}
	}
	
	/* Using POI to write a excel file */
	private void addBookFromExcelFile(String path) {
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			/* Create workbook Object */
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			/* Get the first sheet from workbook */
			XSSFSheet sheet = workbook.getSheetAt(0);
			/* Get all row of the current sheet */
			Iterator<Row> rowIterator = sheet.iterator();
			List<Row> rowList = IteratorUtils.toList(rowIterator);
			
			for (int i = 2; i < rowList.size(); i++) {
				XSSFRow row = (XSSFRow) rowList.get(i);
				Iterator<Cell> cellIterator = row.cellIterator();
				List<Cell> cellList = IteratorUtils.toList(cellIterator);
				
				/* ArrayList save data of current row */
				ArrayList<String> dataOfRow = new ArrayList<String>();
				for (int j = 0; j < cellList.size(); j++) {
					Cell cell = cellList.get(j);
					CellType cellType = cell.getCellTypeEnum();
					String data = "";
					switch(cellType) {
						case STRING:
							data = cell.getStringCellValue();
							break;
						case NUMERIC:
							data = Double.toString(cell.getNumericCellValue());
							break;
						default:
							data = "";
							break;
					}
					dataOfRow.add(data);
				}
				
				if (checkInfor(dataOfRow)) {
					String maSach   = dataOfRow.get(0);
					String tenSach  = dataOfRow.get(1);
					String tacGia   = dataOfRow.get(2);
					String nxb      = dataOfRow.get(3);
					String theLoai  = dataOfRow.get(4);
					
					double namXB    = Double.parseDouble(dataOfRow.get(5));
					int namXBInt    = (int) namXB;
					String namXBStr = Integer.toString(namXBInt);
					double soLuong  = Double.parseDouble(dataOfRow.get(6));
					int soLuongInt  = (int) soLuong;
		
					Book aNewBook   = new Book(maSach, tenSach, tacGia, nxb, theLoai, namXBStr, soLuongInt);
					bookDB.insertBook(aNewBook);
					tableBookView.updateTable(bookDB.getAllBooks());
					dataOfRow.clear();
				}
				else return;
			}
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Lỗi File");
			e.printStackTrace();
		}
	}
	
	/* Check format of current row */
	private boolean checkInfor(ArrayList<String> dataOfRow) {
		// Check if empty
		for (int i = 0; i < dataOfRow.size(); i++) {
			if (dataOfRow.get(i).equals("")) return false;
		}
		// Check namXB and soLuong are integer?
		try {
			double namXB   = Double.parseDouble(dataOfRow.get(5));
			int namXBInt = (int) namXB;
			if (namXBInt != namXB) return false;
			double soLuong = Double.parseDouble(dataOfRow.get(6));
			int soLuongInt = (int) soLuong;
			if (soLuongInt != soLuong) return false;
			// Test < 0 ????
			if(namXBInt < 0 || soLuongInt <0 || namXBInt > 9999 || namXBInt < 1000) {
				JOptionPane.showMessageDialog(this.mainUI, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.mainUI, "Năm xuất bản và số lượng phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		/* Check if maSach is exist*/
		if (!checkID(dataOfRow.get(0))) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã sách đã tồn tại - Hãy nhập lại");
			return false;
		}
		return true;
	}
	
	// Detail Actions
	private void themSach() {
		if (checkInfor(bookInformation)) {
			String maSach = bookInformation.getTfMaSach().getText().toString();
			String tenSach = bookInformation.getTfTenSach().getText().toString();
			String tacGia = bookInformation.getTfTacGia().getText().toString();
			String nxb = bookInformation.getTfNXB().getText().toString();
			String theLoai = bookInformation.getTfTheLoai().getText().toString();
			String namXB = bookInformation.getTfNamXB().getText().toString();
			int soLuong = Integer.parseInt(bookInformation.getTfSoLuong().getText().toString());
			
			book = new Book(maSach, tenSach, tacGia, nxb, theLoai, namXB, soLuong);
			bookDB.insertBook(book);
			//Update Table
			tableBookView.updateTable(bookDB.getAllBooks());
			clearInput();
			this.addBookView.setVisible(false);
		}
		else {
			System.out.println("Insert this book false!!!");
		}
	}
	
	private void taoLai() {
		clearInput();
	}
	
	private void huy() {
		clearInput();
		this.addBookView.setVisible(false);
	}
	
	private void clearInput() {
		bookInformation.getTfMaSach().setText(null);;
		bookInformation.getTfTenSach().setText(null);
		bookInformation.getTfNXB().setText(null);
		bookInformation.getTfNamXB().setText(null);
		bookInformation.getTfTacGia().setText(null);
		bookInformation.getTfTheLoai().setText(null);
		bookInformation.getTfSoLuong().setText(null);
	}
}
