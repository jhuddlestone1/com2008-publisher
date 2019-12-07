package team12;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;

public class EditorPanel extends AppPanel {

	JComboBox actions;
	TablePanel table = new TablePanel();
	JButton addButton = new JButton("Add editor");
	JButton removeButton = new JButton("Remove editor");
	JButton passRoleButton = new JButton("Pass role");
	JButton submitButton = new JButton("Submit");

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
		
	}
	*/
	public EditorPanel() {
		super("wrap", "grow");
	}
	
	public EditorPanel(Object... items) {
		this();
		int issn = Integer.parseInt(items[0].toString());
		String journal = items[1].toString();
		int userID = Integer.parseInt(items[2].toString());
		
		actions = new JComboBox(getActionsArray(journal));
		add(actions);
		add(table, "grow");

		addButton.addActionListener(e -> table.addRow());
		addButton.addActionListener(e -> table.removeRow());
		passRoleButton.addActionListener(e -> {
			Object[] data = table.extractRow();				
			String userTitle = data[0].toString();
			String firstNames = data[1].toString();
			String lastName = data[2].toString();
			String university = data[3].toString();
			String email = data[4].toString();
			String fullName = userTitle +' '+ firstNames +' '+ lastName;
			int dialogResult = JOptionPane.showConfirmDialog(this,
				"Do you want to make "+ fullName + " new chief editor?", "Retire", JOptionPane.YES_NO_OPTION
			);
			if (dialogResult == 0) {
				EditorController.transferChief(issn, email);
				JOptionPane.showMessageDialog(this,
					"You are no longer chief editor of this journal.", "", JOptionPane.INFORMATION_MESSAGE
				);
			}				
		});
		submitButton.addActionListener(e -> {
			Object[][] data = table.extractAll();
			String[] editorsEmails = new String[data.length];
			for (int i=0; i < data.length; i++) {
				String userTitle = data[i][0].toString();
				String firstNames = data[i][1].toString();
				String lastName = data[i][2].toString();
				String university = data[i][3].toString();
				String email = data[i][4].toString();
				String fullName = userTitle +' '+ firstNames +' '+ lastName;
				if (App.validate(userTitle, firstNames, lastName, university, email)) {	
					editorsEmails[i] = email;
					if (!UserController.validateEmail(email)) {
						String password = null;
						while (!App.validate(password)) {
							JOptionPane.showInputDialog("Enter password for "+ fullName +":");
						}
						UserController.addUser(email, password, userTitle, firstNames, lastName, university);
					}				
				}
				else {
					JOptionPane.showMessageDialog(this,
						"Editor's details missing.", "Add editors", JOptionPane.WARNING_MESSAGE
					);
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
						isEditor = true;
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
			
			switch (action) {
				case "Publish journal":
					//DO SOMETHING
					break;
				case "See articles":
					getArticleTable(journal);
					break;					
				case "Register other editor":
					getEditorTable(issn);
					add(addButton);
					add(submitButton);
					break;
				case "Pass role":
					getEditorTable(issn);
					add(passRoleButton);
					break; 
				case "See roles":
					getEditorTable(issn);
					break;
				case "Retire":
					int dialogResult = JOptionPane.showConfirmDialog (this,
						"Do you want to retire from the board for the "+ journal + "?", "Retire", JOptionPane.YES_NO_OPTION
					);
					if (dialogResult == 0) {
						if (App.userID == EditorController.getChiefEditorID(journal)){
							if (EditorController.deleteChiefEditor(issn)){
								JOptionPane.showMessageDialog(this,
									"You're no longer editor of this journal", "", JOptionPane.INFORMATION_MESSAGE
								);
								update();
							}
							else {
								JOptionPane.showMessageDialog(this,
									"You cannot retire, you're the only editor", "Message", JOptionPane.ERROR_MESSAGE
								);
							} 
						}
						else {
							EditorController.deleteEditor(App.userID, issn);
							JOptionPane.showMessageDialog(this,
								"You're no longer editor of this journal", "", JOptionPane.INFORMATION_MESSAGE
							);
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

	public String[] getActionsArray(String journal){
		if (App.userID == EditorController.getChiefEditorID(journal)){
			String [] actionsArray = {"See articles","Publish journal","Retire","Register other editor","Pass role","See roles"};
			return actionsArray;
		}
		else {
			String[] actionsArray = {"See articles","Retire"};
			return actionsArray;
		}			
	}

	void getEditorTable(int issn) {
		table.update(
			UserTable.filter(EditorController.getEditors(issn)),
			UserTable.columns
		);
	}

	void getArticleTable(String journal) {
		table.update(
			ArticleTable.filter(EditorController.getSubmissions(journal)),
			ArticleTable.columns
		);
	}

	void clean(){
		remove(addButton);
		remove(submitButton);
		remove(passRoleButton);
	} 
}
