package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TKPersonView extends JDialog{
	private MainUI mainUI;
	private TKPersonInformation tkPersonInformation;
	
	private JButton btnPrint  = new JButton("IN");
	private JButton btnCancel = new JButton("HỦY");
	
	public TKPersonInformation getTKPersonInformation() {
		return tkPersonInformation;
	}
	public JButton getBtnPrint() {
		return btnPrint;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public TKPersonView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Thống kê");
		
		tkPersonInformation = new TKPersonInformation();
		
		add(createMainPanel());
		pack();
		setLocationRelativeTo(null);
	}
	
	private JPanel createMainPanel () {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(tkPersonInformation, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.PAGE_END);
		return mainPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 2, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		btnPrint.setIcon(new ImageIcon(this.getClass().getResource("/print-icon.png")));
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/cancel-icon.png")));
		
		panel.add(btnPrint);
		panel.add(btnCancel);
		return panel;
	}
}
