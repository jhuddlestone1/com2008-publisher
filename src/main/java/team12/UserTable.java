package team12;

import java.awt.*;
import javax.swing.*;

public class UserTable extends TablePanel {

	static String[] columns = new String[] {"Title", "First name(s)", "Last name", "University", "Email address"};

	static Object[][] filter(Object[][] data) {
		Object[][] results = new Object[data.length][columns.length];
		for (int i=0; i < data.length; i++) {
			results[i][0] = data[i][1];
			results[i][1] = data[i][2];
			results[i][2] = data[i][3];
			results[i][3] = data[i][4];
			results[i][4] = data[i][5];
		}
		return results;
	}
	
	void update(Object[][] data) {
		super.update(filter(data), columns);
	}
		
	public UserTable(Object[][] data) {
		update(data);
	}
	
	public UserTable() {}
	
}
