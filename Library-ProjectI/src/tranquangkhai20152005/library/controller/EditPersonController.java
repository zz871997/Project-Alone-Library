package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tranquangkhai20152005.library.model.Person;
import tranquangkhai20152005.library.model.PersonDB;
import tranquangkhai20152005.library.view.EditPersonView;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.PersonInformation;
import tranquangkhai20152005.library.view.TablePersonView;

public class EditPersonController {
	private MainUI mainUI;
	private Person person;
	private PersonDB personDB;
	private String oldID = "";
	
	private EditPersonView editPersonView;
	private JButton btnEditUser;
	private JButton btnEditEmployment;
	
	private PersonInformation personInformation;
	private TablePersonView tableUserView;
	private TablePersonView tableEmploymentView;
	
	public EditPersonController(MainUI mainUI) {
		this.mainUI = mainUI;
		personDB = new PersonDB();
		btnEditUser = mainUI.getManagerView().getBtnEditUser();
		btnEditEmployment = mainUI.getManagerView().getBtnEditEmployment();
		
		tableUserView = mainUI.getTableUserView();
		tableUserView.updateTable(personDB.getAllPersons("docgia"));
	
		tableEmploymentView = mainUI.getTableEmploymentView();
		tableEmploymentView.updateTable(personDB.getAllPersons("nhanvien"));
		
		btnEditEvent();
	}
	
	/*---------------------Event --------------------------*/
	private void btnEditEvent() {
		btnEditUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editPersonView = new EditPersonView(mainUI);
				personInformation = editPersonView.getPersonInformation();
	
				// When click "SUA" on MainUI, AddPerson Panel will be visible 
				// if row is selected
				int index = findIndexOfData("docgia");
				if (index >= 0) {
					editPersonView.setVisible(true);
					// Get id Person
					oldID= getId("docgia", index, 0);
					//System.out.println(oldID);
					loadInfor("docgia", oldID);
					setActionsEditUser();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 người để sửa");
				}
			}
		});
		
		btnEditEmployment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				editPersonView = new EditPersonView(mainUI);
				personInformation = editPersonView.getPersonInformation();
				
				// When click "SUA" on MainUI, AddPerson Panel will be visible 
				// if row is selected
				int index = findIndexOfData("nhanvien");
				if (index >= 0) {
					editPersonView.setVisible(true);
					// Get id Person
					oldID = getId("nhanvien", index, 0);
					loadInfor("nhanvien", oldID);
					setActionsEditEmployment();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 người để sửa");
				}
			}
		});
	}
	
	private int findIndexOfData(String tableName) {
		int index = -1;
		if (tableName.equals("docgia")) {
			index = tableUserView.getTable().getSelectedRow();
		}
		else if (tableName.equals("nhanvien")) {
			index = tableEmploymentView.getTable().getSelectedRow();
		}
		return index;
	}
	
	private String getId(String tableName, int indexRow, int indexCol) {
		if(tableName.equals("docgia")) {
			JTable table = tableUserView.getTable();
			String idUser = table.getModel().getValueAt(indexRow, indexCol).toString();
			return idUser;
		}
		else if (tableName.equals("nhanvien")) {
			JTable table = tableEmploymentView.getTable();
			String idEmp = table.getModel().getValueAt(indexRow, indexCol).toString();
			return idEmp;
		}
		return null;
	}
	
	private void loadInfor(String tableName, String id) {
		person = personDB.getPerson(tableName, id);
		personInformation.getTfId().setText(id);
		personInformation.getTfName().setText(person.getName());
		personInformation.getTfGioiTinh().setText(person.getGioiTinh());
		personInformation.getTfNamSinh().setText(person.getNamSinh());
		personInformation.getTfQueQuan().setText(person.getQueQuan());
		personInformation.getTfDiaChi().setText(person.getDiaChi());
		personInformation.getTfSDT().setText(person.getSdt());
	}
	
	// Set Action Edit User
	private void setActionsEditUser() {
		JButton btnEdit = editPersonView.getBtnEdit();
		JButton btnCancel = editPersonView.getBtnCancel();
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				suaNguoi("docgia");
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				huy();
			}
		});
	}
	
	//Set Action Edit Employment
	private void setActionsEditEmployment() {
		JButton btnEdit = editPersonView.getBtnEdit();
		JButton btnCancel = editPersonView.getBtnCancel();
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				suaNguoi("nhanvien");
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				huy();
			}
		});
	}
	
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
			JOptionPane.showMessageDialog(this.editPersonView, "Các trường dữ liệu không được để trống");
			return false;
		}
		
		// Check namSinh and SDT are integer?
		try {
			//if (strNamXB.length() == 0) continue;
			int namSinh = Integer.parseInt(personInformation.getTfNamSinh().getText().toString().trim());
			int sdt = Integer.parseInt(personInformation.getTfSDT().getText().toString().trim());
			// Test < 0 ????
			if(namSinh < 0 || sdt <0 || namSinh > 9999 || namSinh < 1000) {
				JOptionPane.showMessageDialog(this.editPersonView, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.editPersonView, "Năm sinh và số điện thoại phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		
		// Check ID???????????????????????????????????
		if (!checkID(personInformation.getTfId().getText().toString(), tableName)) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã này đã tồn tại!!!");
			return false;
		}
	
		return true;
	}
	
	private boolean checkID(String id, String tableName) {
		ArrayList<Person> listPerson = personDB.getAllPersons(tableName);
		for (int i = 0; i < listPerson.size(); i++) {
			String maIdFromDB = listPerson.get(i).getId();
			if (id.equals(maIdFromDB) && (id.equals(oldID) == false)) return false;
		}
		return true;
	}
	
	// Edit Person
	private void suaNguoi(String tableName) {
		if (checkInfor(personInformation, tableName)) {
			String newId = personInformation.getTfId().getText().toString();
			String newName = personInformation.getTfName().getText().toString();
			String newGioiTinh = personInformation.getTfGioiTinh().getText().toString();
			String newNamSinh = personInformation.getTfNamSinh().getText().toString();
			String newQueQuan = personInformation.getTfQueQuan().getText().toString();
			String newDiaChi = personInformation.getTfDiaChi().getText().toString();
			String newSdt = personInformation.getTfSDT().getText().toString();
			personDB.updatePerson(tableName, this.person, newId, newName, newNamSinh, newGioiTinh, newQueQuan, newDiaChi, newSdt);
			if (tableName.equals("docgia")) {
				tableUserView.updateTable(personDB.getAllPersons(tableName));
			}
			else if(tableName.equals("nhanvien")) {
				tableEmploymentView.updateTable(personDB.getAllPersons(tableName));
			}
			this.editPersonView.setVisible(false);
		}
		else {
			System.out.println("Edit fail !!!");
		}
	}
	
	//Cancel
	private void huy() {		
		this.editPersonView.setVisible(false);
	}
}
