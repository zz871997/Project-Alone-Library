package tranquangkhai20152005.library.model;

import java.util.ArrayList;

public interface BookDAO {
	public ArrayList<Book> getAllBooks();
	public Book getBook(String maSach);
	
	// Update a Book - not update soLuong
	public void updateBook(Book book, String maSachMoi, String tenSachMoi, String tacGiaMoi,
						   String NXBMoi, String theLoaiMoi, String namXBMoi);
	
	// Insert a book
	public void insertBook(Book book);
	
	// Delete a book
	public void deleteBook(Book book);
}
