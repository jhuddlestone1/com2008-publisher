package team12;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Controller {

    //All users
    public static void addUser(String email, int password, String title, String forename, String surname, String uniAffiliation){
        String query = "INSERT INTO UserDetails(title,forename,surname,uniAffiliation,email) VALUES(?,?,?,?,?)";
        Object[] vars = {title,forename,surname,uniAffiliation,email};
        Query.execute(query,vars);
        String queryU = "SELECT userID FROM UserDetails WHERE email=?";
        Object[] varsU = {email};
        List<List<Object>> table = new ArrayList<>(Query.execute(queryU,varsU));
        int userID = (Integer) table.get(0).get(0);
        String query2 = "INSERT INTO UserLogin(email,password,userID) VALUES(?,?,?)";
        Object[] vars2 = {email, password, userID};
        Query.execute(query2, vars2);
    }

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

    public static Boolean validateUser(String email, int password){
        String query = "SELECT password FROM UserLogin WHERE email=?";
        Object[] vars = {email};
        List<List<Object>> result = new ArrayList<>(Query.execute(query,vars));      
        if (!result.isEmpty()){
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
       String query = "INSERT INTO Journal(ISSN, journalTitle, chiefEditorID) VALUES(?,?,?)";
       Object[] vars = {ISSN,journalTitle,chiefEditorID};
       Query.execute(query,vars);
    }

    public static Object[][] getJournals(int editorID){
        String query = "SELECT * FROM Journal j JOIN JournalEditors je on j.ISSN = je.ISSN WHERE editorID=?";
        Object[] vars = {editorID};
        List<List<Object>> table = new ArrayList<>(Query.execute(query,vars));
        Object[][] journals = new Object[table.size()][table.get(0).size()];
        for (int x=0; x<table.size(); x++){
            for (int y=0; y<table.get(0).size(); y++){
            journals[x][y] = table.get(x).get(y);
            }
        }
        return journals;
    }

    //date format as string "yyyy-MM-dd" e.g. "2019-11-28"
    public static void addVolume(String volume, String date, int ISSN) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = format.parse(date);
        String query = "INSERT INTO Volume(volume,date,ISSN) VALUES(?,?,?)";
        Object[] vars = {volume,date2,ISSN};
        Query.execute(query,vars);
    }

    public static Object[][] getVolumes(int ISSN){
        String query = "SELECT * FROM Volume WHERE ISSN=?";
        Object[] vars = {ISSN};
        List<List<Object>> table = new ArrayList<>(Query.execute(query,vars));
        Object[][] volumes = new Object[table.size()][table.get(0).size()];
        for (int x=0; x<table.size(); x++){
            for (int y=0; y<table.get(0).size(); y++){
            volumes[x][y] = table.get(x).get(y);
            }
        }
        return volumes;
    }

    //date format as string "yyyy-MM-dd" e.g. "2019-11-28"
    public static void addEdition(String edition, String date, int volumeID) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = format.parse(date);
        String query = "INSERT INTO Edition(edition,date,volumeID) VALUES(?,?,?)";
        Object[] vars = {edition,date2,volumeID};
        Query.execute(query,vars);
    }

    //Editors
    public static void addEditors(int ISSN, String[] emails){
        String query = "INSERT INTO JournalEditors(ISSN,editorID) SELECT ISSN,userID FROM Journal,UserDetails WHERE ISSN=? and email=?";
        for (String email : emails){ 
            Object[] vars = {ISSN, email};
            Query.execute(query,vars);
        }
    }

    public static Object[][] getEditors(int ISSN){
        String query = "SELECT userID FROM JournalEditors WHERE ISSN=?";
        Object[] vars = {ISSN};
        List<List<Object>> table = new ArrayList<>(Query.execute(query,vars));
        Object[][] editors = new Object[table.size()][table.get(0).size()];
        for (int x=0; x<table.size(); x++){
            for(int y=0; y<table.get(0).size(); y++){
                editors[x][y] = table.get(x).get(y);
            }
        }
        return editors;
    }
    
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

    public static String[] getSubmissions(int authorID){
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
        // Object[][] js = getJournals(5);
        // for (Object[] j : js){
        //     System.out.println(Arrays.toString(j));
        // }
    }
}