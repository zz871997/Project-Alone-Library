package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class TableThongKeView extends JPanel{
	private JTable table;
	public static final int TABLE_TK_WIDTH = 500;
	public static final int TABLE_TK_HEIGHT = 250;
	
	private String[] titleItem = {"STT", "THUỘC TÍNH", "SỐ LƯỢNG"};
	private String[] title1 = {"STT", "TÁC GIẢ", "SỐ LƯỢNG"};
	private String[] title2 = {"STT", "NHÀ XUẤT BẢN", "SỐ LƯỢNG"};
	private String[] title3 = {"STT", "THỂ LOẠI", "SỐ LƯỢNG"};
	private String[] title4 = {"STT", "NĂM XUẤT BẢN", "SỐ LƯỢNG"};
	
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	
	public TableThongKeView() {
		setLayout(new BorderLayout());
		add(createThongKePanel(), BorderLayout.CENTER);
	}
	
	private JPanel createThongKePanel() {
		JPanel thongKePanel = new JPanel();
		thongKePanel.add(createTablePanel());
		return thongKePanel;
	}
	
	// Create table Panel
	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		table = createTable();
		//Load data...?????????????
		loadData(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(TABLE_TK_WIDTH, TABLE_TK_HEIGHT));
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
	public void updateTable(String[][] listResult, int mode) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = listResult;
			DefaultTableModel tableModel = new DefaultTableModel(data, changeTitle(mode)) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);	
		}});
	}
	
	private String[] changeTitle(int mode) {
		if (mode == 1) return title1;
		else if (mode == 2) return title2;
		else if (mode == 3) return title3;
		else if (mode == 4) return title4;
		return null;
	}
}

