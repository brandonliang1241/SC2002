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
    private ArrayList<String> listOfRemovedStudents = new ArrayList<String>(totalSlots);

    private ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
    private ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
    
    public Camp(String campName, String staffId){
        this.campName = campName;
        this.staffId = staffId;
        this.isVisible = true; //by default visible is true
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
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getStaffId() {
        return this.staffId;
    }
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    public boolean isVisible(){
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
    public int getCampComTaken(){
        return listOfCamCom.size();
    }
    
    public boolean applyForCampCom(Student student) {
        if (campComSlots > 0) {
            listOfCamCom.add(student);
            campComSlots--;
            return true;
        } else {
            System.out.println("No available slots for Camp Committee.");
            return false;
        }
    }

    public void addStudent(Student student){
        if (!listOfStudents.contains(student) && slotsLeft > 0) {
            listOfStudents.add(student);
            slotsLeft--;
        } else {
            System.out.println("Student already added or no slots left.");
        }
    }

    public int addStudent(Student student, int number){ //from database
            listOfStudents.add(student);
            return listOfStudents.indexOf(student);
    }

    public void removeStudent(Student student){
        listOfStudents.remove(student);
        listOfRemovedStudents.add(student.getName());
        slotsLeft++;
    }

    public Boolean findStudent(Student student){
        if(listOfStudents.contains(student)){return true;}
        return false;
    }

    public Boolean findRemovedStudent(String student){
        if(listOfRemovedStudents.contains(student)){return true;}
        return false;
    }

    public ArrayList<Student> getListStudents(){
        return listOfStudents;
    }

    public ArrayList<Enquiry> getEnquiries(){
        return enquiries;
    }
    

    public void printCampDetails(){
        System.out.println("//////////////////////////////////////////");
        System.out.println("Camp name: " + getCampName()); 
        System.out.println("Location: " + getLocation());
        System.out.println("Total Slots: " + getTotalSlots());
        System.out.println("Slots remaining: " + getSlotsLeft());
        System.out.println("Camp Committee Slots Taken: " + listOfCamCom.size());
        System.out.println("Camp Committee Slots Left: " + getCampComSlots());
        System.out.println("Date: " + getCampDate());
        System.out.println("Closing Date time: " + getClosingTime());
        System.out.println("Visibility: " + isVisible()); 
    }
}
