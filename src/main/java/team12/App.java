package team12;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class App extends JFrame {
	
	// Set styles here; much more memory efficient than constantly reinstatiating
	// them inline, and it's easier to update styles for all elements at once.
	static Font headerFont = new Font(null, Font.BOLD, 18);
	static Border defaultBorder = BorderFactory.createEtchedBorder();
	static Border titledBorder(String title) {
		return new TitledBorder(App.defaultBorder, title);
	}
	
	// If this field is greater than zero, we know a user is logged in, and which one!
	int userID;
	
	CardLayout layout = new CardLayout();
	JMenuBar menubar = new JMenuBar();
	Container content = this.getContentPane();
	
	public App() {
		super("Team 12 Academic Publishing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(layout);
		menubar.add(new AppMenu(this));
		setJMenuBar(menubar);
		initialise(); // Set app to defalt state; see below
		setVisible(true);
	}
	
	// Switches between views; pass the view's reference name as defined above.
	// Returns a reference back to the app instance to allow method chaining.
	App switchView(String viewName) {
		layout.show(content, viewName);
		return this;
	}
	
	// Set app to default state
	void initialise() {
		userID = 0;
		content.removeAll();
		content.add(new WelcomeView(this), "welcome");
		content.add(new UserView(this), "user");
		pack();                      // resizes app to contain all elements
		setLocationRelativeTo(null); // centers app on screen
	}
	
	// Set app to allow users to browse articles without logging in
	void browse() {
		userID = 0;
		content.removeAll();
		content.add(new ReaderView(this), "reader");
		pack();                      // resizes app to contain all elements
		setLocationRelativeTo(null); // centers app on screen
	}
		
	// Add views accessible to authorised user and define their reference names.
	// The first view added will be shown when the app loads.
	void login(int id) {
		userID = id;
		content.removeAll();
		
		// TODO: add decision logic for user permissions;
		// maybe add menu items based on array of permissions corresponding to view names?
		// Add views here
		content.add(new MainView(this), "main");
		content.add(new ReaderView(this), "reader");
		content.add(new AuthorView(this), "author");
		content.add(new ReviewerView(this), "reviewer");
		content.add(new EditorView(this), "editor");
		content.add(new JournalView(this), "journal");
		content.add(new UserView(this), "user");
		
		pack();                      // resizes app to contain all elements
		setLocationRelativeTo(null); // centers app on screen
	}
	
	public void logout() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Log out", JOptionPane.YES_NO_OPTION)==0)
			initialise();
	}
	
	public void quit() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit program", JOptionPane.YES_NO_OPTION)==0)
			System.exit(0);
	}

	public static void main(String[] args) {
		
		try {
			// Set UI style to match operating system
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {} // will automatically fall back to default UI style
		
		App frame = new App();
		
		//frame.switchView("editor");
	}

}
