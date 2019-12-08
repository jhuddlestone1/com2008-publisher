package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class SubmitView extends AppView {
	
	UserTable authorTable = new UserTable();
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
		Object[][] data = {UserController.getUserDetails(userID)};
		authorTable.update(data);
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
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			}
		});
		
		addButton.addActionListener(e -> authorTable.addRow());
		removeButton.addActionListener(e -> authorTable.removeRow());
		backButton.addActionListener(e -> app.switchView("author"));
		
		submitButton.addActionListener(e -> {
			
			// TODO: fix this with something not totally arbitrary
			int issn = 12345678;
			
			
			if (file != null && App.validate(articleTitle.getText(), abstractPanel.getText())) {
				Object[][] data = authorTable.extractAll();
				String[] authorEmails = new String[data.length];
				for (int i=0; i < data.length; i++) {
					if (App.validate(data[i])) {
						String title = (String) data[i][0];
						String firstNames = (String) data[i][1];
						String lastName = (String) data[i][2];
						String university = (String) data[i][3];
						String email = (String) data[i][4];
						String fullName = title +' '+ firstNames +' '+ lastName;
						if (!UserController.validateEmail(email)) {
							String password = null;
							while (!App.validate(password)) {
								password = JOptionPane.showInputDialog("Enter password for "+ fullName +":");
							}
							UserController.addUser(email, password, title, firstNames, lastName, university);
						}
						authorEmails[i] = email;
					}
					else {
						JOptionPane.showMessageDialog(null, "Author details missing.", "Submit article", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				try {
					AuthorController.addSubmission(
						articleTitle.getText(), abstractPanel.getText(), App.fileToBlob(file), app.userID, issn, authorEmails
					);
				} catch (Exception error) { error.printStackTrace(); }
				JOptionPane.showMessageDialog(null, "Article submitted.", "Submit article", JOptionPane.INFORMATION_MESSAGE);
				initialise(app.userID);
				app.switchView("author");
			}
			else JOptionPane.showMessageDialog(null, "Article details / PDF file missing.", "Submit article", JOptionPane.WARNING_MESSAGE);
		});
	}
	
}
