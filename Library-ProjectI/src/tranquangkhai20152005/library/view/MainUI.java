package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI extends JFrame{
	private SearchBookView searchBookView;
	private TableBookView tableBookView;
	
	
	private TablePersonView tablePersonView;
	private	SearchPersonView searchPersonView;
	
	private ManagerView managerView;
	
	private JPanel bookDataPanel = createBookDataPanel();
	private JPanel userDataPanel = createUserDataPanel();
	private JPanel employmentDataPanel = createEmploymentDataPanel();
	
	private JPanel dataPanel;
	
	
	
	
	
	// Setter - Getter
	public void setDataPanel(JPanel dataPanel) {
		this.dataPanel = dataPanel;
	}
	public SearchPersonView getSearchPersonView() {
		return searchPersonView;
	}
	public void setSearchPersonView(SearchPersonView searchPersonView) {
		this.searchPersonView = searchPersonView;
	}
	public JPanel getBookDataPanel() {
		return bookDataPanel;
	}
	public void setBookDataPanel(JPanel bookDataPanel) {
		this.bookDataPanel = bookDataPanel;
	}
	public JPanel getUserDataPanel() {
		return userDataPanel;
	}
	public void setUserDataPanel(JPanel userDataPanel) {
		this.userDataPanel = userDataPanel;
	}
	public JPanel getEmploymentDataPanel() {
		return employmentDataPanel;
	}
	public void setEmploymentDataPanel(JPanel employmentDataPanel) {
		this.employmentDataPanel = employmentDataPanel;
	}
	public JPanel getDataPanel() {
		return dataPanel;
	}
	
	public TableBookView getTableBookView() {
		return tableBookView;
	}
	public void setTableBookView(TableBookView tableBookView) {
		this.tableBookView = tableBookView;
	}
	public TablePersonView getTablePersonView() {
		return tablePersonView;
	}
	public void setTablePersonView(TablePersonView tablePersonView) {
		this.tablePersonView = tablePersonView;
	}
	
	public ManagerView getManagerView() {
		return managerView;
	}
	public void setManagerView(ManagerView managerView) {
		this.managerView = managerView;
	}
	public SearchBookView getSearchBookView() {
		return searchBookView;
	}
	public void setSearchBookView(SearchBookView searchBookView) {
		this.searchBookView = searchBookView;
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
		
		/////////////////////////////////
		dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		dataPanel.add(bookDataPanel, BorderLayout.CENTER);;
		add(dataPanel, BorderLayout.CENTER);
		add(managerView, BorderLayout.PAGE_END);
		pack();
		setVisible(true);
	}
	
//	// Create data panel
//	
//	
//	
//	// Create Table panel
//	private JPanel createTablePanel() {
//		JPanel panel = new JPanel(new BorderLayout());
//		tableBookView = new TableBookView();
//		searchBookView = new SearchBookView();
//		
//		panel.add(searchBookView, BorderLayout.PAGE_START);
//		panel.add(tableBookView, BorderLayout.CENTER);
//		return panel;
//		
//	}
	
	// Create Table Book Data
	public JPanel createBookDataPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		tableBookView = new TableBookView();
		searchBookView = new SearchBookView();
		
		panel.add(searchBookView, BorderLayout.PAGE_START);
		panel.add(tableBookView, BorderLayout.CENTER);
		return panel;
	}
	
	//Create Table User Data
	public JPanel createUserDataPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		tablePersonView = new TablePersonView();
		searchPersonView = new SearchPersonView();
	
		panel.add(searchPersonView, BorderLayout.PAGE_START);
		panel.add(tablePersonView, BorderLayout.CENTER);
		return panel;
	}
	
	//Create Table Employment Data
	public JPanel createEmploymentDataPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		tablePersonView = new TablePersonView();
		searchPersonView = new SearchPersonView();
	
		panel.add(searchPersonView, BorderLayout.PAGE_START);
		panel.add(tablePersonView, BorderLayout.CENTER);
		return panel;
	}
	
}
