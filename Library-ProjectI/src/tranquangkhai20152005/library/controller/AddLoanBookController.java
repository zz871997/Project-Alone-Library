package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import tranquangkhai20152005.library.model.Book;
import tranquangkhai20152005.library.model.BookDB;
import tranquangkhai20152005.library.model.Detail;
import tranquangkhai20152005.library.model.DetailDB;
import tranquangkhai20152005.library.model.LoanBook;
import tranquangkhai20152005.library.model.LoanBookDB;
import tranquangkhai20152005.library.model.Person;
import tranquangkhai20152005.library.model.PersonDB;
import tranquangkhai20152005.library.view.AddLoanBookView;
import tranquangkhai20152005.library.view.BookIsLoanView;
import tranquangkhai20152005.library.view.LoanBookInformation;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBookView;
import tranquangkhai20152005.library.view.TableBorrowView;

public class AddLoanBookController {
	private MainUI mainUI;
	private LoanBook loanBook;
	private LoanBookDB loanBookDB;
	
	private AddLoanBookView addLoanBookView;
	private LoanBookInformation loanBookInformation;
	private TableBorrowView tableBorrowView;
	
	private ArrayList<Book> listBookIsLoan = new ArrayList<Book>();
	private JPanel rightPanel;
	//private JPanel bookIsLoanView;
	private ArrayList<BookIsLoanView> arrBookIsLoanView = new ArrayList<BookIsLoanView>();
	
	// Constructor
	public AddLoanBookController(MainUI mainUI) {
		this.mainUI = mainUI;
		loanBookDB = new LoanBookDB();
		JButton btnAddLoanBookManager = mainUI.getManagerView().getBtnBorrow();
		
		
		tableBorrowView = mainUI.getTableBorrowView();
		//Update Table
		tableBorrowView.updateTable(loanBookDB.getAllLoanBooks());
		
		btnAddLoanBookManager.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addLoanBookView     = new AddLoanBookView(mainUI);
				loanBookInformation = addLoanBookView.getLoanBookInformation();
				rightPanel = loanBookInformation.getRightPanel();
				
				loanBookInformation.getCbMaDG().addItem("-- Chon ma DG --");
				PersonDB personDB = new PersonDB();
				ArrayList<Person> listDG = personDB.getAllPersons("docgia");
				for (int i = 0; i < listDG.size(); i++) {
					loanBookInformation.getCbMaDG().addItem(listDG.get(i).getId());
				}
				
				loanBookInformation.getCbMaNV().addItem("-- Chon ma NV --");
				PersonDB personDB2 = new PersonDB();
				ArrayList<Person> listNV = personDB2.getAllPersons("nhanvien");
				for (int i = 0; i < listNV.size(); i++) {
					loanBookInformation.getCbMaNV().addItem(listNV.get(i).getId());
				}
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDate localDate   = LocalDate.now();
				String ngayMuonHT     = Integer.toString(localDate.getDayOfMonth());
				String thangMuonHT    = Integer.toString(localDate.getMonthValue());
				String namMuonHT      = Integer.toString(localDate.getYear());
				loanBookInformation.getTfNgayMuon().setText(ngayMuonHT + "-" + thangMuonHT + "-" + namMuonHT);
				loanBookInformation.getTfNgayMuon().setEditable(false);
				
				JButton btnAddBookIsLoan = loanBookInformation.getBtnAddThisBook();
				btnAddBookIsLoan.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setActionForBtnAddBookIsLoan();
						setActionOnRightPanel();
					}
				});
				
				
				
				JComboBox<String> cbMaDG = loanBookInformation.getCbMaDG();
				cbMaDG.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(cbMaDG.getSelectedIndex() == 0) {
							loanBookInformation.getLbHoTenDG().setText("");
							return;
						}
						String hotenDG = new PersonDB().getPerson("docgia", cbMaDG.getSelectedItem().toString()).getName();
						loanBookInformation.getLbHoTenDG().setText(hotenDG);
					}
				});
				
				JComboBox<String> cbMaNV = loanBookInformation.getCbMaNV();
				cbMaNV.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(cbMaNV.getSelectedIndex() == 0) {
							loanBookInformation.getLbHoTenNV().setText("");
							return;
						}
						String hotenNV = new PersonDB().getPerson("nhanvien", cbMaNV.getSelectedItem().toString()).getName();
						loanBookInformation.getLbHoTenNV().setText(hotenNV);
					}
				});
				
				addLoanBookView.setVisible(true);
				setMainActions();
			}
		});
	}
	
	// Set Action for btnAddBookIsLoan
	private void setActionForBtnAddBookIsLoan() {
		if (!checkBook(loanBookInformation)) {
				return;
		}
		else if (Integer.parseInt(loanBookInformation.getTfSoLuongSach().getText().toString()) > 
				new BookDB().getBook(loanBookInformation.getTfMaSach().getText().toString()).getSoLuong()) {
			JOptionPane.showMessageDialog(new JDialog(), "Số lượng sách mượn vượt quá số lượng sách còn trong thư viện" + "\n"
													   + "Hãy nhập lại số lượng");
			return;
		}
		else {
			String maSach     = loanBookInformation.getTfMaSach().getText().toString();
			String soLuong    = loanBookInformation.getTfSoLuongSach().getText().toString();
			
			Book book = new BookDB().getBook(maSach);
			String tenSach =  book.getTenSach();
			listBookIsLoan.add(book);
			
			BookIsLoanView bookIsLoanView = new BookIsLoanView(maSach, tenSach, soLuong);
			arrBookIsLoanView.add(bookIsLoanView);
			rightPanel.add(bookIsLoanView);
				
			rightPanel.revalidate();
			rightPanel.repaint();
			rightPanel.setVisible(true);
			loanBookInformation.getTfMaSach().setText("");
			loanBookInformation.getTfSoLuongSach().setText("");
				
		}
	}
	/* Check id of book and check text field "Add book" to loan*/
	/* Maximum of book is allowed loan is 10*/
	private boolean checkBook(LoanBookInformation loanBookInformation) {
		if (loanBookInformation.getTfMaSach().getText().toString().trim().equals("") ||
			loanBookInformation.getTfSoLuongSach().getText().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(new JDialog(), "Không để trống mã sách và số lượng sách");
				return false;
		}
		// Is soLuong a integer?
		try {
			int soLuong = Integer.parseInt(loanBookInformation.getTfSoLuongSach().getText().toString());
			// Test < 0 ????
			if(soLuong <= 0) {
				JOptionPane.showMessageDialog(this.addLoanBookView, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.addLoanBookView, "Số lượng phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		// Check maSach is exist?
		if (!checkMaSach(loanBookInformation.getTfMaSach().getText().toString())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã sách này không tồn tại" + "\n" +
														 "Chú ý mã sách phân biệt in hoa thường");
			return false;
		}
		// Check maximum book is allowed loan
		if (listBookIsLoan.size() >= 10) {
			JOptionPane.showMessageDialog(new JDialog(), "Không được mượn quá 10 loại sách");
			return false;
		}
		// Check if book is Loan
		for (int i = 0; i < listBookIsLoan.size(); i++) {
			if (loanBookInformation.getTfMaSach().getText().toString().equals(listBookIsLoan.get(i).getMaSach())){
				JOptionPane.showMessageDialog(new JDialog(), "Mã sách này đã được chọn" + "\n" + 
															 "Vui lòng thêm sách khác");
				return false;
			}
		}
		
		return true;
	}
	private boolean checkMaSach(String maSach) {
		BookDB bookDB = new BookDB();
		ArrayList<Book> listBook = bookDB.getAllBooks();
		for (int i = 0; i < listBook.size(); i++) {
			if (maSach.equals(listBook.get(i).getMaSach())) return true;
		}
		return false;
	}
	
	/* Actions on right panel */
	private void setActionOnRightPanel() {
		System.out.println(arrBookIsLoanView.size());
		for (int i = 0; i < arrBookIsLoanView.size(); i++) {
			BookIsLoanView bookIsLoanView = arrBookIsLoanView.get(i);
			JMenuItem menuItem    = new JMenuItem("Remove");
		    JPopupMenu menuPopup  = new JPopupMenu();
		    menuPopup.add(menuItem);
		    bookIsLoanView.setComponentPopupMenu(menuPopup);
		   
		    String maSach = bookIsLoanView.getLbMaSach().getText().toString();
		    
		    menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < listBookIsLoan.size(); j++) {
						if (listBookIsLoan.get(j).getMaSach().equals(maSach)) 
							listBookIsLoan.remove(j);
					}
					arrBookIsLoanView.remove(bookIsLoanView);
					rightPanel.remove(bookIsLoanView);
					rightPanel.revalidate();
					rightPanel.repaint();
				}
			});
			
		}
	}
	
	
	/*------------------------------Actions on AddLoanBookView----------------------*/
	// Check format of text field
	private boolean checkInfor(LoanBookInformation loanBookInformation) {
		// Are text fields empty?
		if (loanBookInformation.getTfMaMT().getText().toString().trim().equals("")        ||
			loanBookInformation.getTfNgayMuon().getText().toString().trim().equals("")    ||
			loanBookInformation.getCbMaDG().getSelectedIndex() == 0 ||
			loanBookInformation.getCbMaNV().getSelectedIndex() == 0 ||
			loanBookInformation.getTfTienCoc().getText().toString().trim().equals("")     ||
			listBookIsLoan.size() == 0) {
			System.out.println("Khong de trong cac truong du lieu");
			JOptionPane.showMessageDialog(this.addLoanBookView, "Các trường dữ liệu không được để trống");
			return false;
		}
		/* Check if maMT is exist */
		if (!checkMaMT(loanBookInformation.getTfMaMT().getText().toString())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã mượn trả đã tồn tại - Hãy nhập lại");
			return false;
		}
		/* Check ngayHenTra*/
		// Check format of ngayHenTra
		String month = loanBookInformation.getCbThangHenTra().getSelectedItem().toString();
		String date = loanBookInformation.getCbNgayHenTra().getSelectedItem().toString();
		if ((month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) && date.equals("31")) {
			JOptionPane.showMessageDialog(new JDialog(), "Tháng 4, 6, 9, 11 không có 31 ngày");
			return false;
		}
		if (month.equals("2") && (date.equals("30") || date.equals("31"))) {
			JOptionPane.showMessageDialog(new JDialog(), "Tháng 2 không có quá 29 ngày");
			return false;
		}
		
		// Check tienCoc is integer?
		try {
			int tienCoc = Integer.parseInt(loanBookInformation.getTfTienCoc().getText().toString());
			// Test < 0 ????
			if(tienCoc <= 0) {
				JOptionPane.showMessageDialog(this.addLoanBookView, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.addLoanBookView, "Tiền cọc phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}

	private boolean checkMaMT (String maMT) {
		ArrayList<LoanBook> listLoanBook = loanBookDB.getAllLoanBooks();
		for (int i = 0; i < listLoanBook.size(); i++) {
			String maMTFromDB = listLoanBook.get(i).getMaMT();
			if (maMT.equals(maMTFromDB)) return false;
		}
		return true;
	}

	/*----------------------------- SET MAIN ACTIONS _---------------------------*/
	private void setMainActions() {
		JButton btnThemMT   = addLoanBookView.getBtnAdd();
		JButton btnTaoLaiMT = addLoanBookView.getBtnReset();
		JButton btnHuyMT    = addLoanBookView.getBtnCancel();
		
		// Set actions for each button
		btnThemMT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				themMT();
			}
		});
		
		btnTaoLaiMT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taoLaiMT();
			}
		});
		
		btnHuyMT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huyMT();
			}
		});
	}

	private void themMT() {
		if (checkInfor(loanBookInformation)) {
			String maMT       = loanBookInformation.getTfMaMT().getText().toString();
			String maDG       = loanBookInformation.getCbMaDG().getSelectedItem().toString();
			String maNV       = loanBookInformation.getCbMaNV().getSelectedItem().toString();
			String ngayMuon   = loanBookInformation.getTfNgayMuon().getText().toString();
			String ngayHenTra = loanBookInformation.getCbNgayHenTra().getSelectedItem().toString() + "-"
							   + loanBookInformation.getCbThangHenTra().getSelectedItem().toString() + "-"
							   + loanBookInformation.getCbNamHenTra().getSelectedItem().toString();
			int tienCoc       = Integer.parseInt(loanBookInformation.getTfTienCoc().getText().trim().toString());
			
			loanBook = new LoanBook(maMT, maDG, maNV, ngayMuon, ngayHenTra, tienCoc);
			loanBookDB.insertLoanBook(loanBook);
			
//			for (int i  = 0; i < listBookIsLoan.size(); i++) {
//				Detail detail = new Detail(maMT, listBookIsLoan.get(i).getMaSach(),Integer.parseInt(loanBookInformation.getTfSoLuongSach().getText().trim().toString()) 
//										   , "", 0.0);
//				DetailDB detailDB = new DetailDB();
//				detailDB.insertDetail(detail);
//			}
			
			for (int i = 0; i < arrBookIsLoanView.size(); i++) {
				String maSach = arrBookIsLoanView.get(i).getLbMaSach().getText().trim().toString();
				String tenSach = arrBookIsLoanView.get(i).getLbTenSach().getText().trim().toString();
				int soLuongMuon = Integer.parseInt(arrBookIsLoanView.get(i).getLbSoluong().getText().trim().toString());
				Detail aDetail = new Detail(maMT, maSach, soLuongMuon, "", 0.0);
				DetailDB detailDB = new DetailDB();
				detailDB.insertDetail(aDetail);
			}
			
			
			// Recreate number of Book is Loan
			for (int i = 0; i < listBookIsLoan.size(); i++) {
				int soLuongMoi = listBookIsLoan.get(i).getSoLuong() - Integer.parseInt(arrBookIsLoanView.get(i).getLbSoluong().getText().toString());
				BookDB bookDB = new BookDB();
				bookDB.updateBook(listBookIsLoan.get(i), soLuongMoi);
			}
			TableBookView tableBookView = mainUI.getTableBookView();
			tableBookView.updateTable(new BookDB().getAllBooks());
			
			
			tableBorrowView.updateTable(loanBookDB.getAllLoanBooks());
			clearInput();
			this.addLoanBookView.setVisible(false);
		}
		else {
			System.out.println("Insert loanBook is fail !!!");
		}
	}

	private void taoLaiMT() {
		clearInput();
	}

	private void huyMT() {
		clearInput();
		this.addLoanBookView.setVisible(false);
	}
	
	private void clearInput() {
		loanBookInformation.getTfMaMT().setText("");
		loanBookInformation.getTfMaSach().setText("");
		loanBookInformation.getTfSoLuongSach().setText("");
		loanBookInformation.getTfTienCoc().setText("");
		loanBookInformation.getCbMaDG().setSelectedIndex(0);
		loanBookInformation.getCbMaNV().setSelectedIndex(0);
		loanBookInformation.getCbNgayHenTra().setSelectedIndex(0);
		loanBookInformation.getCbThangHenTra().setSelectedIndex(0);
		loanBookInformation.getCbNamHenTra().setSelectedIndex(0);
		arrBookIsLoanView.clear();
		listBookIsLoan.clear();
	}

}
