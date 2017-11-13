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

public class TableDetailVew extends JPanel {
	private JTable table;
	public static final int TABLE_DETAIL_WIDTH = 500;
	public static final int TABLE_DETAIL_HEIGHT = 200;
	private String[] titleItem = {"Mã sách", "Tên sách", "Số lượng mượn"};
	
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	
	public TableDetailVew() {
		setLayout(new BorderLayout());
		add(createDetailPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createDetailPanel() {
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
		scroll.setPreferredSize(new Dimension(TABLE_DETAIL_WIDTH, TABLE_DETAIL_HEIGHT));
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
	public void updateTable(String[][] listBook) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = listBook;
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
}
