package Java.SC2002FinalProj;
import java.time.*;
import java.util.ArrayList;

public class Camp {
    private String campName;
    private LocalDate campDate;
    private LocalDateTime closingTime;
    private String userGroup;
    private String location;

    private int totalSlots;
    private int campComSlots;
    private int slotsLeft;

    private String description;
    private User staffInCharge;
    private ArrayList<Student> listOfStudents = new ArrayList<Student>(totalSlots);

    public void viewListStudents(){
        //return the arraylist of students
    }
}
