package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserView extends AppView {
	
	DetailsPanel detailsPanel = new DetailsPanel();
	
	public UserView(App app) {
		super("wrap, align center", "align center", "grow");
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Add user details")).setFont(App.headerFont);
		add(detailsPanel);
		
		detailsPanel.submitButton.addActionListener(e -> {
			String email = detailsPanel.email.getText();
			boolean emailExists = UserController.validateEmail(email);
			if (!emailExists) {
				UserController.addUser(
					email,
					detailsPanel.password.getText(),
					detailsPanel.title.getSelectedItem().toString(),
					detailsPanel.firstNames.getText(),
					detailsPanel.lastName.getText(),
					detailsPanel.university.getText()
				);
				JOptionPane.showMessageDialog(app, "Account created.", "Add user", JOptionPane.INFORMATION_MESSAGE);
				if (!app.isLoggedIn()) {
					app.login(UserController.getUserID(email));
				}
			} else {
				JOptionPane.showMessageDialog(app, "Account already exists.", "Add user", JOptionPane.WARNING_MESSAGE);
			}
		});
	}
	
}
