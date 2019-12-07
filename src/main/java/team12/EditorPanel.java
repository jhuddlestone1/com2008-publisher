package team12;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;

public class EditorPanel extends AppPanel {

	TablePanel articleTable = new TablePanel();
	JButton addButton = new JButton("Add editor");
	JButton submitButton = new JButton("Submit");
	JButton passRoleButton = new JButton("Pass role");

	public void empty() {
		removeAll();
		refresh();
	}
	
	public void update(Object... items) {
		removeAll();
		for (Object item : items) {
			add(new JLabel(item.toString()));
		}
		refresh();
	}
	
	public void update(ArrayList items) {
		removeAll();
		for (Object item : items) {
			add(new JLabel(item.toString()));
		}
		refresh();
	}
	/*
	void initialise(int userID) {
		authorTable.update(
			new Object[][] {UserController.getUserDetails(userID)},
			new String[] {"Title", "First name(s)", "Last name", "University", "Email address"}
		);
		file = null;
	}
	*/
	public EditorPanel() {
		super("wrap", "grow");
	}
	
	public EditorPanel(Object... items) {
		this();
		int issn = Integer.parseInt(items[0].toString());
		String title = items[1].toString();
		int userID = Integer.parseInt(items[2].toString());
		JComboBox actions = new JComboBox(getActionsArray (title));
		add(actions);
		add(articleTable, "grow");

		addButton.addActionListener(e -> articleTable.addRow());

		passRoleButton.addActionListener(e -> {
			Object[] data = articleTable.extractRow();				
			String userTitle = data[0].toString();
			String firstNames = data[1].toString();
			String lastName = data[2].toString();
			String university = data[3].toString();
			String email = data[4].toString();
			String fullName = userTitle +' '+ firstNames +' '+ lastName;
			int dialogResult = JOptionPane.showConfirmDialog (this,
				"Do you want to make "+ fullName + " new chief editor?",
												 "Retire", JOptionPane.YES_NO_OPTION);
			if (dialogResult == 0) {
				EditorController.transferChief(issn, email);
				JOptionPane.showMessageDialog(this, "You're no longer chief editor of this journal",
															   "", JOptionPane.INFORMATION_MESSAGE);
			}				
		});

		submitButton.addActionListener(e -> {
			Object[][] data = articleTable.extractAll();
			String[] editorsEmails = new String[data.length];
			String userTitle = "";
			String firstNames = "";
			String lastName ="";
			String university = "";
			String email = "";
			String fullName = "";				
			for (int i=0; i < data.length; i++) {
				if (App.validate(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4])) {	
					userTitle = data[i][0].toString();
					firstNames = data[i][1].toString();
					lastName = data[i][2].toString();
					university = data[i][3].toString();
					email = data[i][4].toString();
					editorsEmails[i] = email;
					fullName = userTitle +' '+ firstNames +' '+ lastName;
					if (App.validate(userTitle, firstNames, lastName, university, email)) {
						if (!UserController.validateEmail(email)) {
							String password = null;
							while (!App.validate(password)) {
								JOptionPane.showInputDialog("Enter password for "+ fullName +":");
							}
							UserController.addUser(email, password, userTitle, firstNames, lastName, university);
						}
					}
					else {
						JOptionPane.showMessageDialog(this, "Editor's details missing.", "Add editors", JOptionPane.WARNING_MESSAGE);
						return;
					}					
				}
				else {
					JOptionPane.showMessageDialog(this, "Editor's details missing.", "Add editors", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			Object [][] results = EditorController.getEditors(issn);
			String [] currentEditors = new String [results.length]; 
			for (int i=0; i < results.length; i++){
				currentEditors[i] = (String) results[i][5];
			}
			boolean isEditor = false;
			for (int o=0; o < editorsEmails.length; o++){
				for (int i=0; i < currentEditors.length; i++){
					if (editorsEmails[o].equals(currentEditors[i])){
						isEditor=true;
					}
				}
				if (!isEditor){
					EditorController.addEditor(issn, editorsEmails[o]);
				}
				isEditor=false;
			}								
			JOptionPane.showMessageDialog(this, "Editors changed.", "Add editors", JOptionPane.INFORMATION_MESSAGE);				
		});		

		actions.addActionListener(e -> {
			clean();
			String action = (String) actions.getSelectedItem();
			
			switch (action){
				case "Publish journal":
					//DO SOMETHING
					break;
				case "See articles":
					getArticles(title);
					break;					
				case "Register other editor":
					getEditors(issn);
					add(addButton);
					add(submitButton);
					break;
				case "Pass role":
					getEditors(issn);
					add(passRoleButton);
					break; 
				case "See roles":
					getEditors(issn);
					break;
				case "Retire":
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (this,
						"Do you want to retire from the board for the "+ title + "?",
															"Retire",dialogButton);
				if (dialogResult == 0){
					if (App.userID == EditorController.getChiefEditorID(title)){
						if (EditorController.deleteChiefEditor(issn)){
							JOptionPane.showMessageDialog(this, "You're no longer editor of this journal",
																	 "", JOptionPane.INFORMATION_MESSAGE);
							update();
						}
						else {
							JOptionPane.showMessageDialog(this, 
							"You cannot retire, you're the only editor", "Message", JOptionPane.ERROR_MESSAGE);
						} 
					}
					else {
						EditorController.deleteEditor(App.userID, issn);
						JOptionPane.showMessageDialog(this, "You're no longer editor of this journal",
																 "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				break;
			}
			refresh();
		});
	}
	
	public EditorPanel(ArrayList items) {
		this();
		update(items);
	}

	public String [] getActionsArray (String title){
		if (App.userID == EditorController.getChiefEditorID(title)){
			String [] actionsArray = {"See articles","Publish journal","Retire","Register other editor","Pass role","See roles"};
			return actionsArray;
		}
		else {
			String [] actionsArray = {"See articles","Retire"};
			return actionsArray;
		}			
	}

	void getEditors(int issn) {
		articleTable.update(
			UserTable.filter(EditorController.getEditors(issn)),
			UserTable.columns
		);
	}

	void getArticles(String title) {
		articleTable.update(
			ArticleTable.filter(EditorController.getSubmissions(title)),
			ArticleTable.columns
		);
	}

	void clean(){
		remove(addButton);
		remove(submitButton);
		remove(passRoleButton);
	} 
}
