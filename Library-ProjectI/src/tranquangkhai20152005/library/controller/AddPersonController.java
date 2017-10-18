package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import tranquangkhai20152005.library.model.Person;
import tranquangkhai20152005.library.model.PersonDB;
import tranquangkhai20152005.library.view.AddPersonView;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.PersonInformation;
import tranquangkhai20152005.library.view.TablePersonView;

public class AddPersonController {
	private MainUI mainUI;
	private Person person;
	private PersonDB personDB;
	
	private AddPersonView addPersonView;
	private PersonInformation personInformation;
	private TablePersonView tableUserView;
	private TablePersonView tableEmploymentView;
	
	public AddPersonController(MainUI mainUI) {
		this.mainUI = mainUI;
		personDB = new PersonDB();
		
		JButton btnAddUserManager = mainUI.getManagerView().getBtnAddUser();
		JButton btnAddEmploymentManager = mainUI.getManagerView().getBtnAddEmployment();
		
		tableUserView = mainUI.getTableUserView();
		//Update Table
		tableUserView.updateTable(personDB.getAllPersons("docgia"));
		
		tableEmploymentView = mainUI.getTableEmploymentView();
		tableEmploymentView.updateTable(personDB.getAllPersons("nhanvien"));
		
		btnAddUserManager.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPersonView = new AddPersonView(mainUI);
				personInformation = addPersonView.getPersonInformation();
				addPersonView.setVisible(true);
				
				setActionAddUser();
			}
		});
		
		btnAddEmploymentManager.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addPersonView = new AddPersonView(mainUI);
				personInformation = addPersonView.getPersonInformation();
				addPersonView.setVisible(true);
				
				setActionAddEmployment();
			}
		});
	}
	
	/*---------------- Set Event ---------------------------*/
	// Check information of textField
	private boolean checkInfor(PersonInformation personInformation, String tableName) {
		// Are TextFields empty?
		if (personInformation.getTfId().getText().toString().trim().equals("")       || 
			personInformation.getTfName().getText().toString().trim().equals("")     || 
			personInformation.getTfNamSinh().getText().toString().trim().equals("")  || 
			personInformation.getTfGioiTinh().getText().toString().trim().equals("") ||
			personInformation.getTfQueQuan().getText().toString().trim().equals("")  ||
			personInformation.getTfDiaChi().getText().toString().trim().equals("")   ||
			personInformation.getTfSDT().getText().toString().trim().equals("")) {
			System.out.println("Khong de trong cac truong du lieu");
			JOptionPane.showMessageDialog(this.addPersonView, "Các trường dữ liệu không được để trống");
			return false;
		}
		
		// Check namSinh and SDT are integer?
		try {
			//if (strNamXB.length() == 0) continue;
			int namSinh = Integer.parseInt(personInformation.getTfNamSinh().getText().toString().trim());
			int sdt = Integer.parseInt(personInformation.getTfSDT().getText().toString().trim());
			// Test < 0 ????
			if(namSinh < 0 || sdt <0 || namSinh > 9999 || namSinh < 1000) {
				JOptionPane.showMessageDialog(this.addPersonView, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.addPersonView, "Năm sinh và số điện thoại phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		
		// Check ID???????????????????????????????????
		if (!checkID(personInformation.getTfId().getText().toString(), tableName)) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã này đã tồn tại!!!");
			return false;
		}
		
		///////////////////////////////////////////////////
		return true;
	}
	
	private boolean checkID(String id, String tableName) {
		ArrayList<Person> listPerson = personDB.getAllPersons(tableName);
		for (int i = 0; i < listPerson.size(); i++) {
			String maIdFromDB = listPerson.get(i).getId();
			if (id.equals(maIdFromDB)) return false;
		}
		return true;
	}
	
	/* Set Actions*/
	// Set action add user
	private void setActionAddUser() {
		JButton btnThem   = addPersonView.getBtnAdd();
		JButton btnTaoLai = addPersonView.getBtnReset();
		JButton btnHuy    = addPersonView.getBtnCancel();
		
		// Set actions for buttons
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				themNguoi("docgia");
			}
		});
		
		btnTaoLai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taoLai();
	
			}
		});
		
		btnHuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				huy();
			}
		});
	}
	
	// Set action add Employment
	private void setActionAddEmployment() {
		JButton btnThem   = addPersonView.getBtnAdd();
		JButton btnTaoLai = addPersonView.getBtnReset();
		JButton btnHuy    = addPersonView.getBtnCancel();
		
		// Set actions for buttons
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				themNguoi("nhanvien");
			}
		});
		
		btnTaoLai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				taoLai();
	
			}
		});
		
		btnHuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				huy();
			}
		});
	}
	
	private void themNguoi(String tableName) {
		if (checkInfor(personInformation, tableName)) {
			String id       = personInformation.getTfId().getText().toString();
			String name     = personInformation.getTfName().getText().toString();
			String namSinh  = personInformation.getTfNamSinh().getText().toString();
			String gioiTinh = personInformation.getTfGioiTinh().getText().toString();
			String queQuan  = personInformation.getTfQueQuan().getText().toString();
			String diaChi   = personInformation.getTfDiaChi().getText().toString();
			String sdt      = personInformation.getTfSDT().getText().toString();
			
			person = new Person(id, name, namSinh, gioiTinh, queQuan, diaChi, sdt);
			personDB.insertPerson(tableName, person);
			
			// Update table
			if (tableName.equals("docgia")) {
				tableUserView.updateTable(personDB.getAllPersons(tableName));
			}
			if (tableName.equals("nhanvien")) {
				tableEmploymentView.updateTable(personDB.getAllPersons(tableName));
			}
			
			
			
			clearInput();
			this.addPersonView.setVisible(false);
		}
		else {
			System.out.println("Insert this person false!!!");
		}
	}
	
	private void taoLai() {
		clearInput();
	}
	
	private void huy() {
		clearInput();
		this.addPersonView.setVisible(false);
	}
	private void clearInput() {
		 personInformation.getTfId().setText("");
		 personInformation.getTfName().setText("");
		 personInformation.getTfNamSinh().setText("");
		 personInformation.getTfGioiTinh().setText("");
		 personInformation.getTfQueQuan().setText("");
		 personInformation.getTfDiaChi().setText("");
		 personInformation.getTfSDT().setText("");
	}
	
	
}
