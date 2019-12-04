package team12;

import java.awt.*;
import javax.swing.*;

public class LoginPanel extends AppPanel {
	
	JTextField email = new JTextField(16);
	JPasswordField password = new JPasswordField(16);
	JButton loginButton = new JButton("Log in");
	JButton signupButton = new JButton("Sign up");
	
	public LoginPanel() {
		super("wrap 2");
		add(new JLabel("Email address: "));
		add(email);
		add(new JLabel("Password: "));
		add(password);
		add(loginButton, "skip, split 2");
		add(signupButton);
	}

}
