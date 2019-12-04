package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeView extends AppView {
	
	LoginPanel loginPanel = new LoginPanel();
	JButton readerButton = new JButton("Read articles");
	//JButton authorButton = new JButton("Submit article");
	//JButton registerButton = new JButton("Register journal");
	
	public WelcomeView(App app) {
		super("wrap", "grow, align center", "grow");
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Welcome")).setFont(App.headerFont);
		add(loginPanel);
		add(readerButton);
		//add(authorButton);
		//add(registerButton);
		
		//loginPanel.loginButton.addActionListener(
			//new ActionHandlers.logInListener(loginPanel.emailField, loginPanel.passwordField)
		//);
		loginPanel.loginButton.addActionListener(e -> app.login(1));
		readerButton.addActionListener(e -> app.switchView("reader"));
		//authorButton.addActionListener(e -> app.switchView("author"));
		//registerButton.addActionListener(e -> app.switchView("register"));
	}
	
}
