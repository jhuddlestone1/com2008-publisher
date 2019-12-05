package team12;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ActionHandlers {

	public static Component src(ActionEvent event) {
		return (Component) event.getSource();
	}

/* ******************************* REPEATED ACTIONS *********************************************** */

public static class SubmitArticle implements ActionListener {
		
	public void actionPerformed(ActionEvent e) {
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
			String password = new String(passwordField.getPassword());
			if (UserController.validateEmail(usernameField.getText()) && UserController.validateUser (usernameField.getText(), password)){
				// CHANGE INTERFACE
				//it should pass username to next interfaces! 
				JOptionPane.showMessageDialog(src(e).getParent(), "User DOES exist", "No user found",JOptionPane.ERROR_MESSAGE);
			}
			else {
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
	JTextArea results;
	JTextArea abstracts;
	
	public FindArticles (JRadioButton j, JRadioButton a, JTextField s, JTextArea r, JTextArea ab) {
		this.isJournal = j; 
		this.isArticle = a;
		this.search = s;  
		this.results = r;
		this.abstracts = ab;
	}
	
	public void actionPerformed(ActionEvent e) {
		results.setText("");
		abstracts.setText("");
		String text = search.getText();
		if (!isJournal.isSelected() && !isArticle.isSelected()) {
			JOptionPane.showMessageDialog(src(e).getParent(), "Please select option", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
		else if (text.isEmpty()) {
			JOptionPane.showMessageDialog(src(e).getParent(), "Please, write what you are looking for.", "Empty fields", JOptionPane.ERROR_MESSAGE);			
		}
		else {
			if (isJournal.isSelected()) {
				Object [][] journals = UserController.getJournals(text);
			}
			else {
				Object [][] articles = UserController.getArticles(text);				
			}
		}
		//JOptionPane.showMessageDialog(src(e).getParent(), "You're not looking for anything constructive", "Empty fields", JOptionPane.ERROR_MESSAGE);
		src(e).getParent().revalidate();
	}
}


/* ********************************** EDITOR INTERFACE ****************************************** */
public static class chooseEditorAction implements ActionListener {
	
	JComboBox actions;
	String journal;
	
	public chooseEditorAction(JComboBox c, String j) {
		this.actions = c;
		this.journal=j;
	} 
	
	public void actionPerformed(ActionEvent e) {
		switch ((String) actions.getSelectedItem()){
			case "See articles":
				Object[][] articles =EditorController.getSubmissions(journal);
				/*add articles to the textarea 
				//when editor clicks on the article, he can read them and 
				make a final decision about it
				*/
				break;

			case "Retire":
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (src(e).getParent(),
					"Do you want to retire from the board for the "+ journal + "?",
															"Retire",dialogButton);
				if (dialogResult==0){
					//EditorController.deleteEditor(username);
					JOptionPane.showMessageDialog(src(e).getParent(), "Yes", "Empty fields", JOptionPane.ERROR_MESSAGE);
					// close that tab panel and remove that journal from the list of
					//available journals 
				}
			break;
		}
		src(e).getParent().revalidate();
	}
}	

/* *************************** CHIEF EDITOR INTERFACE ******************************************* */
public static class chooseChiefEditorAction implements ActionListener {
	
	JComboBox actions;
	String journal;
	
	public chooseChiefEditorAction(JComboBox c, String j) {
		this.actions = c;
		this.journal = j;
	} 
	
	// returns list of editors with their {title, forename, surname, uniAffiliation} 
	public String[][] seeEditors(int ISNN) {
			Object [][] results = EditorController.getEditors(ISNN);
			int length = results.length;
			String [][] editors = new String [length] [4]; 
			for (int i=0; i<length; i++){
				editors[i][0] = (String) results[i][1];
				editors[i][1] = (String) results[i][2];
				editors[i][2] = (String) results[i][3];
				editors[i][3] = (String) results[i][4];
			}
			return editors;
	}

	public void actionPerformed(ActionEvent e) {
		String action = (String) actions.getSelectedItem();
		int ISNN = EditorController.getISSN(journal);
		switch (action){
			case "Publish":
				//EditorController.addEdition();
				break;
			case "Register other editor":
				// changes interface to register interface
				break;
			case "Pass role":
				String [][] editors = seeEditors(ISNN);
				// later:  transferChief(ISSN, usernameofnewchief)
				break; 
			case "See roles":
				String [][] editorss = seeEditors(ISNN);
				break;
			case "Retire":
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (src(e).getParent(),
					"Do you want to retire from the board for the "+ journal + "?",
														"Retire",dialogButton);
			if (dialogResult==0){
				//int ISNN = getISNN(String journal);
				if (false){ //EditorController.deleteChiefEditor(username);
					JOptionPane.showMessageDialog(src(e).getParent(), "Yes", "Empty fields", JOptionPane.ERROR_MESSAGE);
					// close that tab panel and remove that journal from the list of
					//available journals
				}
				else{
					JOptionPane.showMessageDialog(src(e).getParent(), 
					"You cannot retire, you're the only editor", "Message", JOptionPane.ERROR_MESSAGE);
				} 
			}
			break;
		}
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
		String password = new String (passwordField.getPassword());
		if (username.isEmpty() ||  forename.isEmpty() ||  title.isEmpty() ||  
			surname.isEmpty() || affiliation.isEmpty() ||  password.isEmpty()) {
			JOptionPane.showMessageDialog(src(e).getParent(), "No data provided!", "Empty fields", JOptionPane.ERROR_MESSAGE);
		}
		else {
			if (UserController.validateEmail(username))
				JOptionPane.showMessageDialog(src(e).getParent(), "Choose different email", "Not unique email",JOptionPane.ERROR_MESSAGE);
			else {
				UserController.addUser(username, password, title, forename, surname, affiliation);
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
				//int authorID = UserController.getUserID(username);
				//Controller.addSubmission(title,abs,pdf,authorID);
				//Controller.addAuthors(Controller.getSubmissionID(title,authorID), authors);
			}
			JOptionPane.showMessageDialog(src(e).getParent(), "Yay!", "Yay", JOptionPane.INFORMATION_MESSAGE);
			
		}
		else 
			JOptionPane.showMessageDialog(src(e).getParent(), "Please, click add before submit", "",JOptionPane.INFORMATION_MESSAGE);
		src(e).getParent().revalidate();
	}
}	
	
	
	
}