package edu.uw.ischool.trellis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by christina3135 on 4/30/2016.
 */
public class ConversationCategory {
    //conversation category name (academics, family, etc.)
    private String title;
    //maps the level of the question on a scale of 1-5 for how personal/intimate a question is
    //to a list of questions or conversation starters with that level in the given category
    private Map<Integer, List<String>> levelToConversationStarters;
    private static List<String> allConversationStarterTitles;
    private static List<String> allConversationStarterStrings;
    private static List<ConversationCategory> allConversationStarters;

    private int size;

    public ConversationCategory(JSONObject jsonObject) throws JSONException {
        //to be completed once structure of JSON file is determined
        this.title = jsonObject.getString("category");
        JSONArray conversationStartersArray = jsonObject.getJSONArray("starter");
        JSONArray levelArray = jsonObject.getJSONArray("level");
        levelToConversationStarters = new HashMap<Integer, List<String>>();

        if (allConversationStarterStrings == null) {
            allConversationStarterStrings = new ArrayList<String>();
        }

        size = 0;

        for (int i = 0; i < conversationStartersArray.length(); i++) {
            String starter = conversationStartersArray.getString(i);
            int level = levelArray.getInt(i);

            if (levelToConversationStarters.get(level) == null) {
                levelToConversationStarters.put(level, new ArrayList<String>());
            }

            allConversationStarterStrings.add(starter);
            levelToConversationStarters.get(level).add(starter);
            size++;
        }

        if (allConversationStarters == null) {
            allConversationStarters = new ArrayList<ConversationCategory>();
        }

        if (allConversationStarterTitles == null) {
            allConversationStarterTitles = new ArrayList<String>();
        }

        allConversationStarters.add(this);
        allConversationStarterTitles.add(this.title);
    }

    public static String[] getConversationStarterTitlesArray() {
        String[] conversationStarterTitles = new String[allConversationStarterTitles.size()];

        for (int i = 0; i < allConversationStarterTitles.size(); i++) {
            conversationStarterTitles[i] = allConversationStarterTitles.get(i);
        }

        return conversationStarterTitles;
    }

    //Takes the name of the conversation starter category and returns
    //the questions/statements related to that category
    public static String[] getConversationStartersArray(String title) {
        for (ConversationCategory currentCategory : allConversationStarters) {
            if (currentCategory.getTitle().equals(title)) {
                String[] results = createCSArray(currentCategory);

                return results;
            }
        }
        return null;
    }

    private static String[] createCSArray(ConversationCategory category) {
        String[] results = new String[category.getSize()];
        Map<Integer, List<String>> categoryMap = category.getLevelToConversationStarters();
        int index = 0;

        for (int currentKey : categoryMap.keySet()){
            List<String> currentList = categoryMap.get(currentKey);

            for (String currentConversationStarter : currentList) {
                results[index] = currentConversationStarter;
                index++;
            }
        }

        return results;
    }

    //Min and max both inclusive
    public static String[] getCSByLevelAllCategories(int min, int max) {
        int localSize = 0;
        List<List<String>> lstConversationStarters = new ArrayList<List<String>>();

        for (ConversationCategory currentCategory : allConversationStarters) {

            for (int i = min; i <= max; i++)
            {
                List<String> currentList = currentCategory.getLevelToConversationStarters().get(i);

                if (currentList != null)
                {
                    lstConversationStarters.add(currentList);
                    localSize += currentList.size();
                }
            }
        }

        String[] output = new String[localSize];
        int index = 0;

        for (List<String> currentList : lstConversationStarters)
        {

            for (String currentConversationStarter : currentList)
            {
                output[index] = currentConversationStarter;
                index++;
            }
        }

        return output;
    }

    //Min and max both inclusive
    public String[] getCSByLevelCurrentCategory(int min, int max)
    {
        int localSize = 0;

        for (int i = min; i <= max; i++)
        {
            List<String> currentList = levelToConversationStarters.get(i);

            if (currentList != null)
            {
                localSize += currentList.size();
            }
        }

        String[] output = new String[localSize];
        int index = 0;

        for (int i = min; i <= max; i++)
        {
            List<String> currentList = levelToConversationStarters.get(i);

            if (currentList != null)
            {

                for (String currentConversationStarter : currentList)
                {
                    output[index] = currentConversationStarter;
                    index++;
                }
            }
        }

        return output;
    }

    public static String[] getRandomConversationStarters(int num)
    {
        Random r = new Random();
        Collections.shuffle(allConversationStarterStrings, r);
        String[] output = new String[num];

        for (int i = 0; i < num; i++)
        {
            output[i] = allConversationStarterStrings.get(i);
        }

        return output;
    }

    public Map<Integer, List<String>> getLevelToConversationStarters() {
        return levelToConversationStarters;
    }

    public void setLevelToConversationStarters(Map<Integer, List<String>> conversationStarters) {
        this.levelToConversationStarters = levelToConversationStarters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static List<String> getAllConversationStarterTitles() {
        return allConversationStarterTitles;
    }

    public static void setAllConversationStarterTitles(List<String> allConversationStarterTitles) {
        ConversationCategory.allConversationStarterTitles = allConversationStarterTitles;
    }

    public static List<ConversationCategory> getAllConversationStarters() {
        return allConversationStarters;
    }

    public static void setAllConversationStarters(List<ConversationCategory> allConversationStarters) {
        ConversationCategory.allConversationStarters = allConversationStarters;
    }
}
