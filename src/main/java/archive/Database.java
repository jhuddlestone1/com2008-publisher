//import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.*;

public class Database {

	public static int update(String query, String[] vars) {
		int rs = 0;
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
			rs = ps.executeUpdate();
			ps.close();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static ResultSet query(String query, String[] vars) {
		ResultSet rs = null;
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
			//System.out.println(vars[a]);
		}
		ResultSet result = execute(query, vars);
		//System.out.println(result);
	}
}