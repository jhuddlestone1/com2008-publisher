package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserView extends AppView {
	
	JLabel headerLabel = new JLabel("Add user details");
	DetailsPanel detailsPanel = new DetailsPanel();
	
	public UserView(App app) {
		super("wrap, align center", "align center", "grow");
		add(new JLabel("Team 12 Academic Publishing"));
		headerLabel.setFont(App.headerFont);
		add(headerLabel);
		add(detailsPanel);
		
		detailsPanel.submitButton.addActionListener(e -> app.switchView("main"));
	}
	
}
