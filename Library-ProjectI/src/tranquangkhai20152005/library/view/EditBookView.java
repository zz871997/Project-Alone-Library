package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditBookView extends JDialog{
	private MainUI mainUI;
	private BookInformation bookInformation;
	
	private JButton btnEdit   = new JButton("SỬA");
	private JButton btnCancel = new JButton("HỦY");
	
	// Constructor
	public EditBookView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Sửa Sản phẩm");
		
		bookInformation = new BookInformation();
		
		add(createMainPanel());
		pack();      					//Auto update UI
		setLocationRelativeTo(null);
	}
	
	// Setter - Getter
	public BookInformation getBookInformation() {
		return bookInformation;
	}
	public void setBookInformation(BookInformation bookInformation) {
		this.bookInformation = bookInformation;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}	
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(bookInformation, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.PAGE_END);
		return mainPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		btnEdit.setIcon(new ImageIcon(this.getClass().getResource("/edit-icon.png")));
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/cancel-icon.png")));
		
		panel.add(btnEdit);
		panel.add(btnCancel);
		return panel;
	}
}
