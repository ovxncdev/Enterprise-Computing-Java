package ec.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.ml.linalg.Vector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LinearRegressionModelConverter {
    
    public static void main(String[] args) {
        
        // Set up Spark in local mode
        SparkConf conf = new SparkConf()
            .setAppName("Linear Regression Model Converter")
            .setMaster("local[*]")
            .set("spark.executor.memory", "2g");
        
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        // Load the saved model (you need to train and save one first)
        // For demo, we'll create a simple model representation
        
        Map<String, Object> modelData = new HashMap<>();
        modelData.put("modelType", "LinearRegression");
        modelData.put("intercept", 1.5);
        modelData.put("coefficients", new double[]{0.5, 1.2, -0.8});
        modelData.put("description", "Spark Linear Regression Model converted to JSON");
        
        // Convert to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(modelData);
        
        System.out.println("Model JSON:");
        System.out.println(json);
        
        // Save to file
        try {
            FileWriter writer = new FileWriter("C:/enterprise/tmp/model/spark-lr-model.json");
            writer.write(json);
            writer.close();
            System.out.println("\nModel saved to: C:/enterprise/tmp/model/spark-lr-model.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        sc.stop();
    }
}