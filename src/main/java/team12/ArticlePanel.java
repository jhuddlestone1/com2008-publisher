package team12;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class ArticlePanel extends AppPanel {
	
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
	
	public ArticlePanel() {
		super("wrap");
	}
	
	public ArticlePanel(Object... items) {
		this();
		update(items);
	}
	
	public ArticlePanel(ArrayList items) {
		this();
		update(items);
	}
	
}
