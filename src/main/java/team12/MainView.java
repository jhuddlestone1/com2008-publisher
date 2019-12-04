package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainView extends AppView {
	
	JButton readerButton = new JButton("Read articles");
	JButton authorButton = new JButton("Submit articles");
	JButton reviewerButton = new JButton("Review articles");
	JButton editorButton = new JButton("Edit/publish journals");
	JButton journalButton = new JButton("Register new journal");
	
	public MainView(App app) {
		super("wrap", "grow, align center", "grow");
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Main menu")).setFont(App.headerFont);
		add(readerButton, "grow");
		add(authorButton, "grow");
		add(reviewerButton, "grow");
		add(editorButton, "grow");
		add(journalButton, "grow");
		
		readerButton.addActionListener(e -> app.switchView("reader"));
		authorButton.addActionListener(e -> app.switchView("author"));
		reviewerButton.addActionListener(e -> app.switchView("reviewer"));
		editorButton.addActionListener(e -> app.switchView("editor"));
		journalButton.addActionListener(e -> app.switchView("journal"));
	}
	
}
