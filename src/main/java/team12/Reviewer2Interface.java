package team12;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class Reviewer2Interface extends JFrame {

	private JPanel contentPane;
	private JTextField txtSummary;
	private JTextField txtErrors;
	private JTextField txtCriticisims;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reviewer2Interface frame = new Reviewer2Interface();
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
	public Reviewer2Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Change details");
		btnNewButton.setBounds(37, 37, 201, 48);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionHandlers.ChangeDetails());
		
		String articles[] = {"Article1","Article2","Article3"};
		JComboBox comboBox = new JComboBox(articles);
		comboBox.setBounds(361, 57, 201, 44);
		contentPane.add(comboBox);
		
		JLabel lblChooseArticle = new JLabel("Choose article:");
		lblChooseArticle.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseArticle.setBounds(361, 16, 201, 40);
		contentPane.add(lblChooseArticle);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.setBounds(782, 22, 115, 29);
		contentPane.add(btnGoBack);
		btnGoBack.addActionListener(new ActionHandlers.ChangeRole());
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 154, 855, 372);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtSummary = new JTextField();
		txtSummary.setText("Summary:");
		txtSummary.setBounds(50, 49, 770, 54);
		panel.add(txtSummary);
		txtSummary.setColumns(10);
		
		txtErrors = new JTextField();
		txtErrors.setText("Errors:");
		txtErrors.setColumns(10);
		txtErrors.setBounds(50, 119, 770, 54);
		panel.add(txtErrors);
		
		txtCriticisims = new JTextField();
		txtCriticisims.setText("Criticisims:");
		txtCriticisims.setColumns(10);
		txtCriticisims.setBounds(50, 189, 770, 54);
		panel.add(txtCriticisims);
		
		JLabel lblReview = new JLabel("Review:");
		lblReview.setBounds(179, 16, 69, 20);
		panel.add(lblReview);
		
		JButton btnSubmit = new JButton("Submit ");
		btnSubmit.setBounds(705, 257, 115, 29);
		panel.add(btnSubmit);
		
		String actions [] = {"Review","See details","Response to reply", "Submit final verdict"};
		JComboBox comboBox_1 = new JComboBox(actions);
		comboBox_1.setBounds(740, 122, 140, 26);
		contentPane.add(comboBox_1);
	}
}
