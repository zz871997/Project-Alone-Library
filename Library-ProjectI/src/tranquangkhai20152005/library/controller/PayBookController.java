package tranquangkhai20152005.library.controller;

import javax.swing.JButton;

import tranquangkhai20152005.library.model.Detail;
import tranquangkhai20152005.library.model.DetailDB;
import tranquangkhai20152005.library.view.DetailView;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableDetailVew;

public class PayBookController {
	private MainUI mainUI;
	private Detail detail;
	private DetailDB detailDB;
	
	private DetailView detailView;
	private JButton btnPay;
	private TableDetailVew tableDetailVew;
	
	public PayBookController(MainUI mainUI) {
		this.mainUI = mainUI;
		detailDB = new DetailDB();
		
		//detailView
	}
}
