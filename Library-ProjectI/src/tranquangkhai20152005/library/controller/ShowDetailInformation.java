package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tranquangkhai20152005.library.model.BookDB;
import tranquangkhai20152005.library.model.Detail;
import tranquangkhai20152005.library.model.DetailDB;
import tranquangkhai20152005.library.model.LoanBook;
import tranquangkhai20152005.library.model.LoanBookDB;
import tranquangkhai20152005.library.model.PersonDB;
import tranquangkhai20152005.library.view.DetailInformation;
import tranquangkhai20152005.library.view.DetailView;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBorrowView;
import tranquangkhai20152005.library.view.TableDetailVew;

public class ShowDetailInformation {
	private MainUI mainUI;
	private Detail detail;
	private LoanBook loanBook;
	private DetailDB detailDB;
	private LoanBookDB loanBookDB;
	
	private DetailView detailView;
	private JButton btnShowDetail;
	
	private DetailInformation detailInformation;
	private TableDetailVew tableDetailVew;
	private TableBorrowView tableBorrowView;
	
	public ShowDetailInformation (MainUI mainUI) {
		this.mainUI = mainUI;
		detailDB = new DetailDB();
		loanBookDB = new LoanBookDB();
		
		tableBorrowView = mainUI.getTableBorrowView();
		
		btnShowDetail = mainUI.getManagerView().getBtnViewDetail();
		
		
		btnShowDetailEvent();
	}
	
	private void btnShowDetailEvent() {
		btnShowDetail.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				detailView = new DetailView(mainUI);
				detailInformation = detailView.getDetailInformation();
				
				int index = findIndexOfData();
				if (index >= 0) {
					detailView.setVisible(true);
					// Get maSach
					String maMT = getMaMT(index, 0);
					loadInfor(maMT);
					//oldID = getOldID(maSach);
					setActions();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 mượn trả để xem chi tiết");
				}
				
			}
		});
	}
	
	private int findIndexOfData() {
		int index = tableBorrowView.getTable().getSelectedRow();
		return index;
	}
	
	private String getMaMT (int indexRow, int indexCol) {
		JTable table = tableBorrowView.getTable();
		String maMT = table.getModel().getValueAt(indexRow, indexCol).toString();
		return maMT;
	}
	
	private void loadInfor(String maMT) {
		loanBook = loanBookDB.getLoanBook(maMT);
		ArrayList<Detail> listDetail = detailDB.getAllDetailWithID(maMT);
		
		detailInformation.getLbMaMT().setText(maMT);
		detailInformation.getLbMaDG().setText(loanBook.getMaDG());
		detailInformation.getLbMaNV().setText(loanBook.getMaNV());
		detailInformation.getLbHoTenDG().setText(new PersonDB().getPerson("docgia", loanBook.getMaDG()).getName());
		detailInformation.getLbHoTenNV().setText(new PersonDB().getPerson("nhanvien", loanBook.getMaNV()).getName());
		detailInformation.getLbNgayMuon().setText(loanBook.getNgayMuon());
		detailInformation.getLbNgayHenTra().setText(loanBook.getNgayHenTra());
		detailInformation.getLbSoTienCoc().setText(loanBook.getTienCoc() + "");
		detailInformation.getLbNgayTra().setText(listDetail.get(0).getNgayTra());
		if (detailInformation.getLbNgayTra().getText().trim().toString().equals("")) {
			detailInformation.getLbTrangThai().setText("Chưa trả");
		}
		else {
			detailInformation.getLbTrangThai().setText("Đã trả");
		}
		detailInformation.getLbSoTienPhat().setText(listDetail.get(0).getSoTienPhat() + "");
		
		String[][] listBook = new String[listDetail.size()][3];
		for (int i = 0; i < listDetail.size(); i++) {
			listBook[i][0] = listDetail.get(i).getMaSach();
			listBook[i][1] = new BookDB().getBook(listDetail.get(i).getMaSach()).getTenSach();
			listBook[i][2] = listDetail.get(i).getSoLuong() + "";
		}
		detailInformation.getTableDetailVew().updateTable(listBook);
	
	}
	
	
	private void setActions() {
		JButton btnPrint = detailView.getBtnPrint();
		JButton btnCancel = detailView.getBtnCancel();
		
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
	}
	
	private void huy() {
		this.detailView.setVisible(false);
	}
	
	
	
	
	
	
	
	
	
}
