//import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.*;

public class Query {
    
	public static ResultSet execute(String query, String[] vars) {
        ResultSet rs = null;
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://stusql.dcs.shef.ac.uk/team012",
            "team012",
            "8d470425"
        )) {
            //System.out.println("Hello");
            PreparedStatement ps = conn.prepareStatement(query);
            for (int i = 0; i < vars.length ; i++) {
                ps.setString(i+1, vars[i]);
            }
            rs = ps.executeQuery();
            ps.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    
    public static void main(String[] args) {
        String query = args[0];
        String[] vars = new String[args.length - 1];
        System.out.println(query);
        for (int a = 0; a < args.length - 1; a++) {
            vars[a] = args[a+1];
            System.out.println(vars[a]);
        }
        ResultSet result = execute(query, vars);
        System.out.println(result);
    }
}