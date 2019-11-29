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
		btnChangeDetails.addActionListener(new ActionHandlers.ChangeDetails());
		
		String journals[] = {"Journal1","Journal2","Journal3"};
		JComboBox comboBox = new JComboBox(journals);
		comboBox.setBounds(377, 53, 160, 52);
		contentPane.add(comboBox);
		
		JLabel lblChooseJournal = new JLabel("Choose journal:");
		lblChooseJournal.setBounds(377, 27, 160, 20);
		contentPane.add(lblChooseJournal);
		
		String actions[] = {"Publish","Retire","Register","Pass role","See roles"};
		
		JPanel panel = new JPanel();
		panel.setBounds(15, 155, 832, 342);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(15, 52, 802, 290);
		panel.add(textField);
		textField.setColumns(10);
		JComboBox comboBox_1 = new JComboBox(actions);
		comboBox_1.setBounds(337, 0, 185, 38);
		panel.add(comboBox_1);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));		
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.setBounds(735, 27, 115, 29);
		contentPane.add(btnGoBack);
		btnGoBack.addActionListener(new ActionHandlers.ChangeRole());
	}
}