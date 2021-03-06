package tranquangkhai20152005.library.model;

public class LoanBook {
	private String maMT;
	private String maDG;
	private String maNV;
	private String ngayMuon;
	private String ngayHenTra;
	private int tienCoc;
	
	// Getter
	public String getMaMT() {
		return maMT;
	}
	public String getMaDG() {
		return maDG;
	}
	public String getMaNV() {
		return maNV;
	}
	public String getNgayMuon() {
		return ngayMuon;
	}
	public String getNgayHenTra() {
		return ngayHenTra;
	}
	public int getTienCoc() {
		return tienCoc;
	}
	// COnstructor
	public LoanBook(String maMT, String maDG, String maNV, String ngayMuon, String ngayHenTra, int tienCoc) {
		super();
		this.maMT = maMT;
		this.maDG = maDG;
		this.maNV = maNV;
		this.ngayMuon = ngayMuon;
		this.ngayHenTra = ngayHenTra;
		this.tienCoc = tienCoc;
	}
}
