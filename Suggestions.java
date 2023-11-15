package excel;

public class Suggestion {
  private String suggestionText;
  private boolean status; //accepted or rejected

  public Suggestion(String suggestionText){
    this.suggestionText = suggestionText;
    status = false;
  }
