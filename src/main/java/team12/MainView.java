package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainView extends AppView {
	
	JButton readerButton = new JButton("Read articles");
	JButton reviewerButton = new JButton("Review submissions");
	JButton authorButton = new JButton("Add/manage articles");
	JButton editorButton = new JButton("Add/manage journals");
	
	public MainView(App app) {
		super("wrap", "align center, grow", "grow");
		
		Object[] userDetails = UserController.getUserDetails(app.userID);
		String userName = userDetails[1].toString() +' '+ userDetails[2].toString() +' '+ userDetails[3].toString();
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Welcome, "+ userName)).setFont(App.subheaderFont);
		add(new JLabel("Main menu")).setFont(App.headerFont);
		add(readerButton, "grow");
		add(reviewerButton, "grow");
		add(authorButton, "grow");
		add(editorButton, "grow");
		
		readerButton.addActionListener(e -> app.switchView("reader"));
		reviewerButton.addActionListener(e -> app.switchView("reviewer"));
		authorButton.addActionListener(e -> app.switchView("author"));
		editorButton.addActionListener(e -> app.switchView("editor"));
	}
	
}
