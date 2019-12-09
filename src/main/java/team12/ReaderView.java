package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class ReaderView extends AppView {
	
	SearchPanel searchPanel = new SearchPanel();
	ArticleTable articlePanel = new ArticleTable();
	TextPanel abstractPanel = new TextPanel();
	JButton downloadButton = new JButton("Download article (PDF)");
	Object[][] data;
	File file;
	
	void search(String query) {
		if (searchPanel.searchJournal.isSelected())
			data = UserController.getPublishedJournals(query);
		else 
			data = UserController.getPublishedArticles(query);
		articlePanel.update(data);
		file = null;
	}
	
	public ReaderView(App app) {
		super("wrap", "align center, grow", "[][grow][grow][]");
		articlePanel.setBorder(App.titledBorder("Results"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		
		add(searchPanel, "growx");
		add(articlePanel, "grow");
		add(abstractPanel, "grow");
		add(downloadButton);
		
		search("");
		
		// TODO: filter search
		searchPanel.searchButton.addActionListener(e -> search(searchPanel.searchField.getText()));
		articlePanel.selector.addListSelectionListener(e -> {
			abstractPanel.update(data[articlePanel.getRow()][2]);
			//file = ; // TODO: prepare PDF for download
		});
		downloadButton.addActionListener(e -> file = null); // TODO: get PDF from database and reset File object
	}
	
}
