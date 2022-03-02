import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class InsertDB {
	public static void main(String[] args) {
       
		// Creating a Mongo client
		MongoClient myclient = new MongoClient("localhost", 27017);
		System.out.println("Connected to the database successfully");
				
		// Accesing the database(db가 없으면 처음 문서 저장 시 새로 생성)
		MongoDatabase mydb = myclient.getDatabase("sdb");
				
		MongoCollection<Document> mycol = mydb.getCollection("sampleCol");
		System.out.println("Collection created succesfully");		
		
		
		// bson Document 객체 생성
        Document mydoc = new Document("title", "MongoDB")
        		.append("id", 1)
        		.append("description", "database")
        		.append("likes", 100);
        		
	        

	}

}
