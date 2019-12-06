package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class SubmitView extends AppView {
	
	TablePanel authorTable = new TablePanel();
	JTextField articleTitle = new JTextField(32);
	JButton uploadButton = new JButton("Upload article (PDF)");
	TextPanel abstractPanel = new TextPanel();
	JButton addButton = new JButton("Add author");
	JButton removeButton = new JButton("Remove author");
	JButton backButton = new JButton("Back to list");
	JButton submitButton = new JButton("Submit article");
	JFileChooser fileChooser = new JFileChooser();
	File file;
	
	void initialise(int userID) {
		authorTable.update(
			new Object[][] {UserController.getUserDetails(userID)},
			new String[] {"Title", "First name(s)", "Last name", "University", "Email address"}
		);
		file = null;
	}
	
	public SubmitView(App app) {
		super("wrap", "align center, grow", "[][grow][][grow][][]");
		initialise(app.userID);
		fileChooser.setFileFilter(new FileNameExtensionFilter("PDF file", "pdf"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		authorTable.setBorder(App.titledBorder("Authors"));
		
		add(new JLabel("Title:"), "split 3");
		add(articleTitle, "growx");
		add(uploadButton);
		add(abstractPanel, "grow");
		add(addButton, "split 2");
		add(removeButton);
		add(authorTable, "grow");
		add(new JLabel("Note: you will be prompted to set a default account password for each new author."));
		add(backButton, "split 2");
		add(submitButton);
		
		uploadButton.addActionListener(e -> {
			if (fileChooser.showOpenDialog(app) == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			}
		});
		addButton.addActionListener(e -> authorTable.addRow());
		removeButton.addActionListener(e -> authorTable.removeRow());
		backButton.addActionListener(e -> app.switchView("author"));
		submitButton.addActionListener(e -> {
			if (file != null && App.validate(articleTitle.getText(), abstractPanel.getText())) {
				Object[][] data = authorTable.extract();
				String[] authorEmails = new String[data.length];
				for (int i=0; i < data.length; i++) {
					String title = data[i][0].toString();
					String firstNames = data[i][1].toString();
					String lastName = data[i][2].toString();
					String university = data[i][3].toString();
					String email = data[i][4].toString();
					String fullName = title +' '+ firstNames +' '+ lastName;
					String password = JOptionPane.showInputDialog("Enter password for "+ fullName +":");
					if (App.validate(email, password, title, firstNames, lastName, university)) {
						if (!UserController.validateEmail(email)) {
							UserController.addUser(email, password, title, firstNames, lastName, university);
						}
						authorEmails[i] = email;
					}
					else {
						JOptionPane.showMessageDialog(app, "Author details missing.", "Submit article", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				// TODO: add authors to submission and send PDF file to database
				//AuthorController.addSubmission(articleTitle.getText(), abstractPanel.getText(), file, app.userID);
				//AuthorController.addAuthors(submissionID, authorEmails);
				JOptionPane.showMessageDialog(app, "Article submitted.", "Submit article", JOptionPane.INFORMATION_MESSAGE);
				initialise(app.userID);
				app.switchView("author");
			}
			else JOptionPane.showMessageDialog(app, "Article details / PDF file missing.", "Submit article", JOptionPane.WARNING_MESSAGE);
		});
	}
	
}
