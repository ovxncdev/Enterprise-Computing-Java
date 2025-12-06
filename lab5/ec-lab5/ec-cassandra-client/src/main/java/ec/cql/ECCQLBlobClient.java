package ec.cql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import ec.stats.StatsSummary;
import java.nio.ByteBuffer;

public class ECCQLBlobClient {

	public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException {

		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();

		// Creating Session object
		Session session = cluster.connect();
		
		
        // Query
        String query = "CREATE KEYSPACE ecKeyspace WITH REPLICATION = {'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3 } AND DURABLE_WRITES = false;";

        // Executing the query
        session.execute(query);

		// Using the KeySpace
		session.execute("USE ecKeyspace;");

		// Creating a model object table
		session.execute("CREATE TABLE model ( name text, model blob, PRIMARY KEY (name));");

		ObjectInputStream is = null;
		StatsSummary ssobj = null;
		
		// create StatsSummary model object
		ssobj = new StatsSummary();
		ssobj.setCount(1);
		ssobj.setMin(1);
		ssobj.setMax(1);
		ssobj.setMean(1);
		ssobj.setSTD(0);
		
//		// load model object from file
//		is = new ObjectInputStream(new FileInputStream("C:/enterprise/tmp/model/stats.bin"));
//		ssobj = (StatsSummary) is.readObject();

		System.out.println("before inserting:\n" + ssobj.toString());
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(ssobj);
		oos.flush();
		byte[] blobData = bos.toByteArray();
		ByteBuffer byteBuffer = ByteBuffer.wrap(blobData);

		// insert blob bytebuffer to table
		String model_name = "stats";
		PreparedStatement insertStatement = session.prepare("INSERT INTO model (name, model) VALUES (?, ?);");
		BoundStatement bound = insertStatement.bind().setString(0, model_name).setBytes(1, byteBuffer);
		session.execute(bound);

		// insert blob bytebuffer from table
		ResultSet results = session.execute("SELECT * FROM model WHERE name='" + model_name + "';");

		ByteBuffer data = null;

		Row row = results.one();
		if (row != null) {
			data = row.getBytes("model");
		}

		ByteArrayInputStream in = new ByteArrayInputStream(data.array());
		is = new ObjectInputStream(in);

		ssobj = (StatsSummary) is.readObject();

		System.out.println("after retrieving:\n" + ssobj.toString());

		// cleaning up
		session.execute("DROP TABLE model;");
        session.execute(" DROP KEYSPACE ecKeyspace;");
		session.close();
		session.getCluster().close();
		System.out.println("client end");
	}
}
