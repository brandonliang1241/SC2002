package excel;

import java.util.*;

public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>(10);
    private int numOfCamps = 0;
    private List<Enquiry> enquiries = new ArrayList<>();

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

    public void editCamp() {
        Scanner sc = new Scanner(System.in);
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
                System.out.println("Enter new camp date: ");
                selectedCamp.setCampDate(sc.nextLine());
                break;
            case 3:
                System.out.println("Enter new closing time: ");
                selectedCamp.setClosingTime(sc.nextLine());
                break;
            case 4:
                System.out.println("Enter new user group: ");
                selectedCamp.setUserGroup(sc.nextLine());
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
        sc.close();
    } //can edit campName, campDate, closingTime, userGroup, location, totalSlots, campComSlots, description
    
    public void toggleCampVisibility(String campName) {
        if (campsCreated == null || campsCreated.isEmpty()) {
            System.out.println("No camps available to toggle visibility.");
            return;
        }

        for (Camp camp : campsCreated) {
            if (campName.equals(camp.getCampName())) {
                camp.toggleVisibility(); // Assuming Camp class has a toggleVisibility method
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
        sc.nextLine(); // Consume the leftover newline
        String reply = sc.nextLine();

        System.out.println("Your reply: " + reply);
        selectedEnquiry.setStatus("Resolved"); // Update status or handle as needed

        System.out.println("Reply sent and enquiry status updated.");
    }
    public void viewSuggestions(){} //from camp committee
    public void approveSuggestions(){}
    public void generateList(){} //generate list of students
    
    public void viewStudentsInCamp(int camp){
        campsCreated.get(camp).viewListStudents();
    }
}
