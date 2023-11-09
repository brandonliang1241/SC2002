package Java.SC2002FinalProj;
import java.util.*;
import java.util.Scanner;

public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>(10);
    private int numOfCamps = 0;

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
        numOfCamps++;
        sc.close();
    }


    public void viewCampList(){
        for (int i = 0; i<numOfCamps; i++){
            System.out.println(campsCreated[i].getCampName() + " : " + (i+1));
    }

    public void deleteCamp(String campName){
        
    }
    public void editCamp(){} //can edit campName, campDate, closingTime, userGroup, location, totalSlots, campComSlots, description
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
