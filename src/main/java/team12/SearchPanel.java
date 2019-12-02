package team12;

import java.awt.*;
import javax.swing.*;

public class SearchPanel extends AppPanel {
	
	public SearchPanel() {
		super("", "[][][grow][]");
		
		JRadioButton searchArticle = new JRadioButton("Article");
		JRadioButton searchJournal = new JRadioButton("Journal");
		ButtonGroup searchType = new ButtonGroup();
		searchType.add(searchArticle);
		searchType.add(searchJournal);
		searchArticle.setSelected(true);
		this.add(searchArticle);
		this.add(searchJournal);
		
		this.add(new JTextField(16), "grow");
		this.add(new JButton("Search"));
		
		this.setBorder(App.titledBorder("Search"));
	}
	
}