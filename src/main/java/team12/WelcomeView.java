package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeView extends AppView {
	
	LoginPanel loginPanel = new LoginPanel();
	JButton readerButton = new JButton("Read articles as guest");
	
	public WelcomeView(App app) {
		super("wrap, align center", "align center", "grow");
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Welcome")).setFont(App.headerFont);
		add(loginPanel);
		add(readerButton);
		
		//loginPanel.loginButton.addActionListener(
			//new ActionHandlers.logInListener(loginPanel.email, loginPanel.password)
		//);
		loginPanel.loginButton.addActionListener(e -> app.login(1));
		loginPanel.signupButton.addActionListener(e -> app.switchView("user"));
		readerButton.addActionListener(e -> app.browse());
	}
	
}
