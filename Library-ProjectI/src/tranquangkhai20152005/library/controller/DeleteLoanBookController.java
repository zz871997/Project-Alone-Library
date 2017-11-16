package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tranquangkhai20152005.library.model.Detail;
import tranquangkhai20152005.library.model.DetailDB;
import tranquangkhai20152005.library.model.LoanBook;
import tranquangkhai20152005.library.model.LoanBookDB;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBorrowView;

public class DeleteLoanBookController {
	private MainUI mainUI;
	private Detail detail;
	private DetailDB detailDB;
	private LoanBook loanBook;
	private LoanBookDB loanBookDB;
	
	private JButton btnDelete;
	private TableBorrowView tableBorrowView;
	
	public DeleteLoanBookController(MainUI mainUI) {
		this.mainUI = mainUI;
		loanBookDB = new LoanBookDB();
		detailDB = new DetailDB();
		
		btnDelete = mainUI.getManagerView().getBtnDeleteBorrow();
		tableBorrowView = mainUI.getTableBorrowView();
		setAction();
	}
	
	private void setAction() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = findIndexOfData();
				if (index >= 0) {
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá sách này không?",
							 "Xoá", JOptionPane.YES_NO_OPTION,
							 JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(select == 0) {
						String id = getId(index, 0);
						if (!checkDetail(id)) {
							JOptionPane.showMessageDialog(new JDialog(), "Mượn trả này còn có sách chưa trả nên không thể xóa");
							return;
						}
						else {
							detailDB.deleteDetail(id);
							loanBookDB.deleteLoanBook(id);
							tableBorrowView.updateTable(loanBookDB.getAllLoanBooks());
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 mượn trả để xóa");	
				}
			}
		});
	}
	
	/* Get index of Row */
	private int findIndexOfData() {
		int index = tableBorrowView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get id's loanBook */
	private String getId(int indexRow, int indexCol) {
		JTable table = tableBorrowView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	/* Kiem tra chi tiet muon tra con sach chua tra khong? */
	private boolean checkDetail(String maMT) {
		ArrayList<Detail> listDetail = detailDB.getAllDetailWithID(maMT);
		for (int i = 0; i < listDetail.size(); i++) {
			if (listDetail.get(i).getNgayTra().equals("")) return false;
		}
		return true;
	}
}
