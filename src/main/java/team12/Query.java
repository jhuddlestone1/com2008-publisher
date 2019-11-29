//import com.mysql.cj.jdbc.Driver;
package team12;
import java.sql.*;
import java.util.*;

public class Query {
    
	public static List<List<Object>> execute(String query, Object[] vars) {
        ResultSet rs = null;
        List<List<Object>> table = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://stusql.dcs.shef.ac.uk/team012",
            "team012",
            "8d470425"
        )) {
            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < vars.length ; i++) {
                ps.setObject(i+1, vars[i]); 
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
    
    public static Object[][] formTable(String query, Object[] vars){
        List<List<Object>> table = new ArrayList<>(Query.execute(query,vars));
        Object[][] result;
        if (!table.isEmpty()){
            result = new Object[table.size()][table.get(0).size()];
            for (int x=0; x<table.size(); x++){
                for (int y=0; y<table.get(0).size(); y++){
                result[x][y] = table.get(x).get(y);
                }
            }
        }
        else {
            result = new Object[0][0];
        }
        return result;
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