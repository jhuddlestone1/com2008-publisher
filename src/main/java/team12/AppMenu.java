package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppMenu extends JMenu {
	
	JMenuItem mainMenu = new JMenuItem("Main menu");
	JMenuItem logOut = new JMenuItem("Log out");
	JMenuItem changeDetails = new JMenuItem("Change user details");
	JMenuItem quitProgram = new JMenuItem("Quit program");
	
	public AppMenu(App app) {
		super("Actions");
		setMnemonic(KeyEvent.VK_A);
		add(mainMenu).setMnemonic(KeyEvent.VK_M);
		add(changeDetails).setMnemonic(KeyEvent.VK_C);
		add(logOut).setMnemonic(KeyEvent.VK_L);
		addSeparator();
		add(quitProgram).setMnemonic(KeyEvent.VK_Q);
		
		mainMenu.addActionListener(e -> {
			if (app.isLoggedIn()) {
				app.switchView("main");
			} else {
				app.initialise();
				//JOptionPane.showMessageDialog(null, "The main menu is not available.", "Main menu", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		changeDetails.addActionListener(e -> app.switchView("user"));
		logOut.addActionListener(e -> app.logout());
		quitProgram.addActionListener(e -> app.quit());
	}
}
