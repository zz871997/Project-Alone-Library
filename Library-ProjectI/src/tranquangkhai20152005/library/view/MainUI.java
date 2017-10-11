package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI extends JFrame{
	
	private TableBookView tableBookView;
	private ManagerView managerView;
	
	// Setter - Getter
	public TableBookView getTableBookView() {
		return tableBookView;
	}
	public void setTableBookView(TableBookView tableBookView) {
		this.tableBookView = tableBookView;
	}
	public ManagerView getManagerView() {
		return managerView;
	}
	public void setManagerView(ManagerView managerView) {
		this.managerView = managerView;
	}
	
	// Constructor
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 600);
		setTitle("Demo quản lí thư viện");
		setResizable(true);
		setLocationRelativeTo(null);
		
		managerView = new ManagerView();
		
		setLayout(new BorderLayout(10, 19));
		
		add(createTablePanel(), BorderLayout.CENTER);
		add(managerView, BorderLayout.PAGE_END);
		pack();
		setVisible(true);
	}
	
	// Create Table panel
	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		tableBookView = new TableBookView();
		
		panel.add(tableBookView);
		return panel;
		
	}
}
