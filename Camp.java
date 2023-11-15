package excel;

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
    private String staffId;
    private boolean isVisible;

    private ArrayList<Student> listOfStudents = new ArrayList<Student>(totalSlots);
    private ArrayList<Student> listOfCamCom = new ArrayList<Student>(totalSlots);

    //suggestions
    private ArrayList<Suggestion> suggestions = new ArrayList<>();

    public Camp(String campName, String staffId){
        this.campName = campName;
        this.staffId = staffId;
    }

    public Camp(String campName, LocalDate campDate, LocalDateTime closingTime, Faculty userGroup, String location, int totalSlots, int campComSlots, int slotsLeft, String staffId) {
        this.campName = campName;
        this.campDate = campDate;
        this.closingTime = closingTime;
        this.userGroup = userGroup;
        this.location = location;
        this.totalSlots = totalSlots;
        this.campComSlots = campComSlots;
        this.staffId = staffId;
        this.slotsLeft = slotsLeft;
        this.isVisible = true;
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
    public String getStaffId() {
        return this.staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public boolean isVisible(Camp camp){
        return isVisible;
    }
    public void toggleVisibility(boolean isVisible){
        this.isVisible = isVisible;
    }
    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }
    public ArrayList<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void viewListStudents(){
        //return the arraylist of students
    }
}
