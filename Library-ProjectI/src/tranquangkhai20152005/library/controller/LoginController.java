package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tranquangkhai20152005.library.model.AcountDB;
import tranquangkhai20152005.library.model.Person;
import tranquangkhai20152005.library.model.PersonDB;
import tranquangkhai20152005.library.view.ChangePassView;
import tranquangkhai20152005.library.view.MainUI;

public class LoginController {
	private JPanel loginPanel;
	private MainUI mainUI;
	
	private JTextField tfAccount;
	private JPasswordField tfPass;
	private JButton btnLogin;
	private JButton btnLogout;
	private JButton btnChangePass;
	private JButton btnAboutMe;
	
	private AcountDB acountDB;
	
	private String account;
	
	
	public LoginController(MainUI mainUI) {
		acountDB = new AcountDB();
		this.mainUI = mainUI;
		loginPanel = mainUI.getLoginPanel();
		mainUI.getMainPanel().setVisible(false);
		mainUI.getMenuView().setVisible(false);
		
		tfAccount     = mainUI.getLoginView().getTfAcount();
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
		new DeleteLoanBookController(mainUI);
	}
	
	private void setAction() {
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				account = tfAccount.getText().toString().trim();
				String pass = new String(tfPass.getPassword());
//				String passFromDB = getPassFromDB();
				System.out.println(pass);
				
				
				
				
				
				
				if(isLogin(account, pass)) {
					System.out.println("Login success");
					tfPass.setText("");
					loginPanel.setVisible(false);
					mainUI.getMainPanel().setVisible(true);
					mainUI.getMenuView().setVisible(true);
					
					
					
					if (!account.equals("Admin")) {
						mainUI.getMenuView().getBtnViewListEmployment().setEnabled(false);
					}
						
						
						
					createController();
					return;
				}
				else {
					JOptionPane.showMessageDialog(new JDialog(), "Tài khoản hoặc Mật khẩu sai - Vui lòng nhập lại");
				}
				return;
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				account = "";
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
				JLabel 		   lbAccount   = changePassView.getLbAccount();
				lbAccount.setText(account);
				JPasswordField tfOldPass   = changePassView.getTfOldPass();
				JPasswordField tfNewPass   = changePassView.getTfNewPass();
				JPasswordField tfReNewPass = changePassView.getTfReNewPass();
				JButton btnChange = changePassView.getBtnChange();
				JButton btnCancel = changePassView.getBtnCancel();

				btnChange.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String oldPassFromDB = "";
						if (account.equals("Admin")) {
							oldPassFromDB = acountDB.getPassAdmin();
						}
						else oldPassFromDB = acountDB.getPassEmpl(account);
						
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
							changePassFromDB(account, newPass);
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
	
	
	/* Login */
	private boolean isLogin (String account, String pass) {
		if (account.equals("Admin")) {
			String passOfAdmin = acountDB.getPassAdmin();
			if (pass.equals(passOfAdmin)) return true;
			else return false;
		}
		else {
			ArrayList<Person> listEmpl = new PersonDB().getAllPersons("nhanvien");
			ArrayList<String> listMaNV = new ArrayList<String>();
			for (int i = 0; i < listEmpl.size(); i++) {
				listMaNV.add(listEmpl.get(i).getId());
			}
			if (listMaNV.indexOf(account) >= 0 && pass.equals(acountDB.getPassEmpl(account))) {
				return true;
			}
			else return false;
		}
	}
	
	/* Change Pass */
	private void changePassFromDB(String account, String newPass) {
		if (account.equals("Admin")) {
			acountDB.changePassAdminFromDB(newPass);
		} else {
			acountDB.changePassEmplFromDB(account, newPass);
		}
	}
	
	
}
