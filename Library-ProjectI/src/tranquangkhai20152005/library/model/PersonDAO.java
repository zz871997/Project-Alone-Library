package tranquangkhai20152005.library.model;

import java.util.ArrayList;

public interface PersonDAO {
	public ArrayList<Person> getAllPersons(String tableName);
	public Person getPerson(String tableName, String id);
	
	// Update a person
	public void updatePerson(String tableName, Person person, String idMoi, String nameMoi, String namSinhMoi,
							String gioiTinhMoi, String queQuanMoi, String diaChiMoi, String sdtMoi);
	
	// Insert a person
	public void insertPerson(String tableName, Person person);
	
	// Delete a person
	public void deletePerson(String tableName, Person person);
	
	// Thong ke person
	public ArrayList<ArrayList<String>> thongKePerson(String tableName, String colName);
}
