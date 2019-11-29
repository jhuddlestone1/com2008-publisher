package team12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class ReviewerController {

    public static void addReview(String title, String summary, String typoList, String verdict, int submissionID, int reviewerID){
        String query = "INSERT INTO Review(title,summary,typoList,verdict,submissionID,reviewerID) VALUES(?,?,?,?,?)";
        Object[] vars = {title,summay,typoList,verdict,submissionID,reviewerID};
        Query.execute(query,vars);
    }

} 