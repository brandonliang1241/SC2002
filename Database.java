    package excel;

    import java.util.ArrayList;

    public class Database {
        private static int CAMP_CAPACITY = 10;
        private static int MAX_STUDENT = 100;
        private static int MAX_STAFF = 10;
        private ArrayList<Camp> camps = new ArrayList<Camp>(CAMP_CAPACITY);
        private ArrayList<Student> students = new ArrayList<Student>(MAX_STUDENT);
        private ArrayList<Staff> staffs = new ArrayList<Staff>(MAX_STAFF);

        public Database(){}

        public Camp getCamp(String campName) {
            for(int i = 0; i < camps.size(); i++){
                if(camps.get(i).getCampName().equals(campName)){return camps.get(i);}
            }
            return null; //need write exceptions
        }

        public void addCamp(Camp camp) {
            camps.add(camp);
        }

        public void removeCamp(Camp camp) {
            for(int i = 0; i < camps.size(); i++){
                if(camps.get(i).getCampName().equals(camp.getCampName())){
                    camps.remove(i);
                    return;
                }
            }
            System.out.println("No camp found");
        }

        public Student getStudent(String studentName) {
            for(int i = 0; i < students.size(); i++){
                if(students.get(i).getUserId().equals(studentName)){return students.get(i);}
            }
            return null; //need write exceptions
        }

        public void addStudent(Student student) {
            students.add(student);
        }

        public void removeStudent(Student student) {
            for(int i = 0; i < students.size(); i++){
                if(students.get(i).getUserId().equals(student.getUserId())){
                    students.remove(i);
                    return;
                }
            }
            System.out.println("No student found");
        }

        public Staff getStaff(String staffName) {
            for(int i = 0; i < staffs.size(); i++){
                if(staffs.get(i).getUserId().equals(staffName)){return staffs.get(i);}
            }
            return null; //need write exceptions
        }

        public void addStaff(Staff staff) {
            staffs.add(staff);
        }

        public void removeStaff(Staff staff) {
            for(int i = 0; i < staffs.size(); i++){
                if(staffs.get(i).getUserId().equals(staff.getUserId())){
                    staffs.remove(i);
                    return;
                }
            }
            System.out.println("No staff found");
        }

        public ArrayList<Camp> listOfCampsFaculty(ArrayList<Camp> inputCamp, Faculty faculty){
            for(int i = 0; i < camps.size(); i++){
                if(camps.get(i).getUserGroup().equals(faculty)){inputCamp.add(camps.get(i));}
            }
            return inputCamp;
        }
    }
