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
		
		loginPanel.loginButton.addActionListener(e -> {
			String email = loginPanel.email.getText();
			String password = loginPanel.password.getText();
			if (UserController.validateEmail(email) && UserController.validateUser(email, password)) {
				app.login(UserController.getUserID(email));
			} else {
				JOptionPane.showMessageDialog(null, "Login unsuccessful.", "Log in", JOptionPane.ERROR_MESSAGE);
			}
		});
		loginPanel.signupButton.addActionListener(e -> app.switchView("user"));
		readerButton.addActionListener(e -> app.browse());
	}
	
}
