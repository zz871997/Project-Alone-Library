package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailInformation extends JPanel{
	private JLabel lbMaMT;
	private JLabel lbMaDG;
	private JLabel lbHoTenDG;
	private JLabel lbMaNV;
	private JLabel lbHoTenNV;
	
	private TableDetailVew tableDetailVew;
	
	private JLabel lbNgayMuon;
	private JLabel lbNgayHenTra;
	private JLabel lbTrangThai;
	private JLabel lbNgayTra;
	private JLabel lbSoTienPhat;
	private JLabel lbSoTienCoc;
	
	// Getter
	public JLabel getLbMaMT() {
		return lbMaMT;
	}
	public JLabel getLbMaDG() {
		return lbMaDG;
	}
	public JLabel getLbHoTenDG() {
		return lbHoTenDG;
	}
	public JLabel getLbMaNV() {
		return lbMaNV;
	}
	public JLabel getLbHoTenNV() {
		return lbHoTenNV;
	}
	public TableDetailVew getTableDetailVew() {
		return tableDetailVew;
	}
	public JLabel getLbNgayMuon() {
		return lbNgayMuon;
	}
	public JLabel getLbNgayHenTra() {
		return lbNgayHenTra;
	}
	public JLabel getLbTrangThai() {
		return lbTrangThai;
	}
	public JLabel getLbNgayTra() {
		return lbNgayTra;
	}
	public JLabel getLbSoTienPhat() {
		return lbSoTienPhat;
	}
	public JLabel getLbSoTienCoc() {
		return lbSoTienCoc;
	}
	
	public DetailInformation() {
		setLayout(new BorderLayout());
		add(createHeaderPanel(), BorderLayout.PAGE_START);
		add(createCenterPanel(),  BorderLayout.CENTER);
		add(createFooterPanel(), BorderLayout.PAGE_END);
		
	}
	
	private JPanel createHeaderPanel() {
		JPanel header = new JPanel (new GridLayout(5, 2, 10, 10));
		header.add(new JLabel("Mã mượn trả:      "));	  		header.add(lbMaMT    = new JLabel());
		header.add(new JLabel("Mã độc giả:       "));			header.add(lbMaDG    = new JLabel());
		header.add(new JLabel("Họ tên độc giả:   "));			header.add(lbHoTenDG = new JLabel());
		header.add(new JLabel("Mã nhân viên:     "));			header.add(lbMaNV    = new JLabel());
		header.add(new JLabel("Họ tên nhân viên: "));			header.add(lbHoTenNV = new JLabel());
		
		return header;
	}
	
	private JPanel createCenterPanel() {
		JPanel center = new JPanel (new BorderLayout());
		center.add(new JLabel("Danh sách sách được mượn"), BorderLayout.PAGE_START);
		
		
		// Hmmmm .......... Load Data???????????
		tableDetailVew = new TableDetailVew();
		center.add(tableDetailVew, BorderLayout.CENTER);
		
		return center;
	}
	
	private JPanel createFooterPanel() {
		JPanel footer = new JPanel(new GridLayout(6, 2, 10, 10));
		footer.add(new JLabel("Ngày mượn: "));				footer.add(lbNgayMuon = new JLabel());
		footer.add(new JLabel("Ngày hẹn trả:"));			footer.add(lbNgayHenTra = new JLabel());
		footer.add(new JLabel("Trạng thái:"));				footer.add(lbTrangThai = new JLabel());
		footer.add(new JLabel("Ngày trả: "));				footer.add(lbNgayTra = new JLabel());
		footer.add(new JLabel("Tiền cọc: "));				footer.add(lbSoTienCoc = new JLabel());
		footer.add(new JLabel("Số tiền phạt: "));			footer.add(lbSoTienPhat = new JLabel());
		
		return footer;
	}
	

}
