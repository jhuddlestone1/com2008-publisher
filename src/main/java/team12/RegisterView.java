package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegisterView extends AppView {
	
	JButton registerButton = new JButton("Register journal");
	
	public RegisterView(App app) {
		super("wrap", "grow, align center", "grow");
		add(new JLabel("Register journal")).setFont(App.headerFont);
		add(registerButton);
		
		registerButton.addActionListener(e -> app.switchView("main"));
	}
	
}
