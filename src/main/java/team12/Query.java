//import com.mysql.cj.jdbc.Driver;
//package team12;
import java.sql.*;
import java.util.*;

public class Query {
    
	public static List<List<Object>> execute(String query, String[] vars) {
        ResultSet rs = null;
        List<List<Object>> table = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://stusql.dcs.shef.ac.uk/team012",
            "team012",
            "8d470425"
        )) {
            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < vars.length ; i++) {
                ps.setString(i+1, vars[i]); 
            }
            if (ps.execute()){
                rs = ps.getResultSet(); 

                ResultSetMetaData rsmt = rs.getMetaData();
                int col = rsmt.getColumnCount();
                int row = 0;
                while (rs.next()){
                    table.add(new ArrayList<>());
                    for (int x=1; x<=col ; x++){
                        table.get(row).add(rs.getObject(x));
                    }
                    row+=1;
                }
            }
            else {
                int count = ps.getUpdateCount();
                table.add(new ArrayList<>());
                table.get(0).add(count);
            };
            ps.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return table;
    }
    
    public static void main(String[] args) {
        String query = args[0];
        String[] vars = new String[args.length - 1];
        for (int a = 0; a < args.length - 1; a++) {
            vars[a] = args[a+1];
        }
        List<List<Object>> table = execute(query, vars);
        for (List<Object> row : table) {
            System.out.println(row);
        }
    }
}