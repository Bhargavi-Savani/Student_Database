public class StudentDetails {
    private String Id;
    private String Name;
    private String Batch;
    private String Semester;
    private String Programme;
    private String College;

    public StudentDetails() {
        Batch = "2020-24";
        Semester = "3rd";
        College = "CSPIT";
    }

    public StudentDetails(String id, String name, String batch, String semester, String programme, String college) {
        Id = id;
        Name = name;
        Batch = batch;
        Semester = semester;
        Programme = programme;
        College = college;
    }

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

    public void setBatch(String batch) {
        Batch = batch;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getProgramme() {
        return Programme;
    }

    public void setProgramme(String programme) {
        Programme = programme;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }
}
