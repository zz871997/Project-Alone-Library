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

public class AcountDB {
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
	
	public String getPassAdmin() {
		connection = getConnection();
		Statement statement = null;
		String passAdmin = "";
	
		try {
			String sql = "SELECT * FROM accountadmin";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				passAdmin = result.getString("Pass");
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
		
		return passAdmin;
	}
	
	public String getPassEmpl (String maNV) {
		String passEmpl = "";
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM accountEmpl WHERE MaNV = ?";
		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maNV);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				passEmpl = result.getString("Pass");
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
		
		return passEmpl;
	}
	
	/* Change Password from DB */
	public void changePassAdminFromDB(String newPass) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE accountadmin SET Pass=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, newPass);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This pass has been changed");
			
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
				e.printStackTrace();
			}
		}
	}
	
	public void changePassEmplFromDB(String accountEmpl, String newPass) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE accountempl SET Pass=? where MaNV=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, newPass);
			preStatement.setString(2, accountEmpl);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This pass has been changed");
			
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
				e.printStackTrace();
			}
		}
	}
	
	/* insert account for employment */
	public void insertAccEmpl (String maNV) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO accountEmpl (MaNV, Pass)"
                    + "VALUE (?, ?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maNV);
			preStatement.setString(2, maNV.toLowerCase());
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This accountEmpl has been inserted");
			
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
	
	/* Delete account of Employment */
	public void deleteAccEmpl(String maNV) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "DELETE FROM accountempl WHERE MaNV = ?";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maNV);
			
			int rows = preStatement.executeUpdate();
			if(rows > 0) System.out.println("This accountEmpl has been deleted");
			
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
}
