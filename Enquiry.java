package excel;
public class Enquiry {
    private String enquiryText;
    private String status; // e.g., "Pending", "Resolved", "In Progress"
    private String owner; // ID or name of the user who made the enquiry

    // Constructor
    public Enquiry(String enquiryText, String owner) {
        this.enquiryText = enquiryText;
        this.owner = owner;
        this.status = "Pending"; // Default status
    }

    // Getters and Setters
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

    // Method to display enquiry details
    public void displayEnquiry() {
        System.out.println("Enquiry by: " + owner);
        System.out.println("Status: " + status);
        System.out.println("Enquiry: " + enquiryText);
    }
}
