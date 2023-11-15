package excel;

public class Suggestion {
  private String suggestionText;
  private boolean status; //accepted or rejected

  public Suggestion(String suggestionText){
    this.suggestionText = suggestionText;
    status = false;
  }

  public String getSuggestionText() {
      return suggestionText;
  }

  public void setSuggestionText(String suggestionText) {
      this.suggestionText = suggestionText;
  }

  public boolean getStatus(){
    return status;
  }

  public void setStatus(boolean status){
    this.status = status;
  }
    
