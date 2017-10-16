package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SearchBookView extends JPanel{
	private JTextField tfSearch = new JTextField(20);
	private String[] listSearch = {"Tên sách", "Mã sách", "Tác giả", "Nhà xuất bản", "Thể loại", "Năm xuất bản"};
	private JComboBox<String> cbSearch = new JComboBox<String>(listSearch);
	private JButton btnPrint = new JButton("IN");
	
	// Setter - Getter
	public JTextField getTfSearch() {
		return tfSearch;
	}
	public void setTfSearch(JTextField tfSearch) {
		this.tfSearch = tfSearch;
	}
	public JComboBox<String> getCbSearch() {
		return cbSearch;
	}
	public void setCbSearch(JComboBox<String> cbSearch) {
		this.cbSearch = cbSearch;
	}
	public JButton getBtnPrint() {
		return btnPrint;
	}
	public void setBtnPrint(JButton btnPrint) {
		this.btnPrint = btnPrint;
	}
	
	// Constructor
	public SearchBookView() {
		setSize(450, 30);
		setLayout(new BorderLayout(5, 5));
		setBorder(new EmptyBorder(10, 10, 30, 10));
		add(new JLabel("Tìm kiếm"), BorderLayout.WEST);
		add(tfSearch, BorderLayout.CENTER);
		add(createActionsPanel(), BorderLayout.EAST);
	}
	
	private JPanel createActionsPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 2, 5, 5));
		panel.add(cbSearch);
		panel.add(btnPrint);
		return panel;
	}
}
