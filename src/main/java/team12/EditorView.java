package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditorView extends AppView {
	
	TabPanel journalTabs = new TabPanel();
	JButton createButton = new JButton("Create new journal");
	JButton updateButton = new JButton("Update list");
	
	void update(App app) {
		Object[][] journals = EditorController.getJournals(app.userID);
		String[] titles = new String[journals.length];
		AppPanel[] tabs = new AppPanel[journals.length];
		for (int i=0; i < journals.length; i++) {
			titles[i] = journals[i][1].toString(); // title
			tabs[i] = new EditorTab(app, journals[i]);
		}
		journalTabs.update(titles, tabs);
	}
	
	public EditorView(App app) {
		super("wrap", "align center, grow", "[][][][grow]");
		
		update(app);
		
		add(new JLabel("Team 12 Academic Publishing"));
		add(new JLabel("Add/manage journals")).setFont(App.headerFont);
		add(createButton, "split 2");
		add(updateButton);
		add(journalTabs, "grow");
		
		createButton.addActionListener(e -> {
			JTextField titleField = new JTextField();
			JTextField issnField = new JTextField();
			Object[] message = {
				"Title of journal:", titleField,
				"ISSN:", issnField
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Create new journal", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				String title = titleField.getText();
				try {
					int issn = Integer.parseInt(issnField.getText());
					if (App.validate(title, issnField.getText()) && EditorController.addJournal(issn, title, app.userID)) {
						update(app);
					}
					else {
						JOptionPane.showMessageDialog(null,
							"Could not create new journal.", "Retire from journal", JOptionPane.WARNING_MESSAGE
						);
					}
				}
				catch (NumberFormatException o) {
					JOptionPane.showMessageDialog(null,
						"Invalid format for ISSN number.", "Retire from journal", JOptionPane.WARNING_MESSAGE
					);
				}
			}
		});
		updateButton.addActionListener(e -> update(app));
	}
	
}
