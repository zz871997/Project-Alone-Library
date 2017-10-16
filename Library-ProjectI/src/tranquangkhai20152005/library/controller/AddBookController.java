package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
	
	// Constructor
	public AddBookController(MainUI mainUI){
		this.mainUI = mainUI;
		bookDB = new BookDB();
		JButton btnAddBookManager = mainUI.getManagerView().getBtnAddBook();
		tableBookView = mainUI.getTableBookView();
		//Update table
		tableBookView.updateTable(bookDB.getAllBooks());
		
		btnAddBookManager.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBookView     = new AddBookView(mainUI);
				bookInformation = addBookView.getBookInformation();
				addBookView.setVisible(true);
				// Set actions on AddBook View
				setActions();
			}
		});
	}
	
	/*------------------------------Actions on AddBookView----------------------*/
	// Check format of text field
	private boolean checkInfor(BookInformation bookInformation) {
		// Are text fields empty?
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
		// Check namXB and soLuong are integer?
		try {
			//if (strNamXB.length() == 0) continue;
			int namXB = Integer.parseInt(bookInformation.getTfNamXB().getText().trim().toString());
			int soLuong = Integer.parseInt(bookInformation.getTfSoLuong().getText().toString().trim());
			// Test < 0 ????
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
		return true;
		
		/* Check if maSach is exist*/
		
		///////////////////////////////...............//////////
	}
	
	/* Set Actions */
	private void setActions() {
		JButton btnThem = addBookView.getBtnAdd();
		JButton btnTaoLai = addBookView.getBtnReset();
		JButton btnHuy = addBookView.getBtnCancel();
		
		// Set actions for buttons
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
