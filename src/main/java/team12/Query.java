//import com.mysql.cj.jdbc.Driver;
//package team12;
import java.sql.*;
import java.util.*;

public class Query {
    
	public static void execute(String query, String[] vars) {
        ResultSet rs = null;
        int count;
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://stusql.dcs.shef.ac.uk/team012",
            "team012",
            "8d470425"
        )) {
            //System.out.println(conn);
            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < vars.length ; i++) {
                ps.setString(i+1, vars[i]);
            }
            if (ps.execute()){
                rs = ps.getResultSet();               
                ResultSetMetaData rsmt = rs.getMetaData();
                int col = rsmt.getColumnCount();
                while (rs.next()){
                    for (int x=1; x<=col ; x++){
                        System.out.print(rs.getObject(x) + " | ");
                    }
                    System.out.println();
                }
            }
            else {
                count = ps.getUpdateCount();
                System.out.println(count);
            };
            ps.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        String query = args[0];
        String[] vars = new String[args.length - 1];
        for (int a = 0; a < args.length - 1; a++) {
            vars[a] = args[a+1];
        }
        Query.execute(query, vars);
    }
}