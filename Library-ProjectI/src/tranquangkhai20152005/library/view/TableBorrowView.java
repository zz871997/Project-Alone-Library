package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import tranquangkhai20152005.library.model.Book;
import tranquangkhai20152005.library.model.LoanBook;

public class TableBorrowView extends JPanel{
	private JTable table;
	public static final int TABLE_BOOK_WIDTH = 800;
	public static final int TABLE_BOOK_HEIGHT = 200;
	private String[] titleItem = {"Mã mượn", "Mã độc giả", "Mã nhân viên", "Ngày mượn", "Ngày hẹn trả"};
	
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	
	public TableBorrowView() {
		setLayout(new BorderLayout());
		add(createBorrowPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createBorrowPanel() {
		JPanel borrowPanel = new JPanel();
		borrowPanel.add(createTablePanel());
		return borrowPanel;
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
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}
	
	// Load Data
	private void loadData(JTable table) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
			String data[][] = null;
		    //Update the model here
			DefaultTableModel tableModel = new DefaultTableModel(data, titleItem) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);		
		}});
	}
	
	// Update Model of Table
	public void updateTable(ArrayList<LoanBook> list) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = convertData(list);
			DefaultTableModel tableModel = new DefaultTableModel(data, titleItem) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);	
		}});
	}
//	
	// Convert list of Book => Array 2D
	private String[][] convertData(ArrayList<LoanBook> list) {
		int size = list.size();
		String data[][] = new String[size][titleItem.length];
		for (int i = 0; i < size; i++) {
			LoanBook aLoanBook = list.get(i);
			data[i][0] = aLoanBook.getMaMT();
			data[i][1] = aLoanBook.getMaDG();
			data[i][2] = aLoanBook.getMaNV();
			data[i][3] = aLoanBook.getNgayMuon();
			data[i][4] = aLoanBook.getNgayHenTra();
		}
		return data;
	}
}
