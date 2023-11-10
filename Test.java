package excel;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Database database = new Database();
        Scanner sc = new Scanner(System.in); 
        //now to add into database
        Student brandon = new Student("BLIANG003", "password", Faculty.SCSE);
        Staff david = new Staff("DAVID", "password", Faculty.LKC);
        database.addCamp(david.createCamp(sc));// database adds the camp that david creates.
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
                if(tempStudent.getPassword().equals(password)){System.out.println("Student Login Successful!");}
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
                System.out.println("Staff Creation Successful!");
                break;
                case 3: break;
                default: System.out.println("Unknown Input"); 
                break;
            }
        }while(choice != 3);
    }
}
