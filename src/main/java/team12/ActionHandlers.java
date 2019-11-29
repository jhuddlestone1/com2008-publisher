package team12;
import java.awt.*;
import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.event.*;

public class ActionHandlers {

	public static Component src(ActionEvent event) {
		return (Component) event.getSource();
	}
	
	// public static class Hover implements MouseListener { 

	// 	public void mouseEntered(MouseEvent e) { 
	// 		Object[][] vars = UserController.getArticles(); 
		
	// 		for (String var: vars) { 
	// 			String summary = var[2];
	// 			System.out.println(summary);
	// 		}
	// 	}
	// }

/* ******************************* REPEATED ACTIONS *********************************************** */

public static class SubmitArticle implements ActionListener {
		
	public void actionPerformed(ActionEvent e) {
		//variable newUser = 0
		JOptionPane.showMessageDialog(src(e).getParent(), "Are you sure?", "Empty fields", JOptionPane.ERROR_MESSAGE);
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

/* *****************************************DEFAULT PAGE ******************************************* */
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
			if (Controller.validateEmail (usernameField.getText())){ //&& validatePassword(passwordField.getPassword()) ){
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


public static class Reader implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(src(e).getParent(), "Okay then...", "Empty fields", JOptionPane.ERROR_MESSAGE);
	}
}

public static class RegisterJournal implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		//variable newUser=1
		JOptionPane.showMessageDialog(src(e).getParent(), "Hope you have good idea!", "Empty fields", JOptionPane.ERROR_MESSAGE);
	}
}


/* ******************************************** READER INTERFACE ************************************/
public static class GoToLogIn implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(src(e).getParent(), "Let's go then!!", "Empty fields", JOptionPane.ERROR_MESSAGE);
	}
}	

public static class FindArticles implements ActionListener {

	JRadioButton isJournal; 
	JRadioButton isArticle;
	JTextField search; 
	JTextPane results;
	JTextPane abstracts;
	
	public FindArticles (JRadioButton j, JRadioButton a, JTextField s, JTextPane r, JTextPane ab) {
		this.isJournal = j; 
		this.isArticle = a;
		this.search = s;  
		this.results = r;
		this.abstracts = ab;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!isJournal.isSelected() && !isArticle.isSelected()) {
			JOptionPane.showMessageDialog(src(e).getParent(), "Please select option", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
		else if (search.getText().isEmpty()) {
			JOptionPane.showMessageDialog(src(e).getParent(), "You're not looking for anything...", "Empty fields", JOptionPane.ERROR_MESSAGE);			
		}
		else
		JOptionPane.showMessageDialog(src(e).getParent(), "You're not looking for anything constructive", "Empty fields", JOptionPane.ERROR_MESSAGE);
		src(e).getParent().revalidate();
	}
}


/* ********************************** LOG IN PAGE *********************************************** */	
public static class LogOut implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(src(e).getParent(), "You're logged out!!", "Empty fields", JOptionPane.ERROR_MESSAGE);
		// delete data from global variables: username, password
		// go back to DefaultPage
	}
}	


/* *************************************** CHANGE DETAILS ****************************************** */	
public static class SubmitUpdatedDetails implements ActionListener {
	
	JTextField forenameField;
	JTextField surnameField;
	JTextField usernameField;
	JTextField titleField;
	JTextField affiliationField;
	JPasswordField passwordField;
	
	public SubmitUpdatedDetails(JTextField f, JTextField s, JTextField u, JTextField t, JTextField a, JPasswordField p) {
		this.forenameField = f;
		this.surnameField = s;
		this.usernameField = u;
		this.titleField = t;
		this.affiliationField = a;
		this.passwordField = p;
	}
	
	public void actionPerformed(ActionEvent e) {
		String surname =  surnameField.getText();
		String username = usernameField.getText();
		String forename = forenameField.getText();
		 String title = titleField.getText();
		String affiliation = affiliationField.getText();
		char [] password = passwordField.getPassword();
		if (username.isEmpty() ||  forename.isEmpty() ||  title.isEmpty() ||  
			surname.isEmpty() || affiliation.isEmpty() ||  password.length==0) {
			JOptionPane.showMessageDialog(src(e).getParent(), "No data provided!", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
		else {
			if (false) //(Controller.validateEmail(username))
				JOptionPane.showMessageDialog(src(e).getParent(), "Choose different email", "Not unique email",JOptionPane.ERROR_MESSAGE);
			else {
				//based on global variable - newUser={0-author,1-editor} - can be declared here!!
				//Controller UpdateUser(surname,username,forename,title,affiliation,password)
				JOptionPane.showMessageDialog(src(e).getParent(), "/updated", "Success",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		src(e).getParent().revalidate();
	}
}	

/* ****************************************** REGISTER ************************************** */	
public static class SubmitNewDetails implements ActionListener {
	
	JTextField forenameField;
	JTextField surnameField;
	JTextField usernameField;
	JTextField titleField;
	JTextField affiliationField;
	JPasswordField passwordField;
	
	public SubmitNewDetails(JTextField f, JTextField s, JTextField u, JTextField t, JTextField a, JPasswordField p) {
		this.forenameField = f;
		this.surnameField = s;
		this.usernameField = u;
		this.titleField = t;
		this.affiliationField = a;
		this.passwordField = p;
	}
	
	public void actionPerformed(ActionEvent e) {
		String surname =  surnameField.getText();
		String username = usernameField.getText();
		String forename = forenameField.getText();
		 String title = titleField.getText();
		String affiliation = affiliationField.getText();
		char [] password = passwordField.getPassword();
		if (username.isEmpty() ||  forename.isEmpty() ||  title.isEmpty() ||  
			surname.isEmpty() || affiliation.isEmpty() ||  password.length==0) {
			JOptionPane.showMessageDialog(src(e).getParent(), "No data provided!", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
		else {
			if (false) //(Controller.validateEmail(username))
				JOptionPane.showMessageDialog(src(e).getParent(), "Choose different email", "Not unique email",JOptionPane.ERROR_MESSAGE);
			else {
				//based on global variable - newUser={0-author,1-editor}
				//Controller CreateUser(surname,username,forename,title,affiliation,password)
				JOptionPane.showMessageDialog(src(e).getParent(), "new account!", "Success",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		src(e).getParent().revalidate();
	}
}	

/* ******************************************** SUBMIT ARTICLE ***********************************/	

public static class AddCoauthor implements ActionListener {
	
	JTextField addField;
	JTextArea coauthorsField;
	
	public AddCoauthor(JTextField o, JTextArea c) {
		this.addField = o;
		this.coauthorsField = c;
	}
	
	public void actionPerformed(ActionEvent e) {
		String add = addField.getText();
		if (!add.isEmpty()) {
			coauthorsField.append(add + "\n");
			addField.setText("");
		}
		src(e).getParent().revalidate();
	}
}	
	
<<<<<<< HEAD
	public static class Hover implements MouseListener { 

		public void mouseEntered(MouseEvent e) { 
			Object[][] vars = UserController.getArticles(); 
		
			for (Object[] var: vars) { 
				String summary = (String) var[2];
				System.out.println(summary);
=======
public static class AddArticle implements ActionListener {
	
	JTextField abstractField;
	JTextField pdfField;
	JTextArea coauthorsField;
	JTextField titleField;
	JTextField addField;
	
	public AddArticle(JTextField t, JTextField a, JTextField p, JTextField o, JTextArea c) {
		this.titleField = t;
		this.abstractField = a;
		this.pdfField = p;
		this.addField = o;
		this.coauthorsField = c;
	}
	
	public void actionPerformed(ActionEvent e) {
		String abs = abstractField.getText();
		String pdf = pdfField.getText();
		String coauthors = coauthorsField.getText();
		String title = titleField.getText();
		String add = addField.getText();	
		if (abs.isEmpty() ||  pdf.isEmpty() ||  title.isEmpty()) {
			JOptionPane.showMessageDialog(src(e).getParent(), "Empty Fields!", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
		else if (add.isEmpty()){
			if (!coauthors.isEmpty()) {
				String[] authors = coauthors.split("\n");
			
				for (String coauthor: authors) {
					System.out.println(coauthor);
					//Controller.addArticle(title,abs,pdf,coauthor)
				}
>>>>>>> fa4a4ac974ba0f5617be8963b71dc34fe8f03d55
			}
			JOptionPane.showMessageDialog(src(e).getParent(), "Yay!", "Yay", JOptionPane.INFORMATION_MESSAGE);
			//Controller.addArticle(title,abs,pdf,mainauthorusrname)
		}
		else 
			JOptionPane.showMessageDialog(src(e).getParent(), "Please, click add before submit", "",JOptionPane.INFORMATION_MESSAGE);
		src(e).getParent().revalidate();
	}
}	
	
	
	
}