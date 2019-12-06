package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AuthorView extends AppView {
	
	TabPanel articleTabs = new TabPanel();
	JButton submitButton = new JButton("Submit new article");
	JButton updateButton = new JButton("Update list");
	
	void update(int userID) {
		Object[][] submissions = AuthorController.getSubmissions(userID);
		String[] titles = new String[submissions.length];
		AppPanel[] tabs = new AppPanel[submissions.length];
		for (int i=0; i < submissions.length; i++) {
			titles[i] = submissions[i][1].toString(); // title
			tabs[i].add(new ArticlePanel(submissions[i]));
		}
		articleTabs.update(titles, tabs);
	}
		
	public AuthorView(App app) {
		super("wrap", "align center, grow", "[][][][grow]");
		
		update(app.userID);
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Add/manage articles")).setFont(App.headerFont);
		add(submitButton, "split 2");
		add(updateButton);
		add(articleTabs, "grow");
		
		submitButton.addActionListener(e -> app.switchView("submit"));
		updateButton.addActionListener(e -> update(app.userID));
	}
	
}
