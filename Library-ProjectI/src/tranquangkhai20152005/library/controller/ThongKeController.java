package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import tranquangkhai20152005.library.model.BookDB;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.ThongKeInformation;
import tranquangkhai20152005.library.view.ThongKeView;

public class ThongKeController {
	private MainUI mainUI;
	private BookDB bookDB;
	
	
	
	private ThongKeView thongKeView;
	private ThongKeInformation thongKeInformation;
	private JButton btnThongKeSach;
	private JComboBox<String> cbTKSach;
	
	public ThongKeController(MainUI mainUI) {
		this.mainUI = mainUI;
		bookDB = new BookDB();
		
		btnThongKeSach = mainUI.getManagerView().getBtnThongKeSach();
		cbTKSach = mainUI.getManagerView().getCbTKSach();
		
		btnThongKeSachEvent();
	}
	
	private void btnThongKeSachEvent() {
		btnThongKeSach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbTKSach.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(mainUI, "Hãy chọn 1 kiểu thống kê");
					return;
				}
				
				else {
					thongKeView = new ThongKeView(mainUI);
					thongKeInformation = thongKeView.getThongKeInformation();
					thongKeView.setVisible(true);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate localDate   = LocalDate.now();
					String ngayHT     = Integer.toString(localDate.getDayOfMonth());
					String thangHT    = Integer.toString(localDate.getMonthValue());
					String namHT      = Integer.toString(localDate.getYear());
					String now = ngayHT + "-" + thangHT + "-" + namHT;
					thongKeInformation.getLbCurrentDate().setText(now);
					
					int indexOfCombo = cbTKSach.getSelectedIndex();
					String title = cbTKSach.getSelectedItem().toString();
					thongKeInformation.getLbTitle().setText("THỐNG KÊ SÁCH THEO " + title);
					
					loadDataForTKTable(indexOfCombo);
				
				}
			}
		});
	}
	
	private void loadDataForTKTable(int indexOfCb) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if (indexOfCb == 1) {
			result = bookDB.thongKeSach("Tacgia");
		}
		else if (indexOfCb == 2) {
			result = bookDB.thongKeSach("NXB");
			
		}
		else if (indexOfCb == 3) {
			result = bookDB.thongKeSach("Theloai");
		}
		else if (indexOfCb == 4) {
			result = bookDB.thongKeSach("NamXB");
		}
		
		String[][] data = convertToString(result);
		thongKeInformation.getTableThongKeView().updateTable(data, indexOfCb);
	}
	
	/* Convert ArrayList<ArrayList<String>> to String[][] */
	private String[][] convertToString (ArrayList<ArrayList<String>> arr) {
		String[][] convertResult = new String[arr.size()][3];
		for (int i = 0; i < arr.size(); i ++) {
			convertResult[i][0] = Integer.toString(i+1);
			convertResult[i][1] = arr.get(i).get(0);
			convertResult[i][2] = arr.get(i).get(1);
		}
		
		return convertResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
