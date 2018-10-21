import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.*;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONwithMongoDB {
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	Document document;
	
	String databaseName,collectionName;
	static Scanner takeinput = new Scanner(System.in);

	String studentname,address;
	int rollno,mobileno,parentmobileno;
	
	JSONObject student;
	JSONArray  contact;
	Map        map;
	public JSONwithMongoDB() {
		mongoClient = new MongoClient("localhost", 27017);
	}

	public void getinputs() {
		System.out.println("Enter Database Name");
		databaseName = takeinput.nextLine();
		database = mongoClient.getDatabase(databaseName);

		System.out.println("Enter Collection Name");
		collectionName = takeinput.nextLine();
		database.createCollection(collectionName);
		collection = database.getCollection(collectionName);	
	}
	
	public void insertData(){
		System.out.println("Enter your name");
		studentname = takeinput.nextLine();
		System.out.println("Enter your address");
		address = takeinput.nextLine();
		System.out.println("Enter your rollno");
		rollno = takeinput.nextInt();
		System.out.println("Enter your mobileno");
		mobileno = takeinput.nextInt();
		System.out.println("Enter your parent mobileno");
		parentmobileno = takeinput.nextInt();
	}
	
	public void variables_to_json(){
		student = new JSONObject();
		student.put("name",studentname);
		student.put("address",address);
		student.put("rollno",new Integer(rollno));
		contact = new JSONArray();
		map     = new LinkedHashMap();
		map.put("mobileno", new Integer(mobileno));
		map.put("parentmobileno", new Integer(parentmobileno));
		contact.add(map);
		student.put("contact", contact);		
	}

	public void  json_to_database() {
		document = new Document(student);
		collection.insertOne(document);
		System.out.println("Inserted");
	}
	public static void main(String[] args) {
		JSONwithMongoDB jsonmongo = new JSONwithMongoDB();
		jsonmongo.getinputs();		
		jsonmongo.insertData();
		jsonmongo.variables_to_json();
		jsonmongo.json_to_database();
	
	}

}
