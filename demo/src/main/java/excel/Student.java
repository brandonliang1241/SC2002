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
            System.out.println("1: Change Enquiry");
            System.out.println("2: Delet Enquiry");
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

    public void campComInterface(Scanner sc,Camp camp){
        int choice;
        do{
            System.out.println("//////////////////////////////////////////");
            System.out.println("You are a member of this camp committee");
            System.out.println("1: View camp details");
            System.out.println("2: View enquires");
            System.out.println("3: View Suggestions");
            System.err.println("4: Return");
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
                    getCampCom().replyEnquiries(camp); break;
                case 3:
                    getCampCom().submitSuggestions(camp); break;
                default:
                    break;
            }
        }while(choice != 4);
    }
}
