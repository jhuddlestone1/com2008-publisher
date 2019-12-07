package team12;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class TablePanel extends JScrollPane {
	
	JTable table = new JTable();
	DefaultTableModel model;
	DefaultListSelectionModel selector;
	
	void initialise() {
		model = (DefaultTableModel) table.getModel();
    table.setModel(model);
		selector = (DefaultListSelectionModel) table.getSelectionModel();
		selector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setSelectionModel(selector);
		setViewportView(table);
	}
	
	int getRow() {
		return table.getSelectedRow();
	}
	
	void addRow() {
		model.addRow(new Object[table.getColumnCount()]);
	}
	
	void addRow(Object[] data) {
		model.addRow(data);
	}
	
	void removeRow() {
		if (!selector.isSelectionEmpty() && table.getRowCount() > 1) {
			model.removeRow(getRow());
		}
	}
	
	void empty() {
		model.setRowCount(0);
		addRow();
	}
	
	void update(Object[][] data, Object[] cols) {
		model.setDataVector(data, cols);
	}
	
	Object[] extractRow() {
		int row = getRow();
		int cols = table.getColumnCount();
		Object[] data = new String[cols];
		for (int i=0; i < cols; i++) {
			data[i] = table.getValueAt(row, i);
		}
		return data;
	}
	
	Object[][] extractAll() {
		int rows = table.getRowCount();
		int cols = table.getColumnCount();
		Object[][] data = new String[rows][cols];
		for (int i=0; i < rows; i++) {
			for (int j=0; j < cols; j++) {
				data[i][j] = table.getValueAt(i,j);
			}
		}
		return data;
	}

	public TablePanel() {
		initialise();
	}
	
	public TablePanel(Object[][] data, Object[] cols) {
		initialise();
		update(data, cols);
	}
	
}
