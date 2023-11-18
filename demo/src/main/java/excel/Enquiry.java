package excel;

public class Enquiry {
	private static int enquiryCounter;
	
    private String enquiryText;
    private String replyText;
    private boolean status; //true = replied, false = not replied
    private int enquiryIndex;
    private String owner; // ID or name of the user who made the enquiry
    private String campName;

    public Enquiry(String enquiryText, String owner, String campName) {
        this.enquiryText = enquiryText;
        this.enquiryIndex = enquiryCounter;
        enquiryCounter++;
        this.owner = owner;
        this.status = false; // Default status not replied yet
        this.campName = campName;
    }

    public String getEnquiryText() {
        return enquiryText;
    }

    public void setEnquiryText(String enquiryText) {
        this.enquiryText = enquiryText;
    }
    
    public String getReplyText() {
    	return replyText;
    }
    
    public void setReplyText(String replyText) {
    	this.replyText = replyText;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public int getEnquiryIndex() {
    	return enquiryIndex;
    }
    
    public void setEnquiryIndex(int enquiryIndex) {
    	this.enquiryIndex = enquiryIndex;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCampName(){
        return campName;
    }

    public void setCampName(String campName){
        this.campName = campName;
    }

    public void displayEnquiry() {
    	System.out.println("Enquiry Index: " + enquiryIndex);
        System.out.println("Enquiry: " + enquiryText + " by " + owner);
        System.out.println("Status: " + (status ? "Replied" : "Not Replied"));
        if (status) {
            System.out.println("Reply: " + replyText);
        }
    }
}