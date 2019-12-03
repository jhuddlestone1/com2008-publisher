package team12;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ListPanel extends AppPanel {
	
	public void empty() {
		removeAll();
		refresh();
	}
	
	public void update(String[] items) {
		empty();
		for (String item : items) {
			add(new JLabel(item));
		}
		refresh();
	}
	
	public void update(ArrayList items) {
		empty();
		for (Object item : items) {
			add(new JLabel(item.toString()));
		}
		refresh();
	}
	
	public ListPanel() {
		super("wrap");
		setBorder(App.defaultBorder);
	}
	
	public ListPanel(String title) {
		super("wrap");
		setBorder(App.titledBorder(title));
	}
	
	public ListPanel(String[] items) {
		this();
		update(items);
	}
	
	public ListPanel(ArrayList items) {
		this();
		update(items);
	}
	
	public ListPanel(String title, String[] items) {
		this(title);
		update(items);
	}
	
	public ListPanel(String title, ArrayList items) {
		this(title);
		update(items);
	}
	
}
