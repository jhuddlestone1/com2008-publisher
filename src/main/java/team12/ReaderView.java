package team12;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import net.miginfocom.layout.*;

public class ReaderView extends AppView {
	
	public ReaderView() {
		super("wrap 2", "grow", "[][grow]");
		this.add(new SearchPanel(), "span 2, growx");
		this.add(new ResultsPanel(), "grow");
		this.add(new DetailsPanel(), "grow");
	}
	
}