import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import java.util.*;
import java.util.Map.Entry;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EncodeDecodeJSONwithMongoDB {

	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	Document document;

	DBCollection dbCollection;
	DBCursor cursor;

	String databaseName, collectionName;
	static Scanner takeinput = new Scanner(System.in);

	String studentname, address;
	int rollno, mobileno, parentmobileno;

	JSONObject student;
	JSONArray contact;
	JSON json;
	Map map;

	String jsonstring;
	Object object;
	JSONArray jsonarray;
	JSONObject jsonobject ;

	String decoded_name, decoded_rollno, decoded_address, decoded_id;
	JSONArray decode_contact;

	public EncodeDecodeJSONwithMongoDB() {
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
		dbCollection = mongoClient.getDB(databaseName).getCollection(
				collectionName);
	}

	public void insertData() {
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

	public void encodeJSON() {

		student = new JSONObject();
		student.put("name", studentname);
		student.put("address", address);
		student.put("rollno", new Integer(rollno));

		contact = new JSONArray();
		map = new LinkedHashMap();
		map.put("mobileno", new Integer(mobileno));
		map.put("parentmobileno", new Integer(parentmobileno));

		contact.add(map);
		student.put("contact", contact);
	}

	public void json_to_database() {
		document = new Document(student);
		collection.insertOne(document);
		System.out.println("Inserted");
		
		System.out.println("*********Encoded JSON*********");
		System.out.println(student.toJSONString());
	}

	public void database_to_json() throws ParseException {
		cursor = dbCollection.find();
		json = new JSON();
		jsonstring = json.serialize(cursor);

		object = new JSONParser().parse(jsonstring);
		jsonarray =  (JSONArray) object;
		jsonobject = (JSONObject) jsonarray.get(0);
	}

	public void decodeJSON() {
		System.out.println("*********Decoded JSON*********");
		decode_contact = (JSONArray) jsonobject.get("contact");

		System.out.println("rollno : " + jsonobject.get("rollno"));
		System.out.println("name : " + jsonobject.get("name"));
		System.out.println("address : " + jsonobject.get("address"));

		Iterator it1, it2;
		it1 = decode_contact.iterator();
		while (it1.hasNext()) {
			it2 = ((Map) it1.next()).entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry pair = (Entry) it2.next();
				System.out.println(pair.getKey() + " : " + pair.getValue());
			}
		}
	}

	public static void main(String[] args) throws ParseException {
		EncodeDecodeJSONwithMongoDB jsonmongo = new EncodeDecodeJSONwithMongoDB();
		jsonmongo.getinputs();
		jsonmongo.insertData();
		jsonmongo.encodeJSON();

		jsonmongo.json_to_database();
		jsonmongo.database_to_json();
		jsonmongo.decodeJSON();
	}

}
