package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddLoanBookView extends JDialog{
	private MainUI mainUI;
	private LoanBookInformation loanBookInformation;
	
	private JButton btnAdd    = new JButton("THÊM MT");
	private JButton btnReset  = new JButton("TẠO LẠI");
	private JButton btnCancel = new JButton("HỦY");
	
	// Constructor
	public AddLoanBookView(MainUI mainUI) {
		this.mainUI = mainUI;
		setPreferredSize(new Dimension(960, 338));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);
		setTitle("Thêm mượn trả mới");
		
		loanBookInformation = new LoanBookInformation();
		
		add(createMainPanel());
		pack();
		setLocationRelativeTo(null);
	}

	public LoanBookInformation getLoanBookInformation() {
		return loanBookInformation;
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
		mainPanel.add(loanBookInformation, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.PAGE_END);
		return mainPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 3, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		btnAdd.setIcon(new ImageIcon(this.getClass().getResource("/add-icon.png")));
		btnReset.setIcon(new ImageIcon(this.getClass().getResource("/refresh-icon.png")));
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/cancel-icon.png")));
		
		panel.add(btnAdd);
		panel.add(btnReset);
		panel.add(btnCancel);
		return panel;
	}
}
