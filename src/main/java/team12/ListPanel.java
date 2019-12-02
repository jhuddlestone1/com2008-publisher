package team12;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ListPanel extends AppPanel {
	
	void empty() {
		this.removeAll();
	}
	
	public ListPanel() {
		super("wrap");
		this.setBorder(App.defaultBorder);
	}
	
	public ListPanel(String title) {
		super("wrap");
		this.setBorder(App.titledBorder(title));
	}
	
	public ListPanel(String[] items) {
		this();
		for (String item : items) {
			this.add(new JLabel(item));
		}
	}
	
	public ListPanel(String title, String[] items) {
		this(title);
		for (String item : items) {
			this.add(new JLabel(item));
		}
	}
	
	public ListPanel(ArrayList items) {
		this();
		for (Object item : items) {
			this.add(new JLabel(item.toString()));
		}
	}
	
	public ListPanel(String title, ArrayList items) {
		this(title);
		for (Object item : items) {
			this.add(new JLabel(item.toString()));
		}
	}
	
}