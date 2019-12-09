package team12;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class TabPanel extends JTabbedPane {
	
	public void refresh() {
		validate();
		repaint();
	}
	
	public void remove(JPanel panel) {
		remove(panel);
		refresh();
	}
	
	public void empty() {
		removeAll();
		refresh();
	}
	
	public void update(JPanel... panels) {
		removeAll();
		for (JPanel panel : panels) {
			add(panel);
		}
		refresh();
	}
	
	public void update(ArrayList panels) {
		removeAll();
		for (Object panel : panels) {
			add((JPanel) panel);
		}
		refresh();
	}
	
	public void update(String[] names, JPanel... panels) {
		removeAll();
		for (int i = 0; i < panels.length; i++) {
			add(names[i], panels[i]);
		}
		refresh();
	}
	
	public void update(ArrayList names, ArrayList panels) {
		removeAll();
		for (int i = 0; i < panels.size(); i++) {
			add((String) names.get(i), (JPanel) panels.get(i));
		}
		refresh();
	}
	
	public TabPanel(JPanel... panels) {
		update(panels);
	}
	
	public TabPanel(ArrayList panels) {
		update(panels);
	}
	
	public TabPanel(String[] names, JPanel... panels) {
		update(names, panels);
	}
	
	public TabPanel(ArrayList names, ArrayList panels) {
		update(names, panels);
	}

}
