package team12;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;

public class ArticlePanel extends AppPanel {
	
	public void empty() {
		removeAll();
		refresh();
	}
	
	public void update(Object... items) {
		removeAll();
		for (Object item : items) {
			add(new JLabel(item.toString()));
		}
		refresh();
	}
	
	public void update(ArrayList items) {
		removeAll();
		for (Object item : items) {
			add(new JLabel(item.toString()));
		}
		refresh();
	}
	
	public ArticlePanel() {
		//
		super("wrap");
	}
	
	public ArticlePanel(Object... items) {
		this();
		removeAll();
		int ISNN = Integer.parseInt(items[0].toString());
		String title = items[1].toString();
		int userID = Integer.parseInt(items[2].toString());
		JComboBox actions = new JComboBox(getActionsArray (userID, title));
		add(actions);
		actions.addActionListener(e -> {
			String action = (String) actions.getSelectedItem();
			
			switch (action){
				case "Publish":
					//EditorController.addEdition();
					break;
				case "See articles":
					//EditorCo
					break;					
				case "Register other editor":
					// changes interface to register interface
					break;
				case "Pass role":
					//String [][] editors = seeEditors(ISNN);
					// later:  transferChief(ISSN, usernameofnewchief)
					break; 
				case "See roles":
					//String [][] editorss = seeEditors(ISNN);
					break;
				case "Retire":
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (this,
						"Do you want to retire from the board for the "+ title + "?",
															"Retire",dialogButton);
				if (dialogResult==0){
					if (EditorController.deleteChiefEditor(userID)){
						JOptionPane.showMessageDialog(this, "You're no longer editor of this journal", "", JOptionPane.INFORMATION_MESSAGE);
						// close that tab panel
						update();
					}
					else{
						JOptionPane.showMessageDialog(this, 
						"You cannot retire, you're the only editor", "Message", JOptionPane.ERROR_MESSAGE);
					} 
				}
				break;
			}
			refresh();
		});
	}
	
	public ArticlePanel(ArrayList items) {
		this();
		update(items);
	}

	public String [] getActionsArray (int userID, String title){
		if (userID==EditorController.getChiefEditorID(title)){
			String [] actionsArray = {"See articles","Publish journal","Retire","Register","Pass role","See roles"};
			return actionsArray;
		}
		else {
			String [] actionsArray = {"See articles","Retire"};
			return actionsArray;
		}			
	}
}
