package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JournalView extends AppView {
	
	JButton submitButton = new JButton("Submit details");
	
	public JournalView(App app) {
		super("wrap, align center", "align center", "grow");
		add(new JLabel("Add journal details")).setFont(App.headerFont);
		
		// TODO: add form fields here
		
		add(submitButton);
		
		submitButton.addActionListener(e -> app.switchView("editor")); // TODO: re-instantiate this
	}
	
}
