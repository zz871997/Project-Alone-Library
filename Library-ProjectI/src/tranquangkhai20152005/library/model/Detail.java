package tranquangkhai20152005.library.model;

public class Detail {
	private String maMuon;
	private String maSach;
	private int soLuong;
	private String ngayTra;
	private double soTienPhat;
	private String trangThai;
	
	public String getMaMuon() {
		return maMuon;
	}
	public String getMaSach() {
		return maSach;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public String getNgayTra() {
		return ngayTra;
	}
	public double getSoTienPhat() {
		return soTienPhat;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}	
	public String getTrangThai() {
		return trangThai;
	}
	
	public Detail(String maMuon, String maSach, int soLuong, String ngayTra, double soTienPhat) {
		super();
		this.maMuon = maMuon;
		this.maSach = maSach;
		this.soLuong = soLuong;
		this.ngayTra = ngayTra;
		this.soTienPhat = soTienPhat;
	}
	
	
}
