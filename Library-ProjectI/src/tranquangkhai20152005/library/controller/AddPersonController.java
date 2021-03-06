package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tranquangkhai20152005.library.model.AcountDB;
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
		JButton btnAddUserExcel = mainUI.getManagerView().getBtnAddUserExcel();
		JButton btnAddEmploymentExcel = mainUI.getManagerView().getBtnAddEmploymentExcel();
		
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
		
		btnAddUserExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPersonFromExcel("docgia");
			}
		});
		
		btnAddEmploymentExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPersonFromExcel("nhanvien");
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
	
	/* Add Person From Excel */
	private void addPersonFromExcel (String tableName) {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showOpenDialog(this.mainUI);
		String openFilePath = "";
		
		if (select == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getCurrentDirectory().toString() 
			       	   + "\\" + fileChooser.getSelectedFile().getName();
			if(path.indexOf(".xlsx") >= 0) {
				openFilePath = path;
			}
			else {
				openFilePath = path + ".xlsx";
			}
			
			System.out.println(openFilePath);
			addPersonFromExcelFilePath(openFilePath, tableName);
			
			JOptionPane.showMessageDialog(new JDialog(), "Đã thêm từ Excel");
		}	
	}
	
	private void addPersonFromExcelFilePath(String path, String tableName) {
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			// Create workbook Object
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// Get the first sheet from workbook
			XSSFSheet sheet = null;
			if (tableName.equals("docgia")) {
				sheet = workbook.getSheetAt(1);
			}
			else if (tableName.equals("nhanvien")) {
				sheet = workbook.getSheetAt(2);
			}
			
			// Get all row of the current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			List<Row> rowList = IteratorUtils.toList(rowIterator);
			
			for (int i = 2; i < rowList.size(); i++) {
				XSSFRow row = (XSSFRow) rowList.get(i);
				Iterator<Cell> cellIterator = row.cellIterator();
				List<Cell> cellList = IteratorUtils.toList(cellIterator);
				
				// ArrayList save data of current row
				ArrayList<String> dataOfRow = new ArrayList<String>();
				for (int j = 0; j < cellList.size(); j++) {
					Cell cell = cellList.get(j);
					CellType cellType = cell.getCellTypeEnum();
					String data = "";
					switch(cellType) {
						case STRING:
							data = cell.getStringCellValue();
							break;
						case NUMERIC:
							data = Double.toString(cell.getNumericCellValue());
							break;
						default:
							data = "";
							break;
					}
					dataOfRow.add(data);
				}
				
				if (checkInfor(dataOfRow, tableName)) {
					String id = dataOfRow.get(0);
					String name = dataOfRow.get(1);
					double namSinh = Double.parseDouble(dataOfRow.get(2));
					int namSinhInt = (int) namSinh;
					String namSinhStr = Integer.toString(namSinhInt);
					String gioiTinh = dataOfRow.get(3);
					String queQuan = dataOfRow.get(4);
					String diaChi = dataOfRow.get(5);
					String sdt = dataOfRow.get(6);
					
					Person person = new Person(id, name, namSinhStr, gioiTinh, queQuan, diaChi, sdt);
					personDB.insertPerson(tableName, person);
					if (tableName.equals("nhanvien")) {
						new AcountDB().insertAccEmpl(id);
					}
					//Update table
					if (tableName.equals("docgia")) {
						tableUserView.updateTable(personDB.getAllPersons("docgia"));
					}
					else if (tableName.equals("nhanvien")) {
						tableEmploymentView.updateTable(personDB.getAllPersons("nhanvien"));
					}
					dataOfRow.clear();
				}
				else return;
			}
		}	
		catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Lỗi File");
			e.printStackTrace();
		}	
	}
	
	// Check Information of dataOfRow
	private boolean checkInfor(ArrayList<String> dataOfRow, String tableName) {
		// Check if empty
		for (int i = 0; i < dataOfRow.size(); i++) {
			if (dataOfRow.get(i).equals("")) return false;
		}
		// Check if namSinh and sDT are integer?
		try {
			double namSinh = Double.parseDouble(dataOfRow.get(2));
			int namSinhInt = (int) namSinh;
			if (namSinhInt != namSinh) return false;
			
			String sdt = dataOfRow.get(6);
			long sdtInt = Long.parseLong(sdt);
			
			// Test < 0 ????
			if(namSinhInt < 0 || sdtInt <0 || namSinhInt > 9999 || namSinhInt < 1000) {
				JOptionPane.showMessageDialog(this.mainUI, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.mainUI, "Năm sinh và số điện thoại phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		/* Check if id is exist? */
		if (!checkID(dataOfRow.get(0), tableName)) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã này đã tồn tại - Hãy kiểm tra lại");
			return false;
		}
		return true;
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
			
			/* add account */
			if (tableName.equals("nhanvien")) {
				new AcountDB().insertAccEmpl(id);
			}
			
			
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
