package team12;

import java.awt.*;
import javax.swing.*;

public class ReaderView extends AppView {
	
	public ReaderView() {
		super("wrap 2", "grow", "[][grow]");
		this.add(new SearchPanel(), "span 2, growx");
		this.add(new ListPanel("Results"), "grow");
		this.add(new ListPanel("Abstract"), "grow");
	}
	
}