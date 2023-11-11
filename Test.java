package excel;

import java.util.Scanner;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Database database = new Database();
        Scanner sc = new Scanner(System.in); 
        //now to add into database
        Student brandon = new Student("BLIANG003", "password", Faculty.SCSE);
        Staff david = new Staff("DAVID", "password", Faculty.LKC);
        database.addCamp(david.createCamp(sc));// database adds the camp that david creates.
        database.getCamp("SCSE").setUserGroup(Faculty.SCSE);
        database.addStudent(brandon);
        //System.out.println(database.getCamp("SCSE").getStaffId()); //Finds SCSE camp and grabs the staff name
        int choice;
        do{
            userInterface();
            choice = Integer.parseInt(sc.nextLine());
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
        }while(choice < 3);
        sc.close();
    }

    public static void userInterface(){
        System.out.println("Welcome! Please select your desired path."); 
        System.out.println("1: Login"); 
        System.out.println("2: Create new account"); 
        System.out.println("3: quit");
    }

    public static void loginInterface(Scanner sc, Database database){
        int choice;
        do{
            System.out.println("Login Page"); 
            System.out.println("1: Student Login"); 
            System.out.println("2: Staff Login"); 
            System.out.println("3: Return"); 
            choice = Integer.parseInt(sc.nextLine());
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
                if(tempStudent.getPassword().equals(password)){studentInterface(sc, database, tempStudent);}
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
                if(tempStaff.getPassword().equals(password)){System.out.println("Staff Login Successful!");}
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
        do{
            System.out.println("Create New User Page"); 
            System.out.println("1: New Student"); 
            System.out.println("2: New Staff"); 
            System.out.println("3: Return"); 
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1: 
                System.out.println("Username:");
                String username = sc.nextLine();
                System.out.println("Password:");
                String password = sc.nextLine();
                System.out.println("Faculty:"); //Do another function for gods sake
                String faculty = sc.nextLine();
                Student tempStudent = new Student(username, password, Faculty.valueOf(faculty));
                database.addStudent(tempStudent);
                System.out.println("Student Creation Successful!");
                break;

                case 2:
                System.out.println("Username:");
                username = sc.nextLine();
                System.out.println("Password:");
                password = sc.nextLine();
                System.out.println("Faculty:"); //Do another function for gods sake
                faculty = sc.nextLine();
                Staff tempStaff = new Staff(username, password, Faculty.valueOf(faculty));
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
            System.out.println("1: See available list of camps"); 
            System.out.println("2: List of camps that you have joined"); 
            System.out.println("3: Student information"); 
            System.out.println("4: Return"); 
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                studentInterfaceCamp(sc, database, student); 
                case 2:
                case 3:
                case 4:
                default:
            }
        } while (choice != 10);
    }

    public static void studentInterfaceCamp(Scanner sc, Database database, Student student){
        int choice;
        System.out.println("Here is the list of available camps for " + student.getFacultyInfo() + " students."); 
        //Need to create an array for each student that stores the names of the camps that they have joined.
        ArrayList<Camp> tempCamp = new ArrayList<Camp>(10);
        database.listOfCampsFaculty(tempCamp, student.getFacultyInfo());
        for(int i = 0; i < tempCamp.size(); i++){
            System.out.println(i+1 + ": " + tempCamp.get(i).getCampName());
        }
        choice = Integer.parseInt(sc.nextLine()); //choice chooses the camp we would like to access. 
        studentInterfaceCampInterface(sc, tempCamp.get(choice-1), student);
    }

    public static void studentInterfaceCampInterface(Scanner sc, Camp camp, Student student){
        //Specific to the selected camp
        int choice;
        System.out.println("Visibility: "); //need a visibility value
        System.out.println("Slots remaining: ");
        System.out.println("Slots remaining: ");
        System.out.println("You have joined this camp"); //or not we need to check with the student obj
        //if student is camp com state if camp com. (can make camp com store the camp he is com of) 
        //print more choices if camp com
        System.out.println("1: Leave this camp"); //or join
        System.out.println("2: Manage enquiries"); // (have it be able to change own enquires)
        System.out.println("3:");
        System.out.println("4:");
    }
}
