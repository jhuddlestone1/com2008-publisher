package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReaderView extends AppView {
	
	SearchPanel searchPanel = new SearchPanel();
	ListPanel resultsPanel = new ListPanel("Results");
	ListPanel abstractPanel = new ListPanel("Abstract");
	
	public ReaderView(App app) {
		super("wrap 2", "grow", "[][grow]");
		
		resultsPanel.setBorder(App.titledBorder("Results"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		
		add(searchPanel, "span 2, growx");
		add(resultsPanel, "grow");
		add(abstractPanel, "grow");
		
		searchPanel.searchButton.addActionListener(e -> resultsPanel.update(testArray));
	}
	
	String[] testArray = {"one", "two", "three"};
	
}
