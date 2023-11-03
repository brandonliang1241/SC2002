package Java.SC2002FinalProj;
import java.time.*;
import java.util.ArrayList;

public class Camp {
    private String campName;
    private LocalDate campDate;
    private LocalDateTime closingTime;
    private Faculty userGroup;
    private String location;

    private int totalSlots;
    private int campComSlots;
    private int slotsLeft;

    private String description;
    private User staffInCharge;
    private ArrayList<Student> listOfStudents = new ArrayList<Student>(totalSlots);'

    public Camp(String campName, LocalDate campDate, LocalDateTime closingTime, Faculty userGroup, String location, int totalSlots, int campComSlots, int slotsLeft, User staffInCharge) {
        this.campName = campName;
        this.campDate = campDate;
        this.closingTime = closingTime;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campComSlots = campComSlots;
        this.staffInCharge = staffInCharge;
        this.slotsLeft = slotsLeft;
    }

    public String getCampName() {
        return this.campName;
    }
    public void setCampName(String campName) {
        this.campName = campName;
    }
    public LocalDate getCampDate() {
        return this.campDate;
    }
    public void setCampDate(LocalDate campDate) {
        this.campDate = campDate;
    }
    public LocalDateTime getClosingTime() {
        return this.closingTime;
    }
    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }
    public Faculty getUserGroup() {
        return this.userGroup;
    }
    public void setUserGroup(Faculty userGroup) {
        this.userGroup = userGroup;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
     public int getTotalSlots() {
        return this.totalSlots;
     }
     public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
     }
    public int getCampComSlots() {
        return this.campComSlots;
    }
    public void setCampComSlots(int campComSlots) {
        this.campComSlots = campComSlots;
    }
    public int getSlotsLeft(){
        return this.slotsLeft;
    }
    public void setSlotsLeft(int slotsLeft){ //i dont think this will be needed? just total-campcomslots
        this.slotsLeft = slotsLeft;
    }
    public User getStaffInCharge() {
         return staffInCharge;
    }
    public void setStaffInCharge(Staff staffInCharge) {
         this.staffInCharge = staffInCharge;
    }

    public void viewListStudents(){
        //return the arraylist of students
    }
}
