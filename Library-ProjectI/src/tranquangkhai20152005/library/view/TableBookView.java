package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import tranquangkhai20152005.library.model.Book;

public class TableBookView extends JPanel{
	private JTable table;
	public static final int TABLE_BOOK_WIDTH = 900;
	public static final int TABLE_BOOK_HEIGHT = 200;
	private String[] titleItem = {"Mã sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Thể loại", "Năm xuất bản", "Số lượng"};
	
	// setter - getter
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	
	// Constructor
	public TableBookView() {
		setLayout(new BorderLayout());
		add(createBookPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createBookPanel() {
		JPanel bookPanel = new JPanel();
		bookPanel.add(createTablePanel());
		return bookPanel;
	}
	
	// Create Table Panel
	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		table = createTable();
		//Load data...?????????????
		loadData(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(TABLE_BOOK_WIDTH, TABLE_BOOK_HEIGHT));
		panel.add(scroll, BorderLayout.CENTER);
		
		return panel;
	}
	
	// Create table
	private JTable createTable() {
		JTable table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}
	
	// Load Data
	private void loadData(JTable table) {
		String data[][] = null;
		DefaultTableModel tableModel = new DefaultTableModel(data, titleItem) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.setModel(tableModel);		
	}
	
	// Update Model of Table
	public void updateTable(ArrayList<Book> list) {
		String data[][] = convertData(list);
		DefaultTableModel tableModel = new DefaultTableModel(data, titleItem) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.setModel(tableModel);	
	}
	
	// Convert list of Book => Array 2D
	private String[][] convertData(ArrayList<Book> list) {
		int size = list.size();
		String data[][] = new String[size][titleItem.length];
		for (int i = 0; i < size; i++) {
			Book aBook = list.get(i);
			data[i][0] = aBook.getMaSach();
			data[i][1] = aBook.getTenSach();
			data[i][2] = aBook.getTacGia();
			data[i][3] = aBook.getNXB();
			data[i][4] = aBook.getTheLoai();
			data[i][5] = aBook.getNamXB();
			data[i][6] = aBook.getSoLuong()+"";		
		}
		return data;
	}
}
