package excel;

public class Suggestion {
  private String suggestionText;
  private int suggestionIndex;
  private boolean status; //accepted or rejected

  public Suggestion(String suggestionText, int suggestionIndex){
    this.suggestionText = suggestionText;
    this.suggestionIndex = suggestionIndex;
    status = false;  //initialise suggestion as not accepted
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

  public String toString() {
      return "Suggestion: " + suggestionText + "\nStatus: " + (status ? "Accepted" : "Rejected");
  }
}
    
