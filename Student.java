package excel;

import java.util.ArrayList;

public class Student extends User implements CampCom{
    private boolean isCampCom;
    private ArrayList<JoinedCamps> joinedCamps = new ArrayList<JoinedCamps>(10);
    //private ArrayList<Enquiry> listOfEnquiries = new ArrayList<Enquiry>(10);
    public void viewCampList(){}
    
    public Student(String userId, String password, Faculty facultyInfo){
        super(userId, password, facultyInfo);
    }

    public void addCamp(String campName,Boolean isCampCom){
        for(int i = 0; i < joinedCamps.size(); i++){
            if(joinedCamps.get(i).getCampName().equals(campName)){
                if(joinedCamps.get(i).getInCamp().equals(true)){System.out.println("Already added");}
                else{System.out.println("Cannot join a camp that you have left!");}
                return;
            }
        }
        JoinedCamps newCamp = new JoinedCamps(campName, true, isCampCom);
        joinedCamps.add(newCamp);
    }
    
    public void removeCamp(String campName){
        for(int i = 0; i < joinedCamps.size(); i++){
            if(joinedCamps.get(i).getCampName().equals(campName)){
                joinedCamps.get(i).setInCamp(false);
            }
        }
    }

    //all below use if isCamCom is True.
    public void viewCampDetails(){}
    public void submitSuggestions(){}
    public void viewEnquiries(){}
    public void replyEnquiries(){}
    public void addPoints(){}
}
