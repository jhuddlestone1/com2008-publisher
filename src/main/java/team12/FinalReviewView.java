package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class FinalReviewView extends AppView {
	
	JComboBox reviewSelector = new JComboBox(new String[] {"Review 1", "Review 2", "Review 3"});
	JLabel verdict = new JLabel();
	Object[] data;
	Object[][] replies;
	String finalVerdict;
		
	TextPanel summaryPanel = new TextPanel();
	TextPanel errorsPanel = new TextPanel();
	TextPanel questionsPanel = new TextPanel();
	TextPanel responsePanel = new TextPanel();

	JButton backButton = new JButton("Back to list");
	JButton submitButton = new JButton("Submit final verdict");
	JRadioButton strongReject = new JRadioButton("Strong reject");
	JRadioButton weakReject = new JRadioButton("Weak reject");
	JRadioButton weakAccept = new JRadioButton("Weak accept");
	JRadioButton strongAccept = new JRadioButton("Strong accept");
	ButtonGroup ratingButtons = new ButtonGroup();	
	
	void update() {
		
		replies = AuthorController.getCriticisms((int) data[17]);
		summaryPanel.setText((String) data[18]);
		errorsPanel.setText((String) data[19]);
		questionsPanel.setText(App.getColumn(replies,1));
		responsePanel.setText(App.getColumn(replies,2));
		verdict.setText((String) data[20]);
		
	}
	
	public FinalReviewView(App app, Object[] data) {
		super("wrap 2", "align center, grow", "[][grow][grow][grow][][]");
		this.data = data;
		
		update();
		
		summaryPanel.setBorder(App.titledBorder("Summary"));
		errorsPanel.setBorder(App.titledBorder("Proofing errors"));
		questionsPanel.setBorder(App.titledBorder("Criticisms (new line separates)"));
		responsePanel.setBorder(App.titledBorder("Response to criticisms (new line separates)"));
		ratingButtons.add(strongReject);
		ratingButtons.add(weakReject);
		ratingButtons.add(weakAccept);
		ratingButtons.add(strongAccept);		
		
		add(new JLabel("Your initial verdict: "), "align left, split 2");
		add(verdict);
		verdict.setFont(App.headerFont);
		add(summaryPanel, "grow, skip");
		add(responsePanel, "grow, span 1 3");
		add(errorsPanel, "grow");
		add(questionsPanel, "grow");
		add(strongReject, "split 4");
		add(weakReject);
		add(weakAccept);
		add(strongAccept);
		add(backButton);
		add(submitButton, "split 2");

		strongReject.addActionListener(e -> finalVerdict = "Strong reject");
		weakReject.addActionListener(e -> finalVerdict = "Weak reject");
		weakAccept.addActionListener(e -> finalVerdict = "Weak accept");
		strongAccept.addActionListener(e -> finalVerdict = "Strong accept");

		backButton.addActionListener(e -> {
			app.switchView("reviewer");
			app.content.remove(this);
		});
		
		submitButton.addActionListener(e -> {
			if (App.validate(finalVerdict)){
				ReviewerController.addFinalVerdict(finalVerdict, (int) data[17]);
				app.switchView("reviewer");
				app.content.remove(this);
			}
			else JOptionPane.showMessageDialog(null, "Choose final verdict.", "Submit final review", JOptionPane.WARNING_MESSAGE);
		});
	}
	
}
