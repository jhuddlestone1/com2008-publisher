package team12;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import net.miginfocom.layout.*;

public class WelcomeView extends AppView {
	
	public WelcomeView() {
		super("wrap", "grow, align center", "grow");
		this.add(new JLabel("Team 12 Academic Publishing"));
		this.add(new JLabel("Welcome")).setFont(App.headerFont);
		this.add(new LoginPanel());
		this.add(new JButton("Browse articles"), "split 3");
		this.add(new JButton("Submit article"));
		this.add(new JButton("Register journal"));
	}
	
}