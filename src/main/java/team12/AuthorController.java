package team12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class AuthorController {

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

    public static void addAnswer(String answer, int criticismID){
        String query = "UPDATE Criticism SET answer=? WHERE criticismID=?";
        Object[] vars = {answer,criticismID};
        Query.execute(query,vars);
    }

    public static void main(String[]args) {

    }
}