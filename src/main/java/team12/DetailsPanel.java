package team12;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import net.miginfocom.layout.*;

public class DetailsPanel extends AppPanel {
	
	public DetailsPanel() {
		super("", "grow", "grow");
		this.add(new JTextArea(), "grow");
		
		this.setBorder(App.etchedBorder);
	}
	
}