package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserView extends AppView {
	
	DetailsPanel detailsPanel = new DetailsPanel();
	
	void instantiate(int userID) {
		Object[] userData = UserController.getUserDetails(userID);
		detailsPanel.title.setSelectedItem((String) userData[1]);
		detailsPanel.firstNames.setText((String) userData[2]);
		detailsPanel.lastName.setText((String) userData[3]);
		detailsPanel.university.setText((String) userData[4]);
		detailsPanel.email.setText((String) userData[5]);
	}
	
	public UserView(App app) {
		super("wrap, align center", "align center", "grow");
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("User details")).setFont(App.headerFont);
		add(detailsPanel);
		add(new JLabel("Note: changing email will create a new account."));
		
		if (app.isLoggedIn()) {
			instantiate(app.userID);
		}
		
		detailsPanel.submitButton.addActionListener(e -> {
			String email = detailsPanel.email.getText();
			String password = detailsPanel.password.getText();
			String title = detailsPanel.title.getSelectedItem().toString();
			String firstNames = detailsPanel.firstNames.getText();
			String lastName = detailsPanel.lastName.getText();
			String university = detailsPanel.university.getText();
			boolean emailExists = UserController.validateEmail(email);
			if (App.validate(email, password, title, firstNames, lastName, university)) {
				if (app.isLoggedIn() && UserController.getUserID(email) == app.userID) {
					UserController.updateUser(email, password, title, firstNames, lastName, university);
					JOptionPane.showMessageDialog(null, "Account updated.", "Add user", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "Hope that's OK.", "Add user", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (!emailExists) {
					UserController.addUser(email, password, title, firstNames, lastName, university);
					if (!app.isLoggedIn()) {
						app.login(UserController.getUserID(email));
					}
					else JOptionPane.showMessageDialog(null, "Account created.", "Change details", JOptionPane.INFORMATION_MESSAGE);
				}
				else JOptionPane.showMessageDialog(null, "Account already exists.", "Add user", JOptionPane.WARNING_MESSAGE);
			}
			else JOptionPane.showMessageDialog(null, "All details must be provided.", "Add user", JOptionPane.WARNING_MESSAGE);
		});
	}
	
}
