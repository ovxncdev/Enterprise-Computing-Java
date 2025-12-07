package ec.stats.hd;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class StatsWritable implements Writable {
    private long count;
    private double min;
    private double max;
    private double sum;
    private double sumOfSquares;
    
    public StatsWritable() {
        this.count = 0;
        this.min = Double.MAX_VALUE;
        this.max = Double.MIN_VALUE;
        this.sum = 0.0;
        this.sumOfSquares = 0.0;
    }
    
    public void addValue(double value) {
        count++;
        sum += value;
        sumOfSquares += value * value;
        if (value < min) min = value;
        if (value > max) max = value;
    }
    
    public void merge(StatsWritable other) {
        this.count += other.count;
        this.sum += other.sum;
        this.sumOfSquares += other.sumOfSquares;
        if (other.min < this.min) this.min = other.min;
        if (other.max > this.max) this.max = other.max;
    }
    
    public double getMean() {
        return count > 0 ? sum / count : 0.0;
    }
    
    public double getStd() {
        if (count == 0) return 0.0;
        double mean = getMean();
        double variance = (sumOfSquares / count) - (mean * mean);
        return Math.sqrt(variance);
    }
    
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(count);
        out.writeDouble(min);
        out.writeDouble(max);
        out.writeDouble(sum);
        out.writeDouble(sumOfSquares);
    }
    
    @Override
    public void readFields(DataInput in) throws IOException {
        count = in.readLong();
        min = in.readDouble();
        max = in.readDouble();
        sum = in.readDouble();
        sumOfSquares = in.readDouble();
    }
    
    @Override
    public String toString() {
        return count + "," + min + "," + max + "," + getMean() + "," + getStd();
    }
    
    // Getters
    public long getCount() { return count; }
    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getSum() { return sum; }
}