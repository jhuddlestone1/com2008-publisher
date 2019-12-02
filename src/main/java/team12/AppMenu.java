package team12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppMenu extends JMenu {
	
	public AppMenu() {
		super("User");
		this.add(new JMenuItem("Change role..."));
		this.add(new JMenuItem("Log out"));
	}
	
}