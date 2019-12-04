package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainView extends AppView {
	
	LoginPanel loginPanel = new LoginPanel();
	JButton authorButton = new JButton("Submit article");
	JButton reviewerButton = new JButton("Review article");
	JButton editorButton = new JButton("Edit/publish journal");
	JButton registerButton = new JButton("Register new journal");
	
	public MainView(App app) {
		super("wrap", "grow, align center", "grow");
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Main menu")).setFont(App.headerFont);
		add(authorButton, "grow");
		add(reviewerButton, "grow");
		add(editorButton, "grow");
		add(registerButton, "grow");
		
		authorButton.addActionListener(e -> app.switchView("author"));
		reviewerButton.addActionListener(e -> app.switchView("reviewer"));
		editorButton.addActionListener(e -> app.switchView("editor"));
		registerButton.addActionListener(e -> app.switchView("register"));
	}
	
}
