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
		
	public AuthorView(App app) {
		super("wrap, align center");
		add(articleTabs);
		add(updateButton, "split 2");
		add(submitButton);
		
		updateButton.addActionListener(e -> articleTabs.update()); // TODO: grab current list of articles
		submitButton.addActionListener(e -> app.switchView("submit"));
	}
	
}
