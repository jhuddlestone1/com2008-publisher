package team12;

import java.awt.*;
import javax.swing.*;

public class DetailsPanel extends AppPanel {
	
	JComboBox title = new JComboBox(new String[] {"Prof", "Dr", "Mr", "Ms", "Mx"});
	JTextField firstNames = new JTextField(16);
	JTextField lastName = new JTextField(16);
	JTextField university = new JTextField(16);
	JTextField email = new JTextField(16);
	JPasswordField password = new JPasswordField(16);
	JButton submitButton = new JButton("Submit details");
	
	public DetailsPanel() {
		super("wrap 2");
		add(new JLabel("Title: "));
		add(title);
		add(new JLabel("First name(s): "));
		add(firstNames);
		add(new JLabel("Last name: "));
		add(lastName);
		add(new JLabel("University: "));
		add(university);
		add(new JLabel("Email address: "));
		add(email);
		add(new JLabel("Password: "));
		add(password);
		add(submitButton, "skip");
	}

}
