package excel;

import java.util.*;
import java.time.*;

public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>(10);
    private List<Enquiry> enquiries = new ArrayList<>();

    public Staff(String userId, String password, Faculty facultyInfo){
        super(userId, password, facultyInfo);
        this.campsCreated = new ArrayList<Camp>();
    }

    public void createCamp(Scanner sc){
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();
        Camp camp1 = new Camp(campName, super.getUserId());
        campsCreated.add(camp1);
        System.out.println("Camp created successfully!");
        Database.addCamp(camp1);
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
        Iterator<Camp> iterator = campsCreated.iterator();
        while (iterator.hasNext()) {
            Camp camp = iterator.next();
            if (campName.equals(camp.getCampName())) {
                Database.removeCamp(camp);
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
        //sc.nextLine(); // Consume the leftover newline

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
                //sc.nextLine(); // Consume the leftover newline
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
    
    public void toggleCampVisibility(String campName) {
        for (Camp camp : campsCreated) {
            if (campName.equals(camp.getCampName())) {
                camp.toggleVisibility(true);
                System.out.println("Visibility toggled for camp: " + campName);
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
    
    public void replyEnquiries(String campName) {
        Scanner sc = new Scanner(System.in);

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
    
    public void approveSuggestions(String campName, int suggestionIndex) {
        for (Camp camp : campsCreated) {
            if (camp.getCampName().equals(campName)) {
                ArrayList<Suggestion> suggestions = camp.getSuggestions();
                if (suggestionIndex < 0 || suggestionIndex >= suggestions.size()) {
                    System.out.println("Invalid suggestion index.");
                    return;
                }

                Suggestion suggestion = suggestions.get(suggestionIndex);
                suggestion.setStatus(true);
                System.out.println("Suggestion approved: " + suggestion.getSuggestionText());
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
