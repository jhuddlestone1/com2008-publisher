package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeView extends AppView {
	
	LoginPanel loginPanel = new LoginPanel();
	JButton browseButton = new JButton("Browse articles");
	JButton articleButton = new JButton("Submit article");
	JButton journalButton = new JButton("Register journal");
	
	public WelcomeView(App app) {
		super("wrap", "grow, align center", "grow");
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Welcome")).setFont(App.headerFont);
		add(loginPanel);
		add(browseButton, "split 3");
		add(articleButton);
		add(journalButton);
		
		loginPanel.loginButton.addActionListener(
			new ActionHandlers.logInListener(loginPanel.usernameField, loginPanel.passwordField)
		);
		browseButton.addActionListener(e -> app.switchView("reader"));
		articleButton.addActionListener(e -> app.switchView("submit"));
		journalButton.addActionListener(e -> app.switchView("register"));
	}
	
}
