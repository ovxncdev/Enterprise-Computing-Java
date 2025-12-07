package ec.stats.hd;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StatsCombine extends Reducer<Text, StatsWritable, Text, StatsWritable> {
    
    @Override
    public void reduce(Text key, Iterable<StatsWritable> values, Context context)
            throws IOException, InterruptedException {
        
        StatsWritable result = new StatsWritable();
        
        for (StatsWritable val : values) {
            result.merge(val);
        }
        
        context.write(key, result);
    }
}