
package ec.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ECRestFullClient {;  
    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();            
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8000");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);            
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                String responseString = EntityUtils.toString(entity1);
                System.out.println(responseString);      
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

            HttpPost httpPost = new HttpPost("http://localhost:8000/transformer");
                     
            final String json = "{\"type\":\"sentiment\",\"text\":\"I like EC\"}";
            final StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            
            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                String responseString2 = EntityUtils.toString(entity2);
                System.out.println(responseString2);               
                EntityUtils.consume(entity2);    
                                            
                JsonObject jsonObject = JsonParser.parseString(responseString2).getAsJsonArray().get(0).getAsJsonObject();
                System.out.println(jsonObject.toString());
                System.out.println(jsonObject.get("label"));
                System.out.println(jsonObject.get("score"));
                
            } finally {
                response2.close();
            }
           
        } finally {
            httpclient.close();
        }
    }
}