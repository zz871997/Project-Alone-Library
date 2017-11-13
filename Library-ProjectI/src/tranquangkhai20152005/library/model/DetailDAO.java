package tranquangkhai20152005.library.model;

import java.util.ArrayList;

public interface DetailDAO {
	public ArrayList<Detail> getAllDetailWithID (String maMT);
	
	public Detail getDetail(String maMT, String maSachMuon);
	
	public void insertDetail(Detail detail);
	
	public void updateDetail(Detail detail, String ngayTraMoi, double tienPhatMoi);
	
	public double tinhTongPhat(String maMT);
}
