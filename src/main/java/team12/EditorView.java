package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditorView extends AppView {
	
	SearchPanel searchPanel = new SearchPanel();
	TabPanel tabPanel = new TabPanel();
	
	public EditorView(App app) {
		super("wrap", "grow", "[][grow]");
		add(searchPanel, "growx");
		add(tabPanel, "grow");
		
		tabPanel.update(panels);
		
		//searchPanel.searchButton.addActionListener(e -> tabPanel.update(testArray));
	}
	
	String[] names = {"tab1", "tab2", "tab3"};
	JPanel[] panels = {new JPanel(), new ListPanel(), new TextPanel()};
	
}
