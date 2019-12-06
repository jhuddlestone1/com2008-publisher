package team12;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;

public class UserController {

    //Hashing the password methods
	public static String getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		StringBuilder result = new StringBuilder();
		for(int i=0; i< salt.length; i++){
			result.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
		}
		return result.toString();
	}

	public static String getSecurePassword(String passwordToHash, String salt){
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++){
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	} 
    //add new user into database in tables UserLogin and UserDetails
    public static void addUser(String email, String password, String title, String forename, String surname, String uniAffiliation){
        //insert user's personal information into table UserDetails
        String query1 = "INSERT INTO UserDetails(title,forename,surname,uniAffiliation,email) VALUES(?,?,?,?,?)";
        Object[] vars1 = {title,forename,surname,uniAffiliation,email};
        Query.execute(query1,vars1); //execute this first to get the auto incremented userID, to be put into UserLogin

        //insert user's login details into table UserLogin
        String salt = getSalt();
        String hashedPassword = getSecurePassword(password,salt);
        System.out.println(salt);
        System.out.println(hashedPassword);
        String query2 = "INSERT INTO UserLogin(email,password,salt,userID) VALUES(?,?,?,(SELECT userID FROM UserDetails WHERE email=?))";
        Object[] vars2 = {email,hashedPassword,salt,email};
        Query.execute(query2, vars2);
    }


    //check if email exists - return "invalid email"
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
        System.out.println("Work!");
        String query = "SELECT password,salt FROM UserLogin WHERE email=?";
        Object[] vars = {email};
        String hashedPassword = (String) Query.formTable(query,vars)[0][0];
        String salt = (String) Query.formTable(query,vars)[0][1];
        System.out.println(salt);
        System.out.println(hashedPassword);
        if (validateEmail(email)){
            if (hashedPassword.equals(getSecurePassword(password,salt))) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            System.out.println("invalid email");
            return false;
        }
    }

    //change user's password
    //input (email, old password, new password)
    public static void updatePassword(String email, String oldPassword, String newPassword){
        if (validateUser(email,oldPassword)==true){
            String salt = getSalt();
            String hashedPassword = getSecurePassword(newPassword,salt);            
            String query = "UPDATE UserLogin SET password=?, salt=? WHERE email=?";
            Object[] vars = {hashedPassword, salt, email};
            Query.execute(query,vars);
        }
    }
    
    //return userID with given email
    //to be store as global variable - userID
    //userID needed to get list of journals/submissions/review when logged in as different role
    public static int getUserID(String email){
        String query = "SELECT userID FROM UserLogin WHERE email=?";
        Object[] vars = {email};
        int userID = (Integer) Query.formTable(query,vars)[0][0];
        return userID;
    }
    
    //return userID with given email
    //to be store as global variable - userID
    //userID needed to get list of journals/submissions/review when logged in as different role
    public static Object[] getUserDetails(int userID){
        String query = "SELECT title, forename, surname, uniAffiliation, email FROM UserDetails WHERE userID=?";
        Object[] vars = {userID};
        Object[] userDetails = Query.formTable(query,vars)[0];
        return userDetails;
    }

    //return uni affiliation of user to avoid conflict of interest
    public static String getUserStatus(int userID){
        String query = "SELECT uniAffiliation FROM UserDetails WHERE userID=?";
        Object[] vars = {userID};
        String result = (String) Query.formTable(query,vars)[0][0];
        return result;
    }

    //delete user from tables UserLogin and UserDetails
    public static void deleteUser(String email){
        String query = "DELETE UserLogin.*,UserDetails.* FROM UserLogin INNER JOIN UserDetails ON UserLogin.email = UserDetails.email WHERE UserLogin.email=?";
        Object[] vars = {email};
        Query.execute(query,vars);
    }

    //journal title provided by user for searching
    public static Object[] getJournals(String title){
        String query = "SELECT * FROM Journal WHERE journalTitle=?";
        Object[] vars = {title};
        Object[] result = Query.formTable(query,vars)[0];
        return result;
    }

    public static Object[][] getPublishedArticles(String title){
        String query = "SELECT * FROM Submission INNER JOIN Article ON Submission.submissionID = Article.submissionID WHERE Submission.isApproved=1 AND Submission.title LIKE ?";
        Object[] vars = {title};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }
    
    public static void main(String[]args){

        // addUser("eddie@gmail.com","0711","Mr","Eddie","Chieng","Uni of Sheffield");
        // addUser("dami@gmail.com","dami","Ms","Dami","Adeleye","University of Adeleye");
        // addUser("jamie@gmail.com","jamie1","Mr","Jamie","Huddlestone","Uni_of_Manchester");
        // addUser("aleksandra@gmail.com","aleksandra-2","Ms","Aleksandra","Kulbaka","Uni of Leeds");
        // addUser("random1@gmail.com","r.u","Dr","Random","User","Uni-of-Liverpool");
         validateUser("random1@gmail.com","r.u");

        //uniAffiliation not standardised
    } 
}
