package excel;

public class Enquiry {
    private String enquiryText;
    private String status; // e.g., "Pending", "Resolved", "In Progress"
    private String owner; // ID or name of the user who made the enquiry
    private String campName;

    public Enquiry(String enquiryText, String owner, String campName) {
        this.enquiryText = enquiryText;
        this.owner = owner;
        this.status = "Pending"; // Default status
        this.campName = campName;
    }

    public String getEnquiryText() {
        return enquiryText;
    }

    public void setEnquiryText(String enquiryText) {
        this.enquiryText = enquiryText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        System.out.println("Enquiry by: " + owner);
        System.out.println("Status: " + status);
        System.out.println("Enquiry: " + enquiryText);
    }
}
