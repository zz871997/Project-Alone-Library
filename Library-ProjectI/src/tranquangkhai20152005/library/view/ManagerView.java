package tranquangkhai20152005.library.view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ManagerView extends JPanel{
	private JButton btnViewListBook = new JButton("DANH SÁCH SÁCH");
	private JButton btnAddBook = new JButton("THÊM");
	private JButton btnEditBook = new JButton("SỬA");
	private JButton btnDeleteBook = new JButton("XÓA");
	private JButton btnThongKeSach = new JButton("THỐNG KÊ");
	
	// Constructor
	public ManagerView() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(1, 3, 10, 10));
		add(createBookManagerPanel());
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
	
	
	
	
	
	
}
