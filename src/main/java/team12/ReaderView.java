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
		
		searchPanel.searchButton.addActionListener(e -> updateResultsPanel());
		
		this.add(searchPanel, "span 2, growx");
		this.add(resultsPanel, "grow");
		this.add(abstractPanel, "grow");
	}
	
	void updateResultsPanel() {
		JOptionPane.showMessageDialog(null, "Meow!");
	}
	
}