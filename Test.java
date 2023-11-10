package excel;

public class Test {
    public static void main(String[] args) {
        Database database = new Database();
        //now to add into database
        Student brandon = new Student("BLIANG003", "password", Faculty.SCSE);
        Staff david = new Staff("DAVID", "password", Faculty.LKC);
        database.addCamp(david.createCamp());// database adds the camp that david creates.
        System.out.println(database.getCamp("SCSE").getStaffId()); //Finds SCSE camp and grabs the staff name
    }
}
