package Java.SC2002FinalProj;

public abstract class User {
    private String userId;
    private String password;
    private String facultyInfo;

    public User(){
    }
    
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacultyInfo() {
        return this.facultyInfo;
    }

    public void setFacultyInfo(String facultyInfo) {
        this.facultyInfo = facultyInfo;
    }
    //Function to view camp list
    public abstract void viewCampList();
}
