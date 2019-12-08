package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class ReviewerView extends AppView {
	
	JButton createButton = new JButton("Create new review");
	JButton updateButton = new JButton("Update list");
	ArticleTable initialPanel = new ArticleTable();
	ArticleTable finalPanel = new ArticleTable();
	TextPanel abstractPanel = new TextPanel();
	JButton downloadButton = new JButton("Download article (PDF)");
	Object[][] data;
	File file;
	
	void update(int userID) {
		data = ReviewerController.getSubmissions(userID);
		//Object[][] initialResults = new Object[data.length][initialPanel.columns.length];
		//Object[][] finalResults = new Object[data.length][finalPanel.columns.length];
		initialPanel.update(data);
		//finalPanel.update(finalResults);
		file = null;
	}
		
	public ReviewerView(App app) {
		super("wrap", "align center, grow", "[][][][grow][grow][]");
		initialPanel.setBorder(App.titledBorder("Awaiting initial review"));
		finalPanel.setBorder(App.titledBorder("Awaiting final review"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Review submissions")).setFont(App.headerFont);
		add(createButton, "split 2");
		add(updateButton);
		add(initialPanel, "split 2, grow");
		add(finalPanel, "grow");
		add(abstractPanel, "grow");
		add(downloadButton);
		
		update(app.userID);
		
		createButton.addActionListener(e -> {
			int row = initialPanel.getRow();
			if (row >= 0) {
			int submissionID = (int) data[row][0];
				SubmissionView submissionView = new SubmissionView(app, submissionID);
				app.content.add(submissionView, "submission");
				app.switchView("submission");
			}
			else JOptionPane.showMessageDialog(null, "No row selected.", "Create review", JOptionPane.WARNING_MESSAGE);
		});
		updateButton.addActionListener(e -> update(app.userID));
		downloadButton.addActionListener(e -> file = null); // TODO: get PDF from database and reset File object
	}
	
}
