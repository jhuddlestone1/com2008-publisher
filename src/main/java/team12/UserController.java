package team12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class UserController {

      //insert new user information into tables UserLogin and UserDetails
        public static void addUser(String email, String password, String title, String forename, String surname, String uniAffiliation){
        //insert into table UserDetails
        String query = "SELECT * FROM UserDetails WHERE email=?";
        Object[] vars = {email};
        if (Query.execute(query,vars).isEmpty()){
            String query1 = "INSERT INTO UserDetails(title,forename,surname,uniAffiliation,email) VALUES(?,?,?,?,?)";
            Object[] vars1 = {title,forename,surname,uniAffiliation,email};
            Query.execute(query1,vars1); //execute this first to get the auto incremented userID, to be put into UserLogin

            String query2 = "INSERT INTO UserLogin(email,password,userID) VALUES(?,?,(SELECT userID FROM UserDetails WHERE email=?))";
            Object[] vars2 = {email,password,email};
            Query.execute(query2, vars2);
        }
        else {System.out.println("user existed");}
    }

     //change user password in tatble UserLogin
    //input email and new password
    public static void updatePassword(String email, String password){
        String query = "UPDATE UserLogin SET password=? WHERE email=?";
        Object[] vars = {password, email};
        Query.execute(query,vars);
    }

    //check if email exist -  return "invalid email if false"
    public static Boolean validateEmail(String email){
        String query = "SELECT * FROM UserLogin WHERE email=?";
        Object[] vars = {email};      
        if (Query.execute(query,vars).isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    //check if email and password is valid - return "incorrect password"
    public static Boolean validateUser(String email, String password){
        String query = "SELECT password FROM UserLogin WHERE email=?";
        Object[] vars = {email};
        Object[][] result = Query.formTable(query,vars);
        if (result.length != 0 ){
            if ( ((String)result[0][0]).equals(password)) {
                return true;
            }
            else {
                System.out.println("incorrect password");
                return false;
            }
        }
        else {
            System.out.println("invalid email");
            return false;
        }
    }

    //authorise user
    //input: (email, authEditor, authAuthor, authReviewer)
    public static void authUser(String email, boolean authEditor, boolean authAuthor, boolean authReviewer){
        String query = "UPDATE UserDetails SET authEditor=?,authAuthor=?,authReviewer=? WHERE email=?";
        Object[] vars = {authEditor,authAuthor,authReviewer,email};
        Query.execute(query,vars);
    }

    //return user authorisation
    //output: [authEditor, authAuthor, authReviewer]
    public static boolean[] userAuth (String email){
        String query = "SELECT authEditor,authAuthor,authReviewer FROM UserDetails WHERE email=?";
        Object[] vars = {email};
        List<List<Object>> table = new ArrayList<>(Query.execute(query,vars));
        boolean[] result = new boolean[3];
        for (int x=0; x<3; x++){
            result[x] = (boolean) table.get(0).get(x);
        }
        return result;
    }

    //delete user from tables UserLogin and UserDetails
    public static void deleteUser(String email){
        String query = "DELETE UserLogin.*,UserDetails.* FROM UserLogin INNER JOIN UserDetails ON UserLogin.email = UserDetails.email WHERE UserLogin.email=?";
        Object[] vars = {email};
        Query.execute(query,vars);
    }

    public static void main(String[]args) {}
} 