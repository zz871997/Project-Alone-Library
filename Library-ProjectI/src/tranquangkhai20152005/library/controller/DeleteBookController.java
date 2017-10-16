package tranquangkhai20152005.library.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import tranquangkhai20152005.library.model.Book;
import tranquangkhai20152005.library.model.BookDB;
import tranquangkhai20152005.library.view.MainUI;
import tranquangkhai20152005.library.view.TableBookView;

public class DeleteBookController {
	private MainUI mainUI;
	private Book book;
	private BookDB bookDB;
	
	private JButton btnDelete;
	private	TableBookView tableBookView;
	
	// Constructor
	public DeleteBookController(MainUI mainUI) {
		this.mainUI = mainUI;
		bookDB = new BookDB();
		
		btnDelete     = mainUI.getManagerView().getBtnDeleteBook();
		tableBookView = mainUI.getTableBookView();
		tableBookView.updateTable(bookDB.getAllBooks());
		setAction();
	}
	
	/* ------------------ Set Action -------------------- */
	private void setAction() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = findIndexOfData();
				if(index >= 0) {
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá sản phẩm này không?",
							 "Xoá", JOptionPane.YES_NO_OPTION,
							 JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(select == 0) {
						String id = getId(index, 0);
						book = bookDB.getBook(id);
						bookDB.deleteBook(book);
						tableBookView.updateTable(bookDB.getAllBooks());
					}
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 sản phẩm để xóa");	
				}
			}
		});
	}
	
	/* Get index of Row */
	private int findIndexOfData() {
		int index = tableBookView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get id's Product */
	private String getId(int indexRow, int indexCol) {
		JTable table = tableBookView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
}
