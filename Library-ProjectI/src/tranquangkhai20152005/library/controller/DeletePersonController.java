package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tranquangkhai20152005.library.model.AcountDB;
import tranquangkhai20152005.library.model.LoanBook;
import tranquangkhai20152005.library.model.LoanBookDB;
import tranquangkhai20152005.library.model.Person;
import tranquangkhai20152005.library.model.PersonDB;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TablePersonView;

public class DeletePersonController {
	private MainUI mainUI;
	private Person person;
	private PersonDB personDB;
	
	private JButton btnDeleteUser;
	private JButton btnDeleteEmployment;
	private TablePersonView tableUserView;
	private TablePersonView tableEmploymentView;
	
	public DeletePersonController(MainUI mainUI) {
		this.mainUI = mainUI;
		personDB = new PersonDB();
		
		btnDeleteUser = mainUI.getManagerView().getBtnDeleteUser();
		btnDeleteEmployment = mainUI.getManagerView().getBtnDeleteEmployment();
		
		tableUserView = mainUI.getTableUserView();
		tableUserView.updateTable(personDB.getAllPersons("docgia"));
		tableEmploymentView = mainUI.getTableEmploymentView();
		tableEmploymentView.updateTable(personDB.getAllPersons("nhanvien"));
		
		setActions();
	}
	
	/*------------------------- Set Actionss ----------------------------*/
	private void setActions() {
		btnDeleteUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = findIndexOfData("docgia");
				if(index >= 0) {
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá độc giả này không?",
							 "Xoá", JOptionPane.YES_NO_OPTION,
							 JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(select == 0) {
						String id = getId("docgia", index, 0);
						
						if (!checkUser(id)) {
							JOptionPane.showMessageDialog(new JDialog(), "Độc giả đã hoặc đang trong quan hệ mượn trả \n " +
																		  "Hãy xóa các mượn trả liên quan đến độc giả này");
							return;
						}
						else {
							person = personDB.getPerson("docgia", id);
							personDB.deletePerson("docgia", person);
							tableUserView.updateTable(personDB.getAllPersons("docgia"));
						}
						
						
						
						
						
						
						
						
						
					}
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 độc giả để xóa");	
				}
			}
		});
		
		btnDeleteEmployment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = findIndexOfData("nhanvien");
				if(index >= 0) {
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá nhân viên này không?",
							 "Xoá", JOptionPane.YES_NO_OPTION,
							 JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(select == 0) {
						String id = getId("nhanvien", index, 0);
						
						if (!checkEmpl(id)) {
							JOptionPane.showMessageDialog(new JDialog(), "Nhân viên đã hoặc đang trong quan hệ mượn trả \n " +
																		  "Hãy xóa các mượn trả liên quan đến nhân viên này");
							return;
						}
						else {
							person = personDB.getPerson("nhanvien", id);
							new AcountDB().deleteAccEmpl(id);
							personDB.deletePerson("nhanvien", person);
							tableEmploymentView.updateTable(personDB.getAllPersons("nhanvien"));
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 nhân viên để xóa");	
				}
			}
		});
	}
	
	/* Get index of Row */
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
	
	/* Get id's Person */
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
	
	/* Kiem tra xem nhan vien co dang cho trong quan he muon tra nao khong */
	private boolean checkEmpl (String id) {
		ArrayList<LoanBook> listLoanBook = new LoanBookDB().getAllLoanBooks();
		for (int i = 0; i < listLoanBook.size(); i++) {
			if (listLoanBook.get(i).getMaNV().equals(id)) return false;
		}
		return true;
	}
	
	private boolean checkUser (String id) {
		ArrayList<LoanBook> listLoanBook = new LoanBookDB().getAllLoanBooks();
		for (int i = 0; i < listLoanBook.size(); i++) {
			if (listLoanBook.get(i).getMaDG().equals(id)) return false;
		}
		return true;
	}
}
