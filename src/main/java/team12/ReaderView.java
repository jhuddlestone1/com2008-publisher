package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class ReaderView extends AppView {
	
	SearchPanel searchPanel = new SearchPanel();
	ArticleTable articleTable = new ArticleTable();
	TextPanel abstractPanel = new TextPanel();
	JButton downloadButton = new JButton("Download article (PDF)");
	Object[][] data;
	File file;
	
	void search(String query) {
		abstractPanel.empty();
		//articleTable.table.getSelectionModel().clearSelection();
		//articleTable.table.getColumnModel().getSelectionModel().clearSelection();
		if (searchPanel.searchJournal.isSelected())
			data = UserController.getPublishedJournals(query);
		else 
			data = UserController.getPublishedArticles(query);
		articleTable.update(data);
		file = null;
	}
	
	public ReaderView(App app) {
		super("wrap", "align center, grow", "[][grow][grow][]");
		articleTable.setBorder(App.titledBorder("Results"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		
		add(searchPanel, "growx");
		add(articleTable, "grow");
		add(abstractPanel, "grow");
		add(downloadButton);
		
		search("");
		
		// TODO: filter search
		searchPanel.searchButton.addActionListener(e -> search(searchPanel.searchField.getText()));
		articleTable.selector.addListSelectionListener(e -> {
			int row = articleTable.getRow();
			if (row < 0) return;
			abstractPanel.update(data[row][2]);
			//file = ; // TODO: prepare PDF for download
		});
		downloadButton.addActionListener(e -> file = null); // TODO: get PDF from database and reset File object
	}
	
}
