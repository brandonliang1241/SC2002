package excel;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>();

    public Staff(String userId, String name, String password, Faculty facultyInfo){
        super(userId, name, password, facultyInfo);
        this.campsCreated = new ArrayList<Camp>();
    }

    public Camp createCamp(Scanner sc, Database database) {
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();

        // Check if a camp with the same name already exists
        for (Camp existingCamp : campsCreated) {
            if (existingCamp.getCampName().equalsIgnoreCase(campName)) {
                System.out.println("A camp with this name already exists. Please choose a different name.");
                return null; // Return null to indicate that no new camp was created
            }
        }

        Camp newCamp = new Camp(campName, super.getUserId());
        campsCreated.add(newCamp);
        System.out.println("Camp created successfully!");
        database.addCamp(newCamp);
        return newCamp;
    }


    public void viewCampList(){
        if (campsCreated.isEmpty()){
            System.out.println("No camps available");
            return;
        }
    	System.out.println("All camps: ");
        for (int i = 0; i<campsCreated.size(); i++){
            System.out.println((i+1) + ": " + campsCreated.get(i).getCampName());
        }
    }

    public void deleteCamp(String campName, Database database) {
        Iterator<Camp> iterator = campsCreated.iterator();
        while (iterator.hasNext()) {
            Camp camp = iterator.next();
            if (campName.equals(camp.getCampName())) {
				database.removeCamp(camp);
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
    
     //created new method to display enquiry based on campname
    private ArrayList<Enquiry> filterAndDisplayEnquiries(String campName) {
        for (Camp camp : campsCreated) {
            if (camp.getCampName().equals(campName)) {
                ArrayList<Enquiry> enquiries = camp.getEnquiries();
                ArrayList<Enquiry> filteredEnquiries = new ArrayList<>();
                
                for (Enquiry enquiry : enquiries) {
                    if (enquiry.getCampName().equals(campName)) {
                        filteredEnquiries.add(enquiry);
                    }
                }

                if (filteredEnquiries.isEmpty()) {
                    System.out.println("No enquiries available for camp: " + campName);
                } else {
                	System.out.println("Enquiries for camp: " + campName);
                	for (Enquiry enquiry : filteredEnquiries) {
                		enquiry.displayEnquiry();
                	}
                }
                return filteredEnquiries;
            }
        }
        System.out.println("Camp not found: " + campName);
        return new ArrayList<>(); //Return empty list if camp not found
    }
    
    public void viewEnquiries(String campName) {
        filterAndDisplayEnquiries(campName);
    }

    public void replyEnquiries(Scanner sc, String campName) {
        ArrayList<Enquiry> filteredEnquiries = filterAndDisplayEnquiries(campName);

        if (filteredEnquiries.isEmpty()) {
            return;
        }

        System.out.println("Enter the number of the enquiry you wish to reply to:");
        int enquiryNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (enquiryNumber < 1 || enquiryNumber > filteredEnquiries.size()) {
            System.out.println("Invalid enquiry number.");
            return;
        }

        Enquiry selectedEnquiry = filteredEnquiries.get(enquiryNumber - 1);

        System.out.println("Enter your reply:");
        String reply = sc.nextLine();

        selectedEnquiry.setReplyText(reply);
        selectedEnquiry.setStatus(true); // Set the status as replied

        System.out.println("Reply sent successfully.");
        System.out.println("Updated Enquiry:");
        selectedEnquiry.displayEnquiry();
    }
    
    //created new method to display suggestion based on campname
    private ArrayList<Suggestion> filterAndDisplaySuggestions(String campName) {
        for (Camp camp : campsCreated) {
            if (camp.getCampName().equals(campName)) {
                ArrayList<Suggestion> suggestions = camp.getSuggestions();
                ArrayList<Suggestion> filteredSuggestions = new ArrayList<>();

                for (Suggestion suggestion : suggestions) {
                    if (suggestion.getCampName().equals(campName)) {
                        filteredSuggestions.add(suggestion);
                    }
                }

                if (filteredSuggestions.isEmpty()) {
                    System.out.println("No suggestions available for this camp.");
                } else {
                    System.out.println("Suggestions for camp: " + campName);
                    for (Suggestion suggestion : filteredSuggestions) {
                        suggestion.displaySuggestion();
                    }
                }
                return filteredSuggestions;
            }
        }
        System.out.println("Camp not found: " + campName);
        return new ArrayList<>(); // Return empty list if camp not found
    }

    public void viewSuggestions(String campName) {
        filterAndDisplaySuggestions(campName);
    }

    public void replySuggestions(Scanner sc, String campName) {
        ArrayList<Suggestion> filteredSuggestions = filterAndDisplaySuggestions(campName);
        
        if (filteredSuggestions.isEmpty()) {
            return;
        }

        System.out.println("Enter the index of the suggestion to reply:");
        int suggestionNumber = sc.nextInt() - 1;
        sc.nextLine();

        if (suggestionNumber < 0 || suggestionNumber >= filteredSuggestions.size()) {
            System.out.println("Invalid suggestion index.");
            return;
        }

        System.out.println("Do you want to approve or reject the suggestion?");
        System.out.println("1: Approve");
        System.out.println("2: Reject");
        int choice = sc.nextInt();
        sc.nextLine();

        Suggestion suggestion = filteredSuggestions.get(suggestionNumber);

        if (choice == 1) {
            suggestion.setStatus(true);
        } else if (choice == 2) {
            suggestion.setStatus(false);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        // Display the updated suggestion
        System.out.println("Updated Suggestion:");
        suggestion.displaySuggestion();
    }

    public void generateCampReport(String campName){} //generate list of students (attendees/campcom)

    public void generatePerformanceReport(){} //generate performance report of campcom members

    
    public void viewStudentsInCamp(int camp){
        campsCreated.get(camp).viewListStudents();
    }
}
