package team12;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class DefaultPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaultPage frame = new DefaultPage();
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
	public DefaultPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 982, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblWelcomeToReader = new JLabel("Welcome to Reader!");
		lblWelcomeToReader.setFont(new Font("Times New Roman", Font.ITALIC, 55));
		panel.add(lblWelcomeToReader);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblLogIn = new JLabel("Log in: ");
		lblLogIn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogIn.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLogIn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		panel_1.setLayout(new GridLayout(4, 2, 0, 0));
		panel_1.add(lblLogIn);
		
		JLabel label = new JLabel(" ");
		panel_1.add(label);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(lblUsername);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_1.add(textField);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(lblPassword);
		
		passwordField = new JPasswordField();
		panel_1.add(passwordField);
		
		JLabel label_1 = new JLabel(" ");
		panel_1.add(label_1);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_1.add(btnNewButton);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnSubmitArticle = new JButton("Submit article");
		btnSubmitArticle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSubmitArticle.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSubmitArticle.setHorizontalAlignment(SwingConstants.LEADING);
		panel_2.add(btnSubmitArticle);
		
		JButton btnImAReader = new JButton("I'm a reader");
		btnImAReader.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(btnImAReader);
		
		JButton btnRegisterJournal = new JButton("Register journal");
		btnRegisterJournal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(btnRegisterJournal);
	}

}
