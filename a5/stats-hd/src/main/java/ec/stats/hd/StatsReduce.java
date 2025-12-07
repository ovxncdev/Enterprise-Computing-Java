package ec.stats.hd;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StatsReduce extends Reducer<Text, StatsWritable, Text, Text> {
    
    @Override
    public void reduce(Text key, Iterable<StatsWritable> values, Context context)
            throws IOException, InterruptedException {
        
        StatsWritable result = new StatsWritable();
        
        for (StatsWritable val : values) {
            result.merge(val);
        }
        
        // Output as Text with format: count,min,max,mean,std
        context.write(key, new Text(result.toString()));
    }
}