package excel;

public class JoinedCamps {
    private String campName;
    private Boolean inCamp; //if false means that they have left the camp b4 and cannot join again
    private Boolean isCampCom; 

    JoinedCamps(String campName, Boolean inCamp, Boolean isCampCom){
        this.campName = campName;
        this.inCamp = inCamp;
        this.isCampCom = isCampCom;
    }
    public String getCampName() {
        return this.campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public Boolean isInCamp() {
        return this.inCamp;
    }

    public Boolean getInCamp() {
        return this.inCamp;
    }

    public void setInCamp(Boolean inCamp) {
        this.inCamp = inCamp;
    }

    public Boolean isIsCampCom() {
        return this.isCampCom;
    }

    public Boolean getIsCampCom() {
        return this.isCampCom;
    }

    public void setIsCampCom(Boolean isCampCom) {
        this.isCampCom = isCampCom;
    }
    
} 
