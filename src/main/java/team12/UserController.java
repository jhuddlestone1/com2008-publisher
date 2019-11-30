package team12;

public class UserController {

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
        String query = "SELECT password FROM UserLogin WHERE email=?";
        Object[] vars = {email};
        String password2 = (String) Query.formTable(query,vars)[0][0];
        if (validateEmail(email)){
            if (password2.equals(password)) {
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

    //add new user into database in tables UserLogin and UserDetails
    public static void addUser(String email, String password, String title, String forename, String surname, String uniAffiliation){
        //insert user's personal information into table UserDetails
        String query1 = "INSERT INTO UserDetails(title,forename,surname,uniAffiliation,email) VALUES(?,?,?,?,?)";
        Object[] vars1 = {title,forename,surname,uniAffiliation,email};
        Query.execute(query1,vars1); //execute this first to get the auto incremented userID, to be put into UserLogin

        //insert user's login details into table UserLogin
        String query2 = "INSERT INTO UserLogin(email,password,userID) VALUES(?,?,(SELECT userID FROM UserDetails WHERE email=?))";
        Object[] vars2 = {email,password,email};
        Query.execute(query2, vars2);
    }

    //change user's password
    //input (email,new password)
    public static void updatePassword(String email, String password){
        String query = "UPDATE UserLogin SET password=? WHERE email=?";
        Object[] vars = {password, email};
        Query.execute(query,vars);
    }
    
    //return userID with given email
    //to be store as global variable - userID
    //userID needed to get list of journals/submissions/review when logged in as different role
    public static int getUserID(String email){
        String query = "SELECT userID FROM UserDetails WHERE email=?";
        Object[] vars = {email};
        int userID = (Integer) Query.formTable(query,vars)[0][0];
        return userID;
    }

    //change user authorisation with different roles
    //assuming user already exists
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
        Object[][] table = Query.formTable(query,vars);
        boolean[] result = new boolean[3];
        for (int x=0; x<3; x++){
            result[x] = (boolean) table[0][x];
        }
        return result;
    }

    //delete user from tables UserLogin and UserDetails
    public static void deleteUser(String email){
        String query = "DELETE UserLogin.*,UserDetails.* FROM UserLogin INNER JOIN UserDetails ON UserLogin.email = UserDetails.email WHERE UserLogin.email=?";
        Object[] vars = {email};
        Query.execute(query,vars);
    }

    public static void main(String[]args){
    }
} 