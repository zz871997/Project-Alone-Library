package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class LoginView extends JPanel{
	private JPasswordField tfPass;
	private JButton btnLogin  = new JButton("ĐĂNG NHẬP");
	private JButton btnCancel = new JButton("HỦY");

	public JPasswordField getTfPass() {
		return tfPass;
	}
	public void setTfPass(JPasswordField tfPass) {
		this.tfPass = tfPass;
	}
	public JButton getBtnLogin() {
		return btnLogin;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	public LoginView() {
		setLayout(new BorderLayout());
		add(createMainPanel(), BorderLayout.CENTER);
		add (createButtonPanel(), BorderLayout.PAGE_END);
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel (new GridLayout(2, 2, 10, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel("Tài khoản "));
		panel.add(new JLabel("Admin"));
		panel.add(new JLabel("Mật khẩu"));
		panel.add(tfPass = new JPasswordField(20));
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.add(btnLogin);
		buttonPanel.add(btnCancel);
		return buttonPanel;
	}
}
