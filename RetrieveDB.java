import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import java.util.Iterator;

public class RetrieveDB {
	public static void main(String[] args) {
		
		MongoClient myclient = new MongoClient("localhost", 27017);
		System.out.println("Connected to the database successfully");

		MongoDatabase mydb = myclient.getDatabase("sdb");
		
		MongoCollection<Document> mycol = mydb.getCollection("sampleCol");
		System.out.println("Collection created succesfully");	
		
		// Getting the iterable object
		FindIterable<Document> iterDoc = mycol.find();
		// Getting the iterator
		Iterator it = iterDoc.iterator(); //iterator 반환
		int i = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			i++;
		}
		System.out.println(i);
	}

}
