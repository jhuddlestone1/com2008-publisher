//package team12;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ReaderInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderInterface frame = new ReaderInterface();
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
	public ReaderInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblSearch.setBounds(53, -16, 150, 78);
		contentPane.add(lblSearch);
		
		JRadioButton rdbtnArticle = new JRadioButton("article");
		rdbtnArticle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnArticle.setBounds(63, 65, 155, 29);
		contentPane.add(rdbtnArticle);
		
		JRadioButton rdbtnJournal = new JRadioButton("journal");
		rdbtnJournal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnJournal.setBounds(63, 102, 155, 29);
		contentPane.add(rdbtnJournal);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textPane.setBounds(278, 67, 464, 64);
		contentPane.add(textPane);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSearch.setBounds(798, 102, 115, 29);
		contentPane.add(btnSearch);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBounds(844, 17, 115, 29);
		contentPane.add(btnGoBack);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(25, 249, 451, 346);
		contentPane.add(textPane_1);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.ITALIC, 22));
		lblResults.setBounds(25, 209, 155, 35);
		contentPane.add(lblResults);
		
		textField = new JTextField();
		textField.setBounds(511, 249, 448, 346);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblSelectedAbstract = new JLabel("Selected abstract:");
		lblSelectedAbstract.setFont(new Font("Tahoma", Font.ITALIC, 22));
		lblSelectedAbstract.setBounds(511, 209, 251, 35);
		contentPane.add(lblSelectedAbstract);
	}
}
