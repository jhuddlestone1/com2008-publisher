package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginPanel extends AppPanel {
	
	JTextField usernameField = new JTextField(16);
	JPasswordField passwordField = new JPasswordField(16);
	JButton loginButton = new JButton("Log in");
	
	public LoginPanel() {
		super("wrap 2");
		this.add(new JLabel("Username: "));
		this.add(usernameField);
		this.add(new JLabel("Password: "));
		this.add(passwordField);
		this.add(loginButton, "skip");
	}

}