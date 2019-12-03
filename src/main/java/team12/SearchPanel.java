package team12;

import java.awt.*;
import javax.swing.*;

public class SearchPanel extends AppPanel {
	
	ButtonGroup searchType = new ButtonGroup();
	JRadioButton searchArticle = new JRadioButton("Article");
	JRadioButton searchJournal = new JRadioButton("Journal");
	JTextField searchField = new JTextField(16);
	JButton searchButton = new JButton("Search");
	
	public SearchPanel() {
		super("", "[][][grow][]");
				
		searchType.add(searchArticle);
		searchType.add(searchJournal);
		searchArticle.setSelected(true);
		
		add(searchArticle);
		add(searchJournal);
		add(searchField, "grow");
		add(searchButton);
	}
	
}
