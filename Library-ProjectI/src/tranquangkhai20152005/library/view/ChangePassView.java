package tranquangkhai20152005.library.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class ChangePassView extends JDialog{
	private MainUI mainUI;
	private JButton btnChange = new JButton("ĐỔI MẬT KHẨU");
	private JButton btnCancel = new JButton("HỦY");
		
	private JPasswordField tfOldPass;
	private JPasswordField tfNewPass;
	private JPasswordField tfReNewPass;
		
	public ChangePassView (MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Đổi mật khẩu");
		setLocationRelativeTo(null);
			
		add(createMainPanel());
		pack();
	}
		
		
	/* Setter - Getter */
	public JButton getBtnChange() {
		return btnChange;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public JPasswordField getTfOldPass() {
		return tfOldPass;
	}
	public JPasswordField getTfNewPass() {
		return tfNewPass;
	}
	public JPasswordField getTfReNewPass() {
		return tfReNewPass;
	}
	
	// Create Main Panel
	private JPanel createMainPanel(){
		JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panel.add(new JLabel("Mật khẩu cũ"));
		panel.add(tfOldPass = new JPasswordField(20));
		panel.add(new JLabel("Mật khẩu mới"));
		panel.add(tfNewPass = new JPasswordField(20));
		panel.add(new JLabel("Nhập lại mật khẩu mới"));
		panel.add(tfReNewPass = new JPasswordField(20));
		panel.add(btnChange);
		panel.add(btnCancel);
		return panel;
	}
	
}
