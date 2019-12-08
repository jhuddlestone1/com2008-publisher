package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AuthorView extends AppView {
	
	//TabPanel articleTabs = new TabPanel();
	ArticleTable articleTable = new ArticleTable();
	JButton submitButton = new JButton("Submit new article");
	JButton updateButton = new JButton("Update list");
	JButton reviewButton = new JButton("View reviews");
	Object[][] data;
	
	/*
	void update(int userID) {
		Object[][] submissions = AuthorController.getSubmissions(userID);
		String[] titles = new String[submissions.length];
		AppPanel[] tabs = new AppPanel[submissions.length];
		for (int i=0; i < submissions.length; i++) {
			titles[i] = submissions[i][1].toString(); // title
System.out.println(submissions[i]);
			tabs[i] = new ListPanel(submissions[i]);
		}
		articleTabs.update(titles, tabs);
	}
	*/	
	void update(int userID) {
		data = AuthorController.getSubmissions(userID);
		articleTable.update(data);
	}
	
	public AuthorView(App app) {
		super("wrap", "align center, grow", "[][][][grow][]");
		
		update(app.userID);
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Add/manage articles")).setFont(App.headerFont);
		add(submitButton, "split 2");
		add(updateButton);
		add(articleTable, "grow");
		add(reviewButton);
		
		submitButton.addActionListener(e -> app.switchView("submit"));
		updateButton.addActionListener(e -> update(app.userID));
		reviewButton.addActionListener(e -> {
			int row = articleTable.getRow();
			if (row >= 0) {
			int submissionID = (int) data[row][0];
				ReviewView reviewView = new ReviewView(app, AuthorController.getReviews(submissionID));
				if (reviewView.data.length > 0) {
					reviewView.update(0);
					app.content.add(reviewView, "review");
					app.switchView("review");
				}
				else JOptionPane.showMessageDialog(null, "No reviews available.", "View review", JOptionPane.INFORMATION_MESSAGE);
			}
			else JOptionPane.showMessageDialog(null, "No row selected.", "View review", JOptionPane.WARNING_MESSAGE);
		});
	}
	
}
