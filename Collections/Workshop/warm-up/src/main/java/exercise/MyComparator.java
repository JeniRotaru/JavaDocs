package exercise;

import java.util.Comparator;

/**
 * Created by user on 7/1/2016.
 */
public class MyComparator implements Comparator<String> {
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
}
