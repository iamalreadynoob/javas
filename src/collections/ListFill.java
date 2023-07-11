package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListFill
{
    public static void fill(ArrayList<?> list, int size)
    {
        for (int i = 0; i < size; i++) list.add(null);
    }

    public static void fill(ArrayList<Integer> list, int size, Integer value)
    {
        for (int i = 0; i < size; i++) list.add(value);
    }

    public static void fill(ArrayList<String> list, int size, String value)
    {
        for (int i = 0; i < size; i++) list.add(value);
    }

    public static void fill(ArrayList<Character> list, int size, Character value)
    {
        for (int i = 0; i < size; i++) list.add(value);
    }

    public static void fill(ArrayList<Double> list, int size, Double value)
    {
        for (int i = 0; i < size; i++) list.add(value);
    }

    public static void fill(ArrayList<Float> list, int size, Float value)
    {
        for (int i = 0; i < size; i++) list.add(value);
    }

    public static void fill(ArrayList<Short> list, int size, Short value)
    {
        for (int i = 0; i < size; i++) list.add(value);
    }

    public static void fill(ArrayList<Byte> list, int size, Byte value)
    {
        for (int i = 0; i < size; i++) list.add(value);
    }

    public static void fill(ArrayList<Boolean> list, int size, Boolean value)
    {
        for (int i = 0; i < size; i++) list.add(value);
    }

    public static void fill(ArrayList<Long> list, int size, Long value)
    {
        for (int i = 0; i < size; i++) list.add(value);
    }

    public static void fill(ArrayList<Integer> list, Relation relation)
    {
        String[] commands = relation.getRelation().split(";");

        int size = 0;
        Integer value = null;
        Map<Integer, Integer> exceptions = new HashMap<>();

        for (int i = 0; i < commands.length; i++)
        {
            String[] args = commands[i].split("=");

            if (args[0].trim().equals("if"))
            {
                String[] vals = args[1].split(",");

                for (int j = 0; j < vals.length; j++)
                {
                    String[] pieces = vals[j].split("@");

                    exceptions.put(Integer.parseInt(pieces[1].trim()), Integer.parseInt(pieces[0].trim()));
                }
            }
            else if (args[0].trim().equals("value")) value = Integer.parseInt(args[1].trim());
            else if (args[0].trim().equals("size")) size = Integer.parseInt(args[1].trim());
        }

        for (int i = 0; i < size; i++)
        {
            if (exceptions.containsKey(i)) list.add(exceptions.get(i));
            else list.add(value);
        }
    }

    public static class Relation
    {
        private String relation;
        public Relation(String relation) {this.relation = relation;}

        public String getRelation() {return relation;}
    }
}
