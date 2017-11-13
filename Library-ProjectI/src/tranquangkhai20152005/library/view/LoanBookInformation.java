package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LoanBookInformation extends JPanel {
	int padding = 10;
	private JTextField tfMaMT;
	private JComboBox<String> cbMaDG;
	private JComboBox<String> cbMaNV;
	private JTextField tfMaSach;
	private JTextField tfSoLuongSach;
	private JTextField tfNgayMuon;
	private JTextField tfTienCoc;
	
	private String[] date = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
							 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
							 "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private JComboBox<String> cbNgayHenTra = new JComboBox<String>(date);
	private String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			 				  "11", "12"};
	private JComboBox<String> cbThangHenTra = new JComboBox<>(month);
	private String[] year = {"2017", "2018", "2019", "2020"};
	private JComboBox<String> cbNamHenTra = new JComboBox<>(year);
	private JLabel lbHoTenDG;
	private JLabel lbHoTenNV;
	private JButton btnAddThisBook = new JButton("THÊM");
	
	private JPanel rightPanel      = createRightPanel();
	
	public LoanBookInformation() {
		setLayout(new GridLayout(1, 2, 5, 5));
		add(createCenterPanel());
		JScrollPane scroll = new JScrollPane(rightPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(430, 400));
		scroll.setBorder(new TitledBorder("Sách mượn"));
		
		add(scroll);
		repaint();
	}

	// Setter - Getter
	public JPanel getRightPanel() {
		return rightPanel;
	}
	public JTextField getTfMaMT() {
		return tfMaMT;
	}
	public void setCbMaDG(JComboBox<String> cbMaDG) {
		this.cbMaDG = cbMaDG;
	}

	public void setCbMaNV(JComboBox<String> cbMaNV) {
		this.cbMaNV = cbMaNV;
	}

	public JComboBox<String> getCbMaDG() {
		return cbMaDG;
	}
	public JComboBox<String> getCbMaNV() {
		return cbMaNV;
	}
	public JTextField getTfMaSach() {
		return tfMaSach;
	}
	public JTextField getTfSoLuongSach() {
		return tfSoLuongSach;
	}
	public JTextField getTfNgayMuon() {
		return tfNgayMuon;
	}
	public JComboBox<String> getCbNgayHenTra() {
		return cbNgayHenTra;
	}
	public JComboBox<String> getCbThangHenTra() {
		return cbThangHenTra;
	}
	public JComboBox<String> getCbNamHenTra() {
		return cbNamHenTra;
	}
	public JLabel getLbHoTenDG() {
		return lbHoTenDG;
	}
	public JLabel getLbHoTenNV() {
		return lbHoTenNV;
	}
	public JButton getBtnAddThisBook() {
		return btnAddThisBook;
	}
	public JTextField getTfTienCoc() {
		return tfTienCoc;
	}
	
	// Create Right panel 
	private JPanel createRightPanel() {
		JPanel right = new JPanel();
		right.setLayout(new FlowLayout(FlowLayout.CENTER));
		right.add(createTitlePanel());
		right.setPreferredSize(new Dimension(430, 520));
		right.setVisible(true);
		return right;
	}
	
	private JPanel createTitlePanel () {
		JPanel titlePanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		titlePanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 40;
		gbc.ipady = 20;
		titlePanel.add(createLPanelTitle("Mã sách", 15, 20), gbc);
		
		gbc.gridx = 1;
		gbc.ipadx = 200;
		titlePanel.add(createLPanelTitle("Tên sách", 140, 20), gbc);
		
		gbc.gridx = 2;
		gbc.ipadx = 25;
		titlePanel.add(createLPanelTitle("SL", 15, 20), gbc);

		return titlePanel;
	}
	
	private JPanel createLPanelTitle(String titleLabel, int width, int height) {
		JPanel panelTitle = new JPanel(new FlowLayout());
		panelTitle.setPreferredSize(new Dimension(width, height));
		panelTitle.add(new Label(titleLabel), FlowLayout.LEFT);
		return panelTitle;
	}

	// Create Center Panel
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel (new BorderLayout(5, 5));
		panel.setBorder(new TitledBorder("Mượn trả"));
		panel.add(createLabelPanel(), BorderLayout.WEST);
		panel.add(createInputPanel(), BorderLayout.CENTER);
		panel.add(createSubPanel(), BorderLayout.EAST);
		return panel;
	}
	
	private JPanel createLabelPanel() {
		JPanel labelPanel = new JPanel(new GridLayout(6, 1, 5, 5));
		labelPanel.add(new JLabel("Mã mượn trả "));
		labelPanel.add(new JLabel("Mã Độc giả "));
		labelPanel.add(new JLabel("Mã nhân viên "));
		labelPanel.add(new JLabel("Mã sách "));
		labelPanel.add(new JLabel("Ngày mượn "));
		labelPanel.add(new JLabel("Ngày hẹn trả "));
		return labelPanel;
	}
	
	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel(new GridLayout(6, 1, 5, 5));
		inputPanel.add(tfMaMT = new JTextField(8));
		inputPanel.add(cbMaDG = new JComboBox<String>());
		inputPanel.add(cbMaNV = new JComboBox<String>());
		inputPanel.add(tfMaSach = new JTextField(8));
		inputPanel.add(tfNgayMuon = new JTextField(8));
		inputPanel.add(createPanelNgayHenTra());
		return inputPanel;
	}
	
	private JPanel createSubPanel() {
		JPanel subPanel = new JPanel(new GridLayout(6, 1, 5, 5));
		subPanel.add(new JLabel(""));
		subPanel.add(lbHoTenDG = new JLabel());
		subPanel.add(lbHoTenNV = new JLabel());
		subPanel.add(createSoLuongPanel());
		subPanel.add(new JLabel(""));
		subPanel.add(createTienCocPanel());
		return subPanel;
	}
	
	private JPanel createSoLuongPanel() {
		JPanel soLuongPanel = new JPanel (new BorderLayout(5, 5));
		soLuongPanel.add(new JLabel("Số lượng"), BorderLayout.WEST);
		soLuongPanel.add(tfSoLuongSach = new JTextField(5), BorderLayout.CENTER);
		soLuongPanel.add(btnAddThisBook, BorderLayout.EAST);
		return soLuongPanel;
	}
	
	private JPanel createPanelNgayHenTra() {
		JPanel ngayHenTraPanel = new JPanel();
		ngayHenTraPanel.add(cbNgayHenTra);
		ngayHenTraPanel.add(new JLabel("-"));
		ngayHenTraPanel.add(cbThangHenTra);
		ngayHenTraPanel.add(new JLabel("-"));
		ngayHenTraPanel.add(cbNamHenTra);
		return ngayHenTraPanel;
	}
	
	private JPanel createTienCocPanel() {
		JPanel tienCocPanel = new JPanel(new BorderLayout(5, 5));
		tienCocPanel.add(new JLabel("Tiền cọc"), BorderLayout.WEST);
		tienCocPanel.add(tfTienCoc = new JTextField(10));
		return tienCocPanel;
	}	
}
