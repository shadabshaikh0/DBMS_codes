import java.util.Iterator;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoDBwithJava {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// 1. Connect

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		System.out.println("Connected");
		MongoDatabase database = mongoClient.getDatabase("study");

		/*
		 * MongoCredential credential; credential =
		 * MongoCredential.createCredential
		 * ("shadab","test","shadab".toCharArray());
		 */

		// 2. Create collection

		database.createCollection("sample");
		System.out.println("Collection Created");
		MongoCollection<Document> collection = database.getCollection("sample");

		// 3. Insert document

		Document document = new Document("name", "shadab").append("age", 19);
		collection.insertOne(document);
		System.out.println("Document inserted successfully");

		// 4. Retrieve documents
		FindIterable<Document> iteratedocuments;
		Iterator it;

		iteratedocuments = collection.find();
		it = iteratedocuments.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// 5. Update documents
		collection.updateOne(Filters.eq("age", 19), Updates.set("likes", 15));
		System.out.println("Document updated successfully");

		iteratedocuments = collection.find();
		it = iteratedocuments.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// 6. Delete documents
		collection.deleteOne(Filters.eq("age", 19));
		System.out.println("Document deleted successfully");

		iteratedocuments = collection.find();
		it = iteratedocuments.iterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// 7. Listing collections

		for (String name : database.listCollectionNames()) {
			System.out.println(name);
		}

		// 8. Drop Collection
		collection.drop();
		System.out.println("collection dropped");
	}

}
