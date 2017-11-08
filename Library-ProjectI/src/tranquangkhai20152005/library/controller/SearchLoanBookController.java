package tranquangkhai20152005.library.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import tranquangkhai20152005.library.model.Book;
import tranquangkhai20152005.library.model.LoanBook;
import tranquangkhai20152005.library.model.LoanBookDB;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBorrowView;

public class SearchLoanBookController {
	private MainUI mainUI;
	private LoanBook loanBook;
	private LoanBookDB loanBookDB;
	
	private ArrayList<LoanBook> resultSearch = new ArrayList<LoanBook>();
	
	private JTextField tfSearch;
	private JComboBox cbSearchType;
	
	private TableBorrowView tableLoanBookView;
	private int typeSearch;
	
	private final Color COLOR_DEFAULT   = Color.WHITE;
	private final Color COLOR_NOT_FOUND = Color.PINK;
	
	public ArrayList<LoanBook> getResultSearch() {
		return resultSearch;
	}
	
	public SearchLoanBookController(MainUI mainUI) {
		this.mainUI = mainUI;
		loanBookDB = new LoanBookDB();
		tableLoanBookView = mainUI.getTableBorrowView();
		tfSearch = mainUI.getSearchBorrowView().getTfSearch();
		cbSearchType = mainUI.getSearchBorrowView().getCbSearch();
		
		setActions();
	}
	
	/*--------------------------Set Actions---------------------------- */
	private void setActions() {
		tfSearch.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			/* Event when press a button on keyboard */
			@Override
			public void keyReleased(KeyEvent e) {
				typeSearch = cbSearchType.getSelectedIndex();
				tableLoanBookView.updateTable(search(typeSearch));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		/* ConmboBox's Event */
		cbSearchType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetSearch();
				return;
			}
		});
	}
	
	// Search Function
	private ArrayList<LoanBook> search(int typeSearch) {
		ArrayList<LoanBook> allLoanBooks = loanBookDB.getAllLoanBooks();
		int size = allLoanBooks.size();
		resultSearch.clear();
		String textFind = tfSearch.getText().trim().toLowerCase();
		
		if (textFind.length() == 0) {
			return loanBookDB.getAllLoanBooks();
		}
		
		//Find MaMT
		if (typeSearch == 0) {
			for (int i = 0; i < size; i++) {
				String maMT = allLoanBooks.get(i).getMaMT().trim().toLowerCase();
				if (maMT.indexOf(textFind) >= 0) {
					resultSearch.add(allLoanBooks.get(i));
				}
			}
		}
		
		// Find MaDG
		if (typeSearch == 1) {
			for (int i = 0; i < size; i++) {
				String maDG = allLoanBooks.get(i).getMaDG().trim().toLowerCase();
				if (maDG.indexOf(textFind) >= 0) {
					resultSearch.add(allLoanBooks.get(i));
				}
			}
		}
		
		// Find MaNV
		if (typeSearch == 2) {
			for (int i = 0; i < size; i++) {
				String maNV = allLoanBooks.get(i).getMaNV().trim().toLowerCase();
				if (maNV.indexOf(textFind) >= 0) {
					resultSearch.add(allLoanBooks.get(i));
				}
			}
		}
		
		// Find ngayMuon
		if (typeSearch == 3) {
			for (int i = 0; i < size; i++) {
				String ngayMuon = allLoanBooks.get(i).getNgayMuon().trim().toLowerCase();
				if (ngayMuon.indexOf(textFind) >= 0) {
					resultSearch.add(allLoanBooks.get(i));
				}
			}
		}
		
		// Find ngayHenTra
		if (typeSearch == 4) {
			for (int i = 0; i < size; i++) {
				String ngayHenTra = allLoanBooks.get(i).getNgayHenTra().trim().toLowerCase();
				if (ngayHenTra.indexOf(textFind) >= 0) {
					resultSearch.add(allLoanBooks.get(i));
				}
			}
		}
		
		// Set color for textField Search
		if (resultSearch.size() == 0) {
			tfSearch.setBackground(COLOR_NOT_FOUND);
		}
		else {
			tfSearch.setBackground(COLOR_DEFAULT);
		}
		
		return resultSearch;
	}
	
	// Reset Search when cbSearch is changed
	private void resetSearch() {
		typeSearch = cbSearchType.getSelectedIndex();
		tfSearch.setText("");
		tfSearch.requestFocus();
		tfSearch.setBackground(COLOR_DEFAULT);
		updateData();
	}
	
	private void updateData() {
		if (resultSearch.size() > 0) {
			tableLoanBookView.updateTable(search(typeSearch));
		}
		else {
			tableLoanBookView.updateTable(loanBookDB.getAllLoanBooks());
		}
	}
}
