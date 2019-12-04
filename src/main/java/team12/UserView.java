package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserView extends AppView {
	
	JButton submitButton = new JButton("Submit details");
	
	public UserView(App app) {
		super("wrap", "grow, align center", "grow");
		add(new JLabel("Add user details")).setFont(App.headerFont);
		// TODO: form fields here
		add(submitButton);
		
		submitButton.addActionListener(e -> app.switchView("main"));
	}
	
}
