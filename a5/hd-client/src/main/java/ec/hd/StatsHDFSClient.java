package ec.hd;

import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import ec.stats.StatsSummary;

public class StatsHDFSClient {
    
    public static void main(String[] args) throws Exception {
        
        // Configure to use HDFS
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:19000");
        FileSystem fs = FileSystem.get(conf);
        
        Path hdfsPath = new Path("/stats/part-r-00000");
        
        System.out.println("Reading from HDFS: " + hdfsPath);
        
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(fs.open(hdfsPath))
        );
        
        String line = reader.readLine();
        reader.close();
        
        System.out.println("Stats from HDFS: " + line);
        
        // Parse the stats line: "stats\t28,1.0,100.0,38.93,32.50"
        String[] parts = line.split("\t");
        String[] values = parts[1].split(",");
        
        long count = Long.parseLong(values[0]);
        double min = Double.parseDouble(values[1]);
        double max = Double.parseDouble(values[2]);
        double mean = Double.parseDouble(values[3]);
        double std = Double.parseDouble(values[4]);
        
        // Create StatsSummary object
        StatsSummary stats = new StatsSummary(count, min, max, mean, std);
        System.out.println("StatsSummary: " + stats);
        
        // Create output directory if not exists
        File modelDir = new File("C:/enterprise/tmp/model");
        if (!modelDir.exists()) {
            modelDir.mkdirs();
        }
        
        // Save to file
        String modelFile = "C:/enterprise/tmp/model/hdstats.bin";
        FileOutputStream fos = new FileOutputStream(modelFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(stats);
        oos.close();
        fos.close();
        
        System.out.println("Model saved to: " + modelFile);
    }
}
