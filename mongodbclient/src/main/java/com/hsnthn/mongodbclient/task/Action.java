package com.hsnthn.mongodbclient.task;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.application.HostServices;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class Action {

	private Mongo mongo;
	private String host;
	DB database;
	DBCollection collection;

	public Action(String host) {
		// TODO Auto-generated constructor stub
		this.host = host;
	}

	public void save(ArrayList<String> stringList) {
		try {
			if (mongo == null) {
				try {
					mongo = new Mongo(host, 27017);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MongoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			database = mongo.getDB(stringList.get(1));
			collection = database.getCollection(stringList.get(2));

			BasicDBObject myCollection = new BasicDBObject();
			for (int i = 3; i < stringList.size(); i = i + 2) {
				myCollection.put(stringList.get(i), stringList.get(i + 1));
			}

			collection.insert(myCollection);

			read(stringList.get(1), stringList.get(2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void remove(ArrayList<String> stringList) {
		try {
			if (mongo == null) {
				try {
					mongo = new Mongo(host, 27017);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MongoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			database = mongo.getDB(stringList.get(1));
			collection = database.getCollection(stringList.get(2));

			BasicDBObject myCollection = new BasicDBObject();
			for (int i = 3; i < stringList.size(); i = i + 2) {
				myCollection.put(stringList.get(i), stringList.get(i + 1));
			}

			collection.remove(myCollection);
			read(stringList.get(1), stringList.get(2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(ArrayList<String> stringList) {
		DBObject column = null;
		try {
			if (mongo == null) {
				try {
					mongo = new Mongo(host, 27017);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MongoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			database = mongo.getDB(stringList.get(1));
			collection = database.getCollection(stringList.get(2));

			BasicDBObject myCollection = new BasicDBObject();
			myCollection.put(stringList.get(3), stringList.get(4));
			column = collection.findOne(myCollection);
			for (int i = 3; i < stringList.size(); i = i + 2) {
				column.put(stringList.get(i), stringList.get(i + 1));
			}

			collection.save(column);
			read(stringList.get(1), stringList.get(2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void read(String databaseName, String collectionName) {
		try {
			if (mongo == null) {
				try {
					mongo = new Mongo(host, 27017);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MongoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			database = mongo.getDB(databaseName);
			collection = database.getCollection(collectionName);

			for (DBObject var : collection.find().toArray()) {
				System.out.println(var.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
}
