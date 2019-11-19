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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class Reviewer1Interface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reviewer1Interface frame = new Reviewer1Interface();
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
	public Reviewer1Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnChangeDetails = new JButton("Change details");
		btnChangeDetails.setBounds(43, 27, 167, 52);
		contentPane.add(btnChangeDetails);
		
		String journals[] = {"Journal1","Journal2","Journal3"};
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 135, 825, 363);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblChooseJournal = new JLabel("Choose 3 articles:");
		lblChooseJournal.setBounds(300, 16, 235, 37);
		panel.add(lblChooseJournal);
		lblChooseJournal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JCheckBox chckbxArticle = new JCheckBox("Article 1");
		chckbxArticle.setBounds(11, 86, 139, 29);
		panel.add(chckbxArticle);
		
		JCheckBox checkBox = new JCheckBox("Article 1");
		checkBox.setBounds(11, 140, 139, 29);
		panel.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("Article 1");
		checkBox_1.setBounds(11, 197, 139, 29);
		panel.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Article 1");
		checkBox_2.setBounds(11, 251, 139, 29);
		panel.add(checkBox_2);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(210, 295, 115, 29);
		panel.add(btnSubmit);
		
		textField = new JTextField();
		textField.setBounds(493, 66, 299, 258);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.setBounds(743, 39, 115, 29);
		contentPane.add(btnGoBack);
		
		String actions[] = {"See articles","Retire"};
	}
}
