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
            System.out.println("No camp found.");
            return;
        }
        camp.printCampDetails();
    }
    
    public void viewAllEnquiries(Camp camp){
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
    
    public void submitSuggestions(Scanner sc, Camp camp, Student student){
        System.out.println("Enter your suggestion for the camp:");
        String suggestionText = sc.nextLine();

        Suggestion newSuggestion = new Suggestion(suggestionText, student.getName(), camp.getCampName());
        camp.addSuggestion(newSuggestion);

        System.out.println("Suggestion submitted successfully for camp " + camp.getCampName() + ".");
    }

    public void viewSuggestions(Camp camp){
        ArrayList<Suggestion> suggestions = camp.getSuggestions();
        if (suggestions.isEmpty()) {
            System.out.println("No suggestions available for camp: " + camp.getCampName());
            return;
        }

        System.out.println("Suggestions for camp: " + camp.getCampName());
        for (Suggestion suggestion : suggestions) {
            suggestion.displaySuggestion();
        }
    }
    
    public void editSuggestion(Scanner sc, Camp camp) {
        ArrayList<Suggestion> suggestions = camp.getSuggestions();
        if (suggestions.isEmpty()) {
            System.out.println("No suggestions available for editing.");
            return;
        }

        System.out.println("Enter suggestion index to edit:");
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println((i + 1) + ": " + suggestions.get(i).getSuggestionText());
        }

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > suggestions.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Suggestion suggestion = suggestions.get(choice - 1);
        System.out.println("Enter the new text for the suggestion:");
        String newText = sc.nextLine();
        suggestion.setSuggestionText(newText);
        System.out.println("Suggestion updated successfully.");
    }
    
    public void deleteSuggestion(Scanner sc, Camp camp) {
        ArrayList<Suggestion> suggestions = camp.getSuggestions();
        if (suggestions.isEmpty()) {
            System.out.println("No suggestions available for deletion.");
            return;
        }

        System.out.println("Enter suggestion index to delete:");
        for (int i = 0; i < suggestions.size(); i++) {
            System.out.println((i + 1) + ": " + suggestions.get(i).getSuggestionText());
        }

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > suggestions.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        suggestions.remove(choice - 1);
        System.out.println("Suggestion deleted successfully.");
    }
    
    public void generateCampReport(Camp campName) {}
    
    public void addPoints(Scanner sc){
        int choice;
        System.out.println("How many points to add?");
        choice = Integer.parseInt(sc.nextLine());
        points += choice;
    };

}
