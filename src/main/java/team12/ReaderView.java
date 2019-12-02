package team12;

import java.awt.*;
import javax.swing.*;

public class ReaderView extends AppView {
	
	ListPanel resultsPanel = new ListPanel("Results");
	ListPanel abstractPanel = new ListPanel("Abstract");
	
	public ReaderView() {
		super("wrap 2", "grow", "[][grow]");
		this.add(new SearchPanel(), "span 2, growx").addActionListener(
			new FindArticles(this.searchJournal, this.searchArticle, this.searchText, resultsPanel, abstractPanel)
		);
		this.add(resultsPanel, "grow").addActionListener(new AbstractListener(resultsPanel, abstractPanel));
		this.add(abstractPanel, "grow");
	}
	
}