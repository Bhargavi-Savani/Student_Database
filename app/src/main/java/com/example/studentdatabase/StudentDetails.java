package com.example.studentdatabase;

public class StudentDetails {
    private String Id;
    private String Name;
    private final String Batch = "2020-24";
    private final String Semester = "3rd";;
    private final String Programme = "CSE";
    private final String College = "CSPIT";

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBatch() {
        return Batch;
    }

    public String getSemester() {
        return Semester;
    }

    public String getProgramme() {
        return Programme;
    }

    public String getCollege() {
        return College;
    }
}
