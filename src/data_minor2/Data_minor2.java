/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_minor2;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
/**
 *
 * @author user
 */
public class Data_minor2 {
public static DBCollection collection=null; 
public static Mongo mongo=null;    
public static DB db=null;
/**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, FileNotFoundException, IOException {
        // TODO code application logic here
   final File folder = new File("D:\\HDFtoJSON");
  mongo = new Mongo("localhost", 27017);
db = mongo.getDB("minordata");

collection = db.getCollection("test_metadata");
listFilesForFolder(folder);

    }
    
    
    public static void listFilesForFolder(final File folder) throws FileNotFoundException, IOException {
      
        StringBuffer sb=new StringBuffer();
        String s=null;
        for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
                 String file_path=fileEntry.getAbsolutePath();
                 if(file_path.contains("metadata"))
                 {
                 BufferedReader br=new BufferedReader(new FileReader(file_path));
                 while((s=br.readLine())!=null)
                 {
                     sb.append(s);
                 }
                 String s1=sb.toString();
                 
                 DBObject dbObject = (DBObject) JSON.parse(s1);
 
                 collection.insert(dbObject); 
                 
        
        System.out.println("file_path:: "+file_path);}
      
        }//  System.out.println("file_entry:: "+fileEntry);
    }

                   	// convert JSON to DBObject directly
    }
    
    
    
    
}
