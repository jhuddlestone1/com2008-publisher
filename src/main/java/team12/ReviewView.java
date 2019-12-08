package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReviewView extends AppView {
	
	JComboBox reviewSelector = new JComboBox(new String[] {"Review 1", "Review 2", "Review 3"});
	JLabel verdict = new JLabel();
	Object[][] data;
	
	TextPanel summaryPanel = new TextPanel();
	TextPanel errorsPanel = new TextPanel();
	TextPanel questionsPanel = new TextPanel();
	JButton backButton = new JButton("Back to list");
	
	void update(int reviewNumber) {
		if (reviewNumber < data.length) { // review total
			summaryPanel.setText((String) data[reviewNumber][1]);
			errorsPanel.setText((String) data[reviewNumber][2]);
			questionsPanel.setText((String) "Not ready yet!");
			verdict.setText((String) data[reviewNumber][3]);
		}
		else JOptionPane.showMessageDialog(null, "Review not available.", "Select review", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public ReviewView(App app, Object[][] data) {
		super("wrap", "align center, grow", "[][grow][grow][grow][][]");
		this.data = data;
		
		summaryPanel.setBorder(App.titledBorder("Summary"));
		errorsPanel.setBorder(App.titledBorder("Proofing errors"));
		questionsPanel.setBorder(App.titledBorder("Questions"));
		
		add(reviewSelector, "align left, split 3");
		add(new JLabel("Verdict: "));
		add(verdict);
		add(summaryPanel, "grow");
		add(errorsPanel, "grow");
		add(questionsPanel, "grow");
		add(backButton);
		
		reviewSelector.addActionListener(e -> update(reviewSelector.getSelectedIndex()));
		backButton.addActionListener(e -> {
			app.switchView("author");
			app.content.remove(this);
		});
	}
	
}
