package team12;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditorInterface extends JFrame {

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
					EditorInterface frame = new EditorInterface();
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
	public EditorInterface() {
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
		
		// needs to call EditorController.getJournal method to replace content of it:
		String journals[] = {"Journal1","Journal2","Journal3"};
		JComboBox comboBox = new JComboBox(journals);
		comboBox.setBounds(377, 53, 160, 52);
		contentPane.add(comboBox);
		// that changes tab panel 
		//comboBox.addActionListener(new ActionHandlers.chooseEditorJournal(comboBox));
		
		JLabel lblChooseJournal = new JLabel("Choose journal:");
		lblChooseJournal.setBounds(377, 27, 160, 20);
		contentPane.add(lblChooseJournal);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(43, 113, 807, 385);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		
		txtArticle = new JTextField();
		txtArticle.setHorizontalAlignment(SwingConstants.CENTER);
		txtArticle.setText("article1");
		txtArticle.setBounds(63, 143, 692, 26);
		panel.add(txtArticle);
		txtArticle.setColumns(10);

		String actions[] = {"See articles","Retire"};
		JComboBox comboBox_1 = new JComboBox(actions);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(307, 27, 185, 38);
		panel.add(comboBox_1);
		//Needs to pass the name of the article! 
		String journal = "journal1";
		comboBox_1.addActionListener(new ActionHandlers.chooseEditorAction(comboBox_1, journal));
		
		textField = new JTextField();
		textField.setBounds(28, 100, 759, 235);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.setBounds(735, 27, 115, 29);
		contentPane.add(btnGoBack);
		btnGoBack.addActionListener(new ActionHandlers.ChangeRole());
	}
}
