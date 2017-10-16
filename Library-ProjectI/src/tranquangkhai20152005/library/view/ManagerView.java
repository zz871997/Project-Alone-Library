package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ManagerView extends JPanel{
	private JButton btnAddBook     = new JButton("THÊM S");
	private JButton btnEditBook    = new JButton("SỬA S");
	private JButton btnDeleteBook  = new JButton("XÓA S");
	private JButton btnThongKeSach = new JButton("THỐNG KÊ S");
	
	
	private JButton btnAddUser     = new JButton("THÊM DG");
	private JButton btnEditUser    = new JButton("SỬA DG");
	private JButton btnDeleteUser  = new JButton("XÓA DG");
	private JButton btnThongKeUser = new JButton("THỐNG KÊ DG");
	
	private JButton btnAddEmployment     = new JButton("THÊM NV");
	private JButton btnEditEmployment    = new JButton("SỬA NV");
	private JButton btnDeleteEmployment  = new JButton("XÓA NV");
	private JButton btnThongKeEmployment = new JButton("THỐNG KÊ NV");
	
	private JPanel bookManagerPanel = createBookManagerPanel();
	private JPanel userManagerPanel = createUserManagerPanel();
	private JPanel employmentManagerPanel = createEmploymentManagerPanel();
	
	private JPanel managerPanel;
	
	
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
	public JPanel getBookManagerPanel() {
		return bookManagerPanel;
	}
	public JPanel getUserManagerPanel() {
		return userManagerPanel;
	}
	public JPanel getEmploymentManagerPanel() {
		return employmentManagerPanel;
	}
	public JPanel getManagerPanel() {
		return managerPanel;
	}


	public ManagerView () {
		setBorder(new EmptyBorder(30, 200, 10, 200));
		setLayout(new BorderLayout());
		managerPanel = bookManagerPanel;
		add(managerPanel, BorderLayout.CENTER);
	}
	
	/* Create Book Manager Panel */
	private JPanel createBookManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.add(createBookActionsPanel());
		panel.add(btnThongKeSach);
		return panel;
	}
	private JPanel createBookActionsPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 3, 10, 10));
		panel.add(btnAddBook);
		panel.add(btnEditBook);
		panel.add(btnDeleteBook);
		return panel;
	}
	
	/* Create User Manager Panel */
	private JPanel createUserManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.add(createUserActionsPanel());
		panel.add(btnThongKeUser);
		return panel;
	}
	private JPanel createUserActionsPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 3, 10, 10));
		panel.add(btnAddUser);
		panel.add(btnEditUser);
		panel.add(btnDeleteUser);
		return panel;
	}
	
	/* Create Employment Manager Panel */
	private JPanel createEmploymentManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.add(createEmploymentActionsPanel());
		panel.add(btnThongKeEmployment);
		return panel;
	}
	private JPanel createEmploymentActionsPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 3, 10, 10));
		panel.add(btnAddEmployment);
		panel.add(btnEditEmployment);
		panel.add(btnDeleteEmployment);
		return panel;
	}
}
