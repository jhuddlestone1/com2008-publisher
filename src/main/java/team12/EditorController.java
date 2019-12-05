package team12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditorController {

    //add new journal to database in table Journal
    public static void addJournal(int ISSN, String journalTitle, int chiefEditorID){
        String queryU = "SELECT * FROM Journal WHERE journalTitle=?";
        Object[] varsU = {journalTitle};
        if (Query.execute(queryU,varsU).isEmpty()){
            //add journal to table Journal
            String query1 = "INSERT INTO Journal(ISSN,journalTitle,chiefEditorID) VALUES(?,?,?)";
            Object[] vars1 = {ISSN,journalTitle,chiefEditorID};
            Query.execute(query1,vars1);
            //add chiefEditor to table JournalEditors
            String query2 = "INSERT INTO JournalEditors(ISSN,editorID) VALUES(?,?)";
            Object[] vars2 = {ISSN,chiefEditorID};
            Query.execute(query2,vars2);
        }
        else {
            System.out.println("Journal name already exists");
        }
    }
 
    //display journals an editor has access to
    //[[ ISSN | journalTitle | chiefEditorID]]
    public static Object[][] getJournals(int editorID){
        String query = "SELECT * FROM Journal WHERE ISSN IN (SELECT ISSN FROM JournalEditors WHERE editorID=?)";
        Object[] vars = {editorID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    public static int getJournals(String journal){
        String query = "SELECT ISSN FROM Journal WHERE journalTitle =?";
        Object[] vars = {journal};
        int result = (Integer)(Query.formTable(query,vars)[0][0]); 
        return result;
    }

    //enable editors to add volume to a journal
    //date format as string "yyyy-MM-dd" e.g. "2019-11-28"
    //try-catch block needed for date formating
    //input volume as integer e.g. 1 and will be formatted to "vol.1"
    public static void addVolume(int volume, String date, int ISSN){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date2 = format.parse(date);    
            String query = "INSERT INTO Volume(volume,date,ISSN) VALUES(?,?,?)";
            String volume2 = "vol. " + Integer.toString(volume);
            Object[] vars = {volume2,date2,ISSN};
            Query.execute(query,vars);
        } catch (ParseException e)  {
            e.printStackTrace();
        }
    }

    //display list of volumes of a journal
    //[[volumeID | ISSN | volume(e.g. "vol.1") | date(e.g. "2019-11-28")]]
    public static Object[][] getVolumes(int ISSN){
        String query = "SELECT * FROM Volume WHERE ISSN=?";
        Object[] vars = {ISSN};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    //enable an editor to add new edition to a volume
    //date format as string "yyyy-MM-dd" e.g. "2019-11-28"
    //try-catch block needed for date formating
    //String edition in the format of ordinal number e.g. "1st" and will be formatted to "vol.X, no.Y"
    public static void addEdition(String edition, String date, int volumeID){
        //get volume(vol.X) from volumeID to be concatenated with input edition(no.Y)
        String queryV = "SELECT volume FROM Volume WHERE volumeID=?";
        Object[] varsV = {volumeID};
        String volume = (String) Query.formTable(queryV,varsV)[0][0];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date2 = format.parse(date);           
            String query = "INSERT INTO Edition(edition,date,volumeID) VALUES(?,?,?)";
            String edition2 = volume + ", no. " + edition;
            Object[] vars = {edition2,date2,volumeID};
            Query.execute(query,vars);
        } catch (ParseException e)  {
            e.printStackTrace();
        }
    }

    //display list of editions of a volume
    //[[editionID | volumeID | edition(e.g. "vol. X, no. 1") | date ]]
    public static Object[][] getEditions(int volumeID){
        String query = "SELECT * FROM Edition WHERE volumeID=?";
        Object[] vars = {volumeID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    //add an array of new editors to a journal in the table JournalEditors
    public static void addEditors(int ISSN, String[] emails){
        String query = "INSERT INTO JournalEditors(ISSN,editorID) SELECT ISSN,userID FROM Journal,UserDetails WHERE ISSN=? and email=?";
        for (String email : emails){
            Object[] vars = {ISSN, email};
            Query.execute(query,vars);
        }
    }

    //return list of editors of a journal in 2d array
    //[[userID | title | forename | surname | uniAffiliation | email | authEditor | authAuthor | authReviewer]]
    public static Object[][] getEditors(int ISSN){
        String query = "SELECT * FROM UserDetails WHERE userID IN (SELECT editorID FROM JournalEditors WHERE ISSN=?)";
        Object[] vars = {ISSN};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }
    
    //retire editor from a journal
    //does not remove editor as an user 
    public static void deleteEditor(int userID){
        String query = "DELETE FROM JournalEditors WHERE editorID=?";
        Object[] vars = {userID};
        Query.execute(query,vars);
    }

    //retire chiefEditor from a journal
    public static Boolean deleteChiefEditor(int ISSN){
        //check if more than one editors operating the journal
        String queryE = "SELECT editorID FROM JournalEditors WHERE ISSN=?";       
        Object[] varsE = {ISSN};
        Object[][] editors = Query.formTable(queryE,varsE);
        if (editors.length>1){
            //remove chiefEditor from the journal
            deleteEditor((Integer) editors[0][0]);
            //apppoint second editor in database to be new chief editor
            String query = "UPDATE Journal SET chiefEditorID = ? WHERE ISSN = ?";
            Object[] vars = {editors[1][0],ISSN};
            Query.execute(query,vars);
            return true;
        }
        else {return false;}
    }

    //transfer the chief editor role to another editor operating the journal
    public static void transferChief(int ISSN, int editorID){
        //check if editor is on the board of editor of the journal
        String query1 = "SELECT * FROM JournalEditors WHERE ISSN=? AND editorID=?";
        Object[] vars1 = {ISSN,editorID};
        if (!Query.execute(query1,vars1).isEmpty()){
            String query2 = "UPDATE Journal SET chiefEditorID=? WHERE ISSN=?";
            Object[] vars2 = {editorID,ISSN};
            Query.execute(query2,vars2);
        }
        else {
            System.out.println("Editor don't have authorisation to this journal");
        }
    }
    public static void main(String[]args) {}
}