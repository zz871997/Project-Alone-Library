package tranquangkhai20152005.library.model;

import java.util.ArrayList;

public interface DetailDAO {
	public ArrayList<Detail> getAllDetailWithID (String maMT);
	
	public void insertDetail(Detail detail);
}
