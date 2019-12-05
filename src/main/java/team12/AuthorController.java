package team12;

public class AuthorController {


    //add new submission to database
    public static void addSubmission(String title, String summary, String pdfFilename, int mainAuthorID){
        String query = "INSERT INTO Submission(title,abstract,pdfFilename,mainAuthorID) VALUES(?,?,?,?)";
        Object[] vars = {title,summary,pdfFilename,mainAuthorID};
        Query.execute(query,vars);
    }

    //return only the list of submission that an author has access to
    //[[submissionID | title | abstract | pdfFilename | mainAuthorID]]
    //submissionID obtained can be used in getReview(int submissionID)
    public static Object[][] getSubmissions(int authorID){
        String query = "SELECT * FROM Submission WHERE submissionID IN (SELECT submissionID FROM SubmissionAuthors WHERE authorID=?)";
        Object[] vars = {authorID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    //add array of coauthors to a submission in the table SubmissionAuthors
    public static void addAuthors(int submissionID, String[] emails){
        String query = "INSERT INTO SubmissionAuthors(submissionID,authorID) SELECT submissionID,userID FROM Submission,UserDetails WHERE submissionID=? and email=?";
        for (String email : emails){
            Object[] vars = {submissionID,email};
            Query.execute(query,vars);
        }
    }

    //return list of coauthors of a submission in 2d array
    //[[userID | title | forename | surname | uniAffiliation | email | authEditor | authAuthor | authReviewer]]
    public static Object[][] getAuthors(int submissionID){
        String query = "SELECT * FROM UserDetails WHERE userID IN (SELECT authorID FROM SubmissionAuthors WHERE submissionID=?)";
        Object[] vars = {submissionID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    //allow author to answer a specific criticsm of a review
    public static void addAnswer(String answer, int criticismID){
        String query = "UPDATE Criticism SET answer=? WHERE criticismID=?";
        Object[] vars = {answer,criticismID};
        Query.execute(query,vars);
    }
    
    //get reviews of a submission
    public static Object[][] getReviews(int submissionID){ 
        String query = "SELECT * FROM Review WHERE submissionID = ?";
        Object[] vars = {submissionID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }
    
    public static void main(String[]args) {

    }
}