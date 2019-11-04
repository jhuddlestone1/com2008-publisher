/*
 * App.java
 *
 * Jamie Huddlestone, Aleksandra Kulbaka
 *
 */

import javax.swing.*;
import java.awt.*;

public class App {

	public static void main (String[] args) {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.LEADING));
		frame.setVisible(true);
		frame.setSize(1000, 700);

			JPanel grid = new JPanel();
			grid.setLayout(new GridLayout(2,1));

				JPanel top = new JPanel();
				top.setLayout(new FlowLayout(FlowLayout.LEADING));

					JLabel welcome = new JLabel("Welcome");
					welcome.setFont(new Font("Times New Roman", Font.ITALIC, 64));

					JPanel login = new JPanel();
					login.setLayout(new GridLayout(3, 2));
						JLabel loginLabel = new JLabel("Log in:");
						JLabel blankLabel = new JLabel(" ");
						JLabel usernameLabel = new JLabel("Username:");
						//JTextField usernameField = new JTextField();
						JLabel passwordLabel = new JLabel("Password:");
						//JPasswordField passwordField = new JPasswordField();
					login.add(loginLabel);
					login.add(blankLabel);
					login.add(usernameLabel);
					//login.add(usernameField);
					login.add(passwordLabel);
					//login.add(passwordField);

				top.add(welcome);
				top.add(login);


				JPanel bottom = new JPanel();
				bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 100));

					JButton button1 = new JButton("Submit article");
					JButton button2 = new JButton("Read article");
					JButton button3 = new JButton("Register journal");

				bottom.add(button1);
				bottom.add(button2);
				bottom.add(button3);

			grid.add(top);
			grid.add(bottom);

		frame.add(grid);
	}
}

