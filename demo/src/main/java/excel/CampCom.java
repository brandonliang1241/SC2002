package excel;

import java.util.ArrayList;
import java.util.Scanner;

public class CampCom {
    private int points = 0;
    private boolean isCampCom = false;
    private String camp;

    public CampCom(){
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean getIsCampCom() {
        return this.isCampCom;
    }

    public void setIsCampCom(boolean isCampCom) {
        this.isCampCom = isCampCom;
    }

    public String getCamp() {
        return this.camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public void viewCampDetails(Camp camp){
        if (camp == null) {
            System.out.println("No camp details available.");
            return;
        }
        camp.printCampDetails();
    }
    
    public void viewAllEnquiries(Camp camp){
        if (camp == null) {
            System.out.println("No camp details available.");
            return;
        }

        ArrayList<Enquiry> enquiries = camp.getEnquiries();
        if (enquiries.isEmpty()) {
            System.out.println("No enquiries available for camp: " + camp.getCampName());
        } else {
            System.out.println("Enquiries for camp: " + camp.getCampName());
            for (Enquiry enquiry : enquiries) {
                enquiry.displayEnquiry();
            }
        }
    }
    
    public void replyEnquiries(Scanner sc, Camp camp){
        if (camp == null) {
            System.out.println("No camp details available.");
            return;
        }

        ArrayList<Enquiry> enquiries = camp.getEnquiries();
        if (enquiries.isEmpty()) {
            System.out.println("No enquiries available for camp: " + camp.getCampName());
            return;
        }

        System.out.println("Enquiries for camp: " + camp.getCampName());
        for (int i = 0; i < enquiries.size(); i++) {
            enquiries.get(i).displayEnquiry();
        }

        System.out.println("Enter the number of the enquiry you wish to reply to:");
        int enquiryNumber;
        try {
            enquiryNumber = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (enquiryNumber < 1 || enquiryNumber > enquiries.size()) {
            System.out.println("Invalid enquiry number.");
            return;
        }

        Enquiry selectedEnquiry = enquiries.get(enquiryNumber - 1);

        System.out.println("Enter your reply:");
        String reply = sc.nextLine();

        selectedEnquiry.setReplyText(reply);
        selectedEnquiry.setStatus(true); // Set the status as replied
        this.points++;

        System.out.println("Reply sent successfully.");
        System.out.println("Updated Enquiry:");
        selectedEnquiry.displayEnquiry();
    }
    
    public void submitSuggestions(Camp camp){};

    public void addPoints(Scanner sc){
        int choice;
        System.out.println("How many points to add?");
        choice = Integer.parseInt(sc.nextLine());
        points += choice;
    };

}
