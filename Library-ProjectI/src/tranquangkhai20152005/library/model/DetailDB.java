package tranquangkhai20152005.library.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	
}
