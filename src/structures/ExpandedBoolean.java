package structures;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExpandedBoolean
{

    private double referencePoint;
    private boolean isNeutralTrue;
    private ArrayList<Double> set;

    public ExpandedBoolean(double referencePoint, boolean isNeutralTrue)
    {
        this.referencePoint = referencePoint;
        this.isNeutralTrue = isNeutralTrue;
    }

    public void initialize(ArrayList<Double> set)
    {
        if (set.size() > 0) set.clear();

        this.set = set;
    }

    public void setReferencePoint(double referencePoint) {this.referencePoint = referencePoint;}
    public void setNeutralBehavior(boolean isNeutralTrue) {this.isNeutralTrue = isNeutralTrue;}

    public Truth getTruth(int index)
    {
        double reqVal = set.get(index);
        Truth reqTruth = null;

        if (reqVal == 0) reqTruth = Truth.FALSE;
        else if (reqVal > 0 && reqVal < referencePoint) reqTruth = Truth.ALMOST_FALSE;
        else if (reqVal == referencePoint) reqTruth = Truth.NEUTRAL;
        else if (reqVal > referencePoint && reqVal < 1) reqTruth = Truth.ALMOST_TRUE;
        else if (reqVal == 1) reqTruth = Truth.TRUE;

        return reqTruth;
    }
    public boolean getBoolean(int index)
    {
        Boolean equivalent = null;
        Truth truth = getTruth(index);

        if (truth == Truth.TRUE || truth == Truth.ALMOST_TRUE || (truth == Truth.NEUTRAL && isNeutralTrue)) equivalent = true;
        else if (truth == Truth.FALSE || truth == Truth.ALMOST_FALSE || (truth == Truth.NEUTRAL && !isNeutralTrue)) equivalent = false;

        return equivalent;
    }

    public String getRange(Truth truth)
    {
        String range = null;

        switch (truth)
        {
            case TRUE: range = "[1]"; break;
            case ALMOST_TRUE: range = "(" + referencePoint + ", 1)"; break;
            case NEUTRAL: range = "[" + referencePoint + "]"; break;
            case ALMOST_FALSE: range = "(0, " + referencePoint + ")"; break;
            case FALSE: range = "[0]"; break;
        }

        return range;
    }

    public ArrayList<Double> getSet(){return set;}
    public void addSet(Double value) {set.add(value);}
    public void addSet(ArrayList<Double> values) {for (Double value: values) set.add(value);}
    public void removeSet(int index) {set.remove(index);}
    public void removeSet(int from, int to) {for (int i = 0; i < to - from; i++) set.remove(from);}
    public void configureSet(int index, double value) {set.set(index, value);}
    public void configureSet(int from, int to, double value) {for (int i = from; i < to; i++) set.set(i, value);}
    public void configureSet(int from, int to, ArrayList<Double> values) {for (int i = from; i < to; i++) set.set(i, values.get(i - from));}

    public enum Truth
    {
        TRUE, ALMOST_TRUE, NEUTRAL, ALMOST_FALSE, FALSE
    }
}
