package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JournalView extends AppView {

	JTextField title = new JTextField(32);
	JTextField issn = new JTextField(16);	
	JButton submitButton = new JButton("Submit details");
	
	public JournalView(App app) {
		super("wrap, align center", "align center", "grow");
		add(new JLabel("Add journal details")).setFont(App.headerFont);
		add(new JLabel("Title: "));
		add(title);
		add(new JLabel("ISSN: "));
		add(issn);		

		add(submitButton);
		
		submitButton.addActionListener(e -> {
			if (title.getText().isEmpty() || issn.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please fill in all fields", "Add journal details", JOptionPane.ERROR_MESSAGE);				
			}
			else {
				int isnn = Integer.parseInt(issn.getText());
				EditorController.addJournal(isnn, title.getText(), app.userID);
				JOptionPane.showMessageDialog(null, "Journal added!", "Add journal details", JOptionPane.INFORMATION_MESSAGE);				
				app.switchView("editor");
			}
		});
	}
	
}
