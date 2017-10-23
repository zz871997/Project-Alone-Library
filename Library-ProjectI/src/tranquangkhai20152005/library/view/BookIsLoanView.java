package tranquangkhai20152005.library.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class BookIsLoanView extends JPanel {
	private JLabel lbMaSach;
	private JLabel lbTenSach;
	private JLabel lbSoluong;
	
	public JLabel getLbMaSach() {
		return lbMaSach;
	}
	public void setLbMaSach(JLabel lbMaSach) {
		this.lbMaSach = lbMaSach;
	}
	public JLabel getLbTenSach() {
		return lbTenSach;
	}
	public void setLbTenSach(JLabel lbTenSach) {
		this.lbTenSach = lbTenSach;
	}
	public JLabel getLbSoluong() {
		return lbSoluong;
	}
	public void setLbSoluong(JLabel lbSoluong) {
		this.lbSoluong = lbSoluong;
	}
	
	JButton btnMaSach;
	JButton btnTenSach;
	JButton btnSoluong;
	
	public BookIsLoanView(String maSach, String tenSach, String soLuong) {
//		setBounds(5, 5, 400, 20);
		setBorder(new TitledBorder(""));
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 40;
		gbc.ipady = 20;
		add(createMaSachPanel(maSach), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 200;
		add(createTenSachPanel(tenSach), gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.ipadx = 25;
		add(createSoLuongPanel(soLuong), gbc);
	}
	
	private JPanel createMaSachPanel(String maSach) {
		JPanel maSachPanel = new JPanel (new FlowLayout());
		maSachPanel.setPreferredSize(new Dimension(15, 20));
//		maSachPanel.setMinimumSize(new Dimension(40, 20));
//		maSachPanel.setMaximumSize(new Dimension(40, 20));
		//maSachPanel.setBorder(new TitledBorder(""));
		
		maSachPanel.add(lbMaSach = new JLabel(maSach), FlowLayout.LEFT);
//		maSachPanel.repaint();
//		maSachPanel.validate();
		return maSachPanel;
	}
	
	private JPanel createTenSachPanel(String tenSach) {
		JPanel tenSachPanel = new JPanel (new FlowLayout());
		
		tenSachPanel.setPreferredSize(new Dimension(140, 20));
		//tenSachPanel.setBorder(new TitledBorder(""));
		tenSachPanel.add(lbTenSach = new JLabel(tenSach), FlowLayout.LEFT);
		return tenSachPanel;
	}
	
	private JPanel createSoLuongPanel(String soLuong) {
		JPanel soLuongPanel = new JPanel (new FlowLayout());
		soLuongPanel.setPreferredSize(new Dimension(15, 20));
		//soLuongPanel.setBorder(new TitledBorder(""));
		soLuongPanel.add(lbSoluong = new JLabel(soLuong), FlowLayout.LEFT);
		return soLuongPanel;
	}
}
