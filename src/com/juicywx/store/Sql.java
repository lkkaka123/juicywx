package com.juicywx.store;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

public class Sql {
	
	public static Entity get(String kind,String name){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity  result=null;
		try {
			result = datastore.get(KeyFactory.createKey(kind,name));
		} catch (EntityNotFoundException e) {
			//not  exist
		}
		return result;
	}
	public static void put(Entity entity){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(entity);
	}
	
}
