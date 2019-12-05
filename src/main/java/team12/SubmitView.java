package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class SubmitView extends AppView {
	
	TablePanel authorTable = new TablePanel();
	JButton uploadButton = new JButton("Upload article (PDF)");
	JButton backButton = new JButton("Back to list");
	JButton submitButton = new JButton("Submit article");
	JFileChooser fileChooser = new JFileChooser();
	File file;
	
	void initialise() {
		// TODO: populate the first line of this table with the details of the current user
		authorTable.update(
			new Object[1][5],
			new String[] {"Title", "First name(s)", "Last name", "University", "Email address", "Password"}
		);
		file = null;
	}
	
	public SubmitView(App app) {
		super("wrap, align center", "align center", "grow");
		initialise();
		fileChooser.setFileFilter(new FileNameExtensionFilter("PDF file", "pdf"));
		authorTable.setBorder(App.titledBorder("Authors"));
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Add article details")).setFont(App.headerFont);
		add(uploadButton);
		add(authorTable);
		add(backButton, "split 2");
		//add(new JLabel("Note: a default password will be sent seperately to new authors."));
		add(submitButton);
		
		uploadButton.addActionListener(e -> {
			if (fileChooser.showOpenDialog(app) == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
			}
		});
		backButton.addActionListener(e -> app.switchView("author"));
		// TODO: add article create/update code
		submitButton.addActionListener(e -> {
			if (file != null) {
				// TODO: send PDF file to database
				Object[][] data = authorTable.extract();
				for (Object[] row : data) {
					String title = row[0].toString();
					String firstNames = row[1].toString();
					String lastName = row[2].toString();
					String university = row[3].toString();
					String email = row[4].toString();
					String password = row[5].toString();
					UserController.addUser(email, password, title, firstNames, lastName, university);
					// TODO: add to authors
				}
				initialise();
				app.switchView("author");
			} else JOptionPane.showMessageDialog(app, "No file uploaded.", "Submit article", JOptionPane.WARNING_MESSAGE);
		});
	}
	
}
