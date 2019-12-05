import java.sql.*;
import java.util.*;

public class FindDrivers{
    public static void main(String[] args) throws Exception {
        System.out.println("Drivers loaded as properties:");
        System.out.println(System.getProperty("jdbc.drivers"));
        System.out.println("Drivers loaded by DriverManager:");
        Enumeration<Driver> list = DriverManager.getDrivers();
        while (list.hasMoreElements())
            System.out.println(list.nextElement());
	}
}