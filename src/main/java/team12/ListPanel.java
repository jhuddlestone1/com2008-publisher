package team12;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ListPanel extends AppPanel {
	
	public void empty() {
		removeAll();
		refresh();
	}
	
	public void update(String... items) {
		removeAll();
		for (String item : items) {
			add(new JLabel(item));
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
	
	public ListPanel(String title) {
		super("wrap");
	}
	
	public ListPanel(String... items) {
		this();
		update(items);
	}
	
	public ListPanel(ArrayList items) {
		this();
		update(items);
	}
	
}
