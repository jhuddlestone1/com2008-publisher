package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class ReviewerView extends AppView {
	
	JButton createButton = new JButton("Create new review");
	JButton updateButton = new JButton("Update list");
	TablePanel initialPanel = new TablePanel();
	TablePanel finalPanel = new TablePanel();
	TextPanel abstractPanel = new TextPanel();
	JButton downloadButton = new JButton("Download article (PDF)");
	Object[][] initialData;
	Object[][] finalData;
	File file;
	
	void update(int userID) {
		String[] columns = new String[] {"Title", "Lead author"};
		Object[][] data = ReviewerController.getSubmissions(userID);
		Object[][] initialResults = new Object[data.length][columns.length];
		Object[][] finalResults = new Object[data.length][columns.length];
		for (int i=0; i < data.length; i++) {
			// TODO: send data to initial or final depending on review status
			initialResults[i][0] = data[i][1];
			initialResults[i][1] = data[i][7];
			initialResults[i][2] = data[i][5];
		}
		initialPanel.update(initialResults, columns);
		finalPanel.update(finalResults, columns);
		file = null;
	}
		
	public ReviewerView(App app) {
		super("wrap", "align center, grow", "[][][][grow][grow][]");
		initialPanel.setBorder(App.titledBorder("Awaiting initial review"));
		finalPanel.setBorder(App.titledBorder("Awaiting final review"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Review submissions")).setFont(App.headerFont);
		add(createButton, "split 2");
		add(updateButton);
		add(initialPanel, "split 2, grow");
		add(finalPanel, "grow");
		add(abstractPanel, "grow");
		add(downloadButton);
		
		update(app.userID);
		
		createButton.addActionListener(e -> app.switchView("submission"));
		updateButton.addActionListener(e -> update(app.userID));
		downloadButton.addActionListener(e -> file = null); // TODO: get PDF from database and reset File object
	}
	
}
