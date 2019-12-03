package team12;

import java.awt.*;
import javax.swing.*;

public class LoginPanel extends AppPanel {
	
	JTextField usernameField = new JTextField(16);
	JPasswordField passwordField = new JPasswordField(16);
	JButton loginButton = new JButton("Log in");
	
	public LoginPanel() {
		super("wrap 2");
		add(new JLabel("Username: "));
		add(usernameField);
		add(new JLabel("Password: "));
		add(passwordField);
		add(loginButton, "skip");
	}

}
