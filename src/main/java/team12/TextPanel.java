package team12;

import java.awt.*;
import javax.swing.*;

public class TextPanel extends AppPanel {
	
	public TextPanel() {
		super("", "grow", "grow");
		this.add(new JTextArea(), "grow");
		this.setBorder(App.defaultBorder);
	}
	
	public TextPanel(String title) {
		super("", "grow", "grow");
		this.add(new JTextArea(), "grow");
		this.setBorder(App.titledBorder(title));
	}
	
	public TextPanel(String title, String text) {
		super("", "grow", "grow");
		this.add(new JTextArea(text), "grow");
		this.setBorder(App.titledBorder(title));
	}
	
}