package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ManagerView extends JPanel{
	private JButton btnAddBook      = new JButton("THÊM S");
	private JButton btnAddFromExcel = new JButton("THÊM EXCEL");
	private JButton btnEditBook     = new JButton("SỬA S");
	private JButton btnDeleteBook   = new JButton("XÓA S");
	private JButton btnThongKeSach  = new JButton("THỐNG KÊ S");
	private String[] listTKSach     = {"--Chọn kiểu thống kê--","Tác giả", "Nhà xuất bản", "Thể loại", "Năm xuất bản"};
	private JComboBox<String> cbTKSach = new JComboBox<String>(listTKSach);
	
	private JButton btnAddUser      = new JButton("THÊM DG");
	private JButton btnAddUserExcel = new JButton ("THÊM ĐG EXCEL");
	private JButton btnEditUser     = new JButton("SỬA DG");
	private JButton btnDeleteUser   = new JButton("XÓA DG");
	private JButton btnThongKeUser  = new JButton("THỐNG KÊ DG");
	private String[] listTKPerson   = {"--Chọn kiểu thống kê--","Năm sinh", "Giới tính", "Quê quán", "Địa chỉ"};
	private JComboBox<String> cbTKUser = new JComboBox<String>(listTKPerson);
	
	private JButton btnAddEmployment      = new JButton("THÊM NV");
	private JButton btnAddEmploymentExcel = new JButton("THÊM NV EXCEL");
	private JButton btnEditEmployment     = new JButton("SỬA NV");
	private JButton btnDeleteEmployment   = new JButton("XÓA NV");
	private JButton btnThongKeEmployment  = new JButton("THỐNG KÊ NV");
	private JComboBox<String> cbTKEmployment = new JComboBox<String>(listTKPerson);
	
	private JButton btnBorrow       = new JButton("THÊM MƯỢN");
	private JButton btnEditBorrow   = new JButton("SỬA MT");
	private JButton btnDeleteBorrow = new JButton("XÓA MT");
	private JButton btnViewDetail   = new JButton("XEM CHI TIẾT");
	private JButton btnThongKeMuon  = new JButton("THỐNG KÊ");
	private String[] listTKLoanBook = {"--Chọn kiểu thống kê--", "Mã độc giả", "Mã nhân viên", "Ngày mượn", "Ngày hẹn trả",
										"Độc giả vi phạm", "Tổng tiền phạt của độc giả", "Tổng sách mượn của độc giả", 
										"Số sách chưa trả của độc giả"};
	private JComboBox<String> cbTKLoanBook = new JComboBox<String>(listTKLoanBook);
	
	private JPanel bookManagerPanel = createBookManagerPanel();
	private JPanel userManagerPanel = createUserManagerPanel();
	private JPanel employmentManagerPanel = createEmploymentManagerPanel();
	private JPanel borrowManagerPanel = createBorrowManagerPanel();
	
	private JPanel managerPanel;
	
	public JComboBox<String> getCbTKLoanBook() {
		return cbTKLoanBook;
	}
	public JComboBox<String> getCbTKSach() {
		return cbTKSach;
	}
	public JButton getBtnDeleteBorrow() {
		return btnDeleteBorrow;
	}
	public JComboBox<String> getCbTKUser() {
		return cbTKUser;
	}
	public JComboBox<String> getCbTKEmployment() {
		return cbTKEmployment;
	}
	public JButton getBtnAddBook() {
		return btnAddBook;
	}
	public JButton getBtnAddFromExcel() {
		return btnAddFromExcel;
	}
	public JButton getBtnAddUserExcel() {
		return btnAddUserExcel;
	}
	public JButton getBtnAddEmploymentExcel() {
		return btnAddEmploymentExcel;
	}
	public JPanel getBorrowManagerPanel() {
		return borrowManagerPanel;
	}
	public JButton getBtnBorrow() {
		return btnBorrow;
	}
	public JButton getBtnEditBorrow() {
		return btnEditBorrow;
	}
	public JButton getBtnViewDetail() {
		return btnViewDetail;
	}
	public JButton getBtnThongKeMuon() {
		return btnThongKeMuon;
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
		setBorder(new EmptyBorder(10, 150, 10, 150));
		setLayout(new BorderLayout());
		managerPanel = bookManagerPanel;
		add(managerPanel, BorderLayout.CENTER);
	}
	
	/* Create Book Manager Panel */
	private JPanel createBookManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.add(createBookActionsPanel());
		panel.add(createTKSachPanel());
		return panel;
	}
	private JPanel createBookActionsPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 4, 10, 10));
		btnAddBook.setIcon(new ImageIcon(this.getClass().getResource("/book-add-icon.png")));
		btnAddFromExcel.setIcon(new ImageIcon(this.getClass().getResource("/book-add-icon.png")));
		btnEditBook.setIcon(new ImageIcon(this.getClass().getResource("/edit-book-icon.png")));
		btnDeleteBook.setIcon(new ImageIcon(this.getClass().getResource("/delete-book-icon.png")));
		panel.add(btnAddBook);
		
		panel.add(btnAddFromExcel);
		panel.add(btnEditBook);
		panel.add(btnDeleteBook);
		return panel;
	}
	private JPanel createTKSachPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 2, 10, 10));
		btnThongKeSach.setIcon(new ImageIcon(this.getClass().getResource("/chart-add-icon.png")));
		panel.add(cbTKSach);
		panel.add(btnThongKeSach);
		return panel;
	}
	
	/* Create User Manager Panel */
	private JPanel createUserManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.add(createUserActionsPanel());
		panel.add(createTKUserPanel());
		return panel;
	}
	
	private JPanel createUserActionsPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 4, 10, 10));
		
		btnAddUser.setIcon(new ImageIcon(this.getClass().getResource("/user-add-icon.png")));
		btnAddUserExcel.setIcon(new ImageIcon(this.getClass().getResource("/user-add-icon.png")));
		btnEditUser.setIcon(new ImageIcon(this.getClass().getResource("/edit-user-icon.png")));
		btnDeleteUser.setIcon(new ImageIcon(this.getClass().getResource("/delete-user-icon.png")));
		
		panel.add(btnAddUser);
		panel.add(btnAddUserExcel);
		panel.add(btnEditUser);
		panel.add(btnDeleteUser);
		return panel;
	}
	private JPanel createTKUserPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 2, 10, 10));
		btnThongKeUser.setIcon(new ImageIcon(this.getClass().getResource("/chart-add-icon.png")));
		panel.add(cbTKUser);
		panel.add(btnThongKeUser);
		return panel;
	}
	
	/* Create Employment Manager Panel */
	private JPanel createEmploymentManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.add(createEmploymentActionsPanel());
		
		panel.add(createTKEmploymentPanel());
		return panel;
	}
	
	private JPanel createTKEmploymentPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 2, 10, 10));
		btnThongKeEmployment.setIcon(new ImageIcon(this.getClass().getResource("/chart-add-icon.png")));
		panel.add(cbTKEmployment);
		panel.add(btnThongKeEmployment);
		return panel;	
	}
	
	private JPanel createEmploymentActionsPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 4, 10, 10));
		
		btnAddEmployment.setIcon(new ImageIcon(this.getClass().getResource("/add-employee-icon.png")));
		btnAddEmploymentExcel.setIcon(new ImageIcon(this.getClass().getResource("/add-employee-icon.png")));
		btnEditEmployment.setIcon(new ImageIcon(this.getClass().getResource("/edit-employee-icon.png")));
		btnDeleteEmployment.setIcon(new ImageIcon(this.getClass().getResource("/delete-employee-icon.png")));
		
		panel.add(btnAddEmployment);
		panel.add(btnAddEmploymentExcel);
		panel.add(btnEditEmployment);
		panel.add(btnDeleteEmployment);
		return panel;
	}
	
	/* Create Borrow Manager Panel */
	private JPanel createBorrowManagerPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.add(createLoanBookActionPanel());
		panel.add(createTKLoanBookPanel());
		
		return panel;
	}
	
	private JPanel createLoanBookActionPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 4, 10, 10));
		btnBorrow.setIcon(new ImageIcon(this.getClass().getResource("/add-loan-icon.png")));
		btnEditBorrow.setIcon(new ImageIcon(this.getClass().getResource("/edit-icon.png")));
		btnDeleteBorrow.setIcon(new ImageIcon(this.getClass().getResource("/cancel-icon.png")));
		btnViewDetail.setIcon(new ImageIcon(this.getClass().getResource("/detail-icon.png")));
		panel.add(btnBorrow);
		panel.add(btnEditBorrow);
		panel.add(btnDeleteBorrow);
		panel.add(btnViewDetail);
		return panel;
	}
	
	private JPanel createTKLoanBookPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
		btnThongKeMuon.setIcon(new ImageIcon(this.getClass().getResource("/chart-add-icon.png")));
		panel.add(cbTKLoanBook);
		panel.add(btnThongKeMuon);
		return panel;
	}
}