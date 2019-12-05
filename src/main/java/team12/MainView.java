package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainView extends AppView {
	
	JButton readerButton = new JButton("Read articles");
	JButton authorButton = new JButton("Submit article");
	JButton reviewerButton = new JButton("Review submissions");
	JButton editorButton = new JButton("Add/manage journals");
	//JButton journalButton = new JButton("Register new journal");
	
	public MainView(App app) {
		super("wrap", "grow, align center", "grow");
		
		Object[] userDetails = UserController.getUserDetails(app.userID);
		String userName = userDetails[1].toString() +' '+ userDetails[2].toString() +' '+ userDetails[3].toString();
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Welcome, "+ userName)).setFont(App.italicFont);
		add(new JLabel("Main menu")).setFont(App.headerFont);
		add(readerButton, "grow");
		add(authorButton, "grow");
		add(reviewerButton, "grow");
		add(editorButton, "grow");
		//add(journalButton, "grow");
		
		readerButton.addActionListener(e -> app.switchView("reader"));
		authorButton.addActionListener(e -> app.switchView("author"));
		reviewerButton.addActionListener(e -> app.switchView("reviewer"));
		editorButton.addActionListener(e -> app.switchView("editor"));
		//journalButton.addActionListener(e -> app.switchView("journal"));
	}
	
}
