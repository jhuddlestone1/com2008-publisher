package team12;

import java.awt.*;
import javax.swing.*;

public class TextPanel extends AppPanel {
	
	JTextArea textArea = new JTextArea();
	
	public void empty() {
		setText(null);
	}
	
	public void update(String text) {
		setText(text);
	}
		
	public TextPanel() {
		super("", "grow", "grow");
		setBorder(App.defaultBorder);
		add(textArea, "grow");
	}
	
	public TextPanel(String title) {
		super("", "grow", "grow");
		setBorder(App.titledBorder(title));
		add(textArea, "grow");
	}
	
	public TextPanel(String title, String text) {
		super("", "grow", "grow");
		setBorder(App.titledBorder(title));
		add(textArea, "grow");
		update(text);
	}
	
}
