package team12;
import java.awt.*;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.event.*;

public class ActionHandlers {

	public static Component src(ActionEvent event) {
		return (Component) event.getSource();
	}

	public static class logInListener implements ActionListener {
		
		JTextField usernameField;
		JPasswordField passwordField;
		
		public logInListener(JTextField u, JPasswordField p) {
			this.usernameField = u;
			this.passwordField = p;
		}
		
		public void actionPerformed(ActionEvent e) {
			boolean user = false;
			if (usernameField.getText().isEmpty() || passwordField.getPassword().length==0){
				JOptionPane.showMessageDialog(src(e).getParent(), "No data provided!", "Empty fields", JOptionPane.ERROR_MESSAGE);
			}
			else {
				System.out.println(usernameField.getText());
				System.out.println(passwordField.getPassword());
				// user = validateUser (usernameField.getText(), passwordField.getPassword());
				if (user){
					// UserPermissions (usernameField.getText(), passwordField.getPassword()); - who is he???
					// CHANGE INTERFACE
					//it should pass username to next interfaces! 
				}
				else {
					System.out.println("Invalid data!");
					JOptionPane.showMessageDialog(src(e).getParent(), "User doesn't exist", "No user found",JOptionPane.ERROR_MESSAGE);
				}
			}
			src(e).getParent().revalidate();
		}
	}	
	
	public static class SubmitArticle implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(src(e).getParent(), "Are you sure?", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static class Reader implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(src(e).getParent(), "Okay then...", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static class RegisterJournal implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(src(e).getParent(), "Hope you have good idea!", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static class ChangeDetails implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(src(e).getParent(), "Change details!", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static class ChangeRole implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(src(e).getParent(), "Change role or log out", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static class Hover implements MouseListener { 

		public void mouseEntered(MouseEvent e) { 
			Object[][] vars = UserController.getArticles(); 
		
			for (String var: vars) { 
				String summary = var[2];
				System.out.println(summary);
			}
		}
	}
	
	
}