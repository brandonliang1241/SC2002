package excel;

import java.util.Scanner;

public class StaffMenu implements MainUI {
    private Staff staff;
    private Scanner sc;
    private Database database;

    public StaffMenu(Staff staff, Database database) {
        this.staff = staff;
        this.sc = new Scanner(System.in);
        this.database = database;
    }

    @Override
    public void displayMenu() {
        System.out.println("Staff Interface - Choose an option:");
        System.out.println("1: Create camp");
        System.out.println("2: Edit camp");
        System.out.println("3: Delete camp");
        System.out.println("4: Change camp visibility");
        System.out.println("5: View all camps");
        System.out.println("6: View camps that I created");
        System.out.println("7: View Enquiries");
        System.out.println("8: Reply to Enquiries");
        System.out.println("9: View Suggestions");
        System.out.println("10: Approve/Reject a Suggestion");
        System.out.println("11: Generate camp report");
        System.out.println("12: Generate performance report of camp committee");
        System.out.println("13: Change Settings");
        System.out.println("14: Return");
    }

    @Override
    public int selectOption() {
        int choice = sc.nextInt();
        sc.nextLine();
        handleChoice(choice);
        return choice;
    }

    private void handleChoice(int choice) {
        switch (choice) {
        case 1:
            staff.createCamp(sc, database);
            break;
        case 2:
            staff.editCamp(sc);
            break;
        case 3:
            System.out.println("Enter camp name to delete: ");
            String newCamp = sc.nextLine();
            if(database.getCamp(newCamp) == null){System.out.println("No such camp"); break;}
            staff.deleteCamp(newCamp, database);
            break;
        case 4:
            System.out.println("Enter camp name to set visibility: ");
            newCamp = sc.nextLine();
            if(database.getCamp(newCamp) == null){System.out.println("No such camp"); break;}
            staff.toggleCampVisibility(sc, newCamp);
            break;
        case 5:
            database.viewCampList();
            break;
        case 6:
            staff.viewCampList();
            break;
        case 7:
            System.out.println("Enter camp name to view enquiries: ");
            newCamp = sc.nextLine();
            if(database.getCamp(newCamp) == null){System.out.println("No such camp"); break;}
            staff.viewEnquiries(newCamp);
            break;
        case 8:
            System.out.println("Enter camp name to reply to enquiry: ");
            newCamp = sc.nextLine();
            if(database.getCamp(newCamp) == null){System.out.println("No such camp"); break;}
            staff.replyEnquiries(sc, newCamp);
            break;
        case 9:
            System.out.println("Enter camp name to view suggestions: ");
            newCamp = sc.nextLine();
            if(database.getCamp(newCamp) == null){System.out.println("No such camp"); break;}
            staff.viewSuggestions(newCamp);
            break;
        case 10:
            System.out.println("Enter camp name: ");
            newCamp = sc.nextLine();
            if(database.getCamp(newCamp) == null){System.out.println("No such camp"); break;}
            staff.replySuggestions(sc, newCamp, database);
            break;
        case 11:
            System.out.println("Enter camp name to generate report: ");
            newCamp = sc.nextLine();
            if(database.getCamp(newCamp) == null){System.out.println("No such camp"); break;}
            System.out.println("How to print?");
        	System.out.println("1: Attendees only");
        	System.out.println("2: Camp committee only");
        	System.out.println("3: Both");
            int number = sc.nextInt();
            staff.generateCampReport(database.getCamp(newCamp), number);
            break;
        case 12:
            System.out.println("Enter camp name to generate report: ");
            newCamp = sc.nextLine();
            if(database.getCamp(newCamp) == null){System.out.println("No such camp"); break;}
            staff.generatePerformanceReport(database.getCamp(newCamp));
            break;
        case 13:
        	System.out.println("What do you want to change?");
        	System.out.println("1: Password");
        	System.out.println("2: StaffId");
        	System.out.println("3: Faculty");
        	int choice2 = sc.nextInt();
        	switch (choice2) {
        	case 1:
                System.out.println("Enter New Password");
                sc.nextLine();
                String newPassword = sc.nextLine();
                staff.setPassword(newPassword);
                System.out.println("Password successfully changed!");
                break;
        	case 2:
                System.out.println("Enter New StaffId");
                sc.nextLine();
                String newStaffId = sc.nextLine();
                staff.setUserId(newStaffId);
                System.out.println("StaffId successfully changed!");
                break;
        	case 3:
                System.out.println("Enter New Faculty");
                sc.nextLine();
                String newFacultyName = sc.nextLine().toUpperCase();
                try {
                    Faculty newFaculty = Faculty.valueOf(newFacultyName);
                    staff.setFacultyInfo(newFaculty);
                    System.out.println("Faculty successfully changed!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid faculty name.");
                }
        	}
        case 14:
            System.out.println("Exiting Staff Interface.");
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
            break;
        }
    }


	@Override
    public void exit() {
        System.out.println("Exiting Staff Interface.");
        sc.close();
    }

    // Other methods specific to StaffUI can be added here
}