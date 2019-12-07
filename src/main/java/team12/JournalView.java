package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JournalView extends AppView {

	JTextField title = new JTextField(30);
	JTextField ISNN = new JTextField(16);	
	JButton submitButton = new JButton("Submit details");
	
	public JournalView(App app) {
		super("wrap, align center", "align center", "grow");
		add(new JLabel("Add journal details")).setFont(App.headerFont);
		add(new JLabel("Title: "));
		add(title);
		add(new JLabel("ISNN: "));
		add(ISNN);		

		add(submitButton);
		
		submitButton.addActionListener(e -> {
			if (title.getText().isEmpty() || ISNN.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please, fill the fields", "Empty fields",JOptionPane.ERROR_MESSAGE);				
			}
			else {
				int isnn = Integer.parseInt(ISNN.getText());
				EditorController.addJournal(isnn, title.getText(), app.userID);
				JOptionPane.showMessageDialog(null, "Journal added!", "Success",JOptionPane.INFORMATION_MESSAGE);				
				app.switchView("editor");
			}
		});
	}
	
}
