package tranquangkhai20152005.library.test;

import tranquangkhai20152005.library.controller.AddBookController;
import tranquangkhai20152005.library.controller.ChangeTableController;
import tranquangkhai20152005.library.controller.DeleteBookController;
import tranquangkhai20152005.library.controller.EditBookController;
import tranquangkhai20152005.library.controller.LoginController;
import tranquangkhai20152005.library.controller.PrintSearchInforController;
import tranquangkhai20152005.library.controller.SearchBookController;
import tranquangkhai20152005.library.view.MainUI;

public class Test {
	public static void main(String[] args) {
		MainUI mainUI = new  MainUI();
		LoginController loginController = new LoginController(mainUI);
	}
}
