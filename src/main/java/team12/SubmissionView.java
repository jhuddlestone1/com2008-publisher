package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class SubmissionView extends AppView {
	
	JRadioButton strongReject = new JRadioButton("Strong reject");
	JRadioButton weakReject = new JRadioButton("Weak reject");
	JRadioButton weakAccept = new JRadioButton("Weak accept");
	JRadioButton strongAccept = new JRadioButton("Strong accept");
	ButtonGroup ratingButtons = new ButtonGroup();
	
	TextPanel summaryPanel = new TextPanel();
	TextPanel errorsPanel = new TextPanel();
	TextPanel questionsPanel = new TextPanel();
	JButton backButton = new JButton("Back to list");
	JButton submitButton = new JButton("Submit review");
	File file;
	
	public SubmissionView(App app) {
		super("wrap", "align center, grow", "[grow][grow][grow][][]");
		summaryPanel.setBorder(App.titledBorder("Summary"));
		errorsPanel.setBorder(App.titledBorder("Proofing errors"));
		questionsPanel.setBorder(App.titledBorder("Questions"));
		ratingButtons.add(strongReject);
		ratingButtons.add(weakReject);
		ratingButtons.add(weakAccept);
		ratingButtons.add(strongAccept);
		
		// TODO: link this page to the previous one!
		
		add(summaryPanel, "grow");
		add(errorsPanel, "grow");
		add(questionsPanel, "grow");
		add(strongReject, "split 4");
		add(weakReject);
		add(weakAccept);
		add(strongAccept);
		add(backButton, "split 2");
		add(submitButton);
		
		backButton.addActionListener(e -> app.switchView("reviewer"));
		submitButton.addActionListener(e -> file = null); // TODO: submit review
	}
	
}
