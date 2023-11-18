package excel;

import java.util.Scanner;

public class CampCom {
    private int points = 0;
    private boolean isCampCom = false;
    private String camp;

    public CampCom(){
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean getIsCampCom() {
        return this.isCampCom;
    }

    public void setIsCampCom(boolean isCampCom) {
        this.isCampCom = isCampCom;
    }

    public String getCamp() {
        return this.camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public void viewCampDetails(Camp camp){};
    public void submitSuggestions(Camp camp){};
    public void viewAllEnquiries(Camp camp){};
    public void replyEnquiries(Camp camp){};

    public void addPoints(Scanner sc){
        int choice;
        System.out.println("How many points to add?");
        choice = Integer.parseInt(sc.nextLine());
        points += choice;
    };

}
