package team12;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;

public class EditorTab extends AppPanel {

	JComboBox actions;
	TablePanel table = new TablePanel();
	JButton addButton = new JButton("Add editor");
	JButton removeButton = new JButton("Remove editor");
	JButton passRoleButton = new JButton("Pass role");
	JButton submitButton = new JButton("Submit");
	JButton publishArticleButton = new JButton("Publish article");
	JButton rejectArticleButton = new JButton("Reject article");

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
	public EditorTab(App app) {
		super("wrap", "grow");
	}
	
	public EditorTab(App app, Object... items) {
		this();
		int issn = Integer.parseInt(items[0].toString());
		String journal = items[1].toString();
		int userID = Integer.parseInt(items[2].toString());
		
		actions = new JComboBox(getActionsArray(journal));
		add(actions);
		add(table, "grow");

		addButton.addActionListener(e -> table.addRow());
		removeButton.addActionListener(e -> {
			//String email = (String) table.extractRow()[4];
			table.removeRow();
			//EditorController.deleteEditor(App.userID, issn);
		});
		
		passRoleButton.addActionListener(e -> {
			Object[] data = table.extractRow();
			if (App.validate(data)) {	
				String userTitle = (String) data[0];
				String firstNames = (String) data[1];
				String lastName = (String) data[2];
				String university = (String) data[3];
				String email = (String) data[4];
				String fullName = userTitle +' '+ firstNames +' '+ lastName;
				int dialogResult = JOptionPane.showConfirmDialog(null,
					"Do you want to make "+ fullName + " new chief editor?", "Retire", JOptionPane.YES_NO_OPTION
				);
				if (dialogResult == 0) {
					EditorController.transferChief(issn, email);
					JOptionPane.showMessageDialog(null,
						"You are no longer chief editor of this journal.", "", JOptionPane.INFORMATION_MESSAGE
					);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Editor details missing.", "Add editors", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		submitButton.addActionListener(e -> {
			Object[][] data = table.extractAll();
			String[] editorsEmails = new String[data.length];
			for (int i=0; i < data.length; i++) {
				if (App.validate(data[i])) {
					String userTitle = (String) data[i][0];
					String firstNames = (String) data[i][1];
					String lastName = (String) data[i][2];
					String university = (String) data[i][3];
					String email = (String) data[i][4];
					String fullName = userTitle +' '+ firstNames +' '+ lastName;
					if (!UserController.validateEmail(email)) {
						String password = null;
						while (!App.validate(password)) {
							password = JOptionPane.showInputDialog("Enter password for "+ fullName +":");
						}
						UserController.addUser(email, password, userTitle, firstNames, lastName, university);
					}
					editorsEmails[i] = email;
				}
				else {
					JOptionPane.showMessageDialog(null, "Editor details missing.", "Add editors", JOptionPane.WARNING_MESSAGE);
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
				isEditor = false;
			}								
			JOptionPane.showMessageDialog(null, "Editors changed.", "Add editors", JOptionPane.INFORMATION_MESSAGE);				
		});		

		actions.addActionListener(e -> {
			clean();
			String action = (String) actions.getSelectedItem();
			
			switch (action) {
				case "Publish edition":
					//DO SOMETHING
					break;
				case "Publish articles":
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
					int dialogResult = JOptionPane.showConfirmDialog(null,
						"Do you want to retire from the board for the "+ journal + "?", "Retire", JOptionPane.YES_NO_OPTION
					);
					if (dialogResult == 0) {
						if (app.userID == EditorController.getChiefEditorID(journal)){
							if (EditorController.deleteChiefEditor(issn)){
								JOptionPane.showMessageDialog(null,
									"You're no longer editor of this journal", "", JOptionPane.INFORMATION_MESSAGE
								);
								update();
							}
							else {
								JOptionPane.showMessageDialog(null,
									"You cannot retire, you're the only editor", "Message", JOptionPane.ERROR_MESSAGE
								);
							} 
						}
						else {
							EditorController.deleteEditor(app.userID, issn);
							JOptionPane.showMessageDialog(null,
								"You're no longer editor of this journal", "", JOptionPane.INFORMATION_MESSAGE
							);
						}
					}
					break;
			}
			refresh();
		});
	}
	
	public EditorTab(App app, ArrayList items) {
		this();
		update(items);
	}

	public String[] getActionsArray(String journal){
		if (app.userID == EditorController.getChiefEditorID(journal)) {
			return new String[] {"Publish articles","Publish edition","Retire","Register other editor","Pass role","See roles"};
		}
		else {
			return new String[] {"Publish articles","Retire"};
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
