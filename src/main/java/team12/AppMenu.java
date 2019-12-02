package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppMenu extends JMenu {
	
	public AppMenu() {
		super("User");
		this.setMnemonic(KeyEvent.VK_U);
		this.add(new JMenuItem("Change role...")).setMnemonic(KeyEvent.VK_C);
		this.add(new JMenuItem("Log out")).setMnemonic(KeyEvent.VK_L);
		this.addSeparator();
		this.add(new JMenuItem("Quit program")).setMnemonic(KeyEvent.VK_Q);
	}
	
}