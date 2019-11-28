package team12;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Controller {

    //All users
    public static Boolean validateEmail(String email){
        String query = "SELECT * FROM UserLogin WHERE email=?";
        Object[] vars = {email};      
        if (Query.execute(query, vars).isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public static Boolean validateUser(String email, int password){
        String query = "SELECT password FROM UserLogin WHERE email=?";
        Object[] vars = {email};
        List<List<Object>> result = new ArrayList<>(Query.execute(query, vars));      
        if ( !result.isEmpty() ){
            if ((Integer) result.get(0).get(0) == password) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    //Journals
    public static void addJournal(int ISSN, String journalTitle, int chiefEditorID){
       String query = "INSERT INTO Journal(ISSN,journalTitle,chiefEditorID) VALUES(?,?,?)";
       Object[] vars = {ISSN,  journalTitle, chiefEditorID};
       Query.execute(query,vars); 
    }

    public static Object[][] getJournals(int userID){
        String query = "SELECT * FROM Journal j JOIN JournalEditors je on j.ISSN = je.ISSN WHERE editorID =?";
        Object[] vars = {userID};
        List<List<Object>> table = new ArrayList<>(Query.execute(query,vars));
        int row = table.size();
        int col = table.get(0).size();
        Object[][] journals = new Object[row][col];
        for (int x=0;x<row;x++){
            for (int y=0;y<col;y++){
            journals[x][y] = table.get(x).get(y);
            }
        }
        return journals;
    }

    public static void addVolume(String volume, Date date, int ISSN){
        String query = "INSERT INTO Volume(volume,date,ISSN) VALUES(?,?,?)";
        Object[] vars = {volume,date,ISSN};
        Query.execute(query,vars);
    }

    //Editors
    public static void addEditors(int ISSN, String[] emails){
        String query = "INSERT INTO JournalEditors(ISSN,editorID) SELECT ISSN,userID FROM Journal,UserDetails WHERE ISSN = ? and email = ?";
        for (String email : emails){ 
            Object[] vars = {ISSN, email};
            Query.execute(query,vars);
        }
    }

    public static int[] getEditors(int ISSN){
        String query = "SELECT userID FROM JournalEditors WHERE ISSN = ?";
        Object[] vars = {ISSN};
        List<List<Object>> table = new ArrayList<>(Query.execute(query,vars));
        int row = table.size();
        int[] editors = new int[row];
        for (int x=0;x<row;x++){
            editors[x] = (Integer) table.get(x).get(0);
        }
        return editors;
    }
    
    public static void editorRetire(int userID){
        String query = "DELETE FROM JournalEditors WHERE editorID = ?";
        Object[] vars = {userID};
        Query.execute(query,vars);
    }

    public static Boolean chiefRetire(int ISSN){
        String queryE = "SELECT editorID FROM JournalEditors WHERE ISSN =?";       
        Object[] varsE = {ISSN};
        List<List<Object>> editors = new ArrayList<>(Query.execute(queryE,varsE));
        if (editors.size()>1){
            editorRetire((Integer) editors.get(0).get(0));
            String query = "UPDATE Journal SET chiefEditorID = ? WHERE ISSN = ?";
            Object[] vars = {editors.get(1).get(0),ISSN};
            Query.execute(query,vars);
            return true;
        }
        else {return false;}
    }

    //Authors
    public static void addArticle(String title, String summary, String pdf, int mainAuthorID){
        String query = "INSERT INTO Submission(title,abstract,pdfFilename,mainAuthorID) VALUES(?,?,?,?)";
        Object[] vars = {title,summary,pdf,mainAuthorID};
        Query.execute(query,vars);
    }

    public static String[] getArticles(int authorID){
        String query = "SELECT * FROM Submission s JOIN SubmissionAuthors sa ON s.submissionID = sa.submissionID WHERE authorID =?";
        Object[] vars = {authorID};
        List<List<Object>> table = new ArrayList<>(Query.execute(query,vars));
        int row = table.size();
        String[] articles = new String[row];
        for (int x=0;x<row;x++){
            articles[x] = (String) table.get(x).get(0);
        }
        return articles;
    }

    public static void addAuthors(int submissionID, String[] emails){
        String query = "INSERT INTO SubmissionAuthors(submissionID,authorID) SELECT submissionID,userID FROM Submission,UserDetails WHERE submissionID = ? and email =?";
        for (String email : emails){
            Object[] vars = {submissionID,email};
            Query.execute(query,vars);
        }
    }

    public static void main(String[] args){
        // String[] emails = {"1234","dami"};
        // addEditors(2008, emails);
        //chiefRetire(2008);
        // Object[][] js = getJournals(5);
        // for (Object[] j : js){
        //     System.out.println(Arrays.toString(j));
        // }
        //Date date = format.parse("11-28-2019");
        addVolume("Vol 1", "11-28-2019", 2008);
    }
}