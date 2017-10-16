package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PersonInformation extends JPanel{
	int padding = 10;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfNamSinh;
	private JTextField tfGioiTinh;
	private JTextField tfQueQuan;
	private JTextField tfDiaChi;
	private JTextField tfSDT;
	
	public PersonInformation() {
		setSize(450, 350);
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Thông tin"));
		add(createPanelLabel(), BorderLayout.WEST);
		add(createPanelTextField(), BorderLayout.CENTER);
	}

	public JTextField getTfId() {
		return tfId;
	}
	public JTextField getTfName() {
		return tfName;
	}
	public JTextField getTfNamSinh() {
		return tfNamSinh;
	}
	public JTextField getTfGioiTinh() {
		return tfGioiTinh;
	}
	public JTextField getTfQueQuan() {
		return tfQueQuan;
	}
	public JTextField getTfDiaChi() {
		return tfDiaChi;
	}
	public JTextField getTfSDT() {
		return tfSDT;
	}
	
	private JPanel createPanelLabel() {
		JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
		panel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		panel.add(new JLabel("Mã"));
		panel.add(new JLabel("Họ Tên"));
		panel.add(new JLabel("Năm sinh"));
		panel.add(new JLabel("Giới tính"));
		panel.add(new JLabel("Quê quán"));
		panel.add(new JLabel("Đại chỉ"));
		panel.add(new JLabel("Số điện thoại"));
		
		return panel;
	}
	
	private JPanel createPanelTextField() {
		JPanel panel = new JPanel (new GridLayout(7, 1, 5, 5));
		panel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		tfId       = new JTextField(30);			panel.add(tfId);
		tfName     = new JTextField(30);			panel.add(tfName);
		tfNamSinh  = new JTextField(30);			panel.add(tfNamSinh);
		tfGioiTinh = new JTextField(30);			panel.add(tfGioiTinh);
		tfQueQuan  = new JTextField(30);			panel.add(tfQueQuan);
		tfDiaChi   = new JTextField(30);			panel.add(tfDiaChi);
		tfSDT      = new JTextField(30);			panel.add(tfSDT);
			
		return panel;
	}
}
