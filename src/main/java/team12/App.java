package team12;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class App extends JFrame {
	
	// Set styles here; much more memory efficient than constantly reinstatiating
	// them inline, and it's easier to update styles for all elements at once.
	public static Font headerFont = new Font(null, Font.BOLD, 18);
	public static Border defaultBorder = BorderFactory.createEtchedBorder();
	public static Border titledBorder(String title) {
		return new TitledBorder(App.defaultBorder, title);
	}
	
	public CardLayout layout = new CardLayout();
	public JMenuBar menu = new JMenuBar();
	public Container content = this.getContentPane();
	
	public App() {
		super("Team 12 Academic Publishing");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(layout);
		menu.add(new AppMenu());
		this.setJMenuBar(menu);
		
		// Add views to be used in the app here, along with their reference names.
		// The first view added will be shown when the app loads.
		content.add(new WelcomeView(this), "welcome");
		content.add(new ReaderView(this), "reader");
		
		this.pack();                      // resizes app to contain all elements
		this.setLocationRelativeTo(null); // centers app on screen
		this.setVisible(true);
	}
	
	// Switches between views; pass the view's reference name as defined above.
	// Returns a reference back to the app instance to allow method chaining.
	public App switchView(String viewName) {
		layout.show(content, viewName);
		return this;
	}

	public static void main(String[] args) {
		
		try {
			// Set UI style to match operating system
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {} // will automatically fall back to default UI style
		
		App frame = new App();
		
		//frame.switchView("reader");
	}

}

