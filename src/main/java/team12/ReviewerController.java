package team12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class ReviewerController {

    public static void addReview(String title, String summary, String typoList, String iniVerdict, int submissionID, int reviewerID){
        String query = "INSERT INTO Review(title,summary,typoList,iniVerdict,submissionID,reviewerID) VALUES(?,?,?,?,?,?)";
        Object[] vars = {title,summary,typoList,iniVerdict,submissionID,reviewerID};
        Query.execute(query,vars);
    }

    public static void addFinalVerdict(String finVerdict, int reviewID){ 
        String query = "UPDATE Review SET finVerdict = ? WHERE reviewID = ?"; 
        Object[] vars = {finVerdict,reviewID}; 
        Query.execute(query, vars); 
    }

    public static Object[][] getReviews(int submissionID){ 
        String query = "SELECT * FROM Review WHERE submissionID = ?";
        Object[] vars = {submissionID}; 
        Object[][] result = Query.formTable(query,vars);
        return result;

    }
    
} 