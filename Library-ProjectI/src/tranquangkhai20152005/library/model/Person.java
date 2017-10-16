package tranquangkhai20152005.library.model;

public class Person {
	private String id;
	private String name;
	private String namSinh;
	private String gioiTinh;
	private String queQuan;
	private String diaChi;
	private String sdt;
	
	// Constructor
	public Person(String id, String name, String namSinh, String gioiTinh, String queQuan, String diaChi, String sdt) {
		this.id = id;
		this.name = name;
		this.namSinh = namSinh;
		this.gioiTinh = gioiTinh;
		this.queQuan = queQuan;
		this.diaChi = diaChi;
		this.sdt = sdt;
	}
	
	// Setter - Getter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNamSinh() {
		return namSinh;
	}
	public void setNamSinh(String namSinh) {
		this.namSinh = namSinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getQueQuan() {
		return queQuan;
	}
	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
}
