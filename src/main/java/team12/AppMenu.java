package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppMenu extends JMenu {
	
	JMenuItem changeRole = new JMenuItem("Change role...");
	JMenuItem logOut = new JMenuItem("Log out");
	JMenuItem quitProgram = new JMenuItem("Quit program");
	
	public AppMenu() {
		super("User");
		this.setMnemonic(KeyEvent.VK_U);
		this.add(changeRole).setMnemonic(KeyEvent.VK_C);
		this.add(logOut).setMnemonic(KeyEvent.VK_L);
		this.addSeparator();
		this.add(quitProgram).setMnemonic(KeyEvent.VK_Q);
	}
	
}
