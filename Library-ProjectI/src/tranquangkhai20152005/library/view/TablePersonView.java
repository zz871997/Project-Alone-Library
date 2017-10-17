package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import tranquangkhai20152005.library.model.Person;

public class TablePersonView extends JPanel{
	private JTable table;
	public static final int TABLE_BOOK_WIDTH = 900;
	public static final int TABLE_BOOK_HEIGHT = 250;
	private String[] titleItem = {"Mã", "Họ tên", "Năm sinh", "Giới tính", "Quê quán", "Địa chỉ", "Số điện thoại"};
	
	
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}

	public TablePersonView() {
		setLayout(new BorderLayout());
		add(createBookPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createBookPanel() {
		JPanel bookPanel = new JPanel();
		bookPanel.add(createTablePanel());
		return bookPanel;
	}
	
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
	
	// Load data
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
	
	// Update model of table
	public void updateTable(ArrayList<Person> list) {
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
	
	// Convert list of Person => Array 2D
	private String[][] convertData(ArrayList<Person> list) {
		int size = list.size();
		String data[][] = new String[size][titleItem.length];
		for (int i = 0; i < size; i++) {
			Person aPerson = list.get(i);
			data[i][0] = aPerson.getId();
			data[i][1] = aPerson.getName();
			data[i][2] = aPerson.getNamSinh();
			data[i][3] = aPerson.getGioiTinh();
			data[i][4] = aPerson.getQueQuan();
			data[i][5] = aPerson.getDiaChi();
			data[i][6] = aPerson.getSdt();		
		}
		return data;
	}
}
