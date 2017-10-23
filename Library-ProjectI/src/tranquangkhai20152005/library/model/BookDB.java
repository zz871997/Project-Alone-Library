package tranquangkhai20152005.library.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class BookDB implements BookDAO{
	private final String dbURL = "jdbc:mysql://localhost:3306/library";
	private String user = "root";
	private String password = "1234";
	private Connection connection;
	
	/* Get a connection - Connect to Database (MySQL) */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbURL, user, password);
			if(conn != null) {
				System.out.println("Connected!");
			}	
		} 
		catch (SQLException e) {
			System.out.println("Connecting Failed!");
			e.printStackTrace();
		}
		
		return conn;
	}

	@Override
	public ArrayList<Book> getAllBooks() {
		connection = getConnection();
		Statement statement = null;
		ArrayList<Book> listBook = new ArrayList<Book>();
		
		try {
			String sql = "SELECT * FROM sach";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				String maSach  = result.getString("Masach");
				String tenSach = result.getString("Tensach");
				String tacGia  = result.getString("Tacgia");
				String NXB     = result.getString("NXB");
				String theLoai = result.getString("Theloai");
				String namXB   = result.getString("NamXB");
				int    soLuong = result.getInt("Soluong");
				
				Book book = new Book(maSach, tenSach, tacGia, NXB, theLoai, namXB, soLuong);
				listBook.add(book);
			}
			// Close connection
			result.close();
			statement.close();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
			
		}
		finally {
			try {
				if(statement  != null) statement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listBook;
	}
	
	// Get a book from database when known maSach
	@Override
	public Book getBook(String maSach) {
		Book book = null;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM sach WHERE Masach = ?";
		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maSach);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String tenSach = result.getString("Tensach");
				String tacGia  = result.getString("Tacgia");
				String NXB     = result.getString("NXB");
				String theLoai = result.getString("Theloai");
				String namXB   = result.getString("NamXB");
				int    soLuong = result.getInt("Soluong");
				
				book = new Book(maSach, tenSach, tacGia, NXB, theLoai, namXB, soLuong);
			}
			// Close connection
			result.close();
			preStatement.close();
			connection.close();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return book;
	}

	// Update book's information
	@Override
	public void updateBook(Book book, String maSachMoi, String tenSachMoi, String tacGiaMoi, String NXBMoi,
			String theLoaiMoi, String namXBMoi) {
		String maSach = book.getMaSach();
		connection = getConnection();
		PreparedStatement preStatement = null;
		try {
			String sql = "UPDATE sach SET Masach=?, Tensach=?, Tacgia=?, NXB=?, Theloai=?, NamXB=? WHERE Masach=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maSachMoi);
			preStatement.setString(2, tenSachMoi);
			preStatement.setString(3, tacGiaMoi);
			preStatement.setString(4, NXBMoi);
			preStatement.setString(5, theLoaiMoi);
			preStatement.setString(6, namXBMoi);
			preStatement.setString(7, maSach);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This book has been update");
			
			// Close connection
			preStatement.close();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Insert a book 
	@Override
	public void insertBook(Book book) {
		String maSach  = book.getMaSach();
		String tenSach = book.getTenSach();
		String tacGia  = book.getTacGia();
		String NXB     = book.getNXB();
		String theLoai = book.getTheLoai();
		String namXB   = book.getNamXB();
		int    soLuong = book.getSoLuong();
		
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO sach (Masach, Tensach, Tacgia, NXB, Theloai, NamXB, Soluong)"
                    + "VALUE (?, ?, ?, ?, ?, ?, ?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maSach);
			preStatement.setString(2, tenSach);
			preStatement.setString(3, tacGia);
			preStatement.setString(4, NXB);
			preStatement.setString(5, theLoai);
			preStatement.setString(6, namXB);
			preStatement.setInt(7, soLuong);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This book has been inserted");
			
			// CLose connections
			preStatement.close();
			connection.close();
		} 
		
		catch (SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
			e.printStackTrace();
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Delete a book
	@Override
	public void deleteBook(Book book) {
		String maSach = book.getMaSach();
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "DELETE FROM sach WHERE Masach = ?";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maSach);
			
			int rows = preStatement.executeUpdate();
			if(rows > 0) System.out.println("This Book has been deleted");
			
			//Close connection
			preStatement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database... \n Check your internet...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void updateBook(Book book, int soLuongMoi) {
		String maSach = book.getMaSach();
		connection = getConnection();
		PreparedStatement preStatement = null;
		try {
			String sql = "UPDATE sach SET Soluong=? WHERE Masach=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, soLuongMoi);
			preStatement.setString(2, maSach);
			
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This book has been update number of book");
			
			// Close connection
			preStatement.close();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
