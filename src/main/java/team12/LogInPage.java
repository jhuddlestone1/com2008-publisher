package team12;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LogInPage extends JFrame {

private JPanel contentPane;

/**
 * Launch the application.
 */
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				LogInPage frame = new LogInPage();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}

/**
 * Create the frame.
 */
public LogInPage() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 982, 366);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));
	setContentPane(contentPane);
	
	JPanel panel = new JPanel();
	contentPane.add(panel, BorderLayout.NORTH);
	panel.setLayout(new GridLayout(0, 2, 0, 0));
	
	JLabel lblWelcomeToReader = new JLabel("Choose a role:");
	lblWelcomeToReader.setHorizontalAlignment(SwingConstants.RIGHT);
	lblWelcomeToReader.setFont(new Font("Times New Roman", Font.ITALIC, 55));
	panel.add(lblWelcomeToReader);
	
	JPanel panel_2 = new JPanel();
	contentPane.add(panel_2, BorderLayout.CENTER);
	
	JLabel label = new JLabel("                                                                                                                                                                                             ");
	label.setFont(new Font("Tahoma", Font.PLAIN, 30));
	panel_2.add(label);
	
	JButton btnSubmitArticle = new JButton("Editor");
	btnSubmitArticle.setFont(new Font("Tahoma", Font.PLAIN, 24));
	btnSubmitArticle.setVerticalAlignment(SwingConstants.BOTTOM);
	btnSubmitArticle.setHorizontalAlignment(SwingConstants.LEADING);
	panel_2.add(btnSubmitArticle);
	
	JButton btnImAReader = new JButton("Author");
	btnImAReader.setFont(new Font("Tahoma", Font.PLAIN, 25));
	panel_2.add(btnImAReader);
	
	JButton btnRegisterJournal = new JButton("Reviewer");
	btnRegisterJournal.setFont(new Font("Tahoma", Font.PLAIN, 25));
	panel_2.add(btnRegisterJournal);
	
	JLabel lblNewLabel = new JLabel("                                             ");
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 75));
	panel_2.add(lblNewLabel);
	
	JButton btnLogOut = new JButton("Log out");
	btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 24));
	panel_2.add(btnLogOut);
}

}