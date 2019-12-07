package team12;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;

public class ArticlePanel extends AppPanel {

	TablePanel articleTable = new TablePanel();
	JButton addButton;
	JButton submitButton;
	JButton passRoleButton;

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
	
	public ArticlePanel() {
		//
		super("wrap");
	}
	
	public ArticlePanel(Object... items) {
		this();
		removeAll();
		int ISNN = Integer.parseInt(items[0].toString());
		String title = items[1].toString();
		int userID = Integer.parseInt(items[2].toString());
		JComboBox actions = new JComboBox(getActionsArray (title));
		add(actions);
		add(articleTable);

		addButton = new JButton("Add editor");
		submitButton = new JButton("Submit");
		passRoleButton = new JButton("Pass role");
		addButton.addActionListener(e -> articleTable.addRow());

		passRoleButton.addActionListener(e -> {
			Object[] data = articleTable.getSelected();				
			String newtitle = data[0].toString();
			String firstNames = data[1].toString();
			String lastName = data[2].toString();
			String university = data[3].toString();
			String email = data[4].toString();
			String fullName = newtitle +' '+ firstNames +' '+ lastName;
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (this,
				"Do you want to make "+ fullName + " new chief editor?",
												 "Retire",dialogButton);
			if (dialogResult==0){
				EditorController.transferChief(ISNN, email);
				JOptionPane.showMessageDialog(this, "You're no longer chief editor of this journal",
															   "", JOptionPane.INFORMATION_MESSAGE);
			}				
		});

		submitButton.addActionListener(e -> {
			Object[][] data = articleTable.extract();
			String[] editorsEmails = new String[data.length];
			String newtitle = "";
			String firstNames = "";
			String lastName ="";
			String university = "";
			String email = "";
			String fullName = "";				
			for (int i=0; i < data.length; i++) {
				if (notNull(data[i][0],data[i][1],data[i][2],data[i][3],data[i][4])){	
					newtitle = data[i][0].toString();
					firstNames = data[i][1].toString();
					lastName = data[i][2].toString();
					university = data[i][3].toString();
					email = data[i][4].toString();
					editorsEmails[i] = email;
					fullName = newtitle +' '+ firstNames +' '+ lastName;
					if (App.validate(newtitle,firstNames,lastName,university,email)) {
						if (!UserController.validateEmail(email)) {
							String password = JOptionPane.showInputDialog("Enter password for "+ fullName +":");
							if (App.validate(password)){
								UserController.addUser(email, password, newtitle, firstNames, lastName, university);
							}
							else {
								JOptionPane.showMessageDialog(this, "Privide correct password.", "Add editors", JOptionPane.WARNING_MESSAGE);
								return;
							}
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
			Object [][] results = EditorController.getEditors(ISNN);
			String [] currentEditors = new String [results.length]; 
			for (int i=0; i < results.length; i++){
				currentEditors[i] = (String) results [i][5];
			}
			boolean isEditor = false;
			for (int o=0; o < editorsEmails.length; o++){
				for (int i=0; i < currentEditors.length; i++){
					if (editorsEmails[o].equals(currentEditors[i])){
						isEditor=true;
					}
				}
				if (!isEditor){
					EditorController.addEditor(ISNN, editorsEmails[o]);
				}
				isEditor=false;
			}								
			JOptionPane.showMessageDialog(this, "Editors changed.", "Add editors", JOptionPane.INFORMATION_MESSAGE);				
		});		

		actions.addActionListener(e -> {
			String action = (String) actions.getSelectedItem();
			
			switch (action){
				case "Publish journal":
					clean();
					//DO SOMETHING
					break;
				case "See articles":
					clean();
					getArticles(ISNN, title);
					break;					
				case "Register other editor":
					clean();
					getEditors(ISNN);
					add(addButton);
					add(submitButton);
					break;
				case "Pass role":
					clean();
					getEditors(ISNN);
					add(passRoleButton);
					break; 
				case "See roles":
					clean();
					getEditors(ISNN);
					break;
				case "Retire":
					clean();
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (this,
						"Do you want to retire from the board for the "+ title + "?",
															"Retire",dialogButton);
				if (dialogResult==0){
					if (App.userID==EditorController.getChiefEditorID(title)){
						if (EditorController.deleteChiefEditor(ISNN)){
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
						EditorController.deleteEditor(App.userID, ISNN);
						JOptionPane.showMessageDialog(this, "You're no longer editor of this journal",
																 "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				break;
			}
			refresh();
		});
	}
	
	public ArticlePanel(ArrayList items) {
		this();
		update(items);
	}

	public String [] getActionsArray (String title){
		if (App.userID==EditorController.getChiefEditorID(title)){
			String [] actionsArray = {"See articles","Publish journal","Retire","Register other editor","Pass role","See roles"};
			return actionsArray;
		}
		else {
			String [] actionsArray = {"See articles","Retire"};
			return actionsArray;
		}			
	}

	void getEditors(int ISNN) {
		Object [][] results = EditorController.getEditors(ISNN);
		Object [][] editors = new Object [results.length] [5]; 
		for (int i=0; i < results.length; i++){
			editors[i][0] = results [i][1];
			editors[i][1] = results [i][2];
			editors[i][2] = results [i][3];
			editors[i][3] = results [i][4];
			editors[i][4] = results [i][5];
		} 
		articleTable.update(
			editors,
			new String[] {"Title", "First name(s)", "Last name", "University", "Email address"}
		);
	}

	void getArticles(int ISNN, String title) {
		Object [][] data = EditorController.getSubmissions(title);
		String[] columns = new String[] {"Title", "Lead author", "Reviews"};
		Object[][] results = new Object[data.length][columns.length];
			for (int i=0; i < data.length; i++) {
				results[i][0] = data[i][1];
				results[i][1] = data[i][7];
				results[i][2] = data[i][5];
			}
			articleTable.update(results, columns);
	}

	boolean notNull(Object... items) {
		for (Object item : items) {
			if (item == null) return false;
		}
		return true;
	}

	void clean(){
		remove(addButton);
		remove(submitButton);
		remove(passRoleButton);
	} 
}
