package structures;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class string
{

    private char[] word;

    public string(char[] chars)
    {
        word = null;
        word = chars;
    }

    public string(char chars)
    {
        word = new char[1];
        word[0] = chars;
    }

    public string(Character[] chars)
    {
        word = null;
        ArrayList<Character> converted = new ArrayList<>();

        for (Character c: chars) if (c == null) converted.add('\0'); else converted.add(c);

        word = new char[converted.size()];
        for (int i = 0; i < word.length; i++) word[i] = converted.get(i);
    }

    public string(Character chars)
    {
        word = new char[1];
        if (chars != null) word[0] = chars;
    }

    public string(String[] chars)
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

        if (whole != null) word = whole.toCharArray();
    }

    public string(String chars)
    {
        word = null;
        if (chars != null) word = chars.toCharArray();
    }

    public string(String separator, String[] chars)
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

    public boolean contains(string exWord)
    {
        boolean flag = false;
        char[] requested = exWord.getCharArray();

        int loc = 0;

        while (loc < word.length)
        {
            if (requested[0] == word[loc])
            {
                int pos = 0;
                boolean insideFlag = true;

                while (pos < requested.length && loc < word.length)
                {
                    if (requested[pos] != word[loc])
                    {
                        insideFlag = false;
                        break;
                    }
                    pos++;
                    loc++;
                }

                if (insideFlag)
                {
                    flag = true;
                    break;
                }
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

    public string trim()
    {
        int from = 0;
        int to = word.length;

        int loc = 0;
        while (loc < word.length)
        {
            if (word[loc] == ' ') from++;
            else break;

            loc++;
        }

        loc = word.length - 1;
        while (loc >= 0)
        {
            if (word[loc] == ' ') to--;
            else break;

            loc--;
        }

        if (to > from)
        {
            char[] requested = new char[to - from];
            int pos = 0;
            for (int i = from; i < to; i++) {requested[pos] = word[i]; pos++;}
            return new string(requested);
        }
        else if (to == from) return new string(new char[0]);
        else throw new IndexOutOfBoundsException();
    }

    public String toString()
    {
        String result = null;

        for (int i = 0; i < word.length; i++)
        {
            if (result == null) result = Character.toString(word[i]);
            else result += Character.toString(word[i]);
        }

        return result;
    }

    public void set(char ch, int index)
    {
        if (index < word.length && index >= 0) word[index] = ch;
        else throw new IndexOutOfBoundsException();
    }

    public string[] split(char separator)
    {
        ArrayList<string> list = new ArrayList<>();
        int loc = 0;
        while (loc < word.length)
        {
            if (word[loc] != separator)
            {
                string temp = new string(new char[]{word[loc]});
                loc++;

                while (loc < word.length && word[loc] != separator)
                {
                    temp.define(new string(new char[]{word[loc]}));
                    loc++;
                }

                list.add(temp);
            }


            loc++;
        }

        string[] array = new string[list.size()];
        for(int i = 0; i < list.size(); i++) array[i] = list.get(i);

        return array;
    }

    public void replace(string target, string with)
    {
        char[] willChange = target.getCharArray();
        char[] willReplace = with.getCharArray();

        Integer from = null;
        Integer to = null;

        int loc = 0;
        while (loc < word.length)
        {
            if (word[loc] == willChange[0])
            {
                boolean flag = true;

                int pos = 0;
                from = loc;
                while (pos < willChange.length && loc < word.length)
                {
                    if (willChange[pos] != word[loc])
                    {
                        flag = false;
                        break;
                    }

                    loc++;
                    pos++;
                }
                to = loc;

                if (flag)
                {
                    char[] before = new char[from];
                    for (int i = 0; i < from; i++) before[i] = word[i];

                    char[] after = new char[word.length - to];
                    for (int i = 0; i < word.length - to; i++) after[i] = word[to + i];

                    word = new char[before.length + willReplace.length + after.length];

                    int current = 0;

                    if (before.length > 0)
                    {
                        for (int i = 0; i < before.length; i++)
                        {
                            word[current] = before[i];
                            current++;
                        }
                    }

                    if (willReplace.length > 0)
                    {
                        for (int i = 0; i < willReplace.length; i++)
                        {
                            word[current] = willReplace[i];
                            current++;
                        }
                    }

                    if (after.length > 0)
                    {
                        for (int i = 0; i < after.length; i++)
                        {
                            word[current] = after[i];
                            current++;
                        }
                    }

                    break;
                }
            }

            loc++;
        }
    }

    public void replaceAll(string target, string with)
    {
        char[] willChange = target.getCharArray();
        char[] willReplace = with.getCharArray();

        Integer from = null;
        Integer to = null;

        int loc = 0;
        while (loc < word.length)
        {
            if (word[loc] == willChange[0])
            {
                boolean flag = true;

                int pos = 0;
                from = loc;
                while (pos < willChange.length && loc < word.length)
                {
                    if (willChange[pos] != word[loc])
                    {
                        flag = false;
                        break;
                    }

                    loc++;
                    pos++;
                }
                to = loc;

                if (flag)
                {
                    char[] before = new char[from];
                    for (int i = 0; i < from; i++) before[i] = word[i];

                    char[] after = new char[word.length - to];
                    for (int i = 0; i < word.length - to; i++) after[i] = word[to + i];

                    word = new char[before.length + willReplace.length + after.length];

                    int current = 0;

                    if (before.length > 0)
                    {
                        for (int i = 0; i < before.length; i++)
                        {
                            word[current] = before[i];
                            current++;
                        }
                    }

                    if (willReplace.length > 0)
                    {
                        for (int i = 0; i < willReplace.length; i++)
                        {
                            word[current] = willReplace[i];
                            current++;
                        }
                    }

                    if (after.length > 0)
                    {
                        loc = current;

                        for (int i = 0; i < after.length; i++)
                        {
                            word[current] = after[i];
                            current++;
                        }
                    }

                }
            }

            loc++;
        }
    }
}
