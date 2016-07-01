/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_minor2;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * @author user
 */
public class phase2_data {
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *
 * @author user
 */
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

collection = db.getCollection("data");
listFilesForFolder(folder);

    }
    
    
    public static void listFilesForFolder(final File folder) throws FileNotFoundException, IOException {
      
        StringBuffer sb=new StringBuffer();
        String s=null;
        for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
                  File[] ar =  fileEntry.listFiles();
          BufferedReader  br1=new BufferedReader(new FileReader(ar[0].getAbsolutePath()));
          String s1=br1.readLine();
          DBObject dbo;
          
           dbo =(DBObject)JSON.parse(s1);
          
         BufferedReader  br2=new BufferedReader(new FileReader(ar[1].getAbsolutePath()));
          s1=br2.readLine();
          DBObject dbo1=(DBObject)JSON.parse(s1);
          dbo.putAll(dbo1);
          collection.insert(dbo);
         
        }
//        File[] ar =  fileEntry.listFiles();
// BufferedReader br1;
// String s1;
// DBObject dBObject1=null;
// DBObject dBObject2=null;
// 
//        for(int i=0;i<3;i++)
//        {
//            if(ar[i].getAbsolutePath().contains("analysis"))
//            {
//                br1=new BufferedReader(new FileReader(ar[i].getAbsolutePath()));
//                s1=br1.readLine();
//                dBObject1=(DBObject)JSON.parse(s1);
//            }
//            else if(ar[i].getAbsolutePath().contains("analysis"))
//            {
//                br1=new BufferedReader(new FileReader(ar[i].getAbsolutePath()));
//                s1=br1.readLine();
//                dBObject2=(DBObject)JSON.parse(s1);
//                
//            }
//            
//         }
//        dBObject1.putAll(dBObject2);
//        System.out.println(dBObject1);    
//        }  
//         else {
//                 String file_path=fileEntry.getAbsolutePath();
//                 if(file_path.contains("metadata"))
//                 {
//                 BufferedReader br=new BufferedReader(new FileReader(file_path));
//                 while((s=br.readLine())!=null)
//                 {
//                     sb.append(s);
//                 }
//                 String s1=sb.toString();
//                 
//                 DBObject dbObject = (DBObject) JSON.parse(s1);
// 
// //                collection.insert(dbObject); 
//                 
//        
//        System.out.println("file_path:: "+file_path);}
//      
//        }//  System.out.println("file_entry:: "+fileEntry);
    }

                   	// convert JSON to DBObject directly
    }
    
    
    
    
}
   

