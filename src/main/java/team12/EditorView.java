package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditorView extends AppView {
	
	TabPanel journalTabs = new TabPanel();
	JButton addButton = new JButton("Add journal");
	JButton updateButton = new JButton("Update list");
		
	public EditorView(App app) {
		super("wrap, align center");
		
		// TODO: update each tab panel with a table of editors
		
		add(journalTabs);
		add(addButton, "split 2");
		add(updateButton);
		
		addButton.addActionListener(e -> app.switchView("journal"));
		updateButton.addActionListener(e -> journalTabs.update()); // TODO: grab current list of articles
	}
	
}
