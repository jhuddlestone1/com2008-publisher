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

public class ChiefEditorInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtArticle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiefEditorInterface frame = new ChiefEditorInterface();
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
	public ChiefEditorInterface() {
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
		JComboBox comboBox = new JComboBox(journals);
		comboBox.setBounds(377, 53, 160, 52);
		contentPane.add(comboBox);
		
		JLabel lblChooseJournal = new JLabel("Choose journal:");
		lblChooseJournal.setBounds(377, 27, 160, 20);
		contentPane.add(lblChooseJournal);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(43, 113, 807, 385);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		String actions[] = {"Publish","Retire","Register","Pass role","See roles"};
		
		
		txtArticle = new JTextField();
		txtArticle.setHorizontalAlignment(SwingConstants.CENTER);
		txtArticle.setText("article1");
		txtArticle.setBounds(63, 143, 692, 26);
		panel.add(txtArticle);
		txtArticle.setColumns(10);
		JComboBox comboBox_1 = new JComboBox(actions);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(307, 27, 185, 38);
		panel.add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(28, 100, 759, 235);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.setBounds(735, 27, 115, 29);
		contentPane.add(btnGoBack);
	}
}