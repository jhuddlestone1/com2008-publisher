package team12;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ListPanel extends AppPanel {
	
	public void empty() {
		removeAll();
		refresh();
	}
	
	public void update(Object... items) {
		removeAll();
		for (Object item : items) {
			add(new JLabel(item.toString()));
		}
		refresh();
	}
	
	public void update(ArrayList items) {
		removeAll();
		for (Object item : items) {
			add(new JLabel(item.toString()));
		}
		refresh();
	}
	
	public ListPanel() {
		super("wrap");
	}
	
	public ListPanel(Object... items) {
		this();
		update(items);
	}
	
	public ListPanel(ArrayList items) {
		this();
		update(items);
	}
	
}
