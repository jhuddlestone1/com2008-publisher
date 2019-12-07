package team12;

import java.awt.*;
import javax.swing.*;

public class ArticleTable extends TablePanel {

	static String[] columns = new String[] {"Title", "Lead author", "Reviews"};
	
	static Object[][] filter(Object[][] data) {
		Object[][] results = new Object[data.length][columns.length];
		for (int i=0; i < data.length; i++) {
			results[i][0] = data[i][1];
			results[i][1] = data[i][7];
			results[i][2] = data[i][5];
		}
		return results;
	}

	void update(Object[][] data) {
		super.update(filter(data), columns);
	}
		
	public ArticleTable(Object[][] data) {
		update(data);
	}
	
	public ArticleTable() {}
	
}
