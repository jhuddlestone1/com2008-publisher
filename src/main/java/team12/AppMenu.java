package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppMenu extends JMenu {
	
	JMenuItem mainMenu = new JMenuItem("Main menu");
	JMenuItem logOut = new JMenuItem("Log out");
	JMenuItem quitProgram = new JMenuItem("Quit program");
	
	public AppMenu(App app) {
		super("Actions");
		setMnemonic(KeyEvent.VK_A);
		add(mainMenu).setMnemonic(KeyEvent.VK_M);
		add(logOut).setMnemonic(KeyEvent.VK_L);
		addSeparator();
		add(quitProgram).setMnemonic(KeyEvent.VK_Q);
		
		mainMenu.addActionListener(e -> {
			if (app.isLoggedIn()) {
				app.switchView("main");
			} else {
				app.logout();
				//JOptionPane.showMessageDialog(app, "The main menu is not available.", "Main menu", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		logOut.addActionListener(e -> app.logout());
		quitProgram.addActionListener(e -> app.quit());
	}
	
}
