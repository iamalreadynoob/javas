package structures;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class string
{

    private char[] word;

    public string(char... chars)
    {
        word = null;
        word = chars;
    }

    public string(Character... chars)
    {
        word = null;
        ArrayList<Character> converted = new ArrayList<>();

        for (Character c: chars) if (c == null) converted.add('\0'); else converted.add(c);

        word = new char[converted.size()];
        for (int i = 0; i < word.length; i++) word[i] = converted.get(i);
    }

    public string(String... chars)
    {
        String whole = null;
        for (String c: chars)
        {
            if (c != null)
            {
                if (whole == null) whole = c;
                else whole += c;
            }
        }
        word = whole.toCharArray();
    }

    public string(String separator, String... chars)
    {
        String whole = null;
        for (String c: chars)
        {
            if (c != null)
            {
                if (whole == null) whole = c;
                else whole += separator + c;
            }
        }
        word = whole.toCharArray();
    }

    public string(string exWord) {word = exWord.getCharArray();}

    public char charAt(int loc)
    {
        if (loc >= word.length || loc < 0) throw new IndexOutOfBoundsException();
        else return word[loc];
    }

    public char[] getCharArray() {return word;}

    public char[] concat(string exWord)
    {
        int size = word.length + exWord.getCharArray().length;
        char[] sum = new char[size];

        for (int i = 0; i < word.length; i++) sum[i] = word[i];
        for (int i = word.length; i < size; i++) sum[i] = exWord.getCharArray()[i - word.length];

        return sum;
    }

    public void define(string exWord) {word = concat(exWord);}
    public string combine(string exWord) {return new string(concat(exWord));}

    public boolean contains(char... requested)
    {
        boolean flag = false;

        int loc = 0;

        while (loc < word.length)
        {
            if (requested[0] == word[loc])
            {
                //TODO: fill there
            }

            loc++;
        }

        return flag;
    }

    public boolean endswith(char... requested)
    {
        boolean flag = true;

        int loc = word.length - requested.length;

        while (loc < word.length)
        {
            if (word[loc] != requested[loc])
            {
                flag = false;
                break;
            }

            loc++;
        }

        return flag;
    }

    public boolean equals(char... requested)
    {
        boolean flag = true;

        if (word.length == requested.length)
        {
            for (int i = 0; i < word.length; i++)
            {
                if (word[i] != requested[i])
                {
                    flag = false;
                    break;
                }
            }
        }
        else flag = false;

        return flag;
    }

    public boolean equals(string exWord) {return equals(exWord.getCharArray());}

    public boolean isEmpty() {if (word == null) return true; else return false;}
    public int length() {return word.length;}
    public boolean startsWith(char... requested)
    {
        boolean flag = true;

        int loc = 0;

        while (loc < requested.length)
        {
            if (word[loc] != requested[loc])
            {
                flag = false;
                break;
            }

            loc++;
        }

        return flag;
    }

    public string sub(int from, int to)
    {
        char[] requested = null;

        Integer init = null;
        Integer fin = null;

        if (from >= 0 && from < word.length) init = from;
        else if (from < 0  && Math.abs(from) <= word.length) init = word.length + from;
        else throw new IndexOutOfBoundsException();

        if (fin >= 0 && fin <= word.length) fin = to;
        else if (fin < 0 && Math.abs(to) <= word.length + 1) fin = word.length + to;
        else throw new IndexOutOfBoundsException();

        if (fin < init) throw new InvalidParameterException();

        return new string(requested);
    }

    public string toLowerCase()
    {
        char[] requested = new char[word.length];
        for (int i = 0; i < word.length; i++) requested[i] = Character.toLowerCase(word[i]);
        return new string(requested);
    }

    public string toUpperCase()
    {
        char[] requested = new char[word.length];
        for (int i = 0; i < word.length; i++) requested[i] = Character.toUpperCase(word[i]);
        return new string(requested);
    }

}
