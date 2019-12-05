package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReaderView extends AppView {
	
	SearchPanel searchPanel = new SearchPanel();
	TablePanel resultsPanel = new TablePanel();
	TextPanel abstractPanel = new TextPanel();
	
	public ReaderView(App app) {
		super("wrap", "grow", "[][grow]");
		
		resultsPanel.setBorder(App.titledBorder("Results"));
		abstractPanel.setBorder(App.titledBorder("Abstract"));
		
		add(searchPanel, "growx");
		add(resultsPanel, "grow");
		add(abstractPanel, "grow");
		
		searchPanel.searchButton.addActionListener(e -> resultsPanel.update(testObject, testArray));
		resultsPanel.model.addListSelectionListener(e -> abstractPanel.update(e.getSource()));
	}
	
	Object[][] testObject = {{1,2,3},{4,5,6}};
	Object[] testArray = {"one", "two", "three"};
	String testAbstract = "This is an abstract.";
	
}
