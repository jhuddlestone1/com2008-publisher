package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class ReviewerView extends AppView {
	
	TabPanel articleTabs = new TabPanel();
	JButton submitButton = new JButton("Submit review");
	JButton updateButton = new JButton("Update list");
		
	public ReviewerView(App app) {
		super("wrap, align center");
		
		// TODO: update each tab panel with a table of articles
		
		add(articleTabs);
		add(submitButton, "split 2");
		add(updateButton);
		
		submitButton.addActionListener(e -> articleTabs.update());
		updateButton.addActionListener(e -> articleTabs.update()); // TODO: grab current list of articles
	}
	
}
