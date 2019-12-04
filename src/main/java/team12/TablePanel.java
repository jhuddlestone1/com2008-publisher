package team12;

import java.awt.*;
import javax.swing.*;

public class TablePanel extends JScrollPane {
	
	JTable table;
	
	public void refresh() {
		validate();
		repaint();
	}
	
	public void empty() {
		table = new JTable();
		setViewportView(table);
		refresh();
	}
	
	public void update(Object[][] data, Object[] cols) {
		table = new JTable(data, cols);
		setViewportView(table);
		refresh();
	}
	
	public TablePanel() {
		empty();
	}
	
	public TablePanel(Object[][] data, Object[] cols) {
		update(data, cols);
	}
	
}
