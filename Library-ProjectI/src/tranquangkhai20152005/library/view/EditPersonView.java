package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditPersonView extends JDialog{
	private MainUI mainUI;
	private PersonInformation personInformation;
	
	private JButton btnEdit   = new JButton("SỬA");
	private JButton btnCancel = new JButton("HỦY");
	
	public EditPersonView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Thêm Sản phẩm");
		
		personInformation = new PersonInformation();
		
		add(createMainPanel());
		pack();      					//Auto update UI
		setLocationRelativeTo(null);
	}

	public PersonInformation getPersonInformation() {
		return personInformation;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(personInformation, BorderLayout.CENTER);
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
