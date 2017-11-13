package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class BookInformation extends JPanel{
	int padding = 10;
	private JTextField tfMaSach;
	private JTextField tfTenSach;
	private JTextField tfTacGia;
	private JTextField tfNXB;
	private JTextField tfTheLoai;
	private JTextField tfNamXB;
	private JTextField tfSoLuong;
	
	// Constructor
	public BookInformation() {
		setSize(450, 350);
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Thông tin"));
		add(createPanelLabel(), BorderLayout.WEST);
		add(createPanelTextField(), BorderLayout.CENTER);
	}

	// Setter - Getter
	public JTextField getTfMaSach() {
		return tfMaSach;
	}
	public void setTfMaSach(JTextField tfMaSach) {
		this.tfMaSach = tfMaSach;
	}
	public JTextField getTfTenSach() {
		return tfTenSach;
	}
	public void setTfTenSach(JTextField tfTenSach) {
		this.tfTenSach = tfTenSach;
	}
	public JTextField getTfTacGia() {
		return tfTacGia;
	}
	public void setTfTacGia(JTextField tfTacGia) {
		this.tfTacGia = tfTacGia;
	}
	public JTextField getTfNXB() {
		return tfNXB;
	}
	public void setTfNXB(JTextField tfNXB) {
		this.tfNXB = tfNXB;
	}
	public JTextField getTfTheLoai() {
		return tfTheLoai;
	}
	public void setTfTheLoai(JTextField tfTheLoai) {
		this.tfTheLoai = tfTheLoai;
	}

	public JTextField getTfNamXB() {
		return tfNamXB;
	}
	public void setTfNamXB(JTextField tfNamXB) {
		this.tfNamXB = tfNamXB;
	}
	public JTextField getTfSoLuong() {
		return tfSoLuong;
	}
	public void setTfSoLuong(JTextField tfSoLuong) {
		this.tfSoLuong = tfSoLuong;
	}
	
	// Create Panel Label
	private JPanel createPanelLabel() {
		JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
		panel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		panel.add(new JLabel("Mã sách"));
		panel.add(new JLabel("Tên sách"));
		panel.add(new JLabel("Tác giả"));
		panel.add(new JLabel("Nhà xuất bản"));
		panel.add(new JLabel("Thể loại"));
		panel.add(new JLabel("Năm xuất bản"));
		panel.add(new JLabel("Số lượng"));
		
		return panel;
	}
	
	// Create panel TextField
	private JPanel createPanelTextField() {
		JPanel panel = new JPanel (new GridLayout(7, 1, 5, 5));
		panel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		tfMaSach  = new JTextField(30);			panel.add(tfMaSach);
		tfTenSach = new JTextField(30);			panel.add(tfTenSach);
		tfTacGia  = new JTextField(30);			panel.add(tfTacGia);
		tfNXB     = new JTextField(30);			panel.add(tfNXB);
		tfTheLoai = new JTextField(30);			panel.add(tfTheLoai);
		tfNamXB   = new JTextField(30);			panel.add(tfNamXB);
		tfSoLuong = new JTextField(30);			panel.add(tfSoLuong);
			
		return panel;
	}	
}
