package team12;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class authorInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					authorInterface frame = new authorInterface();
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
	public authorInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(15, 134, 885, 401);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Article", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblStatus = new JLabel("STATUS:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 43));
		lblStatus.setBounds(134, 0, 175, 60);
		panel.add(lblStatus);
		
		JLabel lblUnknown = new JLabel("Unknown");
		lblUnknown.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUnknown.setBounds(484, 20, 168, 38);
		panel.add(lblUnknown);
		
		JLabel lblReviews = new JLabel("Review:");
		lblReviews.setBounds(34, 96, 69, 20);
		panel.add(lblReviews);
		
		JLabel lblVerdict = new JLabel("Verdict:");
		lblVerdict.setBounds(167, 96, 69, 20);
		panel.add(lblVerdict);
		
		JLabel lblResponse = new JLabel("Response:");
		lblResponse.setBounds(310, 96, 107, 20);
		panel.add(lblResponse);
		
		JLabel lblUpload = new JLabel("Upload:");
		lblUpload.setBounds(513, 96, 69, 20);
		panel.add(lblUpload);
		
		JButton btnNewButton = new JButton("Change details");
		btnNewButton.setBounds(37, 37, 201, 48);
		contentPane.add(btnNewButton);
		
		JButton btnSubmitArticle = new JButton("Submit article");
		btnSubmitArticle.setBounds(717, 37, 201, 48);
		contentPane.add(btnSubmitArticle);
		
		String articles[] = {"Article1","Article2","Article3"};
		JComboBox comboBox = new JComboBox(articles);
		comboBox.setBounds(361, 57, 201, 44);
		contentPane.add(comboBox);
		
		JLabel lblChooseArticle = new JLabel("Choose article:");
		lblChooseArticle.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseArticle.setBounds(361, 16, 201, 40);
		contentPane.add(lblChooseArticle);
	}
}
