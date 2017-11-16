package tranquangkhai20152005.library.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.PreparedStatement;

public class DetailDB implements DetailDAO{
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
	public ArrayList<Detail> getAllDetailWithID(String maMT) {
		connection = getConnection();
		ArrayList<Detail> listDetail = new ArrayList<Detail>();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM chitietmuon WHERE Mamuon = ?";
		
		
		try {
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String maSach = result.getString("Masachmuon");
				int soLuong = result.getInt("Soluongmuon");
				String ngayTra = result.getString("Ngaytra");
				double soTienPhat = result.getDouble("Sotienphat");
				
				Detail detail = new Detail(maMT, maSach, soLuong, ngayTra, soTienPhat);
				listDetail.add(detail);
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
		
		return listDetail;
	}

	@Override
	public void insertDetail(Detail detail) {
		String maMuon     = detail.getMaMuon();
		String maSach     = detail.getMaSach();
		int soLuong       = detail.getSoLuong();
		String ngayTra    = detail.getNgayTra();
		double soTienPhat = detail.getSoTienPhat();
		
		connection = getConnection();
		PreparedStatement preStatement = null;
		try {
			String sql = "INSERT INTO chitietmuon (Mamuon, Masachmuon, Soluongmuon, Ngaytra, Sotienphat)"
                    + "VALUE (?, ?, ?, ?, ?)";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maMuon);
			preStatement.setString(2, maSach);
			preStatement.setInt(3, soLuong);
			preStatement.setString(4, ngayTra);
			preStatement.setDouble(5, soTienPhat);
						
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Detail loan has been insert");
			
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

	@Override
	public void updateDetail(Detail detail, String ngayTraMoi, double tienPhatMoi) {
		String maMT = detail.getMaMuon();
		String maSach = detail.getMaSach();
		connection = getConnection();
		PreparedStatement preStatement = null;
		try {
			String sql = "UPDATE chitietmuon SET Ngaytra=?, Sotienphat=? WHERE (Mamuon=? AND Masachmuon=?)";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, ngayTraMoi);
			preStatement.setDouble(2, tienPhatMoi);
			preStatement.setString(3, maMT);
			preStatement.setString(4, maSach);
			
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This loanBookDetail has been update");
			
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

	@Override
	public Detail getDetail(String maMT, String maSachMuon) {
		Detail detail = null;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM chitietmuon WHERE (Mamuon=? AND Masachmuon=?)";
		try {
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			preStatement.setString(2, maSachMuon);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				int soLuongMuon = result.getInt("Soluongmuon");
				String ngayTra  = result.getString("Ngaytra");
				double soTienPhat = result.getDouble("Sotienphat");
				
				detail = new Detail(maMT, maSachMuon, soLuongMuon, ngayTra, soTienPhat);
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
		
		return detail;
	}

	@Override
	public double tinhTongPhat(String maMT) {
		double tongTienPhat = 0.0;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT sum(Sotienphat) FROM chitietmuon WHERE Mamuon=?";
		try {
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				
				tongTienPhat = result.getDouble("sum(Sotienphat)");
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
		
		return tongTienPhat;
	}

	@Override
	public void deleteDetail(String maMT) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "DELETE FROM chitietmuon WHERE Mamuon = ?";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			
			int rows = preStatement.executeUpdate();
			if(rows > 0) System.out.println("This detail has been deleted");
			
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
	public ArrayList<String> getListBookIsLoan() {
		connection = getConnection();
		Statement statement = null;
		ArrayList<String> listBook = new ArrayList<String>();
		
		try {
			String sql = "select distinct Masachmuon from chitietmuon";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				String maSach  = result.getString("Masachmuon");
				listBook.add(maSach);
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
}
