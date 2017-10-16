package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddPersonView extends JDialog{
	private MainUI mainUI;
	private PersonInformation personInformation;
	
	private JButton btnAdd    = new JButton("THÊM");
	private JButton btnReset  = new JButton("TẠO LẠI");
	private JButton btnCancel = new JButton("HỦY");
	
	public AddPersonView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Thêm sách mới");
		
		personInformation =  new PersonInformation();
		
		add(createMainPanel());
		pack();
		setLocationRelativeTo(null);
	}

	public PersonInformation getPersonInformation() {
		return personInformation;
	}
	public JButton getBtnAdd() {
		return btnAdd;
	}
	public JButton getBtnReset() {
		return btnReset;
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
		JPanel panel = new JPanel (new GridLayout(1, 3, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		panel.add(btnAdd);
		panel.add(btnReset);
		panel.add(btnCancel);
		return panel;
	}
	
}
