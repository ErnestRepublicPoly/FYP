package sg.edu.rp.c346.round3.DataClasses;

public class ProfileEntry {
    private String gender;
    private String position;
    private int age;
    private String id;

    public ProfileEntry() {
    }

    public ProfileEntry(String gender, String position, int age, String id) {
        this.gender = gender;
        this.position = position;
        this.age = age;
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
