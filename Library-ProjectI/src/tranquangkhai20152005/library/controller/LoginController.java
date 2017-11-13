package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import tranquangkhai20152005.library.view.ChangePassView;
import tranquangkhai20152005.library.view.MainUI;

public class LoginController {
	private JPanel loginPanel;
	private MainUI mainUI;
	
	private JPasswordField tfPass;
	private JButton btnLogin;
	private JButton btnLogout;
	private JButton btnChangePass;
	private JButton btnAboutMe;
	
	private final String dbURL = "jdbc:mysql://localhost:3306/library";
	private String user = "root";
	private String password = "1234";
	private Connection connection;

	public LoginController(MainUI mainUI) {
		this.mainUI = mainUI;
		loginPanel = mainUI.getLoginPanel();
		mainUI.getMainPanel().setVisible(false);
		mainUI.getMenuView().setVisible(false);
		
		tfPass        = mainUI.getLoginView().getTfPass();
		btnLogin      = mainUI.getLoginView().getBtnLogin();
		btnLogout     = mainUI.getMenuView().getBtnLogout();
		btnChangePass = mainUI.getMenuView().getBtnChangePass();
		btnAboutMe    = mainUI.getMenuView().getBtnAboutMe();
		
		setAction();
	}
	
	private void createController() {
		new AddBookController(mainUI);
		new EditBookController(mainUI);
		new DeleteBookController(mainUI);
		new SearchBookController(mainUI);
		new PrintSearchBookController(mainUI);
		new PrintSearchPersonController(mainUI);
		new ChangeTableController(mainUI);
		new AddPersonController(mainUI);
		new EditPersonController(mainUI);
		new DeletePersonController(mainUI);
		new SearchPersonController(mainUI);
		new AddLoanBookController(mainUI);
		new ShowDetailInformation(mainUI);
		new ThongKeController(mainUI);
		new TKPersonController(mainUI);
		new EditLoanController(mainUI);
		new SearchLoanBookController(mainUI);
		new PrintSearchLoanBookController(mainUI);
		new TKLoanBookController(mainUI);
	}
	
	private void setAction() {
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String pass = new String(tfPass.getPassword());
				String passFromDB = getPassFromDB();
				System.out.println(pass);
				if(pass.equals(passFromDB)) {
					System.out.println("Login success");
					tfPass.setText("");
					loginPanel.setVisible(false);
					mainUI.getMainPanel().setVisible(true);
					mainUI.getMenuView().setVisible(true);
					createController();
					return;
				}
				else {
					JOptionPane.showMessageDialog(new JDialog(), "Mật khẩu sai - Vui lòng nhập lại");
				}
				return;
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(true);
				mainUI.getMainPanel().setVisible(false);
				mainUI.getMenuView().setVisible(false);
				mainUI.setVisible(false);
				mainUI = null;
				MainUI mainUI2 = new MainUI();
				LoginController newLogin = new LoginController(mainUI2);
				return;
			}
		});
		
		btnChangePass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePassView changePassView = new ChangePassView(mainUI);
				changePassView.setVisible(true);
				JPasswordField tfOldPass   = changePassView.getTfOldPass();
				JPasswordField tfNewPass   = changePassView.getTfNewPass();
				JPasswordField tfReNewPass = changePassView.getTfReNewPass();
				JButton btnChange = changePassView.getBtnChange();
				JButton btnCancel = changePassView.getBtnCancel();

				btnChange.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String oldPassFromDB = getPassFromDB();
						String oldPass   = new String(tfOldPass.getPassword());
						String newPass   = new String(tfNewPass.getPassword());
						String reNewPass = new String(tfReNewPass.getPassword());
						
						if (!oldPass.equals(oldPassFromDB)) {
							System.out.println(oldPass + "   " + oldPassFromDB);
							JOptionPane.showMessageDialog(new JDialog(), "Mật khẩu cũ không đúng");
						}
						else if(!newPass.equals(reNewPass)) {
							JOptionPane.showMessageDialog(new JDialog(), "Vui lòng nhập lại đúng mật khẩu mới");
						}
						else {
							changePassFromDB(newPass);
							JOptionPane.showMessageDialog(new JDialog(), "Đổi mật khẩu thành công");
							changePassView.setVisible(false);
						}
					}
				});
				
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tfOldPass.setText("");
						tfNewPass.setText("");
						tfReNewPass.setText("");
						changePassView.setVisible(false);
					}
				});
			}
		});
		
		btnAboutMe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JDialog(), "Demo Quản lí Thư Viện - Bài tập cá nhân" + "\n" +
												"Sinh viên: Trần Quang Khải"+ "\n" +
												"MSSV: 20152005" + "\n" +
												"Lớp: CNTT2.1 - K60 - HUST");
			}
		});
	}
	
	/* Get a connection - Connect to Database (MySQL) */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbURL, user, password);
			if(conn != null) {
				System.out.println("Connected!");
			}	
		} 
		catch (SQLException e) {
			System.out.println("Connecting Failed!");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	private String getPassFromDB() {
		connection = getConnection();
		String password = null;
		Statement statement = null;
		
		try {
			String sql = "SELECT * FROM account";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				password  = result.getString("Pass");
			}
			// Close connection
			result.close();
			statement.close();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
			
		}
		finally {
			try {
				if(statement  != null) statement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return password;
	}
	
	/* Change Password from DB */
	private void changePassFromDB(String newPass) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE account SET Pass=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, newPass);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This pass has been changed");
			
			// Close connection
			preStatement.close();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
