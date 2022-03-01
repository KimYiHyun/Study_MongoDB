import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class ConnectToDB {
    public static void main( String args[] ) {

      // Creating a Mongo client
      MongoClient myclient = new MongoClient("localhost", 27017);
      System.out.println("Connected to the database successfully");

      // Accesing the database(db�� ������ ó�� ���� ���� �� ���� ����)
      MongoDatabase mydb = myclient.getDatabase("stuentManage");

      mydb. createCollection("student");
      System.out.println("Collection created succesfully");

      
    }
}