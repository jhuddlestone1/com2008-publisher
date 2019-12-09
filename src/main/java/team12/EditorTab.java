package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;

public class EditorTab extends AppPanel {

	UserTable editorTable = new UserTable();
	ArticleTable articleTable = new ArticleTable();
	ListPanel verdictPanel = new ListPanel(" ");
	JButton passButton = new JButton("Pass chief editor role");
	JButton retireButton = new JButton("Retire from journal");
	JButton saveButton = new JButton("Save changes");
	JButton addButton = new JButton("Add row");
	JButton removeButton = new JButton("Remove row");
	JButton acceptButton = new JButton("Accept article");
	JButton rejectButton = new JButton("Reject article");
	JButton publishButton = new JButton("Publish edition");

	public EditorTab(App app) {
		super("wrap", "align center, grow", "[][33%][grow][grow][]");
	}
	
	public EditorTab(App app, Object[] journalData) {
		this(app);
		
		int issn = (int) journalData[0];
		String journal = (String) journalData[1];
		int userID = (int) journalData[2];
		
		Object[][] submissionData = EditorController.getSubmissions(journal);
		editorTable.update(EditorController.getEditors(issn));
		articleTable.update(submissionData);
		
		editorTable.setBorder(App.titledBorder("Editors"));
		articleTable.setBorder(App.titledBorder("Submissions"));
		
		if (app.userID == EditorController.getChiefEditorID(journal)) {
			add(retireButton, "align left, split 5");
			add(passButton);
			add(saveButton);
			add(addButton);
			add(removeButton);
		}
		else add(retireButton, "align left");
		
		add(editorTable, "grow");
		add(articleTable, "grow");
		add(verdictPanel, "grow");
		verdictPanel.setFont(App.headerFont);
		add(acceptButton, "align left, split 3");
		add(rejectButton);
		//add(publishButton);		
		
		passButton.addActionListener(e -> {
			Object[] row = editorTable.extractRow();
			if (App.validate(row)) {
				String email = (String) row[4];
				int confirm = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to pass the chief editor role?", "Pass chief editor role", JOptionPane.YES_NO_OPTION
				);
				if (confirm == 0) {
					EditorController.transferChief(issn, email);
					passButton.setEnabled(false);
					addButton.setEnabled(false);
					removeButton.setEnabled(false);
				}
			}
			else {
				JOptionPane.showMessageDialog(null,
					"No editor selected.", "Pass chief editor role", JOptionPane.WARNING_MESSAGE
				);
			}
		});
		
		retireButton.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to retire from the editors' board?", "Retire from journal", JOptionPane.YES_NO_OPTION
			);
			if (confirm == 0) {
				if (app.userID == EditorController.getChiefEditorID(journal)) {
					if (!EditorController.deleteChiefEditor(issn)) {
						JOptionPane.showMessageDialog(null,
							"You cannot retire until you appoint a new editor", "Retire from journal", JOptionPane.WARNING_MESSAGE
						);
						this.getParent().remove(this);
					}
				}
				else {
					EditorController.deleteEditor(app.userID, issn);
					this.getParent().remove(this);
				}
			}
		});
		
		addButton.addActionListener(e -> editorTable.addRow());
		removeButton.addActionListener(e -> editorTable.removeRow());
		
		saveButton.addActionListener(e -> {
				Object[][] data = editorTable.extractAll();
				String[] editorEmails = new String[data.length];
				String userEmail = UserController.getEmail(app.userID);
				for (int i=0; i < data.length; i++) {
					if (App.validate(data[i])) {
						String title = (String) data[i][0];
						String firstNames = (String) data[i][1];
						String lastName = (String) data[i][2];
						String university = (String) data[i][3];
						String email = (String) data[i][4];
						if (email == userEmail) continue;
						String fullName = title +' '+ firstNames +' '+ lastName;
						if (!UserController.validateEmail(email)) {
							String password = null;
							while (!App.validate(password)) {
								password = JOptionPane.showInputDialog("Enter password for "+ fullName +":");
							}
							UserController.addUser(email, password, title, firstNames, lastName, university);
						}
						editorEmails[i] = email;
					}
					else {
						JOptionPane.showMessageDialog(null, "Editor details missing.", "Submit editor details", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				EditorController.deleteAllExceptChiefEditor(app.userID, issn);
				EditorController.addEditors(issn, editorEmails);
				JOptionPane.showMessageDialog(null, "Editor details updated.", "Submit editor details", JOptionPane.INFORMATION_MESSAGE);
				//update(app.userID);
		});
		
		articleTable.selector.addListSelectionListener(e -> {
			int submissionID = (int) submissionData[articleTable.getRow()][0];
			String verdictString = String.join(", ", EditorController.getFinalVerdict(submissionID));
			verdictPanel.update(verdictString);
		});
		
		acceptButton.addActionListener(e -> {
			int submissionID = (int) submissionData[articleTable.getRow()][0];
			JTextField editionField = new JTextField();
			JTextField pageField = new JTextField();
			Object[] message = {
				"Edition:", editionField,
				"Starting page:", pageField
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Accept article", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				try {
					if (App.validate(editionField, pageField)) {
						int edition = Integer.parseInt(editionField.getText());
						int page = Integer.parseInt(pageField.getText());
						EditorController.addArticles(submissionID, page, edition);
						JOptionPane.showMessageDialog(null,
							"Article accepted.", "Accept article", JOptionPane.INFORMATION_MESSAGE
						);
					}
					else {
						JOptionPane.showMessageDialog(null,
							"Could not accept article.", "Accept article", JOptionPane.WARNING_MESSAGE
						);
					}
				}
				catch (NumberFormatException o) {
					JOptionPane.showMessageDialog(null,
						"Invalid number format.", "Accept article", JOptionPane.WARNING_MESSAGE
					);
				}
			}
		});
		rejectButton.addActionListener(e -> EditorController.rejectArticles((int) submissionData[articleTable.getRow()][0]));
		publishButton.addActionListener(e -> {});
		
	}
	
}
