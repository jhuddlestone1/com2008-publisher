package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class SubmitView extends AppView {
	
	TablePanel authorTable = new TablePanel();
	JButton uploadButton = new JButton("Upload article (PDF)");
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
		super("wrap", "align center, grow", "[][][][grow][]");
		initialise(app.userID);
		fileChooser.setFileFilter(new FileNameExtensionFilter("PDF file", "pdf"));
		authorTable.setBorder(App.titledBorder("Authors"));
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Add article details")).setFont(App.headerFont);
		add(uploadButton, "split 3");
		add(addButton);
		add(removeButton);
		add(authorTable, "grow");
		//add(new JLabel("Note: a default password will be sent seperately to new authors."));
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
			if (file != null) {
				Object[][] data = authorTable.extract();
				for (Object[] row : data) {
					String title = row[0].toString();
					String firstNames = row[1].toString();
					String lastName = row[2].toString();
					String university = row[3].toString();
					String email = row[4].toString();
					String password = row[5].toString();
					UserController.addUser(email, password, title, firstNames, lastName, university);
					// TODO: add to authors in daatabase
					// TODO: send PDF file to database
					JOptionPane.showMessageDialog(app, "Article submitted.", "Submit article", JOptionPane.INFORMATION_MESSAGE);
				}
				initialise(app.userID);
				app.switchView("author");
			} else JOptionPane.showMessageDialog(app, "No file uploaded.", "Submit article", JOptionPane.WARNING_MESSAGE);
		});
	}
	
}
