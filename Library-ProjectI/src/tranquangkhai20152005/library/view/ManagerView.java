package tranquangkhai20152005.library.view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ManagerView extends JPanel{
	private JButton btnViewListBook = new JButton("DANH SÁCH SÁCH");
	private JButton btnAddBook      = new JButton("THÊM");
	private JButton btnEditBook     = new JButton("SỬA");
	private JButton btnDeleteBook   = new JButton("XÓA");
	private JButton btnThongKeSach  = new JButton("THỐNG KÊ");
	
	private JButton btnViewListUser = new JButton("DANH SÁCH ĐỘC GIẢ");
	private JButton btnAddUser      = new JButton("THÊM");
	private JButton btnEditUser     = new JButton("SỬA");
	private JButton btnDeleteUser   = new JButton("XÓA");
	private JButton btnThongKeUser  = new JButton("THỐNG KÊ");
	
	private JButton btnViewListEmployment = new JButton("DANH SÁCH NHÂN VIÊN");
	private JButton btnAddEmployment      = new JButton("THÊM");
	private JButton btnEditEmployment     = new JButton("SỬA");
	private JButton btnDeleteEmployment   = new JButton("XÓA");
	private JButton btnThongKeEmployment  = new JButton("THỐNG KÊ");
	
	// Constructor
	public ManagerView() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(1, 3, 10, 10));
		add(createBookManagerPanel());
		add(createUserManagerPanel());
		add(createEmploymentManagerPanel());
	}
	
	// Setter - getter
	public JButton getBtnViewListBook() {
		return btnViewListBook;
	}
	public JButton getBtnAddBook() {
		return btnAddBook;
	}
	public JButton getBtnEditBook() {
		return btnEditBook;
	}
	public JButton getBtnDeleteBook() {
		return btnDeleteBook;
	}
	public JButton getBtnThongKeSach() {
		return btnThongKeSach;
	}
	public JButton getBtnViewListUser() {
		return btnViewListUser;
	}
	public JButton getBtnAddUser() {
		return btnAddUser;
	}
	public JButton getBtnEditUser() {
		return btnEditUser;
	}
	public JButton getBtnDeleteUser() {
		return btnDeleteUser;
	}
	public JButton getBtnThongKeUser() {
		return btnThongKeUser;
	}
	public JButton getBtnViewListEmployment() {
		return btnViewListEmployment;
	}
	public JButton getBtnAddEmployment() {
		return btnAddEmployment;
	}
	public JButton getBtnEditEmployment() {
		return btnEditEmployment;
	}
	public JButton getBtnDeleteEmployment() {
		return btnDeleteEmployment;
	}
	public JButton getBtnThongKeEmployment() {
		return btnThongKeEmployment;
	}

	// Create Book Manager Panel
	private JPanel createBookManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
		TitledBorder title = BorderFactory.createTitledBorder("Quản lí sách");
		panel.setBorder(title);
		panel.add(btnViewListBook);
		panel.add(createBookActionsPanel());
		panel.add(btnThongKeSach);
		return panel;
	}
	
	private JPanel createBookActionsPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 5, 5));
		panel.add(btnAddBook);
		panel.add(btnEditBook);
		panel.add(btnDeleteBook);
		return panel;
	}
	
	// Create User Manager Panel
	private JPanel createUserManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
		TitledBorder title = BorderFactory.createTitledBorder("Quản lí độc giả");
		panel.setBorder(title);
		panel.add(btnViewListUser);
		panel.add(createUserActionsPanel());
		panel.add(btnThongKeUser);
		return panel;
	}
	private JPanel createUserActionsPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 5, 5));
		panel.add(btnAddUser);
		panel.add(btnEditUser);
		panel.add(btnDeleteUser);
		return panel;
	}
	
	// Create Employment Manager Panel
	private JPanel createEmploymentManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
		TitledBorder title = BorderFactory.createTitledBorder("Quản lí nhân viên");
		panel.setBorder(title);
		panel.add(btnViewListEmployment);
		panel.add(createEmploymentActionsPanel());
		panel.add(btnThongKeEmployment);
		return panel;
	}
	private JPanel createEmploymentActionsPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 5, 5));
		panel.add(btnAddEmployment);
		panel.add(btnEditEmployment);
		panel.add(btnDeleteEmployment);
		return panel;
	}
	
}
