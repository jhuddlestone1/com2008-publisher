package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SubmissionView extends AppView {
	
	JRadioButton strongReject = new JRadioButton("Strong reject");
	JRadioButton weakReject = new JRadioButton("Weak reject");
	JRadioButton weakAccept = new JRadioButton("Weak accept");
	JRadioButton strongAccept = new JRadioButton("Strong accept");
	ButtonGroup ratingButtons = new ButtonGroup();
	String verdict;
	
	TextPanel summaryPanel = new TextPanel();
	TextPanel errorsPanel = new TextPanel();
	TextPanel questionsPanel = new TextPanel();
	JButton backButton = new JButton("Back to list");
	JButton submitButton = new JButton("Submit review");
	
	public SubmissionView(App app, int submissionID) {
		super("wrap", "align center, grow", "[grow][grow][grow][][]");
		summaryPanel.setBorder(App.titledBorder("Summary"));
		errorsPanel.setBorder(App.titledBorder("Proofing errors"));
		questionsPanel.setBorder(App.titledBorder("Criticisms (new line separates)"));
		ratingButtons.add(strongReject);
		ratingButtons.add(weakReject);
		ratingButtons.add(weakAccept);
		ratingButtons.add(strongAccept);
		
		add(summaryPanel, "grow");
		add(errorsPanel, "grow");
		add(questionsPanel, "grow");
		add(strongReject, "split 4");
		add(weakReject);
		add(weakAccept);
		add(strongAccept);
		add(backButton, "split 2");
		add(submitButton);
		
		strongReject.addActionListener(e -> verdict = "Strong reject");
		weakReject.addActionListener(e -> verdict = "Weak reject");
		weakAccept.addActionListener(e -> verdict = "Weak accept");
		strongAccept.addActionListener(e -> verdict = "Strong accept");
		
		backButton.addActionListener(e -> {
			app.switchView("reviewer");
			app.content.remove(this);
		});
		submitButton.addActionListener(e -> {
			String summary = summaryPanel.getText();
			String errors = errorsPanel.getText();
			String questions = questionsPanel.getText();
			if (App.validate(summary, errors, questions, verdict)) {
				ReviewerController.addReview(
					summary, errors, questions.trim().split("\\n+"), verdict, submissionID, app.userID
				);
				JOptionPane.showMessageDialog(null, "Review submitted.", "Submit review", JOptionPane.INFORMATION_MESSAGE);
				app.switchView("reviewer");
				app.content.remove(this);
			}
			else JOptionPane.showMessageDialog(null, "Some fields missing.", "Submit review", JOptionPane.WARNING_MESSAGE);
		});
	}
	
}
