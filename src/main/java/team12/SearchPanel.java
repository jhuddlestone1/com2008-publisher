package team12;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import net.miginfocom.layout.*;

public class SearchPanel extends AppPanel {
	
	public SearchPanel() {
		super("", "[][][grow][]");
		
		JRadioButton searchArticle = new JRadioButton("Article");
		JRadioButton searchJournal = new JRadioButton("Journal");
		ButtonGroup searchType = new ButtonGroup();
		searchType.add(searchArticle);
		searchType.add(searchJournal);
		this.add(searchArticle);
		this.add(searchJournal);
		
		this.add(new JTextField(16), "grow");
		this.add(new JButton("Search"));
		
		this.setBorder(App.etchedBorder);
	}
	
}