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
        String pathName = "/Users/Wei Jun/Downloads/staff_list.xlsx";
        Download(database, pathName);
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
        storeWorkbook(database, pathName);
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
                if(encrypt.authenticate(password.toCharArray(), tempStaff.getPassword())){staffInterface(tempStaff, database);}
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
    
    public static void studentInterface(Scanner sc, Database database, Student student) {
        StudentMenu studentMenu = new StudentMenu(student, database);
        studentMenu.displayMenu();
        while (studentMenu.selectOption() != 4) { 
            studentMenu.displayMenu();
        }
    }
    
    public static void staffInterface(Staff staff, Database database){
        StaffMenu staffMenu = new StaffMenu(staff, database);
        staffMenu.displayMenu();
        while(staffMenu.selectOption() != 14){ 
            staffMenu.displayMenu();
        }
    }

    public static void Download(Database database, String pathName){
        // detecting the file type
		GetStudent getContentFromExcelSheets
			= new GetStudent();
		List<Student> extractedStudentData
			= new ArrayList<Student>();
		try {
			extractedStudentData
				= getContentFromExcelSheets
					.readBooksFromExcelFileStudent(
						pathName);
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
						pathName);
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
						pathName);
		}//   /Users/Wei Jun/Downloads/staff_list.xlsx
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(int i = 0; i < extractedCampData.size(); i++){
            database.addCamp(extractedCampData.get(i));
            database.getStaff(extractedCampData.get(i).getStaffId()).addCamp(extractedCampData.get(i));
            // System.out.println(extractedCampData.get(i).getCampDate()+ " " + extractedCampData.get(i).getClosingTime());
            //System.out.println(database.getCamp(extractedCampData.get(i).getCampName()).getUserGroup());
        }
        try {
			getContentFromExcelSheets
					.readBooksFromExcelFileCampAttendees(
						pathName, database);
		}//   /Users/Wei Jun/Downloads/staff_list.xlsx
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static int scan(Scanner sc){ //catches exceptions
        try{return Integer.parseInt(sc.nextLine());}
        catch(Exception e){
            System.err.println("Not a valid input");
            return 0;
        }
    }

    public static void storeWorkbook(Database database, String pathName){
        GetStudent storeContentToExcel = new GetStudent();
        try{storeContentToExcel.storeWorkbook(database, pathName);}
        catch(Exception e){e.printStackTrace();}
        System.out.println("Workbook Stored.");
    }
}
