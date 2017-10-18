package tranquangkhai20152005.library.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.sql.Statement;

public class PersonDB implements PersonDAO{
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
	public ArrayList<Person> getAllPersons(String tableName) {
		connection = getConnection();
		Statement statement = null;
		ArrayList<Person> listPerson = new ArrayList<Person>();
		
		try {
			String sql = "SELECT * FROM " + tableName;
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (tableName.equals("docgia")) {
				while (result.next()) {
					String id       = result.getString("MaDG");
					String name     = result.getString("TenDG");
					String namSinh  = result.getString("Namsinh");
					String gioiTinh = result.getString("Gioitinh");
					String queQuan  = result.getString("Quequan");
					String diaChi   = result.getString("Diachi");
					String sdt      = result.getString("SDT");
					
					Person person = new Person(id, name, namSinh, gioiTinh, queQuan, diaChi, sdt);
					
					listPerson.add(person);
				}
			}
			else if (tableName.equals("nhanvien")) {
				while (result.next()) {
					String id       = result.getString("MaNV");
					String name     = result.getString("TenNV");
					String namSinh  = result.getString("Namsinh");
					String gioiTinh = result.getString("Gioitinh");
					String queQuan  = result.getString("Quequan");
					String diaChi   = result.getString("Diachi");
					String sdt      = result.getString("SDT");
					
					Person person = new Person(id, name, namSinh, gioiTinh, queQuan, diaChi, sdt);
					
					listPerson.add(person);
				}
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
		
		return listPerson;
	}

	@Override
	public Person getPerson(String tableName, String id) {
		Person person = null;
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		if (tableName.equals("docgia")) {
			String sql = "SELECT * FROM docgia WHERE MaDG = ?";
			
			try {
				preStatement = connection.prepareStatement(sql);
				preStatement.setString(1, id);
				ResultSet result = preStatement.executeQuery();
				
				while (result.next()) {
					
					String name     = result.getString("TenDG");
					String namSinh  = result.getString("Namsinh");
					String gioiTinh = result.getString("Gioitinh");
					String queQuan  = result.getString("Quequan");
					String diaChi   = result.getString("Diachi");
					String sdt      = result.getString("SDT");
					
					person = new Person(id, name, namSinh, gioiTinh, queQuan, diaChi, sdt);
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
			
		}
		
		else if (tableName.equals("nhanvien")){
			String sql = "SELECT * FROM nhanvien WHERE MaNV = ?";
			try {
				preStatement = connection.prepareStatement(sql);
				preStatement.setString(1, id);
				ResultSet result = preStatement.executeQuery();
				
				while (result.next()) {	
					String name     = result.getString("TenNV");
					String namSinh  = result.getString("Namsinh");
					String gioiTinh = result.getString("Gioitinh");
					String queQuan  = result.getString("Quequan");
					String diaChi   = result.getString("Diachi");
					String sdt      = result.getString("SDT");
					
					person = new Person(id, name, namSinh, gioiTinh, queQuan, diaChi, sdt);
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
		}
		return person;
	}

	@Override
	public void updatePerson(String tableName, Person person, String idMoi, String nameMoi, String namSinhMoi,
			String gioiTinhMoi, String queQuanMoi, String diaChiMoi, String sdtMoi) {
		String id = person.getId();
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		if (tableName.equals("docgia")) {
			try {
				String sql = "UPDATE docgia SET MaDG=?, TenDG=?, Namsinh=?, Gioitinh=?, Quequan=?, Diachi=?, SDT=? WHERE MaDG=?";
				preStatement = connection.prepareStatement(sql);
				preStatement.setString(1, idMoi);
				preStatement.setString(2, nameMoi);
				preStatement.setString(3, namSinhMoi);
				preStatement.setString(4, gioiTinhMoi);
				preStatement.setString(5, queQuanMoi);
				preStatement.setString(6, diaChiMoi);
				preStatement.setString(7, sdtMoi);
				preStatement.setString(8, id);
				
				int rows = preStatement.executeUpdate();
				if (rows > 0) System.out.println("This user has been update");
				
				// Close connectuin
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
		else if (tableName.equals("nhanvien")) {
			try {
				String sql = "UPDATE nhanvien SET MaNV=?, TenNV=?, Namsinh=?, Gioitinh=?, Quequan=?, Diachi=?, SDT=? WHERE MaNV=?";
				preStatement = connection.prepareStatement(sql);
				preStatement.setString(1, idMoi);
				preStatement.setString(2, nameMoi);
				preStatement.setString(3, namSinhMoi);
				preStatement.setString(4, gioiTinhMoi);
				preStatement.setString(5, queQuanMoi);
				preStatement.setString(6, diaChiMoi);
				preStatement.setString(7, sdtMoi);
				preStatement.setString(8, id);
				
				int rows = preStatement.executeUpdate();
				if (rows > 0) System.out.println("This employment has been update");
				
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

	@Override
	public void insertPerson(String tableName, Person person) {
		String id       = person.getId();
		String name     = person.getName();
		String namSinh  = person.getNamSinh();
		String gioiTinh = person.getGioiTinh();
		String queQuan  = person.getQueQuan();
		String diaChi   = person.getDiaChi();
		String sdt      = person.getSdt();
		
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		if (tableName.equals("docgia")) {
			try {
				String sql = "INSERT INTO docgia (MaDG, TenDG, Namsinh, Gioitinh, Quequan, Diachi, SDT)"
	                    + "VALUE (?, ?, ?, ?, ?, ?, ?)";
				preStatement = connection.prepareStatement(sql);
				preStatement.setString(1, id);
				preStatement.setString(2, name);
				preStatement.setString(3, namSinh);
				preStatement.setString(4, gioiTinh);
				preStatement.setString(5, queQuan);
				preStatement.setString(6, diaChi);
				preStatement.setString(7, sdt);
				
				int rows = preStatement.executeUpdate();
				if (rows > 0) System.out.println("This user has been inserted");
				
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
		
		else if (tableName.equals("nhanvien")) {
			try {
				String sql = "INSERT INTO nhanvien (MaNV, TenNV, Namsinh, Gioitinh, Quequan, Diachi, SDT)"
	                    + "VALUE (?, ?, ?, ?, ?, ?, ?)";
				preStatement = connection.prepareStatement(sql);
				preStatement.setString(1, id);
				preStatement.setString(2, name);
				preStatement.setString(3, namSinh);
				preStatement.setString(4, gioiTinh);
				preStatement.setString(5, queQuan);
				preStatement.setString(6, diaChi);
				preStatement.setString(7, sdt);
				
				int rows = preStatement.executeUpdate();
				if (rows > 0) System.out.println("This employment has been inserted");
				
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

	@Override
	public void deletePerson(String tableName, Person person) {
		String id = person.getId();
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = null;
		
		if(tableName.equals("docgia")) {
			sql = "DELETE FROM docgia WHERE MaDG = ?";
		}
		else if (tableName.equals("nhanvien")) {
			sql = "DELETE FROM nhanvien WHERE MaNV = ?";
		}
		
		try {
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, id);
			
			int rows = preStatement.executeUpdate();
			if(rows > 0) System.out.println("This person has been deleted");
			
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
