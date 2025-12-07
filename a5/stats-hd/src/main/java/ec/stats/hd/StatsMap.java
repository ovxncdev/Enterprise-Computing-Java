package ec.stats.hd;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StatsMap extends Mapper<LongWritable, Text, Text, StatsWritable> {
    
    private Text outputKey = new Text("stats");
    private StatsWritable outputValue = new StatsWritable();
    
    @Override
    public void map(LongWritable key, Text value, Context context) 
            throws IOException, InterruptedException {
        try {
            String line = value.toString().trim();
            if (!line.isEmpty()) {
                double number = Double.parseDouble(line);
                outputValue = new StatsWritable();
                outputValue.addValue(number);
                context.write(outputKey, outputValue);
            }
        } catch (NumberFormatException e) {
            // Skip invalid lines
        }
    }
}