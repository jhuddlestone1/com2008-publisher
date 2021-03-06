package team12;

public class ReviewerController {

    //get list of all submissions with less than 3 reviews 
    //return list in a 2d array
    //[[submissionID | title | abstract | pdfFile | reviewNumber | isApproved | mainAuthorID | ISSN | title | forename | surname | uniAffiliation | email]]
    public static Object[][] getSubmissions(int reviewerID){
        String reviewerStatus = UserController.getUserStatus(reviewerID);
        String query = "SELECT * FROM Submission INNER JOIN UserDetails ON Submission.mainAuthorID = UserDetails.userID INNER JOIN Journal ON Submission.ISSN = Journal.ISSN WHERE uniAffiliation!=? AND reviewNumber < 3 AND Submission.submissionID NOT IN (SELECT submissionID FROM SubmissionAuthors WHERE SubmissionAuthors.authorID = ?) AND Submission.submissionID NOT IN (SELECT submissionID FROM Review WHERE Review.reviewerID = ?)";
        Object[] vars = {reviewerStatus, reviewerID, reviewerID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }
    
    public static int getReviewsNumber(int reviewerID){
        String query = "SELECT * FROM Review WHERE reviewerID=?";
        Object[] vars = {reviewerID};
        Object[][] result = Query.formTable(query,vars);
        return result.length;
    }
    
    public static Object[][] getRepliedReviews(int reviewerID){
        String query = "SELECT * FROM Submission INNER JOIN UserDetails ON Submission.mainAuthorID = UserDetails.userID INNER JOIN Journal ON Submission.ISSN = Journal.ISSN INNER JOIN Review ON Submission.submissionID = Review.submissionID WHERE reviewID IN (SELECT reviewID FROM Criticism WHERE answer IS NOT NULL) AND reviewerID = ? AND finVerdict IS NULL";
        Object[] vars = {reviewerID};
        Object[][] result = Query.formTable(query,vars);
        return result;
    }

    //add review to a submission
    public static boolean addReview(String summary, String typoList, String[] criticisms, String iniVerdict, int submissionID, int reviewerID){
        //check if submission already has 3 reviews
        String queryS = "SELECT reviewNumber FROM Submission WHERE submissionID=?";
        Object[] varsS = {submissionID};  
        int reviews = (Integer) Query.formTable(queryS,varsS)[0][0];

        if (reviews < 3){
            //add review to table Review
            String query1 = "INSERT INTO Review(summary,typoList,iniVerdict,submissionID,reviewerID) VALUES(?,?,?,?,?)";
            Object[] vars1 = {summary,typoList,iniVerdict,submissionID,reviewerID};
            Query.execute(query1,vars1);

            //add criticism of a review to the table Criticism
            String queryR = "SELECT reviewID FROM Review WHERE summary=?";
            Object[] varsR = {summary};
            int reviewID = (Integer) Query.formTable(queryR,varsR)[0][0];
            String query2 = "INSERT INTO Criticism(criticism,reviewID) VALUES(?,?)";
            for (String criticism : criticisms){
                Object[] vars2 = {criticism,reviewID};
                Query.execute(query2,vars2);
            }
            
            //update reviewNumber(originally 0) in Submission
            String query3 = "UPDATE Submission SET reviewNumber = reviewNumber + 1 WHERE submissionID=?";
            Object[] vars3 = {submissionID};
            Query.execute(query3,vars3);
            return true;
        }
        else {
            System.out.println("Submission already has 3 reviews");
            return false;
        }
    }

    //add final verdict(originally null) to a review
    public static void addFinalVerdict(String finVerdict, int reviewID){ 
        String query = "UPDATE Review SET finVerdict=? WHERE reviewID=?"; 
        Object[] vars = {finVerdict,reviewID}; 
        Query.execute(query, vars);
    }

    public static void main(String[] args){
        //getSubmission return values changed -- listed on top of function
        //Review tables "title" column removed
        //addReview parameters changed -- title(?) removed
    }
} 