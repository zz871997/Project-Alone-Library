package tranquangkhai20152005.library.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import tranquangkhai20152005.library.model.Person;
import tranquangkhai20152005.library.model.PersonDB;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TablePersonView;

public class SearchPersonController {
	private MainUI mainUI;
	private Person person;
	private PersonDB personDB;
	
	private ArrayList<Person> resultSearch = new ArrayList<Person>();
	
	private JTextField tfSearchUser;
	private JTextField tfSearchEmployment;
	private JComboBox cbSearchUserType;
	private JComboBox cbSearchEmploymentType;
	
	private TablePersonView tableUserView;
	private TablePersonView tableEmploymentView;
	
	private int typeSearch;
	
	private final Color COLOR_DEFAULT   = Color.WHITE;
	private final Color COLOR_NOT_FOUND = Color.PINK;
	
	// Setter - Getter
	public ArrayList<Person> getResultSearch() {
		return resultSearch;
	}
	public void setResultSearch(ArrayList<Person> resultSearch) {
		this.resultSearch = resultSearch;
	}
	
	public SearchPersonController(MainUI mainUI) {
		this.mainUI = mainUI;
		personDB = new PersonDB();
		tableUserView = mainUI.getTableUserView();
		tableEmploymentView = mainUI.getTableEmploymentView();
		tfSearchUser = mainUI.getSearchUserView().getTfSearch();
		cbSearchUserType = mainUI.getSearchUserView().getCbSearch();
		tfSearchEmployment = mainUI.getSearchEmploymentView().getTfSearch();
		cbSearchEmploymentType = mainUI.getSearchEmploymentView().getCbSearch();
		
		setActions();
		
	}
	
	/*--------------------------Set Actions---------------------------- */
	private void setActions() {
		tfSearchUser.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			/* Event when press a button on keyboard */
			@Override
			public void keyReleased(KeyEvent arg0) {
				typeSearch = cbSearchUserType.getSelectedIndex();
				tableUserView.updateTable(search("docgia", typeSearch));
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		
		tfSearchEmployment.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			/* Event when press a button on keyboard */
			@Override
			public void keyReleased(KeyEvent e) {
				typeSearch = cbSearchEmploymentType.getSelectedIndex();
				tableEmploymentView.updateTable(search("nhanvien", typeSearch));
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		/* ConmboBox's Event */
		cbSearchUserType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetSearch("docgia");
				return;
			}
		});
		
		cbSearchEmploymentType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetSearch("nhanvien");
				return;
			}
		});
	}
	
	// Search Function
	private ArrayList<Person> search(String tableName, int typeSearch) {
		ArrayList<Person> allPerson = personDB.getAllPersons(tableName);
		int size = allPerson.size();
		resultSearch.clear();
		String textFind = "";
		
		if (tableName.equals("docgia")) {
			textFind = tfSearchUser.getText().trim().toLowerCase();
		}
		else if (tableName.equals("nhanvien")){
			textFind = tfSearchEmployment.getText().trim().toLowerCase();
		}
		
		// Find hoTen
		if (typeSearch == 0) {
			for (int i = 0; i < size; i++) {
				// Get Name of all Persons
				String name = allPerson.get(i).getName().trim().toLowerCase();
				if (name.indexOf(textFind) >= 0) {
					resultSearch.add(allPerson.get(i));
				}
			}
		}
		
		// Find id
		if (typeSearch == 1) {
			for (int i = 0; i < size; i++) {
				// Get Name of all Persons
				String id = allPerson.get(i).getId().trim().toLowerCase();
				if (id.indexOf(textFind) >= 0) {
					resultSearch.add(allPerson.get(i));
				}
			}
		}
		
		// Find NamSinh
		if (typeSearch == 2) {
			for (int i = 0; i < size; i++) {
				// Get Name of all Persons
				String namSinh = allPerson.get(i).getNamSinh().trim().toLowerCase();
				if (namSinh.indexOf(textFind) >= 0) {
					resultSearch.add(allPerson.get(i));
				}
			}
		}
		
		// Find gioiTinh
		if (typeSearch == 3) {
			for (int i = 0; i < size; i++) {
				// Get Name of all Persons
				String gioiTinh = allPerson.get(i).getGioiTinh().trim().toLowerCase();
				if (gioiTinh.indexOf(textFind) >= 0) {
					resultSearch.add(allPerson.get(i));
				}
			}
		}
		
		//Find queQuan
		if (typeSearch == 4) {
			for (int i = 0; i < size; i++) {
				// Get Name of all Persons
				String queQuan = allPerson.get(i).getQueQuan().trim().toLowerCase();
				if (queQuan.indexOf(textFind) >= 0) {
					resultSearch.add(allPerson.get(i));
				}
			}
		}
		
		//Find diaChi
		if (typeSearch == 5) {
			for (int i = 0; i < size; i++) {
				// Get Name of all Persons
				String diaChi = allPerson.get(i).getDiaChi().trim().toLowerCase();
				if (diaChi.indexOf(textFind) >= 0) {
					resultSearch.add(allPerson.get(i));
				}
			}
		}
		
		// Find sDT
		if (typeSearch == 6) {
			for (int i = 0; i < size; i++) {
				// Get Name of all Persons
				String sdt = allPerson.get(i).getSdt().trim().toLowerCase();
				if (sdt.indexOf(textFind) >= 0) {
					resultSearch.add(allPerson.get(i));
				}
			}
		}
		
		// Set color for textField Search
		if (resultSearch.size() == 0 && tableName.equals("docgia")) {
			tfSearchUser.setBackground(COLOR_NOT_FOUND);
		}
		else if (resultSearch.size() == 0 && tableName.equals("nhanvien")) {
			tfSearchEmployment.setBackground(COLOR_NOT_FOUND);
		}
		else if (resultSearch.size() > 0 && tableName.equals("docgia")) {
			tfSearchUser.setBackground(COLOR_DEFAULT);
		}
		else if (resultSearch.size() > 0 && tableName.equals("nhanvien")) {
			tfSearchEmployment.setBackground(COLOR_DEFAULT);
		}
		
		return resultSearch;
	}
	
	// Reset Search when cbSearch is Changed
	private void resetSearch(String tableName) {
		if(tableName.equals("docgia")) {
			typeSearch = cbSearchUserType.getSelectedIndex();
			tfSearchUser.setText("");
			tfSearchUser.requestFocus();
			tfSearchUser.setBackground(COLOR_DEFAULT);
			updateData(tableName);
		}
		else if (tableName.equals("nhanvien")) {
			typeSearch = cbSearchEmploymentType.getSelectedIndex();
			tfSearchEmployment.setText("");
			tfSearchEmployment.requestFocus();
			tfSearchEmployment.setBackground(COLOR_DEFAULT);
			updateData(tableName);
		}
	}
	
	private void updateData(String tableName) {
		if (resultSearch.size() > 0 && tableName.equals("docgia")) {
			tableUserView.updateTable(search(tableName, typeSearch));
		}
		else if (resultSearch.size() > 0 && tableName.equals("nhanvien")) {
			tableEmploymentView.updateTable(search(tableName, typeSearch));
		}
		else if (resultSearch.size() <= 0 && tableName.equals("docgia")) {
			tableUserView.updateTable(personDB.getAllPersons(tableName));
		}
		else if (resultSearch.size() <= 0 && tableName.equals("nhanvien")) {
			tableEmploymentView.updateTable(personDB.getAllPersons(tableName));
		}
	}
}
