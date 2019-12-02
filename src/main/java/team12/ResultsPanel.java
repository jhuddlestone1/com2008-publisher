package team12;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import net.miginfocom.layout.*;

public class ResultsPanel extends AppPanel {
	
	public ResultsPanel() {
		super("", "grow", "grow");
		this.add(new JTextArea(), "grow");
		
		this.setBorder(App.etchedBorder);
	}
	
}