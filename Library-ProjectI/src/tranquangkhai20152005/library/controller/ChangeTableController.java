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
	private JPanel managerPanel;
	
	JButton btnViewListBook;
	JButton btnViewListUser;
	JButton btnViewListEmployment;
	JButton btnBorrow;
	
	public ChangeTableController(MainUI mainUI) {
		this.mainUI = mainUI;
		btnViewListBook = mainUI.getMenuView().getBtnViewListBook();
		btnViewListUser = mainUI.getMenuView().getBtnViewListUser();
		btnViewListEmployment = mainUI.getMenuView().getBtnViewListEmployment();
		btnBorrow = mainUI.getMenuView().getBtnBorrow();
		dataPanel    = mainUI.getDataPanel();
		managerPanel = mainUI.getManagerView();
		
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

				managerPanel.remove(managerPanel.getComponent(0));
				managerPanel.add(mainUI.getManagerView().getBookManagerPanel(), BorderLayout.CENTER);
				managerPanel.revalidate();
				managerPanel.repaint();
			}
		});
		
		btnViewListUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataPanel.remove(dataPanel.getComponent(0));
				dataPanel.add(mainUI.getUserDataPanel(), BorderLayout.CENTER);
				dataPanel.revalidate();
				dataPanel.repaint();
				
				managerPanel.remove(managerPanel.getComponent(0));
				managerPanel.add(mainUI.getManagerView().getUserManagerPanel(), BorderLayout.CENTER);
				managerPanel.revalidate();
				managerPanel.repaint();
			}
		});
		
		btnViewListEmployment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dataPanel.remove(dataPanel.getComponent(0));
				dataPanel.add(mainUI.getEmploymentDataPanel(), BorderLayout.CENTER);
				dataPanel.revalidate();
				dataPanel.repaint();
				
				managerPanel.remove(managerPanel.getComponent(0));
				managerPanel.add(mainUI.getManagerView().getEmploymentManagerPanel(), BorderLayout.CENTER);
				managerPanel.revalidate();
				managerPanel.repaint();
			}
		});
		
		btnBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataPanel.remove(dataPanel.getComponent(0));
				dataPanel.add(mainUI.getBorrowDataPanel(), BorderLayout.CENTER);
				dataPanel.revalidate();
				dataPanel.repaint();
				
				managerPanel.remove(managerPanel.getComponent(0));
				managerPanel.add(mainUI.getManagerView().getBorrowManagerPanel(), BorderLayout.CENTER);
				managerPanel.revalidate();
				managerPanel.repaint();
			}
		});
	}
}
