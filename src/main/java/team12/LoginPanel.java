package team12;

import java.awt.*;
import javax.swing.*;

public class LoginPanel extends AppPanel {
	
	public LoginPanel() {
		super("wrap 2");
		this.add(new JLabel("Username: "));
		this.add(new JTextField(16));
		this.add(new JLabel("Password: "));
		this.add(new JPasswordField(16));
		this.add(new JButton("Log in"), "skip");
	}

}