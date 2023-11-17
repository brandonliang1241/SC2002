package excel;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements CampCom{
    private boolean isCampCom;
    //private ArrayList<Enquiry> listOfEnquiries = new ArrayList<Enquiry>(10);
    public void viewCampList(){}
    
    public Student(String userId, String password, Faculty facultyInfo){
        super(userId, password, facultyInfo);
    }

    public void viewEnquires(Scanner sc, Camp camp){
        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        int choice; int x = 0;
        System.out.println("//////////////////////////////////////////");
        for(int i = 0; i < camp.getEnquiries().size(); i++){
            if(camp.getEnquiries().get(i).getOwner().equals(super.getUserId())){
                x++;
                System.out.println(x + ": Enquiry " + x);
                tempArray.add(camp.getEnquiries().get(i).getEnquiryIndex());//added the locaton of enquiry to end of list
            }
        }
        System.out.println(++x + ": Add a new enquiry");
        System.out.println(++x + ": Return"); 
        System.out.println("//////////////////////////////////////////");
        choice = Integer.parseInt(sc.nextLine()); //havent debugged if i didnt put proper input
        if(choice == x){return;}
        else if(choice == x-1){ //adding enquiry
            System.out.println("Enter new enquiry: ");
            String newString = sc.nextLine();
            Enquiry newEnquiry = new Enquiry(newString, super.getUserId(), camp.getCampName());
            camp.getEnquiries().add(newEnquiry);
            System.out.println("New enquiry set!");
        }
        else{ //chosen inquiry and we want to view it
            x = tempArray.get(choice-1); //storing the location of enquiry
            System.out.println("//////////////////////////////////////////");
            camp.getEnquiries().get(x).displayEnquiry();
            System.out.println("1: Change Enquiry");
            System.out.println("2: Delet Enquiry");
            System.out.println("3: Return");
            System.out.println("//////////////////////////////////////////");
            choice = Integer.parseInt(sc.nextLine());
            if(choice == 1){
                System.out.println("Enter a new enquiry: ");
                String newEnquiry = sc.nextLine();
                camp.getEnquiries().get(x).setEnquiryText(newEnquiry);
                System.out.println("New enquiry set!");
            }
            else if(choice == 2){
                camp.getEnquiries().remove(x);
                System.out.println("Bye bye Enquiry!");
            }
        }
    }

    //all below use if isCamCom is True.
    public void viewCampDetails(){}
    public void submitSuggestions(){}
    public void viewAllEnquiries(){}
    public void replyEnquiries(){}
    public void addPoints(){}
}
