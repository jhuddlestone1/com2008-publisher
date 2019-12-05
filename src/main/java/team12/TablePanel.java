package team12;

import java.awt.*;
import javax.swing.*;

public class TablePanel extends JScrollPane {
	
	JTable table = new JTable();
	ListSelectionModel model = table.getSelectionModel();
	
	void refresh() {
		validate();
		repaint();
	}
	
	void empty() {
		table = new JTable();
		model.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setSelectionModel(model);
		setViewportView(table);
		refresh();
	}
	
	void update(Object[][] data, Object[] cols) {
		table = new JTable(data, cols);
		model.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setSelectionModel(model);
		setViewportView(table);
		refresh();
	}
	
	Object[][] extract() {
		int rows = table.getRowCount();
		int cols = table.getColumnCount();
		Object[][] data = new String[rows][cols];
		for (int i=0; i < rows; i++) {
			for (int j=0; i < cols; j++) {
				data[i][j] = table.getValueAt(i,j);
			}
		}
		return data;
	}
	
	public TablePanel() {
		empty();
	}
	
	public TablePanel(Object[][] data, Object[] cols) {
		update(data, cols);
	}
	
}
