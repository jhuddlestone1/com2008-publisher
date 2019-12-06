package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditorView extends AppView {
	
	TabPanel journalTabs = new TabPanel();
	JButton createButton = new JButton("Create new journal");
	JButton updateButton = new JButton("Update list");
	
	void update(int userID) {
		Object[][] journals = EditorController.getJournals(userID);
		String[] titles = new String[journals.length];
		AppPanel[] tabs = new AppPanel[journals.length];
		for (int i=0; i < journals.length; i++) {
			titles[i] = journals[i][1].toString(); // title
			tabs[i].add(new ArticlePanel(journals[i]));
		}
		journalTabs.update(titles, tabs);
	}
	
	public EditorView(App app) {
		super("wrap", "align center, grow", "[][][][grow]");
		
		update(app.userID);
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Add/manage journals")).setFont(App.headerFont);
		add(createButton, "split 2");
		add(updateButton);
		add(journalTabs, "grow");
		
		createButton.addActionListener(e -> app.switchView("journal"));
		updateButton.addActionListener(e -> update(app.userID));
	}
	
}
