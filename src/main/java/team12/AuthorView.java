package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class AuthorView extends AppView {
	
	TabPanel articleTabs = new TabPanel();
	JButton submitButton = new JButton("Submit new article");
	JButton updateButton = new JButton("Update list");
	
	void update(userID) {
		Object[][] submissions = AuthorController.getSubmissions(userID);
		String[] articles = new String[submissions.length];
		AppPanel[] tabs = new AppPanel[submissions.length];
		for (int i=0; i < submissions.length; i++) {
			articles[i] = submissions[i][1].toString(); // title
			tabs[i].add(new ListPanel(submissions[i]));
		}
		articleTabs.update(articles, tabs);
	}
		
	public AuthorView(App app) {
		super("wrap, align center");
		
		update(app.userID);
		
		add(articleTabs);
		add(submitButton, "split 2");
		add(updateButton);
		
		submitButton.addActionListener(e -> app.switchView("submit"));
		updateButton.addActionListener(e -> update(app.userID)); // TODO: grab current list of articles
	}
	
}
