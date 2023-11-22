package excel;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Database database = new Database();
        Scanner sc = new Scanner(System.in); 
        //now to add into database
        Download(database);
        // Student brandon = new Student("BLIANG003", "Brandon", "password", Faculty.SCSE);
        // Staff david = new Staff("DAVID","David", "password", Faculty.LKC);
        // david.createCamp(sc,database);// database adds the camp that david creates.
        // database.getCamp("SCSE").setUserGroup(Faculty.SCSE);
        // database.getCamp("SCSE").setTotalSlots(100);
        // database.addStudent(brandon);
        // database.addStaff(david);
        //System.out.println(database.getCamp("SCSE").getStaffId()); //Finds SCSE camp and grabs the staff name
        int choice;
        do{
            System.out.println("//////////////////////////////////////////");
            System.out.println("Welcome! Please select your desired path."); 
            System.out.println("1: Login"); 
            System.out.println("2: Create new account"); 
            System.out.println("3: quit");
            System.out.println("//////////////////////////////////////////");
            choice = scan(sc);
            switch(choice){
                case 1:
                loginInterface(sc,database);
                break;
                case 2:
                newUserInterface(sc,database);
                break;
                default: 
                break;
            }
        }while(choice != 3);
        sc.close();
        storeWorkbook(database);
    }

    public static void loginInterface(Scanner sc, Database database){
        int choice;
        do{
            System.out.println("//////////////////////////////////////////");
            System.out.println("Login Page"); 
            System.out.println("1: Student Login"); 
            System.out.println("2: Staff Login"); 
            System.out.println("3: Return"); 
            System.out.println("//////////////////////////////////////////");
            choice = scan(sc);
            switch(choice){
                case 1: 
                System.out.println("Username:");
                String username = sc.nextLine();
                Student tempStudent = database.getStudent(username);
                if(tempStudent == null){
                    System.out.println("No user found.");
                    break;
                }
                System.out.println("Password:");
                String password = sc.nextLine();
                Password encrypt = new Password();
                if(encrypt.authenticate(password.toCharArray(), tempStudent.getPassword())){studentInterface(sc,database,tempStudent);}
                //if(tempStudent.getPassword().equals(password)){studentInterface(sc, database, tempStudent);}
                else{System.out.println("Student Login Failed!");}
                break;

                case 2:
                System.out.println("Username:");
                username = sc.nextLine();
                Staff tempStaff = database.getStaff(username);
                if(tempStaff == null){
                    System.out.println("No user found.");
                    break;
                }
                System.out.println("Password:");
                password = sc.nextLine();
                encrypt = new Password();
                if(encrypt.authenticate(password.toCharArray(), tempStaff.getPassword())){staffInterface(sc,database,tempStaff);}
                // if(tempStaff.getPassword().equals(password)){staffInterface(sc,database,tempStaff);}
                else{System.out.println("Staff Login Failed!");}
                break;

                case 3: break;
                default: System.out.println("Unknown Input"); 
                break;
            }
        }while(choice != 3);
    }

    public static void newUserInterface(Scanner sc, Database database){
        int choice;
        Password encrypt = new Password();
        do{
            System.out.println("//////////////////////////////////////////");
            System.out.println("Create New User Page"); 
            System.out.println("1: New Student"); 
            System.out.println("2: New Staff"); 
            System.out.println("3: Return"); 
            System.out.println("//////////////////////////////////////////");
            choice = scan(sc);
            switch(choice){
                case 1: 
                System.out.println("Name:");
                String name = sc.nextLine();
                System.out.println("Username:");
                String username = sc.nextLine();
                String password1, password2;
                do{
                    System.out.println("Enter new password:");
                    password1 = sc.nextLine();
                    System.out.println("Re-enter password:");
                    password2 = sc.nextLine();
                    if(!password1.equals(password2)){System.out.println("Passwords do not match!");}
                }while(!password1.equals(password2));
                System.out.println("Faculty:"); //Do another function for gods sake
                String faculty = sc.nextLine();
                Student tempStudent = new Student(username, name, encrypt.hash(password1.toCharArray()), Faculty.valueOf(faculty));
                database.addStudent(tempStudent);
                System.out.println("Student Creation Successful!");
                break;

                case 2:
                System.out.println("Name:");
                name = sc.nextLine();
                System.out.println("Username:");
                username = sc.nextLine();
                do{
                    System.out.println("Enter new password:");
                    password1 = sc.nextLine();
                    System.out.println("Re-enter password:");
                    password2 = sc.nextLine();
                    if(!password1.equals(password2)){System.out.println("Passwords do not match!");}
                }while(!password1.equals(password2));
                System.out.println("Faculty:"); //Do another function for gods sake
                faculty = sc.nextLine();
                Staff tempStaff = new Staff(username, name, encrypt.hash(password1.toCharArray()), Faculty.valueOf(faculty));
                database.addStaff(tempStaff);
                System.out.println("Staff Creation Successful!");
                break;
                case 3: break;
                default: System.out.println("Unknown Input"); 
                break;
            }
        }while(choice != 3);
    }

    public static void studentInterface(Scanner sc, Database database, Student student){
        int choice;
        do{
            System.out.println("//////////////////////////////////////////");
            System.out.println("1: See available list of camps"); 
            System.out.println("2: List of camps that you have joined"); 
            System.out.println("3: Student information"); 
            System.out.println("4: Return"); 
            System.out.println("//////////////////////////////////////////");
            choice = scan(sc);
            switch(choice){
                case 1:
                studentInterfaceCamp(sc, database, student, false); break;
                case 2:
                studentInterfaceCamp(sc, database, student, true); break;
                case 3:
                student.informationInterface(sc, database);
                default:
            }
        } while (choice != 4);
    }

    public static void studentInterfaceCamp(Scanner sc, Database database, Student student, Boolean join){
        int choice;
        ArrayList<Camp> tempCamp = new ArrayList<Camp>(10);
        System.out.println("//////////////////////////////////////////");
        if(join.equals(false)){
            //Need to create an array for each student that stores the names of the camps that they have joined.
            database.listOfCampsFaculty(tempCamp, student.getFacultyInfo());
            if(tempCamp.size() == 0){System.out.println("No available for your faculty camps"); return;}
            System.out.println("Here is the list of available camps for " + student.getFacultyInfo() + " students."); 
        }
        else{
            database.listOfCampsJoined(tempCamp, student);
            if(tempCamp.size() == 0){System.out.println("You have no joined camps"); 
            System.out.println("//////////////////////////////////////////");
            return;}
            System.out.println("Here is the list of joined camps."); 
        }

        for(int i = 0; i < tempCamp.size(); i++){
            System.out.println(i+1 + ": " + tempCamp.get(i).getCampName());
        }
        System.out.println("//////////////////////////////////////////");
        do{
            choice = scan(sc); //choice chooses the camp we would like to access.
            if(choice > tempCamp.size() || choice < 1){System.out.println("Not a valid option");}
        }while(choice > tempCamp.size() || choice < 1);
        studentInterfaceCampInterface(sc, tempCamp.get(choice-1), student, join);
        //once we leave this function returns to student interface
    }

    public static void studentInterfaceCampInterface(Scanner sc, Camp camp, Student student, Boolean join){
        //Specific to the selected camp
        int choice;
        do{
            camp.printCampDetails();
            if(join == true){System.out.println("You have joined this camp");} //or not we need to check with the student obj
            else{
                if(camp.findStudent(student)){System.out.println("You have joined this camp");}
                else{System.out.println("You have not joined this camp");}
            }
            //if student is camp com state if camp com. (can make camp com store the camp he is com of) 
            //print more choices if camp com
            if(join == true){System.out.println("1: Leave this camp");}
            else{System.out.println("1: Join this camp");}
            System.out.println("2: Manage enquiries"); // (have it be able to change own enquires)
            System.out.println("3: Camp Committee tasks");
            System.out.println("4: Return");
            System.out.println("//////////////////////////////////////////");
            choice = scan(sc);
            switch(choice){
                case 1:
                    if(join == false){
                        if(camp.getSlotsLeft() == 0){System.out.println("There are no more slots left in the camp"); break;}
                        if(camp.findRemovedStudent(student.getName())){System.out.println("You are already in this camp"); break;} //WTF
                        camp.addStudent(student);
                        System.out.println("You have joined the Camp!");
                        join = true;
                    }
                    else{
                        System.out.println("Do you want to leave this camp?");
                        System.out.println("You will be unable to rejoin this camp.");
                        System.out.println("1: Leave the camp");
                        System.out.println("2: Return");
                        choice = Integer.parseInt(sc.nextLine());
                        if(choice == 1){
                            camp.removeStudent(student);
                            System.out.println("You have left the Camp.");
                            join = false;
                        }
                    }
                    break;
                case 2:
                    student.viewEnquires(sc, camp);
                    break;
                case 3:
                    if(student.getCampCom().getIsCampCom()){student.campComInterface(sc, camp);}
                    else{
                        System.out.println("You are not a member of this camp committee");
                        System.out.println("Submit an application?");
                        System.out.println("1: Yes");
                        System.out.println("2: Return");
                        int temp = Integer.parseInt(sc.nextLine());
                        if(temp == 1){
                            student.getCampCom().setIsCampCom(true);
                            student.getCampCom().setCamp(camp.getCampName());
                            System.out.println("Congratulations you are now a camp committee of this camp!");
                        }
                    }
                    break;
                default:
            }
        }while(choice != 4);
    }

    public static void staffInterface(Scanner sc, Database database, Staff staff) {
        int choice;
        do {
            System.out.println("//////////////////////////////////////////");
            System.out.println("Staff Interface - Choose an option:");
            System.out.println("1: Create camp");
            System.out.println("2: Edit camp");
            System.out.println("3: Delete camp");
            System.out.println("4: Change camp visibility");
            System.out.println("5: View all camps");
            System.out.println("6: View your camps");
            System.out.println("7: View Enquiries");
            System.out.println("8: Reply to Enquiry");
            System.out.println("9: View Suggestions");
            System.out.println("10: Approve/Reject a Suggestion");
            System.out.println("11: Generate camp report");
            ///System.out.println("12: Generate performance report of camp committee");
            //System.out.println("13: Change Password");
            System.out.println("14: Quit");
            System.out.println("//////////////////////////////////////////");
            choice = scan(sc);

            switch (choice) {
                case 1:
                    staff.createCamp(sc, database);
                    break;
                case 2:
                    staff.editCamp(sc);
                    break;
                case 3:
                    System.out.println("Enter camp name to delete: ");
                    String campToBeDeleted = sc.nextLine();
                    staff.deleteCamp(campToBeDeleted,database);
                    break;
                case 4:
                    System.out.println("Enter camp name to set visibility: ");
                    String campToBeEdited = sc.nextLine();
                    staff.toggleCampVisibility(sc, campToBeEdited);
                    break;
                case 5:
                    staff.viewCampList();
                    break;
                case 6:
                    staff.viewCampList();
                    break;
                case 7:
                    System.out.println("Enter camp name to view enquiries: ");
                    String campToViewEnquiries = sc.nextLine();
                    staff.viewEnquiries(campToViewEnquiries);
                    break;
                case 8:
                    System.out.println("Enter camp name to reply to enquiry: ");
                    String campToReplyEnquiry = sc.nextLine();
                    staff.replyEnquiries(sc, campToReplyEnquiry);
                    break;
                case 9:
                    System.out.println("Enter camp name to view suggestions: ");
                    String campToViewSuggestions = sc.nextLine();
                    staff.viewSuggestions(campToViewSuggestions);
                    break;
                case 10:
                    System.out.println("Enter camp name: ");
                    String camp = sc.nextLine();
                    //staff.approveSuggestions(sc, camp);
                    break;
                case 11:    
                    System.out.println("Enter camp name to generate report: ");
                    String campNameForReport = sc.nextLine();
                    staff.generateCampReport(campNameForReport);
                    break;
                case 12:
                    staff.generatePerformanceReport();
                    break;
                case 13:
                    System.out.println("Enter New Password");
                    String newPassword = sc.nextLine();
                    staff.setPassword(newPassword);
                    break;
                case 14:
                    System.out.println("Exiting Staff Interface.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 14);
    }

    public static void Download(Database database){
        // detecting the file type
		GetStudent getContentFromExcelSheets
			= new GetStudent();
		List<Student> extractedStudentData
			= new ArrayList<Student>();
		try {
			extractedStudentData
				= getContentFromExcelSheets
					.readBooksFromExcelFileStudent(
						"/Users/brandon1241/Downloads/staff_list.xlsx");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < extractedStudentData.size(); i++){
            database.addStudent(extractedStudentData.get(i));
        }

        List<Staff> extractedStaffData
			= new ArrayList<Staff>();
		try {
			extractedStaffData
				= getContentFromExcelSheets
					.readBooksFromExcelFileStaff(
						"/Users/brandon1241/Downloads/staff_list.xlsx");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(int i = 0; i < extractedStaffData.size(); i++){
            database.addStaff(extractedStaffData.get(i));
        }
        
        List<Camp> extractedCampData
			= new ArrayList<Camp>();
		try {
			extractedCampData
				= getContentFromExcelSheets
					.readBooksFromExcelFileCamp(
						"/Users/brandon1241/Downloads/staff_list.xlsx");
		}//   /Users/brandon1241/Downloads/staff_list.xlsx
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(int i = 0; i < extractedCampData.size(); i++){
            database.addCamp(extractedCampData.get(i));
            // System.out.println(extractedCampData.get(i).getCampDate()+ " " + extractedCampData.get(i).getClosingTime());
            //System.out.println(database.getCamp(extractedCampData.get(i).getCampName()).getUserGroup());
        }
	}

    public static int scan(Scanner sc){ //catches exceptions
        try{return Integer.parseInt(sc.nextLine());}
        catch(Exception e){
            System.err.println("Not a valid input");
            return 0;
        }
    }

    public static void storeWorkbook(Database database){
        GetStudent storeContentToExcel = new GetStudent();
        try{storeContentToExcel.storeWorkbook(database);}
        catch(Exception e){e.printStackTrace();}
        System.out.println("Workbook Stored.");
    }
}
