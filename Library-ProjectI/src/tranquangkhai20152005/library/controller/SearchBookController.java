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
import tranquangkhai20152005.library.model.BookDB;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBookView;

public class SearchBookController {
	private MainUI mainUI;
	private Book book;
	private BookDB bookDB;
	
	private ArrayList<Book> resultSearch = new ArrayList<Book>();
	
	private JTextField tfSearch;
	private JComboBox cbSearchType;
	
	private TableBookView tableBookView;
	private int typeSearch;
	
	private final Color COLOR_DEFAULT   = Color.WHITE;
	private final Color COLOR_NOT_FOUND = Color.PINK;

	// Setter - Getter
	public ArrayList<Book> getResultSearch() {
		return resultSearch;
	}
	public void setResultSearch(ArrayList<Book> resultSearch) {
		this.resultSearch = resultSearch;
	}

	// Constructor
	public SearchBookController() {
	
	}
	
	public SearchBookController(MainUI mainUI) {
		this.mainUI = mainUI;
		bookDB = new BookDB();
		tableBookView = mainUI.getTableBookView();
		tfSearch = mainUI.getSearchBookView().getTfSearch();
		cbSearchType = mainUI.getSearchBookView().getCbSearch();
		
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
				tableBookView.updateTable(search(typeSearch));
				
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
	private ArrayList<Book> search(int typeSeach){
		ArrayList<Book> allBooks = bookDB.getAllBooks();
		int size = allBooks.size();
		resultSearch.clear();
		String textFind = tfSearch.getText().trim().toLowerCase();
		
		if (textFind.length() == 0) {
			return	bookDB.getAllBooks();
		}
		
		//Find tenSach
		if (typeSeach == 0) {
			for (int i = 0; i < size; i++) {
				// Get name of all books
				String name = allBooks.get(i).getTenSach().trim().toLowerCase();
				if (name.indexOf(textFind) >= 0) {
					resultSearch.add(allBooks.get(i));
				}
			}
		}
		
		//Find maSach
		if (typeSearch == 1) {
			for (int i = 0; i < size; i++) {
				// Get id of all books
				String id = allBooks.get(i).getMaSach().trim().toLowerCase();
				if (id.indexOf(textFind) >= 0) {
					resultSearch.add(allBooks.get(i));
				}
			}
		}
		
		// Find tacGia
		if (typeSeach == 2) {
			for (int i = 0; i < size; i++) {
				// Get tacGia of all books
				String tacGia = allBooks.get(i).getTacGia().trim().toLowerCase();
				if (tacGia.indexOf(textFind) >= 0) {
					resultSearch.add(allBooks.get(i));
				}
			}
		}
		
		// Find NXB
		if (typeSeach == 3) {
			for (int i = 0; i < size; i++) {
				// Get tacGia of all books
				String nxb = allBooks.get(i).getNXB().trim().toLowerCase();
				if (nxb.indexOf(textFind) >= 0) {
					resultSearch.add(allBooks.get(i));
				}
			}
		}
		
		//Find theLoai
		if (typeSeach == 4) {
			for (int i = 0; i < size; i++) {
				// Get tacGia of all books
				String theLoai = allBooks.get(i).getTheLoai().trim().toLowerCase();
				if (theLoai.indexOf(textFind) >= 0) {
					resultSearch.add(allBooks.get(i));
				}
			}
		}
		
		// Find namXB
		if (typeSeach == 5) {
			for (int i = 0; i < size; i++) {
				// Get tacGia of all books
				String namXB = allBooks.get(i).getNamXB().trim().toLowerCase();
				if (namXB.indexOf(textFind) >= 0) {
					resultSearch.add(allBooks.get(i));
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
			tableBookView.updateTable(search(typeSearch));
		}
		else {
			tableBookView.updateTable(bookDB.getAllBooks());
		}
	}
}
