package edu.uw.ischool.trellis;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iguest on 4/19/16.
 */
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String quote;
    private String bio;
    private boolean isSupporter;
    private JSONArray friends;
    private String id;


    public User(String firstName, String lastName, String quote, String bio, boolean isSupporter, String friends, String id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.quote = quote;
        this.bio = bio;
        this.id = id;
        try {
            this.friends = new JSONArray(friends);
        } catch (JSONException e) {
            this.friends = new JSONArray();
        }

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

    public void setBio(String bio) { this.bio = bio; }

    public boolean isSupporter() {
        return isSupporter;
    }

    public void setSupporter(boolean supporter) {
        isSupporter = supporter;
    }

    public JSONArray getFriends() {
        return friends;
    }

    public void setFriends(JSONArray friends) {
        this.friends = friends;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<User> getUserFriends() {
        //TODO: Change this to eventually find the user instead of creating a new one

        List<User> userFriends = new ArrayList<User>();
        for(int i = 0; i < friends.length(); i++) {
            try {
                String[] name = friends.getJSONObject(i).getString("name").trim().split("[ \t]+");
                String id = friends.getJSONObject(i).getString("id");
                String firstName = name[0];
                String lastName = name[name.length - 1];
                userFriends.add(new User(firstName, lastName, "Wow such quote", "Wow such Bio", true, "", id));
            } catch (JSONException e) {

            }

        }

        return userFriends;
    }


    public String toString() {
        return "User: [FirstName: " + firstName + ", LastName: " + lastName + ", isSupporter: " + isSupporter + ", friends: " + friends + "]";
    }

}
