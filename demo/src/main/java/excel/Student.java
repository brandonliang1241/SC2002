package excel;

import java.io.FileWriter;
import java.io.IOException;
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
            System.err.println("5: Print camp details");
            System.err.println("6: Return");
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
                case 5:
                    System.out.println("How to print?");
                    System.out.println("1: Attendees only");
                    System.out.println("2: Camp committee only");
                    System.out.println("3: Both");
                    int number;
                    try{number = Integer.parseInt(sc.nextLine());}
                    catch(Exception e){
                    System.err.println("Not a valid input");
                    number = 0;
                    continue;
                    }
                    generateCampReport(camp, number);
                    break;
                default:
                    break;
            }
        }while(choice != 6);
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
        }
    }

    public void generateCampReport(Camp camp, int number){
        try {
        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("Camp name: " + camp.getCampName());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Location: " + camp.getLocation());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Total Slots: " + camp.getTotalSlots());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Slots remaining: " + camp.getSlotsLeft());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Camp Committee Slots: " + camp.getCampComSlots());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Date: " + camp.getCampDate());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Closing Date time: " + camp.getClosingTime());myWriter.write(System.getProperty( "line.separator" ));
        myWriter.write("Visibility: " + camp.isVisible());myWriter.write(System.getProperty( "line.separator" ));
        switch(number){
            case 3:
                for(int i = 0; i < camp.getListStudents().size(); i++){
                    Student tempStudent = camp.getListStudents().get(i);
                    if(tempStudent.getCampCom().getIsCampCom()){
                        myWriter.write(tempStudent.getName() + " is a camp committee member");myWriter.write(System.getProperty( "line.separator" ));
                    }
                    else{
                        myWriter.write(tempStudent.getName() + " is a participant");myWriter.write(System.getProperty( "line.separator" ));
                    }
                }
                break;
            case 2:
                for(int i = 0; i < camp.getListStudents().size(); i++){
                    Student tempStudent = camp.getListStudents().get(i);
                    if(tempStudent.getCampCom().getIsCampCom()){
                        myWriter.write(tempStudent.getName() + " is a camp committee member");myWriter.write(System.getProperty( "line.separator" ));
                    }
                }
                break;
            case 1:
                for(int i = 0; i < camp.getListStudents().size(); i++){
                    myWriter.write(camp.getListStudents().get(i).getName() + " is a participant");myWriter.write(System.getProperty( "line.separator" ));
                }
                break;
            default:
                System.out.println("Invalid choice.");
                myWriter.close();
                return;
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    } //generate list of students (attendees/campcom)
}
