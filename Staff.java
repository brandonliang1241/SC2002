package excel;

import java.util.*;

public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>(10);
    private int numOfCamps = 0;

    public Staff(String userId, String password, Faculty facultyInfo){
        super(userId, password, facultyInfo);
        this.campsCreated = new ArrayList<Camp>();
    }

    public void createCamp(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();
        Camp camp1 = new Camp(campName, super.getUserId());
        campsCreated.add(camp1);
        System.out.println("Camp created successfully!");
        numOfCamps++;
        campsCreated.add(camp1);
        Database.addCamp(camp1);
        sc.close();
    }


    public void viewCampList(){
        if (campsCreated.isEmpty()){
            System.out.println("No camps available");
        }
        for (int i = 0; i<numOfCamps; i++){
            System.out.println(campsCreated.get(i).getCampName() + " : " + (i+1));
        }
    }

    public void deleteCamp(String campName) {
        for (int i = 0; i < campsCreated.size(); i++) {
            if (campName.equals(campsCreated.get(i).getCampName())) {
                Database.removeCamp(campsCreated.get(i)); // Removing camp from list of all camps
                campsCreated.remove(i); // Removing camp from staff's list of camps
                numOfCamps--; // Decrease the count of camps
                break;
            }
        }
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
