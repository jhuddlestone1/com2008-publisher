package team12;

import java.awt.*;
import javax.swing.*;

public class TextPanel extends AppPanel {
	
	JTextArea textArea = new JTextArea();
	
	void empty() {
		textArea.setText(null);
		refresh();
	}
	
	void setText(Object text) {
		textArea.setText(text.toString());
		refresh();
	}
	
	void update(Object text) {
		setText(text);
	}
	
	String getText() {
		return textArea.getText();
	}
	
	String extract() {
		return getText();
	}
		
	public TextPanel() {
		super("", "grow", "grow");
		add(textArea, "grow");
	}
	
	public TextPanel(Object text) {
		this();
		setText(text);
	}
	
}
