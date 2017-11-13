package tranquangkhai20152005.library.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TKPersonInformation extends JPanel{
	private JLabel lbCurrentDate;
	private JLabel lbTitle;
	private TableTKPersonView tableTKPersonView;
	
	public JLabel getLbCurrentDate() {
		return lbCurrentDate;
	}
	public JLabel getLbTitle() {
		return lbTitle;
	}
	public TableTKPersonView getTableTKPersonView() {
		return tableTKPersonView;
	}
	
	public TKPersonInformation() {
		setLayout(new BorderLayout());
		add(createDatePanel (), BorderLayout.PAGE_START);
		add(createTablePanel(), BorderLayout.CENTER);	
	}
	
	private JPanel createDatePanel() {
		JPanel datePanel = new JPanel(new GridLayout(1, 2, 10, 10));
		datePanel.add(new JLabel(""));
		lbCurrentDate = new JLabel();
		datePanel.add(lbCurrentDate);
		return datePanel;
	}
	
	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		lbTitle = new JLabel ("THỐNG KÊ");
		tableTKPersonView = new TableTKPersonView();
		panel.add(lbTitle, BorderLayout.PAGE_START);
		panel.add(tableTKPersonView, BorderLayout.CENTER);
		return panel;
	}
}
