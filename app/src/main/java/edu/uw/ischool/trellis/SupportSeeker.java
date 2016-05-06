package edu.uw.ischool.trellis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by iguest on 4/19/16.
 */
public class SupportSeeker extends User {
    private Set<Supporter> supporters;
    private static List<SupportSeeker> allSupportSeekers;

    public SupportSeeker(String firstName, String lastName) {
        super(firstName, lastName, null, null, false);
        supporters = new HashSet<Supporter>();
        allSupportSeekers.add(this);
    }

    public void addSupporter(Supporter newSupporter) {
        supporters.add(newSupporter);
        newSupporter.addSupportSeeker(this);
    }

    public static List<SupportSeeker> getAllSupportSeekers() {
        return allSupportSeekers;
    }
}
