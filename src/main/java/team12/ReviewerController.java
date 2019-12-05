package team12;

public class ReviewerController {

    //get list of all submissions with less than 3 reviews 
    //return list in a 2d array
    //[[submissionID | title | abstract | pdfFilename | mainAuthorID]]
    public static Object[][] getSubmission(int reviewerID){
        String reviewerStatus = UserController.getUserStatus(reviewerID);
        String query = "SELECT * FROM Submission INNER JOIN UserDetails ON Submission.mainAuthorID = UserDetails.userID WHERE uniAffiliation!=? AND reviewNumber<3";
        Object[] vars = {reviewerStatus};
        Object[][] table = Query.formTable(query,vars);
        return table;
    }

    //add review to a submission
    public static void addReview(String title, String summary, String typoList, String[] criticisms, String iniVerdict, int submissionID, int reviewerID){
        //check if submission already has 3 reviews
        String queryS = "SELECT reviewNumber FROM Submission WHERE submissionID=?";
        Object[] varsS = {submissionID};  
        Object[][] reviews = Query.formTable(queryS,varsS);

        if ((Integer) reviews[0][0] < 3){
            //add review to table Review
            String query1 = "INSERT INTO Review(title,summary,typoList,iniVerdict,submissionID,reviewerID) VALUES(?,?,?,?,?,?)";
            Object[] vars1 = {title,summary,typoList,iniVerdict,submissionID,reviewerID};
            Query.execute(query1,vars1);

            //add criticism of a review to the table Criticism
            String queryR = "SELECT reviewID FROM Review WHERE summay=?";
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
        }
        else {
            System.out.println("Submission already has 3 reviews");
        }
    }

    //add final verdict(originally null) to a review
    public static void addFinalVerdict(String finVerdict, int reviewID){ 
        String query = "UPDATE Review SET finVerdict=? WHERE reviewID=?"; 
        Object[] vars = {finVerdict,reviewID}; 
        Query.execute(query, vars);
    }

} 