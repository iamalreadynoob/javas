package structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MultiAssign
{
    private Map<String, String> stringVars;
    private Map<String, Character> charVars, characterVars;
    private Map<String, Integer> intVars, integerVars;
    private Map<String, Double> doubleVars, wrapDoubleVars;
    private Map<String, Byte> byteVars, wrapByteVars;
    private Map<String, Short> shortVars, wrapShortVars;
    private Map<String, Boolean> booleanVars, wrapBooleanVars;
    private Map<String, Float> floatVars, wrapFloatVars;
    private Map<String, String[]> arrStringVars;
    private Map<String, Character[]> arrCharVars, arrCharacterVars;
    private Map<String, Integer[]> arrIntVars, arrIntegerVars;
    private Map<String, Double[]> arrDoubleVars, arrWrapDoubleVars;
    private Map<String, Byte[]> arrByteVars, arrWrapByteVars;
    private Map<String, Short[]> arrShortVars, arrWrapShortVars;
    private Map<String, Boolean[]> arrBooleanVars, arrWrapBooleanVars;
    private Map<String, Float[]> arrFloatVars, arrWrapFloatVars;
    private Map<String, ArrayList<String>> alStringVars;
    private Map<String, ArrayList<Character>> alCharacterVars;
    private Map<String, ArrayList<Integer>> alIntegerVars;
    private Map<String, ArrayList<Double>> alDoubleVars;
    private Map<String, ArrayList<Byte>> alByteVars;
    private Map<String, ArrayList<Short>> alShortVars;
    private Map<String, ArrayList<Boolean>> alBooleanVars;
    private Map<String, ArrayList<Float>> alFloatVars;

    public MultiAssign(String[] types, String[] names, String[] vals)
    {
        declare();

        for (int i = 0; i < types.length; i++) putInside(types[i], names[i], vals[i]);
    }

    public MultiAssign(String type, String[] names, String[] vals) {
        declare();
    }

    //TODO: Write a generic class (Datatype) and use it for getter & setter functions

    public Datatype getData(Types type, String name)
    {
        Datatype value = null;
        //TODO: fix return type of arrays
        switch (type) {
            case STRING:
                value = new Datatype<>(stringVars.get(name), false);
                break;
            case CHAR:
                value = new Datatype<>(charVars.get(name), true);
                break;
            case INT:
                value = new Datatype<>(intVars.get(name), true);
                break;
            case DOUBLE:
                value = new Datatype<>(doubleVars.get(name), true);
                break;
            case BYTE:
                value = new Datatype<>(byteVars.get(name), true);
                break;
            case SHORT:
                value = new Datatype<>(shortVars.get(name), true);
                break;
            case BOOLEAN:
                value = new Datatype<>(booleanVars.get(name), true);
                break;
            case FLOAT:
                value = new Datatype<>(floatVars.get(name), true);
                break;
            case CHARACTER:
                value = new Datatype<>(characterVars.get(name), false);
                break;
            case INTEGER:
                value = new Datatype<>(integerVars.get(name), false);
                break;
            case DOUBLE_WRAP:
                value = new Datatype<>(wrapDoubleVars.get(name), false);
                break;
            case FLOAT_WRAP:
                value = new Datatype<>(wrapFloatVars.get(name), false);
                break;
            case BYTE_WRAP:
                value = new Datatype<>(wrapByteVars.get(name), false);
                break;
            case SHORT_WRAP:
                value = new Datatype<>(wrapShortVars.get(name), false);
                break;
            case BOOLEAN_WRAP:
                value = new Datatype<>(wrapBooleanVars.get(name), false);
                break;
            case ARR_STRING:
                value = new Datatype<>(arrStringVars.get(name), false);
                break;
            case ARR_CHAR:
                value = new Datatype<>(arrCharVars.get(name), true);
                break;
            case ARR_INT:
                value = new Datatype<>(arrIntVars.get(name), true);
                break;
            case ARR_DOUBLE:
                value = new Datatype<>(arrDoubleVars.get(name), true);
                break;
            case ARR_BYTE:
                value = new Datatype<>(arrByteVars.get(name), true);
                break;
            case ARR_SHORT:
                value = new Datatype<>(arrShortVars.get(name), true);
                break;
            case ARR_BOOLEAN:
                value = new Datatype<>(arrBooleanVars.get(name), true);
                break;
            case ARR_FLOAT:
                value = new Datatype<>(arrFloatVars.get(name), true);
                break;
            case ARR_CHARACTER:
                value = new Datatype<>(arrCharacterVars.get(name), false);
                break;
            case ARR_INTEGER:
                value = new Datatype<>(arrIntegerVars.get(name), false);
                break;
            case ARR_DOUBLE_WRAP:
                value = new Datatype<>(arrWrapDoubleVars.get(name), false);
                break;
            case ARR_FLOAT_WRAP:
                value = new Datatype<>(arrWrapFloatVars.get(name), false);
                break;
            case ARR_BYTE_WRAP:
                value = new Datatype<>(arrWrapByteVars.get(name), false);
                break;
            case ARR_SHORT_WRAP:
                value = new Datatype<>(arrWrapShortVars.get(name), false);
                break;
            case ARR_BOOLEAN_WRAP:
                value = new Datatype<>(arrWrapBooleanVars.get(name), false);
                break;
            case AL_STRING:
                value = new Datatype<>(alStringVars.get(name), false);
                break;
            case AL_DOUBLE:
                value = new Datatype<>(alDoubleVars.get(name), false);
                break;
            case AL_BYTE:
                value = new Datatype<>(alByteVars.get(name), false);
                break;
            case AL_SHORT:
                value = new Datatype<>(alShortVars.get(name), false);
                break;
            case AL_BOOLEAN:
                value = new Datatype<>(alBooleanVars.get(name), false);
                break;
            case AL_FLOAT:
                value = new Datatype<>(alFloatVars.get(name), false);
                break;
            case AL_CHARACTER:
                value = new Datatype<>(alCharacterVars.get(name), false);
                break;
            case AL_INTEGER:
                value = new Datatype<>(alIntegerVars.get(name), false);
                break;
        }

        return value;
    }

    public class Datatype<T, Boolean>
    {
        private T data;
        private Boolean isPrimitive;

        public Datatype(T data, Boolean isPrimitive)
        {
            this.data = data;
            this.isPrimitive = isPrimitive;
        }

        public T getData()
        {
            return data;
        }

        public Boolean getIsPrimitive()
        {
            return isPrimitive;
        }

        public void setData(T data)
        {
            this.data = data;
        }

        public void setIsPrimitive(Boolean isPrimitive)
        {
            this.isPrimitive = isPrimitive;
        }
    }

    private void putInside(String type, String name, String val)
    {
        if (type.equals("s"))
        {
            if (val.equals("\\null")) stringVars.put(name, null);
            else stringVars.put(name, val);
        }
        else if (type.equals("c"))
        {
            if (val.equals("null")) charVars.put(name, null);
            else charVars.put(name, val.charAt(0));
        }
        else if (type.equals("i")) intVars.put(name, Integer.parseInt(val));
        else if (type.equals("d")) doubleVars.put(name, Double.parseDouble(val));
        else if (type.equals("by")) byteVars.put(name, Byte.parseByte(val));
        else if (type.equals("sh")) shortVars.put(name, Short.parseShort(val));
        else if (type.equals("b")) booleanVars.put(name, Boolean.parseBoolean(val));
        else if (type.equals("f")) floatVars.put(name, Float.parseFloat(val));
        else if (type.equals("C"))
        {
            if (val.equals("null")) characterVars.put(name, null);
            else characterVars.put(name, val.charAt(0));
        }
        else if (type.equals("I")) integerVars.put(name, Integer.parseInt(val));
        else if (type.equals("D")) wrapDoubleVars.put(name, Double.parseDouble(val));
        else if (type.equals("F")) wrapFloatVars.put(name, Float.parseFloat(val));
        else if (type.equals("BY")) wrapByteVars.put(name, Byte.parseByte(val));
        else if (type.equals("S")) wrapShortVars.put(name, Short.parseShort(val));
        else if (type.equals("B")) wrapBooleanVars.put(name, Boolean.parseBoolean(val));

        //Array Lists
        else if (type.equals("As"))
        {
            if (val.equals("null")) alStringVars.put(name, null);
            else if (val.equals("new")) alStringVars.put(name, new ArrayList<>());
            else if (val.startsWith("[") && val.endsWith("]")) alStringVars.put(name, getStrings(val));
        }
        else if (type.equals("Ac") || type.equals("AC"))
        {
            if (val.equals("null")) alCharacterVars.put(name, null);
            else if (val.equals("new")) alCharacterVars.put(name, new ArrayList<>());
            else
            {
                String[] charSet = val.split("(?<!\\\\\\\\),");

                ArrayList<Character> temp = new ArrayList<>();
                for (String c: charSet) temp.add(c.replaceAll("\\\\,", ",").charAt(0));

                alCharacterVars.put(name, temp);
            }
        }
        else if (type.equals("Ai") || type.equals("AI"))
        {
            if (val.equals("null")) alIntegerVars.put(name, null);
            else if (val.equals("new")) alIntegerVars.put(name, new ArrayList<>());
            else
            {
                String[] intSet = val.split(",");

                ArrayList<Integer> temp = new ArrayList<>();
                for (String i: intSet) temp.add(Integer.parseInt(i));

                alIntegerVars.put(name, temp);
            }
        }
        else if (type.equals("Ad") || type.equals("AD"))
        {
            if (val.equals("null")) alDoubleVars.put(name, null);
            else if (val.equals("new")) alDoubleVars.put(name, new ArrayList<>());
            else
            {
                String[] doubleSet = val.split(",");

                ArrayList<Double> temp = new ArrayList<>();
                for (String d: doubleSet) temp.add(Double.parseDouble(d));

                alDoubleVars.put(name, temp);
            }
        }
        else if (type.equals("Aby") || type.equals("ABY"))
        {
            if (val.equals("null")) alByteVars.put(name, null);
            else if (val.equals("new")) alByteVars.put(name, new ArrayList<>());
            else
            {
                String[] byteSet = val.split(",");

                ArrayList<Byte> temp = new ArrayList<>();
                for (String b: byteSet) temp.add(Byte.parseByte(b));

                alByteVars.put(name, temp);
            }
        }
        else if (type.equals("Ash") || type.equals("AS"))
        {
            if (val.equals("null")) alShortVars.put(name, null);
            else if (val.equals("new")) alShortVars.put(name, new ArrayList<>());
            else
            {
                String[] shortSet = val.split(",");

                ArrayList<Short> temp = new ArrayList<>();
                for (String s: shortSet) temp.add(Short.parseShort(s));

                alShortVars.put(name, temp);
            }
        }
        else if (type.equals("Ab") || type.equals("AB"))
        {
            if (val.equals("null")) alBooleanVars.put(name, null);
            else if (val.equals("new")) alBooleanVars.put(name, new ArrayList<>());
            else
            {
                String[] boolSet = val.split(",");

                ArrayList<Boolean> temp = new ArrayList<>();
                for (String b: boolSet) temp.add(Boolean.parseBoolean(b));

                alBooleanVars.put(name, temp);
            }
        }
        else if (type.equals("Af") || type.equals("AF"))
        {
            if (val.equals("null")) alFloatVars.put(name, null);
            else if (val.equals("new")) alFloatVars.put(name, new ArrayList<>());
            else
            {
                String[] floatSet = val.split(",");

                ArrayList<Float> temp = new ArrayList<>();
                for (String f: floatSet) temp.add(Float.parseFloat(f));

                alFloatVars.put(name, temp);
            }
        }


        //Arrays

        else if (type.startsWith("as%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                String[] temp = new String[size];

                if (!val.equals("new") && val.startsWith("[") && val.endsWith("]"))
                {
                    ArrayList<String> elements = getStrings(val);

                    for (int i = 0; i < size; i++) temp[i] = elements.get(i);
                }

                arrStringVars.put(name, temp);
            }
            else arrStringVars.put(name, null);

        }
        else if (type.startsWith("ac%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));

                Character[] temp = new Character[size];

                if (!val.equals("new"))
                {
                    String[] charSet = val.split("(?<!\\\\\\\\),");
                    for (int i = 0; i < size; i++) temp[i] = charSet[i].replaceAll("\\\\", ",").charAt(0);
                }

                arrCharVars.put(name, temp);
            }
            else arrCharVars.put(name, null);

        }
        else if (type.startsWith("ai%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                Integer[] temp = new Integer[size];

                if (!val.equals("new"))
                {
                    String[] intSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Integer.parseInt(intSet[i]);
                }

                arrIntVars.put(name, temp);
            }
            else arrIntVars.put(name, null);

        }
        else if (type.startsWith("ad%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                Double[] temp = new Double[size];

                if (!val.equals("new"))
                {
                    String[] doubleSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Double.parseDouble(doubleSet[i]);
                }

                arrDoubleVars.put(name, temp);
            }
            else arrDoubleVars.put(name, null);
        }
        else if (type.startsWith("aby%"))
        {
            if (type.charAt(4) != 'n')
            {
                int size = Integer.parseInt(type.substring(4));
                Byte[] temp = new Byte[size];

                if (!val.equals("new"))
                {
                    String[] byteSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Byte.parseByte(byteSet[i]);
                }

                arrByteVars.put(name, temp);
            }
            else arrByteVars.put(name, null);
        }
        else if (type.startsWith("ash%"))
        {
            if (type.charAt(4) != 'n')
            {
                int size = Integer.parseInt(type.substring(4));
                Short[] temp = new Short[size];

                if (!val.equals("new"))
                {
                    String[] shortSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Short.parseShort(shortSet[i]);
                }

                arrShortVars.put(name, temp);
            }
            else arrShortVars.put(name, null);
        }
        else if (type.startsWith("ab%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                Boolean[] temp = new Boolean[size];

                if (!val.equals("new"))
                {
                    String[] booleanSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Boolean.parseBoolean(booleanSet[i]);
                }

                arrBooleanVars.put(name, temp);
            }
            else arrBooleanVars.put(name, null);
        }
        else if (type.startsWith("af%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                Float[] temp = new Float[size];

                if (!val.equals("new"))
                {
                    String[] floatSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Float.parseFloat(floatSet[i]);
                }

                arrFloatVars.put(name, temp);
            }
            else arrFloatVars.put(name, null);
        }
        else if (type.startsWith("aC%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));

                Character[] temp = new Character[size];

                if (!val.equals("new"))
                {
                    String[] charSet = val.split("(?<!\\\\\\\\),");
                    for (int i = 0; i < size; i++) temp[i] = charSet[i].replaceAll("\\\\", ",").charAt(0);
                }

                arrCharacterVars.put(name, temp);
            }
            else arrCharacterVars.put(name, null);
        }
        else if (type.startsWith("aI%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                Integer[] temp = new Integer[size];

                if (!val.equals("new"))
                {
                    String[] intSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Integer.parseInt(intSet[i]);
                }

                arrIntegerVars.put(name, temp);
            }
            else arrIntegerVars.put(name, null);
        }
        else if (type.startsWith("aD%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                Double[] temp = new Double[size];

                if (!val.equals("new"))
                {
                    String[] doubleSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Double.parseDouble(doubleSet[i]);
                }

                arrWrapDoubleVars.put(name, temp);
            }
            else arrWrapDoubleVars.put(name, null);
        }
        else if (type.startsWith("aF%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                Float[] temp = new Float[size];

                if (!val.equals("new"))
                {
                    String[] floatSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Float.parseFloat(floatSet[i]);
                }

                arrWrapFloatVars.put(name, temp);
            }
            else arrWrapFloatVars.put(name, null);
        }
        else if (type.startsWith("aBY%"))
        {
            if (type.charAt(4) != 'n')
            {
                int size = Integer.parseInt(type.substring(4));
                Byte[] temp = new Byte[size];

                if (!val.equals("new"))
                {
                    String[] byteSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Byte.parseByte(byteSet[i]);
                }

                arrWrapByteVars.put(name, temp);
            }
            else arrWrapByteVars.put(name, null);
        }
        else if (type.startsWith("aS%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                Short[] temp = new Short[size];

                if (!val.equals("new"))
                {
                    String[] shortSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Short.parseShort(shortSet[i]);
                }

                arrWrapShortVars.put(name, temp);
            }
            else arrWrapShortVars.put(name, null);
        }
        else if (type.startsWith("aB%"))
        {
            if (type.charAt(3) != 'n')
            {
                int size = Integer.parseInt(type.substring(3));
                Boolean[] temp = new Boolean[size];

                if (!val.equals("new"))
                {
                    String[] booleanSet = val.split(",");
                    for (int i = 0; i < size; i++) temp[i] = Boolean.parseBoolean(booleanSet[i]);
                }

                arrWrapBooleanVars.put(name, temp);
            }
            else arrWrapBooleanVars.put(name, null);
        }
    }

    public enum Types
    {
        STRING, CHAR, INT, DOUBLE, BYTE, SHORT, BOOLEAN, FLOAT, CHARACTER, INTEGER, DOUBLE_WRAP, FLOAT_WRAP,
        BYTE_WRAP, SHORT_WRAP, BOOLEAN_WRAP, ARR_STRING, ARR_CHAR, ARR_INT, ARR_DOUBLE, ARR_BYTE, ARR_SHORT,
        ARR_BOOLEAN, ARR_FLOAT, ARR_CHARACTER, ARR_INTEGER, ARR_DOUBLE_WRAP, ARR_FLOAT_WRAP, ARR_BYTE_WRAP,
        ARR_SHORT_WRAP, ARR_BOOLEAN_WRAP, AL_STRING, AL_DOUBLE, AL_BYTE, AL_SHORT,
        AL_BOOLEAN, AL_FLOAT, AL_CHARACTER, AL_INTEGER
    }

    private ArrayList<String> getStrings(String line)
    {
        ArrayList<String> strings = new ArrayList<>();

        int loc = 0;
        while (loc < line.length())
        {
            if (line.charAt(loc) == '\"' && isQuote(line, loc - 1, 0))
            {
                loc++;
                String text = null;

                while (loc < line.length())
                {
                    if (line.charAt(loc) == '\"')
                    {
                        if (isQuote(line, loc - 1, 0)) break;
                        else
                        {
                            if (text == null) text = Character.toString(line.charAt(loc));
                            else text += Character.toString(line.charAt(loc));
                        }
                    }
                    else
                    {
                        if (text == null) text = Character.toString(line.charAt(loc));
                        else text += Character.toString(line.charAt(loc));
                    }
                    loc++;
                }

                strings.add(text);
            }
            loc++;
        }

        return strings;
    }

    private boolean isQuote(String line, int start, int finish)
    {
        boolean is = false;

        int backSlashStreak = 0;

        int subLoc = start;
        while (subLoc >= finish && line.charAt(subLoc) == '\\')
        {
            backSlashStreak++;
            subLoc--;
        }

        if (backSlashStreak % 2 == 0) is = true;

        return is;
    }

    private void declare()
    {
        stringVars = new HashMap<>();
        charVars = new HashMap<>();
        characterVars = new HashMap<>();
        intVars = new HashMap<>();
        integerVars = new HashMap<>();
        doubleVars = new HashMap<>();
        wrapDoubleVars = new HashMap<>();
        byteVars = new HashMap<>();
        wrapByteVars = new HashMap<>();
        shortVars = new HashMap<>();
        wrapShortVars = new HashMap<>();
        booleanVars = new HashMap<>();
        wrapBooleanVars = new HashMap<>();
        floatVars = new HashMap<>();
        wrapFloatVars = new HashMap<>();
        arrStringVars = new HashMap<>();
        arrCharVars = new HashMap<>();
        arrCharacterVars = new HashMap<>();
        arrIntVars = new HashMap<>();
        arrIntegerVars = new HashMap<>();
        arrDoubleVars = new HashMap<>();
        arrWrapDoubleVars = new HashMap<>();
        arrByteVars = new HashMap<>();
        arrWrapByteVars = new HashMap<>();
        arrShortVars = new HashMap<>();
        arrWrapShortVars = new HashMap<>();
        arrBooleanVars = new HashMap<>();
        arrWrapBooleanVars = new HashMap<>();
        arrFloatVars = new HashMap<>();
        arrWrapFloatVars = new HashMap<>();
        alStringVars = new HashMap<>();
        alCharacterVars = new HashMap<>();
        alIntegerVars = new HashMap<>();
        alDoubleVars = new HashMap<>();
        alByteVars = new HashMap<>();
        alShortVars = new HashMap<>();
        alBooleanVars = new HashMap<>();
        alFloatVars = new HashMap<>();
    }
}
