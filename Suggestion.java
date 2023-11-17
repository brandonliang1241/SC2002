package excel;

public class Suggestion {
  private static int suggestionCounter = 0;
	
  private String suggestionText;
  private boolean status; //accepted or rejected
  private int suggestionIndex;
  private String owner; // ID or name of the user who made the enquiry
  private String campName;

  public Suggestion(String suggestionText, String owner, String campName){
    this.suggestionText = suggestionText;
    this.suggestionIndex = suggestionCounter;
    suggestionCounter++;
    status = false;  //initialise suggestion as not accepted
    this.owner = owner;
    this.campName = campName;
  }

  public String getSuggestionText() {
      return suggestionText;
  }

  public void setSuggestionText(String suggestionText) {
      this.suggestionText = suggestionText;
  }
  
  public int getSuggestionIndex() {
	  return suggestionIndex;
  }
  
  public void setSuggestionIndex(int suggestionIndex) {
	  this.suggestionIndex = suggestionIndex;
  }

  public boolean getStatus(){
    return status;
  }

  public void setStatus(boolean status){
    this.status = status;
  }
  
  public String getOwner() {
	  return owner;
  }
  
  public void setOwner(String owner) {
	  this.owner = owner;
  }
  
  public String getCampName() {
	  return campName;
  }

  public void setCampName(String campName) {
	  this.campName = campName;
  }
  
  public String toString() {
      return "Suggestion: " + suggestionText + "\nStatus: " + (status ? "Accepted" : "Rejected");
  }
  
  public void displaySuggestion() {
      System.out.println("Suggestion Index: " + suggestionIndex);
      System.out.println("Suggestion: " + suggestionText + " by " + owner);
      System.out.println("Status: " + status);
  }
}
    
