package Java.SC2002FinalProj;
import java.util.*;

public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>(10);

    public Staff(){
        super();
    }

    public void viewCampList(){
    }

    public void deleteCamp(){}
    public void editCamp(){}
    public void toggleCampVisibility(){}
    public void viewInquiries(){}
    public void replyInquiries(){}
    public void viewSuggestions(){} //from camp committee
    public void approveSuggestions(){}
    public void generateList(){} //generate list of students
    
    public void viewStudentsInCamp(int camp){
        campsCreated.get(camp).viewListStudents();
    }
}
