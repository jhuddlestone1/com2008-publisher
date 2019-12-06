package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class ReaderView extends AppView {
	
	SearchPanel searchPanel = new SearchPanel();
	TablePanel articlePanel = new TablePanel();
	TextPanel abstractPanel = new TextPanel();
	JButton downloadButton = new JButton("Download article (PDF)");
	Object[][] data;
	File file;
	
	void search(String query) {
		if (App.validate(query)) {
			data = UserController.getPublishedArticles(query);
			String[] columns = new String[] {"Title", "Lead author", "Reviews"};
			Object[][] results = new Object[data.length][columns.length];
			for (int i=0; i < data.length; i++) {
				results[i][0] = data[i][1];
				results[i][1] = data[i][7];
				results[i][2] = data[i][5];
			}
			articlePanel.update(results, columns);
			file = null;
		}
		else JOptionPane.showMessageDialog(this, "No search term provided.", "Search", JOptionPane.WARNING_MESSAGE);
	}
	
	public ReaderView(App app) {
		super("wrap", "align center, grow", "[][grow][grow][]");
		articlePanel.setBorder(App.titledBorder("Results"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		
		add(searchPanel, "growx");
		add(articlePanel, "grow");
		add(abstractPanel, "grow");
		add(downloadButton);
		
		// TODO: filter search
		searchPanel.searchButton.addActionListener(e -> search(searchPanel.searchField.getText()));
		articlePanel.selector.addListSelectionListener(e -> {
			abstractPanel.update(data[articlePanel.getRow()][2]);
			//file = ; // TODO: prepare PDF for download
		});
		downloadButton.addActionListener(e -> file = null); // TODO: get PDF from database and reset File object
	}
	
}
