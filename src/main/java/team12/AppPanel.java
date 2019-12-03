package team12;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import net.miginfocom.layout.*;

public abstract class AppPanel extends JPanel {
	
	public AppPanel(String layout, String grid, String component) {
		super(new MigLayout(layout, grid, component));
	}
	
	public AppPanel(String layout, String grid) {
		super(new MigLayout(layout, grid));
	}
	
	public AppPanel(String layout) {
		super(new MigLayout(layout));
	}
	
	public AppPanel() {
		super(new MigLayout());
	}
	
	public void refresh() {
		validate();
		repaint();
	}
	
}
