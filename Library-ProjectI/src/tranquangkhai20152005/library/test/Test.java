package tranquangkhai20152005.library.test;

import tranquangkhai20152005.library.controller.AddBookController;
import tranquangkhai20152005.library.view.MainUI;

public class Test {
	public static void main(String[] args) {
		MainUI mainUI = new  MainUI();
		new AddBookController(mainUI);
		mainUI.setVisible(true);
	}
}
