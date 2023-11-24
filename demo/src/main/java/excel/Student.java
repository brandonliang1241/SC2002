package excel;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
    private CampCom campCom;
    public void viewCampList(){}
    
    public Student(String userId, String name, String password, Faculty facultyInfo){
        super(userId, name, password, facultyInfo);
        campCom = new CampCom();
    }

    public void viewEnquires(Scanner sc, Camp camp){
        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        int choice; int x = 0;
        System.out.println("//////////////////////////////////////////");
        for(int i = 0; i < camp.getEnquiries().size(); i++){
            if(camp.getEnquiries().get(i).getOwner().equals(super.getUserId())){
                x++;
                System.out.println(x + ": Enquiry " + x);
                tempArray.add(i);//added the locaton of enquiry to end of list
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
            System.out.println("1: Edit Enquiry");
            System.out.println("2: Delete Enquiry");
            System.out.println("3: Return");
            System.out.println("//////////////////////////////////////////");
            try{choice = Integer.parseInt(sc.nextLine());}
            catch(Exception e){
                System.err.println("Not a valid input");
                choice = 3;
            }
            if(choice == 1){
                System.out.println("Enter a new enquiry: ");
                String newEnquiry = sc.nextLine();
                camp.getEnquiries().get(x).setEnquiryText(newEnquiry);
                System.out.println("New enquiry set!");
            }
            else if(choice == 2){
                camp.getEnquiries().get(x).deleteEnquiry();
                camp.getEnquiries().remove(x);
                System.out.println("Bye bye Enquiry!");
            }
        }
    }

    public CampCom getCampCom(){
        return this.campCom;
    }

    public void campComInterface(Scanner sc,Camp camp, Student student){
        int choice;
        do{
            System.out.println("//////////////////////////////////////////");
            System.out.println("You are a member of this camp committee");
            System.out.println("1: View camp details");
            System.out.println("2: View enquires");
            System.out.println("3: Reply to enquiries");
            System.out.println("4: Manage Suggestions"); //view, edit, delete
            System.err.println("5: Return");
            System.out.println("//////////////////////////////////////////");
            try{choice = Integer.parseInt(sc.nextLine());}
            catch(Exception e){
                System.err.println("Not a valid input");
                choice = 0;
                continue;
            }
            switch(choice){
                case 1:
                    getCampCom().viewCampDetails(camp); break;
                case 2:
                    getCampCom().viewAllEnquiries(camp); break;
                case 3:
                    getCampCom().replyEnquiries(sc, camp); break;
                case 4:
                	manageSuggestions(sc, camp, student); break;
                default:
                    break;
            }
        }while(choice != 5);
    }
    
    private void manageSuggestions(Scanner sc, Camp camp, Student student) {
        System.out.println("Manage suggestions");
        System.out.println("1: Submit Suggestions");
        System.out.println("2: View Suggestions");
        System.out.println("3: Edit Suggestions");
        System.out.println("4: Delete Suggestions");
        System.out.println("5: Return");

        int choice2 = Integer.parseInt(sc.nextLine());
        switch(choice2) {
            case 1:
                campCom.submitSuggestions(sc, camp, student);
                break;
            case 2:
                campCom.viewSuggestions(camp);
                break;
            case 3:
                campCom.editSuggestion(sc, camp);
                break;
            case 4:
                campCom.deleteSuggestion(sc, camp);
                break;
            default:
            	break;
        }
    }

    public void informationInterface(Scanner sc, Database database){
        int choice;
        do{
        System.out.println("//////////////////////////////////////////");
        System.out.println("Information");
        System.out.println("1: Change password");
        System.out.println("2: View enquiries");
        System.out.println("3: View Suggestions");
        System.err.println("4: Return");
        System.out.println("//////////////////////////////////////////");
        try{choice = Integer.parseInt(sc.nextLine());}
            catch(Exception e){
                System.err.println("Not a valid input");
                choice = 0;
                continue;
            }
        switch (choice) {
            case 1:
                String password1, password2;
                do{
                    System.out.println("Enter new password:");
                    password1 = sc.nextLine();
                    System.out.println("Re-enter password:");
                    password2 = sc.nextLine();
                    if(!password1.equals(password2)){System.out.println("Passwords do not match!");}
                }while(!password1.equals(password2));
                Password encrypt = new Password();
                setPassword(encrypt.hash(password1.toCharArray()));
                System.out.println("New password set!");
                break;
            case 2:
            	viewAllEnquiries(database);
                break;
            case 3:
                if(getCampCom().getIsCampCom()){
                    for(int i = 0; i < database.getCamp(campCom.getCamp()).getEnquiries().size(); i++)
                    database.getCamp(campCom.getCamp()).getEnquiries().get(i).displayEnquiry();
                }
                else{
                    System.out.println("You are not a member of the camp committee");
                }
                break;
            default:
                break;
        }
        }while(choice != 4);
    }
    
    private void viewAllEnquiries(Database database) {
        boolean hasEnquiries = false;
        for (Camp camp : database.getCamps()) {
            for (Enquiry enquiry : camp.getEnquiries()) {
                if (enquiry.getOwner().equals(super.getUserId())) {
                    if (!hasEnquiries) {
                        System.out.println("Your enquiries in all camps:");
                        hasEnquiries = true;
                    }
                    System.out.println("Camp: " + camp.getCampName());
                    enquiry.displayEnquiry();
                }
            }
        }
        if (!hasEnquiries) {
            System.out.println("You have no enquiries in any camp.");
        }
    }
    
    public void submitCampComApplication(Student student, Camp camp){
        if(camp.applyForCampCom(this)){
            student.getCampCom().setIsCampCom(true);
            student.getCampCom().setCamp(camp.getCampName());
        	System.out.println("Congratulations you are now a camp committee of this camp!");
        }
    }
}
