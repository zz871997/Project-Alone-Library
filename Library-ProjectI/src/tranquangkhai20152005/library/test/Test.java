package tranquangkhai20152005.library.test;

import tranquangkhai20152005.library.controller.AddBookController;
import tranquangkhai20152005.library.controller.ChangeTableController;
import tranquangkhai20152005.library.controller.DeleteBookController;
import tranquangkhai20152005.library.controller.EditBookController;
import tranquangkhai20152005.library.controller.PrintSearchInforController;
import tranquangkhai20152005.library.controller.SearchBookController;
import tranquangkhai20152005.library.view.MainUI;

public class Test {
	public static void main(String[] args) {
		MainUI mainUI = new  MainUI();
		new AddBookController(mainUI);
		new EditBookController(mainUI);
		new DeleteBookController(mainUI);
		new SearchBookController(mainUI);
		new PrintSearchInforController(mainUI);
		new ChangeTableController(mainUI);
		mainUI.setVisible(true);	
	}
}
