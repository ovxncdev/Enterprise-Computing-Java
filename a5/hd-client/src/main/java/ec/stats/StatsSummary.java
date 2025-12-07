package ec.stats;

import java.io.Serializable;

public class StatsSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private long count;
    private double min;
    private double max;
    private double mean;
    private double std;
    
    public StatsSummary() {}
    
    public StatsSummary(long count, double min, double max, double mean, double std) {
        this.count = count;
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.std = std;
    }
    
    // Getters and Setters
    public long getCount() { return count; }
    public void setCount(long count) { this.count = count; }
    
    public double getMin() { return min; }
    public void setMin(double min) { this.min = min; }
    
    public double getMax() { return max; }
    public void setMax(double max) { this.max = max; }
    
    public double getMean() { return mean; }
    public void setMean(double mean) { this.mean = mean; }
    
    public double getStd() { return std; }
    public void setStd(double std) { this.std = std; }
    
    @Override
    public String toString() {
        return "StatsSummary{count=" + count + ", min=" + min + 
               ", max=" + max + ", mean=" + mean + ", std=" + std + "}";
    }
}
