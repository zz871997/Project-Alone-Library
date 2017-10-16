package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tranquangkhai20152005.library.model.Book;
import tranquangkhai20152005.library.model.BookDB;
import tranquangkhai20152005.library.view.BookInformation;
import tranquangkhai20152005.library.view.EditBookView;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBookView;

public class EditBookController {
	private MainUI mainUI;
	private Book book;
	private BookDB bookDB;
	
	//Initialize edit BookPanel
	private EditBookView editBookView;
	private JButton btnEdit;
	
	// Get information from editBookView
	private BookInformation bookInformation;
	private TableBookView tableBookView;
	
	// Constructor
	public EditBookController(MainUI mainUI) {
		this.mainUI = mainUI;
		bookDB = new BookDB();
		btnEdit = mainUI.getManagerView().getBtnEditBook();
		tableBookView = mainUI.getTableBookView();
		tableBookView.updateTable(bookDB.getAllBooks());
		btnEditEvent();
	}
	
	/*-------------Event - Action--------------- */
	private void btnEditEvent() {
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editBookView = new EditBookView(mainUI);
				bookInformation = editBookView.getBookInformation();
				// When click "SUA" on MainUI, AddProduct Panel will be visible
				//(if row is selected)
				int index = findIndexOfData();
				if (index >= 0) {
					editBookView.setVisible(true);
					// Get maSach
					String maSach = getId(index, 0);
					loadInfor(maSach);
					setActions();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 sách để sửa");
				}
			}
		});
	}
	
	private int findIndexOfData() {
		int index = tableBookView.getTable().getSelectedRow();
		return index;
	}
	
	private String getId(int indexRow, int indexCol) {
		JTable table = tableBookView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	private void loadInfor(String id) {
		book = bookDB.getBook(id);
		bookInformation.getTfMaSach().setText(id);
		bookInformation.getTfTenSach().setText(book.getTenSach());
		bookInformation.getTfTacGia().setText(book.getTacGia());
		bookInformation.getTfNXB().setText(book.getNXB());
		bookInformation.getTfTheLoai().setText(book.getTheLoai());
		bookInformation.getTfNamXB().setText(book.getNamXB());
		bookInformation.getTfSoLuong().setText(book.getSoLuong() + "");
		bookInformation.getTfSoLuong().setEditable(false);
	}
	
	/*--------------------  Set Actions ----------------- */
	private void setActions() {
		JButton btnEdit = editBookView.getBtnEdit();
		JButton btnCancel = editBookView.getBtnCancel();
		
		// Set Actions for button
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				suaSach();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				huy();
			}
		});
	}
	
	// Check information of all textFields
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
			JOptionPane.showMessageDialog(this.editBookView, "Các trường dữ liệu không được để trống");
			return false;
		}
		// Check namXB and soLuong are integer?
		try {
			String strNamXB = bookInformation.getTfNamXB().getText().toString();
			//if (strNamXB.length() == 0) continue;
			int namXB = Integer.parseInt(bookInformation.getTfNamXB().getText().toString());
			int soLuong = Integer.parseInt(bookInformation.getTfSoLuong().getText().toString().trim());
			// Test < 0 ????
			if(namXB < 0 || soLuong <0 || namXB > 9999 || namXB < 1000) {
				JOptionPane.showMessageDialog(this.editBookView, "Nhập đúng định dạng các trường số !!!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.editBookView, "Năm xuất bản và số lượng phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		return true;
		
		/* Check if maSach is exist*/
		
		///////////////////////////////...............//////////
	}
	
	// Edit Book
	private void suaSach() {
		if (checkInfor(bookInformation)) {
			String maSachMoi = bookInformation.getTfMaSach().getText().toString();
			String tenSachMoi = bookInformation.getTfTenSach().getText().toString();
			String tacGiaMoi = bookInformation.getTfTacGia().getText().toString();
			String nxbMoi = bookInformation.getTfNXB().getText().toString();
			String theLoaiMoi = bookInformation.getTfTheLoai().getText().toString();
			String namXBMoi = bookInformation.getTfNamXB().getText().toString();
			
			bookDB.updateBook(this.book, maSachMoi, tenSachMoi, tacGiaMoi, nxbMoi, theLoaiMoi, namXBMoi);
			tableBookView.updateTable(bookDB.getAllBooks());
			
			this.editBookView.setVisible(false);
		}
		else {
			System.out.println("Insert Fail !!!");
		}
	}		
	
	//Cancel
	private void huy() {		
		this.editBookView.setVisible(false);
	}
}
