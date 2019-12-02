package team12;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import net.miginfocom.layout.*;

public abstract class AppView extends JPanel {
		
	public AppView(String layout, String grid, String component) {
		super(new MigLayout(layout, grid, component));
	}
	
	public AppView(String layout, String grid) {
		super(new MigLayout(layout, grid));
	}
	
	public AppView(String layout) {
		super(new MigLayout(layout));
	}
	
	public AppView() {
		super(new MigLayout());
	}
	
}