package tranquangkhai20152005.library.test;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tranquangkhai20152005.library.controller.LoginController;
import tranquangkhai20152005.library.view.MainUI;

public class Test {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				 try {
					 UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					 MainUI mainUI = new  MainUI();
					 LoginController loginController = new LoginController(mainUI);
				 } 
				 catch (ClassNotFoundException | InstantiationException | 
						IllegalAccessException | UnsupportedLookAndFeelException ex) {
					 System.out.println("Failed");
					 MainUI mainUI = new  MainUI();
					 LoginController loginController = new LoginController(mainUI);
				 }
			}
		});
		
	}
}
