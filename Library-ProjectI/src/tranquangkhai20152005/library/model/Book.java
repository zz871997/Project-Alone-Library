package tranquangkhai20152005.library.model;

public class Book {
	private String maSach;
	private String tenSach;
	private String tacGia;
	private String NXB;
	private String theLoai;
	private String namXB;
	private int soLuong;
	
	// Getter - Setter
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	public String getNXB() {
		return NXB;
	}
	public void setNXB(String NXB) {
		this.NXB = NXB;
	}
	public String getTheLoai() {
		return theLoai;
	}
	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}
	public String getNamXB() {
		return namXB;
	}
	public void setNamXB(String namXB) {
		this.namXB = namXB;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	// Constructor
	public Book(String maSach, String tenSach, String tacGia, String NXB, String theLoai, String namXB, int soLuong) {
		this.maSach  = maSach;
		this.tenSach = tenSach;
		this.tacGia  = tacGia;
		this.NXB     = NXB;
		this.theLoai = theLoai;
		this.namXB   = namXB;
		this.soLuong = soLuong;
	}
	
	
	
	
}
