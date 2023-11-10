package excel;

public class Student extends User implements CampCom{
    private boolean isCampCom;
    public void viewCampList(){}
    
    public Student(String userId, String password, Faculty facultyInfo){
        super(userId, password, facultyInfo);
    }
    //all below use if isCamCom is True.
    public void viewCampDetails(){}
    public void submitSuggestions(){}
    public void viewEnquiries(){}
    public void replyEnquiries(){}
    public void addPoints(){}
}
