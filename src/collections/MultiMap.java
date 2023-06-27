package collections;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class MultiMap
{

    private ArrayList<Map<String, String>> connectedMaps;

    public MultiMap(ArrayList<Map<String, String>> connectedMaps)
    {
        this.connectedMaps = connectedMaps;
    }

    public MultiMap(Map<String, String> initialMap)
    {
        connectedMaps = new ArrayList<>();
        connectedMaps.add(initialMap);
    }

    public void addMap(Map<String, String> relation) {connectedMaps.add(relation);}
    public void addMap(ArrayList<Map<String, String>> relations)
        {for (Map<String, String> relation: relations) connectedMaps.add(relation);}

    public void configure(int index, String key, String value) {connectedMaps.get(index).put(key, value);}

    public Map<String, String> getMap(int index) {return connectedMaps.get(index);}
    public ArrayList<Map<String, String>> getMaps() {return connectedMaps;}

    public void removeMap(int index) {connectedMaps.remove(index);}
    public void removeAll() {for (int i = 0; i < connectedMaps.size(); i++) connectedMaps.remove(0);}

    public Set<String> getKeySet() {return connectedMaps.get(0).keySet();}

    public ArrayList<String> getValues(String key)
    {
        ArrayList<String> values =  new ArrayList<>();

        for (Map<String, String> m: connectedMaps) values.add(m.get(key));

        return values;
    }

    public String getValue(int index, String key) {return connectedMaps.get(index).get(key);}

    public void removeRelation(int index, String key) {connectedMaps.get(index).remove(key);}
    public void removeRelations(String key) {for (Map<String, String> m: connectedMaps) m.remove(key);}

}
