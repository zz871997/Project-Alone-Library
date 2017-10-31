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

public class LoanBookDB implements LoanBookDAO{
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
	public ArrayList<LoanBook> getAllLoanBooks() {
		connection = getConnection();
		Statement statement = null;
		ArrayList<LoanBook> listLoanBook = new ArrayList<LoanBook>();
		
		try {
			String sql = "SELECT * FROM muontra";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				String maMT       = result.getString("MaMT");
				String maDG       = result.getString("MaDG");
				String maNV       = result.getString("MaNV");
				String ngayMuon   = result.getString("Ngaymuon");
				String ngayHenTra = result.getString("Ngayhentra");
				
				LoanBook loanBook = new LoanBook(maMT, maDG, maNV, ngayMuon, ngayHenTra);
				listLoanBook.add(loanBook);
			}
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
		return listLoanBook;
	}

	/* Get a loanBook from database */
	@Override
	public LoanBook getLoanBook(String maMT) {
		LoanBook loanBook = null;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM muontra WHERE MaMT = ?";
		
		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String maDG       = result.getString("MaDG");
				String maNV       = result.getString("MaNV");
				String ngayMuon   = result.getString("Ngaymuon");
				String ngayHenTra = result.getString("Ngayhentra");
				
				loanBook = new LoanBook(maMT, maDG, maNV, ngayMuon, ngayHenTra);
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
		
		
		
		
		return loanBook;
	}

	@Override
	public void insertLoanBook(LoanBook loanBook) {
		String maMT       = loanBook.getMaMT();
		String maDG       = loanBook.getMaDG();
		String maNV       = loanBook.getMaNV();
		String ngayMuon   = loanBook.getNgayMuon();
		String ngayHenTra = loanBook.getNgayHenTra();
		
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO muontra (MaMT, MaDG, MaNV, Ngaymuon, Ngayhentra)"
                    + "VALUE (?, ?, ?, ?, ?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			preStatement.setString(2, maDG);
			preStatement.setString(3, maNV);
			preStatement.setString(4, ngayMuon);
			preStatement.setString(5, ngayHenTra);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This loanBook has been inserted");
			
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

}