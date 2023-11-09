package Java.SC2002FinalProj;
import java.util.*;
import java.util.Scanner;

public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>(10);

    public Staff(){
        super();
        this.campsCreated = new ArrayList<Camp>();
    }

    public void createCamp(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();
        Camp camp1 = new Camp(campName, this.getUserID());
        campsCreated.add(camp1);
        System.out.println("Camp created successfully!");
        sc.close();
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
