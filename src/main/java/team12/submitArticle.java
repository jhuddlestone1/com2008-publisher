package team12;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class submitArticle extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextArea textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					submitArticle frame = new submitArticle();
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
	public submitArticle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 632);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSubmitTheArticle = new JLabel("Submit the article:");
		lblSubmitTheArticle.setFont(new Font("Times New Roman", Font.ITALIC, 42));
		lblSubmitTheArticle.setBounds(317, 16, 334, 87);
		contentPane.add(lblSubmitTheArticle);
		
		JLabel lblNewLabel = new JLabel("Title:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(63, 126, 121, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblAbstract = new JLabel("Abstract:");
		lblAbstract.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAbstract.setBounds(63, 204, 121, 46);
		contentPane.add(lblAbstract);
		
		JLabel lblPdf = new JLabel("PDF:");
		lblPdf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPdf.setBounds(63, 328, 121, 46);
		contentPane.add(lblPdf);
		
		JLabel lblCoauthors = new JLabel("Co-authors:");
		lblCoauthors.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCoauthors.setBounds(63, 407, 121, 46);
		contentPane.add(lblCoauthors);
		
		textField = new JTextField();
		textField.setBounds(294, 138, 466, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(294, 216, 466, 94);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(294, 340, 146, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
				
		textField_3 = new JTextField();
		textField_3.setBounds(294, 418, 358, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextArea();
		textField_4.setColumns(10);
		textField_4.setBounds(294, 453, 358, 107);
		contentPane.add(textField_4);

		JButton btnAdd = new JButton("add:");
		btnAdd.setBounds(63, 452, 115, 29);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionHandlers.AddCoauthor(textField_3, textField_4));		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(780, 499, 115, 29);
		contentPane.add(btnSubmit);	
		btnSubmit.addActionListener(new ActionHandlers.AddArticle(textField, textField_1,
				 textField_2, textField_3, textField_4));		
		
	}
}
