import collections.ListSimplify;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        ArrayList<String> test = new ArrayList<>();
        test.add("2.3");
        test.add("17.8");
        test.add("Orlando Bloom");
        test.add("89");
        test.add("347");
        test.add("999987");
        test.add("23.3");

        ArrayList<String> requested = ListSimplify.getRequestedItems(test, null, ListSimplify.Subprocess.NOT_FLOATING);
        System.out.println(requested);
    }
}