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
			String password = detailsPanel.password.getText();
			String title = detailsPanel.title.getSelectedItem().toString();
			String firstNames = detailsPanel.firstNames.getText();
			String lastName = detailsPanel.lastName.getText();
			String university = detailsPanel.university.getText();
			boolean allValid = App.validate(email, password, title, firstNames, lastName, university);
			boolean emailExists = UserController.validateEmail(email);
			if (allValid) {
				if (!emailExists) {
					UserController.addUser(email, password, title, firstNames, lastName, university);
					JOptionPane.showMessageDialog(null, "Account created.", "Add user", JOptionPane.INFORMATION_MESSAGE);
					if (!app.isLoggedIn()) {
						app.login(UserController.getUserID(email));
					}
				} else JOptionPane.showMessageDialog(null, "Account already exists.", "Add user", JOptionPane.WARNING_MESSAGE);
			} else JOptionPane.showMessageDialog(null, "All details must be provided.", "Add user", JOptionPane.WARNING_MESSAGE);
		});
	}
	
}
