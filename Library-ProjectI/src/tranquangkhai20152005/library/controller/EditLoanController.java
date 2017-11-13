package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tranquangkhai20152005.library.model.LoanBook;
import tranquangkhai20152005.library.model.LoanBookDB;
import tranquangkhai20152005.library.model.Person;
import tranquangkhai20152005.library.model.PersonDB;
import tranquangkhai20152005.library.view.EditLoanView;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBorrowView;

public class EditLoanController {
	private MainUI mainUI;
	private LoanBook loanBook;
	private LoanBookDB loanBookDB;
	
	private EditLoanView editLoanView;
	private JButton btnEditLoan;
	private TableBorrowView tableBorrowView;
	
	private ArrayList<String> listMaNV = new ArrayList<String>();
	private ArrayList<String> listMaDG = new ArrayList<String>();
	
	private String[] date = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			 "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private ArrayList<String> arrDate = new ArrayList<String>(Arrays.asList(date));
	private String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			  "11", "12"};
	private ArrayList<String> arrMonth = new ArrayList<String>(Arrays.asList(month));
	private String[] year = {"2017", "2018", "2019", "2020"};
	private ArrayList<String> arrYear = new ArrayList<String>(Arrays.asList(year));
	
	public EditLoanController(MainUI mainUI) {
		this.mainUI = mainUI;
		loanBookDB = new LoanBookDB();
		btnEditLoan = mainUI.getManagerView().getBtnEditBorrow();
		tableBorrowView = mainUI.getTableBorrowView();
		
		btnEditEvent();
	}
	
	/*-------------Event - Action--------------- */
	private void btnEditEvent() {
		btnEditLoan.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editLoanView = new EditLoanView(mainUI);
				int index = findIndexOfData();
				if (index >= 0) {
					editLoanView.setVisible(true);
					String maMT = getMaMT(index, 0);
					editLoanView.getCbMaDG().addItem("-- Chon ma DG --");
					PersonDB personDB = new PersonDB();
					ArrayList<Person> listDG = personDB.getAllPersons("docgia");
					for (int i = 0; i < listDG.size(); i++) {
						editLoanView.getCbMaDG().addItem(listDG.get(i).getId());
						listMaDG.add(listDG.get(i).getId());
					}
					editLoanView.getCbMaNV().addItem("-- Chon ma NV --");
					PersonDB personDB2 = new PersonDB();
					ArrayList<Person> listNV = personDB2.getAllPersons("nhanvien");
					for (int i = 0; i < listNV.size(); i++) {
						editLoanView.getCbMaNV().addItem(listNV.get(i).getId());
						listMaNV.add(listNV.get(i).getId());
					}
					loadInfor(maMT);
					setActions();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 mượn trả để sửa");
				}
			}
		});
	}
	
	private int findIndexOfData() {
		int index = tableBorrowView.getTable().getSelectedRow();
		return index;
	}
	
	private String getMaMT(int indexRow, int indexCol) {
		JTable table = tableBorrowView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	private void loadInfor(String maMT) {
		loanBook = loanBookDB.getLoanBook(maMT);
		editLoanView.getTfMaMT().setText(maMT);
		editLoanView.getTfMaMT().setEditable(false);
		String maDG = loanBook.getMaDG();
		String maNV = loanBook.getMaNV();
		String ngayMuon = loanBook.getNgayMuon();
		String ngayHenTra = loanBook.getNgayHenTra();
		String dateDB = ngayHenTra.substring(0, 2);
		String monthDB = ngayHenTra.substring(3, 5);
		String yearDB = ngayHenTra.substring(6);
		editLoanView.getCbMaDG().setSelectedIndex(listMaDG.indexOf(maDG) + 1);
		editLoanView.getCbMaNV().setSelectedIndex(listMaNV.indexOf(maNV) + 1);
		editLoanView.getCbNgayHenTra().setSelectedIndex(arrDate.indexOf(dateDB));
		editLoanView.getCbThangHenTra().setSelectedIndex(arrMonth.indexOf(monthDB));
		editLoanView.getCbNamHenTra().setSelectedIndex(arrYear.indexOf(yearDB));
		editLoanView.getLbHoTenDG().setText(new PersonDB().getPerson("docgia", maDG).getName());
		editLoanView.getLbHoTenNV().setText(new PersonDB().getPerson("nhanvien", maNV).getName());
		editLoanView.getTfTienCoc().setText(loanBook.getTienCoc() + "");
		editLoanView.getTfNgayMuon().setText(ngayMuon);
		editLoanView.getTfNgayMuon().setEditable(false);
	}
	
	/*--------------------  Set Actions ----------------- */
	private void setActions() {
		JButton btnEdit = editLoanView.getBtnEdit();
		JButton btnCancel = editLoanView.getBtnCancel();
		JComboBox<String> cbMaDG = editLoanView.getCbMaDG();
		JComboBox<String> cbMaNV = editLoanView.getCbMaNV();
		
		cbMaDG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cbMaDG.getSelectedIndex() == 0) {
					editLoanView.getLbHoTenDG().setText("");
					return;
				}
				String hotenDG = new PersonDB().getPerson("docgia", cbMaDG.getSelectedItem().toString()).getName();
				editLoanView.getLbHoTenDG().setText(hotenDG);
			}
		});
		
		cbMaNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbMaNV.getSelectedIndex() == 0) {
					editLoanView.getLbHoTenNV().setText("");
					return;
				}
				String hotenNV = new PersonDB().getPerson("nhanvien", cbMaNV.getSelectedItem().toString()).getName();
				editLoanView.getLbHoTenNV().setText(hotenNV);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				suaMT();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
	}
	
	private void suaMT() {
		if (checkInfor(editLoanView)) {
			String maDGMoi       = editLoanView.getCbMaDG().getSelectedItem().toString();
			String maNVMoi       = editLoanView.getCbMaNV().getSelectedItem().toString();
			String ngayHenTraMoi = editLoanView.getCbNgayHenTra().getSelectedItem().toString() + "-"
					   		     + editLoanView.getCbThangHenTra().getSelectedItem().toString() + "-"
					             + editLoanView.getCbNamHenTra().getSelectedItem().toString();
			int tienCocMoi       = Integer.parseInt(editLoanView.getTfTienCoc().getText().trim().toString());
		
			loanBookDB.editLoanBook(loanBook, maDGMoi, maNVMoi, ngayHenTraMoi, tienCocMoi);
			tableBorrowView.updateTable(loanBookDB.getAllLoanBooks());
			
			this.editLoanView.setVisible(false);
		}
		else {
			System.out.println("Edit Fail !!!");
		}
	}
	
	private void huy() {
		this.editLoanView.setVisible(false);
	}
	
	private boolean checkInfor(EditLoanView editLoanView) {
		// Check empty?
		if (editLoanView.getTfTienCoc().getText().toString().trim().equals("") ||
			editLoanView.getCbMaDG().getSelectedIndex() == 0 || 
			editLoanView.getCbMaNV().getSelectedIndex() == 0) {
			System.out.println("Khong de trong cac truong du lieu");
			JOptionPane.showMessageDialog(this.editLoanView, "Các trường dữ liệu không được để trống");
			return false;
		}
		
		// Check number field
		try {
			String tienCoc = editLoanView.getTfTienCoc().getText().trim().toString();
			int money = Integer.parseInt(tienCoc);
			// Test < 0 ????
			if(money < 0) {
				JOptionPane.showMessageDialog(this.editLoanView, "Nhập đúng định dạng các trường số !!!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.editLoanView, "Tiền cọc phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
}
