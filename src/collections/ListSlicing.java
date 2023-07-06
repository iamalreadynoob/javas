package collections;


import java.util.ArrayList;

public class ListSlicing
{

    public static ArrayList slice(ArrayList list, String instruction)
    {
        ArrayList result = new ArrayList<>();

        if (instruction.trim().equals(":")) result = list;
        else if (instruction.contains(":"))
        {
            String[] commands = instruction.split(":");

            int length = commands.length;
            if (instruction.endsWith(":")) length++;

            Integer from = null;
            Integer to = null;

            if (length >= 2)
            {
                if (commands[0].equals("")) from = 0;
                else if (Integer.parseInt(commands[0]) < 0 && Math.abs(Integer.parseInt(commands[0])) < list.size())
                {
                    int fromLast = Math.abs(Integer.parseInt(commands[0]));
                    from = list.size() - fromLast;
                }
                else from = Integer.parseInt(commands[0]);

                if (commands.length < 2 || commands[1].equals("")) to = list.size();
                else if (Integer.parseInt(commands[1]) < 0 && Math.abs(Integer.parseInt(commands[1])) < list.size())
                {
                    int toLast = Math.abs(Integer.parseInt(commands[1]));
                    to = list.size() - toLast;
                }
                else to = Integer.parseInt(commands[1]);
            }

            if (length == 2 && from < to) for (int i = from; i < to; i++) result.add(list.get(i));
            else if (length == 3 && !commands[2].equals("") && Integer.parseInt(commands[2]) != 0)
            {
                Integer iteration = null;

                if (Integer.parseInt(commands[2]) < 0 && from > to)
                {
                    iteration = Math.abs(Integer.parseInt(commands[2]));

                    for (int i = from; i > to;  i -= iteration) result.add(list.get(i));
                }
                else if (Integer.parseInt(commands[2]) > 0 && from < to)
                {
                    iteration = Integer.parseInt(commands[2]);

                    for (int i = from; i < to; i += iteration) result.add(list.get(i));
                }
            }

        }
        else if (instruction.contains("="))
        {
            Integer from = null;
            Integer to = null;
            Integer iteration = null;
            ArrayList<Integer> exceptions = new ArrayList<>();
            ArrayList<Integer> must = new ArrayList<>();

            String[] params = instruction.split(";");
            for (String p: params)
            {
                String[] inst = p.split("=");

                if (inst[0].trim().equals("from"))
                {
                    if (Integer.parseInt(inst[1].trim()) < 0) from = list.size() - Math.abs(Integer.parseInt(inst[1].trim()));
                    else from = Integer.parseInt(inst[1].trim());
                }

                else if (inst[0].trim().equals("to"))
                {
                    if (Integer.parseInt(inst[1].trim()) < 0) to = list.size() - Math.abs(Integer.parseInt(inst[1]));
                    else to = Integer.parseInt(inst[1].trim());
                }

                else if (inst[0].trim().equals("except"))
                {
                    String[] temp = inst[1].split(",");
                    for (String t: temp)
                    {
                        int index = Integer.parseInt(t.trim());
                        if (index < 0) index = list.size() + index;
                        exceptions.add(index);
                    }
                }

                else if (inst[0].trim().equals("must"))
                {
                    String[] temp = inst[1].split(",");
                    for (String t: temp)
                    {
                        int index = Integer.parseInt(t.trim());
                        if (index < 0) index = list.size() + index;
                        must.add(index);
                    }
                }

                else if (inst[0].trim().equals("iteration")) iteration = Integer.parseInt(inst[1].trim());

            }

            result = cut(list, from, to, iteration, exceptions, must);

        }

        return result;
    }

    public static ArrayList slice(ArrayList list, SlicingParams[] params, int[] values)
    {
        ArrayList result = new ArrayList<>();

        Integer from = null;
        Integer to = null;
        Integer iteration = null;
        ArrayList<Integer> exceptions = new ArrayList<>();
        ArrayList<Integer> must = new ArrayList<>();

        if (params.length == values.length)
        {
            for (int i = 0; i < params.length; i++)
            {
                switch (params[i])
                {
                    case FROM:
                        from = values[i];
                        if (from < 0) from = list.size() + from;
                        break;
                    case TO:
                        to = values[i];
                        if (to < 0) to = list.size() + to;
                        break;
                    case ITERATION:
                        iteration = values[i];
                        break;
                    case MUST:
                        int mustIndex = values[i];
                        if (mustIndex < 0) mustIndex = list.size() + mustIndex;
                        must.add(mustIndex);
                        break;
                    case EXCEPT:
                        int exception = values[i];
                        if (exception < 0) exception = list.size() + exception;
                        exceptions.add(exception);
                        break;
                }
            }
        }

        result = cut(list, from, to, iteration, exceptions, must);

        return result;
    }

    public static ArrayList slice(ArrayList list, int from, int to)
    {
        ArrayList result = new ArrayList<>();

        int fromIndex = from;
        if (fromIndex < 0) fromIndex = fromIndex + list.size();

        int toIndex = to;
        if (toIndex < 0) toIndex = toIndex + list.size();

        if (fromIndex < toIndex) for (int i = fromIndex; i < toIndex; i++) result.add(list.get(i));

        return result;
    }

    public static ArrayList slice(ArrayList list, PreparedFunctions function)
    {
        ArrayList result = new ArrayList<>();

        switch (function)
        {
            case ODD_INDEXES:
                for (int i = 1; i < list.size(); i += 2) result.add(list.get(i));
                break;
            case EVEN_INDEXES:
                for (int i = 0; i < list.size(); i += 2) result.add(list.get(i));
                break;
            case FIRST_HALF:
                int firstHalf = list.size() / 2;
                for (int i = 0; i < firstHalf; i++) result.add(list.get(i));
                break;
            case SECOND_HALF:
                int secondHalf = list.size() / 2;
                for (int i = secondHalf; i < list.size(); i++) result.add(list.get(i));
                break;
            case FIRST_QUARTER:
                for (int i = 0; i < (list.size() / 4); i++) result.add(list.get(i));
                break;
            case SECOND_QUARTER:
                for (int i = (list.size() / 4); i < (list.size() / 2); i++) result.add(list.get(i));
                break;
            case THIRD_QUARTER:
                for (int i = (list.size() / 2); i < (list.size() * 3 / 4); i++) result.add(list.get(i));
                break;
            case FOURTH_QUARTER:
                for (int i = (list.size() * 3 / 4); i < list.size(); i++) result.add(list.get(i));
                break;
        }

        return result;
    }

    public enum PreparedFunctions
    {
        ODD_INDEXES, EVEN_INDEXES, FIRST_HALF, SECOND_HALF, FIRST_QUARTER, SECOND_QUARTER, THIRD_QUARTER,
        FOURTH_QUARTER
    }

    public enum SlicingParams
    {
        FROM, TO, EXCEPT, ITERATION, MUST
    }

    private static ArrayList cut(ArrayList list, Integer from, Integer to, Integer iteration, ArrayList<Integer> exceptions, ArrayList<Integer> must)
    {
        ArrayList result = new ArrayList<>();

        if (iteration == null && from != null && to != null && from < to)
        {
            for (int i = from; i < to; i++)
            {
                if (!exceptions.contains(i))
                {
                    result.add(list.get(i));
                    if (must.contains(i)) must.remove(must.indexOf(i));
                }
            }

            if (must.size() > 0) for (Integer i: must) result.add(list.get(i));
        }

        else if (iteration != null && iteration > 0 && from != null && to != null && from < to)
        {
            for (int i = from; i < to; i += iteration)
            {
                if (!exceptions.contains(i))
                {
                    result.add(list.get(i));
                    if (must.contains(i)) must.remove(must.indexOf(i));
                }
            }

            if (must.size() > 0) for (Integer i: must) result.add(list.get(i));
        }

        else if (iteration != null && iteration < 0 && from != null && to != null && from > to)
        {
            iteration *= -1;

            for (int i = from; i > to; i -= iteration)
            {
                result.add(list.get(i));
                if (must.contains(i)) must.remove(must.indexOf(i));
            }

            if (must.size() > 0) for (Integer i: must) result.add(list.get(i));
        }

        return result;
    }

}
