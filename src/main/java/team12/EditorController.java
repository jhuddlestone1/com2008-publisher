package team12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class EditorController {

    //journal

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

    public static void main(String[]args) {}
}