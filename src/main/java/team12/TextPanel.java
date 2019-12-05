package team12;

import java.awt.*;
import javax.swing.*;

public class TextPanel extends AppPanel {
	
	JTextArea textArea = new JTextArea();
	
	public void empty() {
		textArea.setText(null);
		refresh();
	}
	
	public void update(Object text) {
		textArea.setText(text.toString());
		refresh();
	}
		
	public TextPanel() {
		super("", "grow", "grow");
		add(textArea, "grow");
	}
	
	public TextPanel(Object text) {
		this();
		update(text);
	}
	
}
