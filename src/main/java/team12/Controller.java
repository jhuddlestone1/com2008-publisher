package team12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class Controller {

    //All users

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
    
    //Journals

    //add journal into table Journal
    public static void addJournal(int ISSN, String journalTitle, int chiefEditorID){
       String query = "INSERT INTO Journal(ISSN, journalTitle, chiefEditorID) VALUES(?,?,?)";
       Object[] vars = {ISSN,journalTitle,chiefEditorID};
       Query.execute(query,vars);
    }

    //return list of journals in a 2d array 
    //[[ISSN | journalTitle | chiefEditorID]]
    public static Object[][] getJournals(int editorID){
        String query = "SELECT * FROM Journal WHERE ISSN IN (SELECT ISSN FROM JournalEditors WHERE editorID=?)";
        Object[] vars = {editorID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    //add new volume to table Volume
    //date format as string "yyyy-MM-dd" e.g. "2019-11-28"
    //try-catch block needed for date formating
    public static void addVolume(String volume, String date, int ISSN){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
            Date date2 = format.parse(date);    
            String query = "INSERT INTO Volume(volume,date,ISSN) VALUES(?,?,?)";
            Object[] vars = {volume,date2,ISSN};
            Query.execute(query,vars);  
		} catch (ParseException e)  {
			e.printStackTrace();
		}
    }

    //return list of volumes in a 2d array
    //[[volumeID | ISSN | volume(e.g. Vol 1) | date]]
    public static Object[][] getVolumes(int ISSN){
        String query = "SELECT * FROM Volume WHERE ISSN=?";
        Object[] vars = {ISSN};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    //add new edition to table Edition
    //date format as string "yyyy-MM-dd" e.g. "2019-11-28"
    public static void addEdition(String edition, String date, int volumeID){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
            Date date2 = format.parse(date);           
            String query = "INSERT INTO Edition(edition,date,volumeID) VALUES(?,?,?)";
            Object[] vars = {edition,date2,volumeID};
            Query.execute(query,vars);  
		} catch (ParseException e)  {
			e.printStackTrace();
		}
    }

    //return list of volumes in a 2d array
    //[[editionID | volumeID | edition(e.g. No 1) | date ]]
    public static Object[][] getEditions(int volumeID){
        String query = "SELECT * FROM Edition WHERE volumeID=?";
        Object[] vars = {volumeID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    //Editors

    //add new editors to table Editors
    //same problem as addUser - two methods available
    public static void addEditors(int ISSN, String[] emails){
        String query = "INSERT INTO JournalEditors(ISSN,editorID) SELECT ISSN,userID FROM Journal,UserDetails WHERE ISSN=? and email=?";
        for (String email : emails){
            Object[] vars = {ISSN, email};
            Query.execute(query,vars);
        }
    }

    //return editors in a 2d array
    //[[userID | title | forename | surname | uniAffiliation | email | authEditor | authAuthor | authReviewer]]
    public static Object[][] getEditors(int ISSN){
        String query = "SELECT * FROM UserDetails WHERE userID IN (SELECT editorID FROM JournalEditors WHERE ISSN=?)";
        Object[] vars = {ISSN};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }
    
    //delete editor from table JournalEditors
    public static void deleteEditor(int userID){
        String query = "DELETE FROM JournalEditors WHERE editorID = ?";
        Object[] vars = {userID};
        Query.execute(query,vars);
    }

    public static Boolean deleteChiefEditor(int ISSN){
        String queryE = "SELECT editorID FROM JournalEditors WHERE ISSN =?";       
        Object[] varsE = {ISSN};
        List<List<Object>> editors = new ArrayList<>(Query.execute(queryE,varsE));
        if (editors.size()>1){
            deleteEditor((Integer) editors.get(0).get(0));
            String query = "UPDATE Journal SET chiefEditorID = ? WHERE ISSN = ?";
            Object[] vars = {editors.get(1).get(0),ISSN};
            Query.execute(query,vars);
            return true;
        }
        else {return false;}
    }

    //Authors
    public static void addSubmission(String title, String summary, String pdf, int mainAuthorID){
        String query = "INSERT INTO Submission(title,abstract,pdfFilename,mainAuthorID) VALUES(?,?,?,?)";
        Object[] vars = {title,summary,pdf,mainAuthorID};
        Query.execute(query,vars);
    }

    public static Object[][] getSubmissions(int authorID){
        String query = "SELECT * FROM Submission WHERE submissionID IN (SELECT submissionID FROM SubmissionAuthors WHERE authorID=?)";
        Object[] vars = {authorID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    public static void addAuthors(int submissionID, String[] emails){
        String query = "INSERT INTO SubmissionAuthors(submissionID,authorID) SELECT submissionID,userID FROM Submission,UserDetails WHERE submissionID=? and email=?";
        for (String email : emails){
            Object[] vars = {submissionID,email};
            Query.execute(query,vars);
        }
    }

    public static Object[][] getAuthors(int submissionID){
        String query = "SELECT * FROM UserDetails WHERE userID IN (SELECT authorID FROM SubmissionAuthors WHERE submissionID=?)";
        Object[] vars = {submissionID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    public static void main(String[] args){
        //String[] user = {"eskchieng1@sheffield.ac.uk"};
        addUser("dami@hotmail.com","1","ads","asdf","asdf","asd");
        // Object[][] es = getAuthors(1);
        // for (Object[] e : es){
        //     System.out.println(Arrays.toString(e));
        // }
    }
}