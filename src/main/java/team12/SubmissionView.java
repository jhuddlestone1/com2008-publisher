package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class SubmissionView extends AppView {
	
	JComboBox reviewSelector = new JComboBox(new String[] {"Review 1", "Review 2", "Review 3"});
	JLabel verdict = new JLabel();
	Object[][] data;
	
	TextPanel summaryPanel = new TextPanel();
	TextPanel errorsPanel = new TextPanel();
	TextPanel questionsPanel = new TextPanel();
	TextPanel responsePanel = new TextPanel();
	JButton backButton = new JButton("Back to list");
	JButton uploadButton = new JButton("Re-upload article (PDF)");
	JButton submitButton = new JButton("Submit reply");
	Object[][] criticisms;
	
	void update(int reviewNumber) {
		if (reviewNumber < data.length) { // review total
			criticisms = AuthorController.getCriticisms((int) data[reviewNumber][0]);
			summaryPanel.setText((String) data[reviewNumber][1]);
			errorsPanel.setText((String) data[reviewNumber][2]);
			questionsPanel.setText(App.getColumn(criticisms,1));
			verdict.setText((String) data[reviewNumber][3]);
		}
		else JOptionPane.showMessageDialog(null, "Review not available.", "Select review", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public SubmissionView(App app, Object[][] data) {
		super("wrap 2", "align center, grow", "[][grow][grow][grow][][]");
		this.data = data;
		
		summaryPanel.setBorder(App.titledBorder("Summary"));
		errorsPanel.setBorder(App.titledBorder("Proofing errors"));
		questionsPanel.setBorder(App.titledBorder("Criticisms (new line separates)"));
		responsePanel.setBorder(App.titledBorder("Response to criticisms (new line separates)"));
		
		add(reviewSelector, "align left, split 3");
		add(new JLabel("Verdict: "), "align left");
		add(verdict);
		verdict.setFont(App.headerFont);
		
		add(summaryPanel, "grow, skip");
		add(responsePanel, "grow, span 1 3");
		add(errorsPanel, "grow");
		add(questionsPanel, "grow");
		add(backButton);
		add(uploadButton, "split 2");
		add(submitButton);
		
		reviewSelector.addActionListener(e -> update(reviewSelector.getSelectedIndex()));
		backButton.addActionListener(e -> {
			app.switchView("author");
			app.content.remove(this);
		});
		uploadButton.addActionListener(e -> {});
		submitButton.addActionListener(e -> {
			try {
				String[] responses = responsePanel.getText().trim().split("\\n+");
				Object[] criticismIDs = App.getColumn(criticisms, 0);
				if (App.validate((Object) responses) && responses.length == criticisms.length) {
					for (int i=0; i < responses.length; i++) {
						
						AuthorController.addAnswer(responses[i], (int) criticismIDs[i]);	
					}
					JOptionPane.showMessageDialog(null, "Response submitted.", "Submit response", JOptionPane.INFORMATION_MESSAGE);
				}
				else JOptionPane.showMessageDialog(null, "Number of responses must match number of criticisms.", "Submit response", JOptionPane.INFORMATION_MESSAGE); 
			} catch (Exception error) { error.printStackTrace(); }
		});
	}
	
}
