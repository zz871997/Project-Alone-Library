package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MainUI extends JFrame{
	private JPanel loginPanel;
	private LoginView loginView = new LoginView();
	private JPanel mainPanel;
	
	private SearchBookView searchBookView;
	private TableBookView tableBookView;
	
	private TablePersonView tableUserView;
	private	SearchPersonView searchUserView;
	
	private TablePersonView tableEmploymentView;
	private SearchPersonView searchEmploymentView;
	
	private SearchBorrowView searchBorrowView;
	private TableBorrowView tableBorrowView;
	
	private MenuView menuView = new MenuView();
	private ManagerView managerView;
	
	private JPanel bookDataPanel = createBookDataPanel();
	private JPanel userDataPanel = createUserDataPanel();
	private JPanel employmentDataPanel = createEmploymentDataPanel();
	private JPanel borrowDataPanel = createBorrowDataPanel();
	
	private JPanel dataPanel;
	
	// Setter - Getter
	public LoginView getLoginView() {
		return loginView;
	}
	public JPanel getBorrowDataPanel() {
		return borrowDataPanel;
	}
	public SearchBorrowView getSearchBorrowView() {
		return searchBorrowView;
	}
	public TableBorrowView getTableBorrowView() {
		return tableBorrowView;
	}
	public JPanel getMainPanel() {
		return mainPanel;
	}
	public JPanel getLoginPanel() {
		return loginPanel;
	}
	public SearchBookView getSearchBookView() {
		return searchBookView;
	}
	public TableBookView getTableBookView() {
		return tableBookView;
	}
	public TablePersonView getTableUserView() {
		return tableUserView;
	}
	public SearchPersonView getSearchUserView() {
		return searchUserView;
	}
	public TablePersonView getTableEmploymentView() {
		return tableEmploymentView;
	}
	public SearchPersonView getSearchEmploymentView() {
		return searchEmploymentView;
	}
	public MenuView getMenuView() {
		return menuView;
	}
	public ManagerView getManagerView() {
		return managerView;
	}
	public JPanel getBookDataPanel() {
		return bookDataPanel;
	}
	public JPanel getUserDataPanel() {
		return userDataPanel;
	}
	public JPanel getEmploymentDataPanel() {
		return employmentDataPanel;
	}
	public JPanel getDataPanel() {
		return dataPanel;
	}
	
	// Constructor
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(this.getClass().getResource("/library-icon.png")).getImage());
		setSize(850, 500);
		setTitle("Demo quản lí thư viện by Tran Quang Khai");
		setResizable(false);
		setLocationRelativeTo(null);
		
		managerView = new ManagerView();
		
		setLayout(new BorderLayout(10, 10));
		
		add(createLoginPanel(), BorderLayout.PAGE_START);
		add(createMainPanel(), BorderLayout.CENTER);
		add(menuView, BorderLayout.WEST);
		
		pack();
		setVisible(true);
	}
	
	// Create LoginPanel
	private JPanel createLoginPanel() {
		loginPanel = new JPanel(new BorderLayout(5, 5));
		loginPanel.setBorder(new EmptyBorder(5, 270, 5, 270));
		loginPanel.add(loginView, BorderLayout.CENTER);
		return loginPanel;
	}
	
	// Create MainPanel
	private JPanel createMainPanel() {
		mainPanel = new JPanel(new BorderLayout(10, 10));
		
		dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		dataPanel.setBorder(new TitledBorder("Data Area"));
		dataPanel.add(bookDataPanel, BorderLayout.CENTER);
		
		mainPanel.add(dataPanel, BorderLayout.CENTER);
		mainPanel.add(managerView, BorderLayout.SOUTH);
		
		return mainPanel;
	}
	
	// Create DataPanel
	
	
	// Create Table Book Data
	public JPanel createBookDataPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		tableBookView = new TableBookView();
		searchBookView = new SearchBookView();
		
		panel.add(searchBookView, BorderLayout.NORTH);
		panel.add(tableBookView, BorderLayout.CENTER);
		return panel;
	}
	
	//Create Table User Data
	public JPanel createUserDataPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		tableUserView = new TablePersonView();
		searchUserView = new SearchPersonView();
	
		panel.add(searchUserView, BorderLayout.NORTH);
		panel.add(tableUserView, BorderLayout.CENTER);
		return panel;
	}
	
	//Create Table Employment Data
	public JPanel createEmploymentDataPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		tableEmploymentView = new TablePersonView();
		searchEmploymentView = new SearchPersonView();
	
		panel.add(searchEmploymentView, BorderLayout.NORTH);
		panel.add(tableEmploymentView, BorderLayout.CENTER);
		return panel;
	}
	
	/* Add "Create Table Borrow Data" */
	public JPanel createBorrowDataPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		tableBorrowView = new TableBorrowView();
		searchBorrowView = new SearchBorrowView();
		
		panel.add(searchBorrowView, BorderLayout.NORTH);
		panel.add(tableBorrowView, BorderLayout.CENTER);
		return panel;
	}
}
