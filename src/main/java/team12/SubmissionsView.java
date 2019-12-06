package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class SubmissionsView extends AppView {
	
	TablePanel resultsPanel = new TablePanel();
	TextPanel abstractPanel = new TextPanel();
	JButton backButton = new JButton("Back to list");
	JButton reviewButton = new JButton("Add to review list");
	File file;
	
	public SubmissionsView(App app) {
		super("wrap, align center", "grow", "[][grow]");
		
		// TODO: instantiate with review queue
		
		resultsPanel.setBorder(App.titledBorder("Submissions"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		
		add(resultsPanel, "grow");
		add(abstractPanel, "grow");
		add(backButton, "split 2");
		add(reviewButton);
		
		// TODO: filter search
		resultsPanel.selector.addListSelectionListener(e -> abstractPanel.update(e.getSource()));
		backButton.addActionListener(e -> app.switchView("reviewer"));
		reviewButton.addActionListener(e -> file = null); // TODO: add article to user's review list
	}
	
	Object[][] testObject = {{1,2,3},{4,5,6}};
	Object[] testArray = {"one", "two", "three"};
	String testAbstract = "This is an abstract.";
	
}
