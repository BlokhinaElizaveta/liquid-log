package ru.naumen.sd40.log.parser.data;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
public class RenderData {
    private ArrayList<Integer> renderTimes = new ArrayList<>();

    private double min;
    private double mean;
    private double percent50;
    private double percent99;
    private double max;
    private long count;

    boolean nan = true;


    public void calculate()
    {
        DescriptiveStatistics ds = new DescriptiveStatistics();
        renderTimes.forEach(t -> ds.addValue(t));
        min = ds.getMin();
        mean = ds.getMean();
        percent50 = ds.getPercentile(50.0);
        percent99 = ds.getPercentile(99.0);
        max = ds.getMax();
        count = ds.getN();
        nan = count == 0;
    }

    public double getMax()
    {
        return max;
    }

    public double getMean()
    {
        return mean;
    }

    public double getMin() { return min; }

    public double getPercent50()
    {
        return percent50;
    }

    public double getPercent99()
    {
        return percent99;
    }

    public long getCount()
    {
        return count;
    }

    public void addRenderTime(int time)
    {
        renderTimes.add(time);
    }

    public boolean isNan()
    {
        return nan;
    }

}
