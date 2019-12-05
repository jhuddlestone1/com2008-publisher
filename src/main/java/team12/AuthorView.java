package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class AuthorView extends AppView {
	
	TabPanel articleTabs = new TabPanel();
	JButton updateButton = new JButton("Update list");
	JButton submitButton = new JButton("Submit new article");
	
	void initialise(App app) {
		removeAll();
		/*
		// TODO: get current articles and put them here
		for (Object article : articles) {
			// format articles
		}
		articleTabs.update(
			// add articles to tabs
		);
		*/
		add(articleTabs);
		add(updateButton, "split 2");
		add(submitButton);
		refresh();
		
		updateButton.addActionListener(e -> initialise(app));
		submitButton.addActionListener(e -> app.switchView("submit"));
	}
	
	public AuthorView(App app) {
		super("wrap, align center");
		initialise(app);
	}
	
}
