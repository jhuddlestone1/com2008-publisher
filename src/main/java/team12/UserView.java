package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserView extends AppView {
	
	DetailsPanel detailsPanel = new DetailsPanel();
	
	public UserView(App app) {
		super("wrap, align center", "align center", "grow");
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Add user details")).setFont(App.headerFont);
		add(detailsPanel);
		
		// TODO: add user create/update code
		detailsPanel.submitButton.addActionListener(e -> app.switchView("main"));
	}
	
}
