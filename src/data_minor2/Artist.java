/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_minor2;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import static data_minor2.Data_minor2.collection;
import static data_minor2.Data_minor2.db;
import static data_minor2.Data_minor2.mongo;
import java.net.UnknownHostException;

/**
 *
 * @author user
 */
public class Artist {
    public static void main(String args[]) throws UnknownHostException
    {
Mongo mongo = new Mongo("localhost", 27017);
db = mongo.getDB("minordata");

collection = db.getCollection("test_metadata");
String s1="{'artist_name':{'$exists':'true'}}";
String s2="{'artist_name':1}";
 DBObject dbObject1 = (DBObject) JSON.parse(s1);
 DBObject dbObject2 = (DBObject) JSON.parse(s2);
        DBCursor cursor=collection.find(dbObject1,dbObject2);
        while(cursor.hasNext())
        {
            DBObject z=(DBObject) cursor.next().get("artist_name");
            //System.out.println(z);
            System.out.println(z.get("0"));
        }
    }
}
