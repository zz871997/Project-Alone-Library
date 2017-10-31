package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MenuView extends JPanel {
	private JButton btnViewListBook = new JButton("SÁCH");
	private JButton btnViewListUser = new JButton("ĐỘC GIẢ");
	private JButton btnViewListEmployment = new JButton("NHÂN VIÊN");
	private JButton btnBorrow = new JButton("MƯỢN SÁCH");
	
	private JButton btnLogout = new JButton("ĐĂNG XUẤT");
	private JButton btnChangePass = new JButton("ĐỔI MẬT KHẨU");
	
	private JButton btnAboutMe = new JButton("ABOUT ME");

	
	public JButton getBtnLogout() {
		return btnLogout;
	}

	public JButton getBtnChangePass() {
		return btnChangePass;
	}

	public JButton getBtnViewListBook() {
		return btnViewListBook;
	}

	public JButton getBtnViewListUser() {
		return btnViewListUser;
	}

	public JButton getBtnViewListEmployment() {
		return btnViewListEmployment;
	}

	public JButton getBtnBorrow() {
		return btnBorrow;
	}

	public JButton getBtnAboutMe() {
		return btnAboutMe;
	}
	
	public MenuView() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout());
		add(createMainPanel(), BorderLayout.PAGE_START);
		add(btnAboutMe, BorderLayout.PAGE_END);
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel (new GridLayout(6, 1, 5, 5));
		panel.setBorder(new TitledBorder(" Quản lí "));
		
		btnViewListBook.setIcon(new ImageIcon(this.getClass().getResource("/book-icon.png")));
		btnViewListUser.setIcon(new ImageIcon(this.getClass().getResource("/user-icon.png")));
		btnViewListEmployment.setIcon(new ImageIcon(this.getClass().getResource("/employee-icon.png")));
		btnBorrow.setIcon(new ImageIcon(this.getClass().getResource("/loan-book-icon.png")));
		btnLogout.setIcon(new ImageIcon(this.getClass().getResource("/log-out-icon.png")));
		btnChangePass.setIcon(new ImageIcon(this.getClass().getResource("/change-pass-icon.png")));
		panel.add(btnViewListBook);
		panel.add(btnViewListUser);
		panel.add(btnViewListEmployment);
		panel.add(btnBorrow);
		panel.add(btnLogout);
		panel.add(btnChangePass);
		return panel;
	}
}
