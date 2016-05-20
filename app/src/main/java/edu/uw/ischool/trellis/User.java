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
    private List<String> supportSkills;
    private List<String> conversationTopics;


    public User(String firstName, String lastName, String quote, String bio, boolean isSupporter, String friends, String id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.quote = quote;
        this.bio = bio;
        this.id = id;
        this.supportSkills = new ArrayList<String>();
        supportSkills.add("- Add any support skills you have to offer!");
        this.conversationTopics = new ArrayList<String>();
        conversationTopics.add("- Add any topics of conversation you feel confortable discussing!");
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

    public List<String> getSupportSkills() {
        return supportSkills;
    }

    public String[] getSupportSkillsArray() {
        String[] result = new String[supportSkills.size()];

        for (int i = 0; i < supportSkills.size(); i++) {
            String current = supportSkills.get(i);

            result[i] = current;
        }

        return result;
    }

    public void setSupportSkills(List<String> supportSkills) {
        this.supportSkills = supportSkills;
    }

    public void addSupportSkill(String newSkill) {
        this.supportSkills.add(newSkill);
    }

    public void deleteSupportSkill(String skillToDelete) {

        for (int i = 0; i < supportSkills.size(); i++) {
            if (supportSkills.get(i).equals(skillToDelete)) {
                supportSkills.remove(i);
            }
        }
    }

    public List<String> getConversationTopics() {
        return conversationTopics;
    }

    public String[] getConversationTopicsArray() {
        String[] result = new String[conversationTopics.size()];

        for (int i = 0; i < conversationTopics.size(); i++) {
            String current = conversationTopics.get(i);

            result[i] = current;
        }

        return result;
    }

    public void setConversationTopics(List<String> conversationTopics) {
        this.conversationTopics = conversationTopics;
    }

    public void addSupportConversationTopic(String newTopic) {
        this.conversationTopics.add(newTopic);
    }

    public void deleteConversationTopic(String topicToDelete) {

        for (int i = 0; i < conversationTopics.size(); i++) {
            if (conversationTopics.get(i).equals(topicToDelete)) {
                conversationTopics.remove(i);
            }
        }
    }


    public String getImageUrl() {
        return  "https://graph.facebook.com/" + id + "/picture?type=large";

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
