package excel;

public abstract class User {
    private String userId;
    private String password;
    private Faculty facultyInfo;

    public User(String userId, String password, Faculty facultyInfo){
        this.userId = userId;
        this.password = password;
        this.facultyInfo = facultyInfo;
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

    public Faculty getFacultyInfo() {
        return this.facultyInfo;
    }

    public void setFacultyInfo(Faculty facultyInfo) {
        this.facultyInfo = facultyInfo;
    }
    //Function to view camp list
    public abstract void viewCampList();
}
