package collections;

import java.util.ArrayList;

public class ListSimplify
{
    public enum Subprocess
    {
        EQUALS, CONTAINS, STARTS_WITH, ENDS_WITH, NOT_EQUALS,
        NOT_CONTAINS, AT, NOT_AT, NOT_STARTS_WITH, NOT_ENDS_WITH,
        LENGTH, GREATER_LENGTH, SMALLER_LENGTH, NOT_LENGTH,
        GREATER_EQUAL_LENGTH, SMALLER_EQUAL_LENGTH, CHAR_AT, NOT_CHAR_AT,
        INTEGER, NOT_INTEGER, FLOATING, NOT_FLOATING
    }

    public static ArrayList<String> getRequestedItems(ArrayList<String> list, String param ,Subprocess subprocess)
    {
        ArrayList<String> requested = new ArrayList<>();

        switch (subprocess)
        {
            case EQUALS: for (String i: list) if (i.equals(param)) requested.add(i); break;
            case CONTAINS: for (String i: list) if (i.contains(param)) requested.add(i); break;
            case STARTS_WITH: for (String i: list) if (i.startsWith(param)) requested.add(i); break;
            case ENDS_WITH: for (String i: list) if (i.endsWith(param)) requested.add(i); break;
            case NOT_EQUALS: for (String i: list) if (!i.equals(param)) requested.add(i); break;
            case NOT_CONTAINS: for (String i: list) if (!i.contains(param)) requested.add(i); break;
            case NOT_STARTS_WITH: for (String i: list) if (!i.startsWith(param)) requested.add(i); break;
            case NOT_ENDS_WITH: for (String i: list) if (!i.endsWith(param)) requested.add(i); break;
            case LENGTH: for (String i: list) if (i.length() == Integer.parseInt(param)) requested.add(i); break;
            case NOT_LENGTH: for (String i: list) if (i.length() != Integer.parseInt(param)) requested.add(i); break;
            case GREATER_LENGTH: for (String i: list) if (i.length() > Integer.parseInt(param)) requested.add(i); break;
            case SMALLER_LENGTH: for (String i: list) if (i.length() < Integer.parseInt(param)) requested.add(i); break;
            case GREATER_EQUAL_LENGTH: for (String i: list) if (i.length() >= Integer.parseInt(param)) requested.add(i); break;
            case SMALLER_EQUAL_LENGTH: for (String i: list) if (i.length() <= Integer.parseInt(param)) requested.add(i); break;
            case CHAR_AT:
                String index = null;
                int loc = 1;
                while (loc < param.length() && param.charAt(loc) != ']')
                {
                    if (index == null) index = Character.toString(param.charAt(loc));
                    else index += Character.toString(param.charAt(loc));

                    loc++;
                }

                char character = param.charAt(param.indexOf('@') + 1);

                for (String i: list) if (i.charAt(Integer.parseInt(index)) == character) requested.add(i);
                break;
            case NOT_CHAR_AT:
                String nIndex = null;
                int nLoc = 1;
                while (nLoc < param.length() && param.charAt(nLoc) != ']')
                {
                    if (nIndex == null) nIndex = Character.toString(param.charAt(nLoc));
                    else nIndex += Character.toString(param.charAt(nLoc));

                    nLoc++;
                }

                char nCharacter = param.charAt(param.indexOf('@') + 1);

                for (String i: list) if (i.charAt(Integer.parseInt(nIndex)) != nCharacter) requested.add(i);
                break;
            case AT:
                String atInterval = null;
                int atLoc = 1;
                while (atLoc < param.length() && param.charAt(atLoc) != ']')
                {
                    if (atInterval == null) atInterval = Character.toString(param.charAt(atLoc));
                    else atInterval += Character.toString(param.charAt(atLoc));

                    atLoc++;
                }

                Integer atFrom = Integer.parseInt(atInterval.substring(0, atInterval.indexOf("-")));
                Integer atTo = Integer.parseInt(atInterval.substring(atInterval.indexOf("-") + 1));

                String atRequested = param.substring(param.indexOf("@") + 1);

                for (String i: list) if (i.substring(atFrom, atTo).equals(atRequested)) requested.add(i);
                break;
            case NOT_AT:
                String atNotInterval = null;
                int atNotLoc = 1;
                while (atNotLoc < param.length() && param.charAt(atNotLoc) != ']')
                {
                    if (atNotInterval == null) atNotInterval = Character.toString(param.charAt(atNotLoc));
                    else atNotInterval += Character.toString(param.charAt(atNotLoc));

                    atNotLoc++;
                }

                Integer atNotFrom = Integer.parseInt(atNotInterval.substring(0, atNotInterval.indexOf("-")));
                Integer atNotTo = Integer.parseInt(atNotInterval.substring(atNotInterval.indexOf("-") + 1));

                String atNotRequested = param.substring(param.indexOf("@") + 1);

                for (String i: list) if (!i.substring(atNotFrom, atNotTo).equals(atNotRequested)) requested.add(i);
                break;

            case INTEGER:
                for (String i: list)
                {
                    boolean isInteger = true;

                    for (Character c: i.toCharArray())
                        if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' &&
                                c != '5' && c != '6' && c != '7' && c != '8' && c != '9')
                        {
                            isInteger = false;
                            break;
                        }

                    if (isInteger) requested.add(i);
                }
                break;
            case NOT_INTEGER:
                for (String i: list)
                {
                    boolean isNotInteger = true;

                    for (Character c: i.toCharArray())
                        if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' &&
                                c != '5' && c != '6' && c != '7' && c != '8' && c != '9')
                        {
                            isNotInteger = false;
                            break;
                        }

                    if (!isNotInteger) requested.add(i);
                }
                break;
            case FLOATING:
                for (String i: list)
                {
                    boolean isFloat = true;
                    int commaAmount = 0;

                    for (Character c: i.toCharArray())
                        if (c == '.') commaAmount++;
                        else if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' &&
                                   c != '5' && c != '6' && c != '7' && c != '8' && c != '9')
                        {
                            isFloat = false;
                            break;
                        }

                    if (isFloat && commaAmount == 1) requested.add(i);
                }
                break;

            case NOT_FLOATING:
                for (String i: list)
                {
                    boolean isNotFloat = true;
                    int notCommaAmount = 0;

                    for (Character c: i.toCharArray())
                        if (c == '.') notCommaAmount++;
                        else if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' &&
                                c != '5' && c != '6' && c != '7' && c != '8' && c != '9')
                        {
                            isNotFloat = false;
                            break;
                        }

                    if (!isNotFloat || notCommaAmount != 1) requested.add(i);
                }
                break;

        }

        return requested;
    }
}
