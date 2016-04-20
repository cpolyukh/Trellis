package edu.uw.ischool.trellis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by iguest on 4/19/16.
 */
public class Supporter extends User {
    private Set<SupportSeeker> supportees;
    private static List<Supporter> allSupporters;
    private int supporteeCount;

    public Supporter(String firstName, String lastName) {
        this(firstName, lastName, null, null);
    }

    public Supporter(String firstName, String lastName, String quote) {
        this(firstName, lastName, quote, null);
    }

    public Supporter(String firstName, String lastName, String quote, String bio) {
        super(firstName, lastName, quote, bio);
        supportees = new HashSet<SupportSeeker>();

        if (allSupporters == null) {
            allSupporters = new ArrayList<Supporter>();
        }
        allSupporters.add(this);
    }

    public void addSupportSeeker(SupportSeeker newSupportSeeker) {
        supportees.add(newSupportSeeker);
        supporteeCount++;
    }

    public static List<Supporter> getAllSupporters() {
        return allSupporters;
    }

    public int getSupporteeCount() {
        return supporteeCount;
    }

    //TODO: remove this method once the app is functional because the supporteeCount should
    //accurately increase as supportees are added and should never need to be manually changed
    public void setSupporteeCount(int count) {
        supporteeCount = count;
    }
}
