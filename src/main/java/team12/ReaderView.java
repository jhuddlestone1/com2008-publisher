package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class ReaderView extends AppView {
	
	SearchPanel searchPanel = new SearchPanel();
	TablePanel resultsPanel = new TablePanel();
	TextPanel abstractPanel = new TextPanel();
	JButton downloadButton = new JButton("Download PDF");
	File file;
	
	public ReaderView(App app) {
		super("wrap", "align center, grow", "[][grow][grow][]");
		
		resultsPanel.setBorder(App.titledBorder("Results"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		
		add(searchPanel, "growx");
		add(resultsPanel, "grow");
		add(abstractPanel, "grow");
		add(downloadButton, "split 2");
		
		// TODO: filter search
		searchPanel.searchButton.addActionListener(e -> resultsPanel.update(testObject, testArray));
		resultsPanel.selector.addListSelectionListener(e -> abstractPanel.update(e.getSource()));
		downloadButton.addActionListener(e -> file = null); // TODO: get PDF from database and reset File object
	}
	
	Object[][] testObject = {{1,2,3},{4,5,6}};
	Object[] testArray = {"one", "two", "three"};
	String testAbstract = "This is an abstract.";
	
}
