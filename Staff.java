package excel;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>(10);
    private List<Enquiry> enquiries = new ArrayList<>();

    public Staff(String userId, String password, Faculty facultyInfo){
        super(userId, password, facultyInfo);
        this.campsCreated = new ArrayList<Camp>();
    }

    public Camp createCamp(Scanner sc, Database database){
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();
        Camp camp1 = new Camp(campName, super.getUserId());
        campsCreated.add(camp1);
        System.out.println("Camp created successfully!");
        database.addCamp(camp1);
        return camp1;
    }


    public void viewCampList(){
        if (campsCreated.isEmpty()){
            System.out.println("No camps available");
        }
        for (int i = 0; i<campsCreated.size(); i++){
            System.out.println(campsCreated.get(i).getCampName() + " : " + (i+1));
        }
    }

    public void deleteCamp(String campName) {
        Iterator<Camp> iterator = campsCreated.iterator();
        while (iterator.hasNext()) {
            Camp camp = iterator.next();
            if (campName.equals(camp.getCampName())) {
				//Database.removeCamp(camp);
                iterator.remove();
                System.out.println("Camp deleted: " + campName);
                return;
            }
        }
        System.out.println("Camp not found: " + campName);
    }

    public void editCamp(Scanner sc) {
        System.out.println("Enter the name of the camp you want to edit: ");
        String campName = sc.nextLine();

        // Find the camp
        Camp selectedCamp = null;
        for (Camp camp : campsCreated) {
            if (camp.getCampName().equals(campName)) {
                selectedCamp = camp;
                break;
            }
        }

        if (selectedCamp == null) {
            System.out.println("Camp not found.");
            return;
        }

        // Edit options
        System.out.println("Select what you want to edit: ");
        System.out.println("1. Camp Name\n2. Camp Date\n3. Closing Time\n4. User Group\n5. Location\n6. Total Slots\n7. Camp Committee Slots\n8. Description");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline

        switch (choice) {
            case 1:
                System.out.println("Enter new camp name: ");
                selectedCamp.setCampName(sc.nextLine());
                break;
            case 2:
                System.out.println("Enter new camp date (yyyy-MM-dd): ");
                String dateStr = sc.nextLine();
                try {
                    LocalDate newDate = LocalDate.parse(dateStr);
                    selectedCamp.setCampDate(newDate);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format.");
                }
                break;
            case 3:
                System.out.println("Enter new closing time (yyyy-MM-ddTHH:mm): ");
                String timeStr = sc.nextLine();
                try {
                    LocalDateTime newClosingTime = LocalDateTime.parse(timeStr);
                    selectedCamp.setClosingTime(newClosingTime);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date and time format.");
                }
                break;
            case 4:
                System.out.println("Enter new user group (e.g., SCSE, LKC): ");
                String facultyName = sc.nextLine().toUpperCase();
                try {
                    Faculty newFaculty = Faculty.valueOf(facultyName);
                    selectedCamp.setUserGroup(newFaculty);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid faculty name.");
                }
            break;
            case 5:
                System.out.println("Enter new location: ");
                selectedCamp.setLocation(sc.nextLine());
                break;
            case 6:
                System.out.println("Enter new total slots: ");
                selectedCamp.setTotalSlots(sc.nextInt());
                sc.nextLine(); // Consume the leftover newline
                break;
            case 7:
                System.out.println("Enter new camp committee slots: ");
                selectedCamp.setCampComSlots(sc.nextInt());
                //sc.nextLine(); // Consume the leftover newline
                break;
            case 8:
                System.out.println("Enter new description: ");
                selectedCamp.setDescription(sc.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Camp updated successfully!");
    } //can edit campName, campDate, closingTime, userGroup, location, totalSlots, campComSlots, description
    
    public void toggleCampVisibility(Scanner sc, String campName) {
        for (Camp camp : campsCreated) {
            if (campName.equals(camp.getCampName())) {
                System.out.println("Do you want to toggle visibility on/off?");
                System.out.println("1: On");
                System.out.println("2: Off");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume the leftover newline

                boolean visibility;
                if (choice == 1) visibility = true;
                else visibility = false;      // If choice is 1, visibility is true (On), otherwise false (Off)

                camp.toggleVisibility(visibility);
                String visibilityStatus = visibility ? "on" : "off";
                System.out.println("Visibility toggled " + visibilityStatus + " for " + campName);
                return;
            }
        }
        System.out.println("Camp not found: " + campName);
    }
    
    public void viewEnquiries(String campName) {
        boolean found = false;
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getCampName().equals(campName)) {
                enquiry.displayEnquiry();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No enquiries found for camp: " + campName);
        }
    }
    
    public void replyEnquiries(Scanner sc, String campName) {
        // Display enquiries for the specified camp
        this.viewEnquiries(campName);
        // Filter enquiries for the specified camp
        List<Enquiry> filteredEnquiries = new ArrayList<>();
        for (Enquiry enquiry : enquiries) {
            if (enquiry.getCampName().equals(campName)) {
                filteredEnquiries.add(enquiry);
            }
        }

        if (filteredEnquiries.isEmpty()) {
            return; // No enquiries to reply to
        }

        System.out.println("Enter the number of the enquiry you wish to reply to: ");
        int enquiryNumber = sc.nextInt();

        if (enquiryNumber < 1 || enquiryNumber > filteredEnquiries.size()) {
            System.out.println("Invalid enquiry number.");
            return;
        }

        Enquiry selectedEnquiry = filteredEnquiries.get(enquiryNumber - 1);

        System.out.println("Enter your reply: ");
        //sc.nextLine(); // Consume the leftover newline
        String reply = sc.nextLine();

        System.out.println("Your reply: " + reply);
        selectedEnquiry.setStatus("Resolved"); // Update status or handle as needed

        System.out.println("Reply sent and enquiry status updated.");
    }
    
    public void viewSuggestions(String campName) {
        for (Camp camp : campsCreated) {
            if (camp.getCampName().equals(campName)) {
                ArrayList<Suggestion> suggestions = camp.getSuggestions();
                if (suggestions.isEmpty()) {
                    System.out.println("No suggestions for this camp.");
                } else {
                    for (Suggestion suggestion : suggestions) {
                        System.out.println(suggestion);
                    }
                }
                return;
            }
        }
        System.out.println("Camp not found: " + campName);
    }//from camp committee
    
    public void replySuggestions(Scanner sc, String campName) {
        for (Camp camp : campsCreated) {
            if (camp.getCampName().equals(campName)) {
                ArrayList<Suggestion> suggestions = camp.getSuggestions(); //create array of suggestions

                if (suggestions.isEmpty()) {
                    System.out.println("No suggestions available for this camp.");
                    return;
                }

                // Display all suggestions for the camp
                for (int i = 0; i < suggestions.size(); i++) {
                    System.out.print((i + 1) + ": ");
                    suggestions.get(i).displaySuggestion();
                }

                System.out.println("Enter the index of the suggestion to reply:");
                int arrayIndex = sc.nextInt() - 1; // Subtract 1 to convert to 0-based index
                sc.nextLine(); // Consume the leftover newline

                if (arrayIndex < 0 || arrayIndex >= suggestions.size()) {
                    System.out.println("Invalid suggestion index.");
                    return;
                }

                System.out.println("Do you want to approve or reject the suggestion?");
                System.out.println("1: Approve");
                System.out.println("2: Reject");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume the leftover newline

                Suggestion suggestion = suggestions.get(arrayIndex); //get array index of suggestions

                if (choice == 1) {
                    suggestion.setStatus(true);
                    suggestion.toString();
                } else if (choice == 2) {
                    suggestion.setStatus(false);
                    suggestion.toString();
                } else {
                    System.out.println("Invalid choice.");
                }
                return;
            }
        }
        System.out.println("Camp not found: " + campName);
    }

    public void generateCampReport(String campName){} //generate list of students (attendees/campcom)

    public void generatePerformanceReport(){} //generate performance report of campcom members

    
    public void viewStudentsInCamp(int camp){
        campsCreated.get(camp).viewListStudents();
    }
}
