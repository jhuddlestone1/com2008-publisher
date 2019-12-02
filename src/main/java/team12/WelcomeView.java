package team12;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import net.miginfocom.layout.*;

public class WelcomeView extends AppView {
	
	LoginPanel loginPanel = new LoginPanel();
	JButton browseButton = new JButton("Browse articles");
	JButton articleButton = new JButton("Submit article");
	JButton journalButton = new JButton("Register journal");
	
	public WelcomeView(App app) {
		super("wrap", "grow, align center", "grow");
		
		loginPanel.loginButton.addActionListener(new ActionHandlers.logInListener(loginPanel.usernameField, loginPanel.passwordField));
		browseButton.addActionListener(e -> app.switchView("reader"));
		
		this.add(new JLabel("Team 12 Academic Publishing"));
		this.add(new JLabel("Welcome")).setFont(App.headerFont);
		this.add(loginPanel);
		this.add(browseButton, "split 3");
		this.add(articleButton);
		this.add(journalButton);
	}
	
}