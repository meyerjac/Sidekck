package jacksonmeyer.com.sidekck.constructors;

public class User {
    String firstName;
    String lastName;
    String displayName;
    String bio;
    String birthday;
    String email;
    String facebookId;
    String picture;

    public User(String firstName, String lastName, String displayName, String bio, String birthday, String email, String facebookId, String picture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.bio = bio;
        this.birthday = birthday;
        this.email = email;
        this.facebookId = facebookId;
        this.picture = picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBio() {
        return bio;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public String getPicture() {
        return picture;
    }
}