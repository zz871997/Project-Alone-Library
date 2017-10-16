package tranquangkhai20152005.library.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBookView;
import tranquangkhai20152005.library.view.TablePersonView;

public class ChangeTableController {
	private MainUI mainUI;
	private TableBookView tableBookView;
	private TablePersonView tablePersonView;
	private JPanel dataPanel;
	
	JButton btnViewListBook;
	JButton btnViewListUser;
	JButton btnViewListEmployment;
	public ChangeTableController(MainUI mainUI) {
		this.mainUI = mainUI;
		btnViewListBook = mainUI.getManagerView().getBtnViewListBook();
		btnViewListUser = mainUI.getManagerView().getBtnViewListUser();
		btnViewListEmployment = mainUI.getManagerView().getBtnViewListEmployment();
		dataPanel = mainUI.getDataPanel();
		
		setActions();
	}
	
	private void setActions() {
		btnViewListBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataPanel.remove(dataPanel.getComponent(0));
				dataPanel.add(mainUI.getBookDataPanel(), BorderLayout.CENTER);
				dataPanel.revalidate();
				dataPanel.repaint();
			}
		});
		
		btnViewListUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataPanel.remove(dataPanel.getComponent(0));
				dataPanel.add(mainUI.getUserDataPanel(), BorderLayout.CENTER);
				dataPanel.revalidate();
				dataPanel.repaint();
			}
		});
		
		btnViewListEmployment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dataPanel.remove(dataPanel.getComponent(0));
				dataPanel.add(mainUI.getEmploymentDataPanel(), BorderLayout.CENTER);
				dataPanel.revalidate();
				dataPanel.repaint();
			}
		});
	}
}
