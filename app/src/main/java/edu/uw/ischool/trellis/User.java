package edu.uw.ischool.trellis;

/**
 * Created by iguest on 4/19/16.
 */
public class User {
    private String firstName;
    private String lastName;
    private String quote;
    private String bio;
    private boolean isSupporter;

    public User(String firstName, String lastName, String quote, String bio, boolean isSupporter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.quote = quote;
        this.bio = bio;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isSupporter() {
        return isSupporter;
    }

    public void setSupporter(boolean supporter) {
        isSupporter = supporter;
    }

}
