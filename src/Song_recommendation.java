import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Song_recommendation {
    
           
public static DBCollection collection=null; 
public static Mongo mongo=null;    
public static DB db=null;
    private static DBObject dbo1;
    private static DBCollection collection1;
    public static void main(String args[]) throws UnknownHostException
    {
       try{
        mongo = new Mongo("localhost", 27017);
        db = mongo.getDB("minordata");
        collection = db.getCollection("data");
        double factor;
        collection1=db.getCollection("similiar");
        DBCursor cursor=collection.find();
        int count=0;
       cursor.addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);

           
        while(cursor.hasNext())
        {
            
            int count2=0;
            DBObject dbo1=cursor.next();
            DBObject dbObject1;
            String track_id=(String)((DBObject)dbo1.get("track_id")).get("0");
            double tempo1=(double) ((DBObject)dbo1.get("tempo")).get("0");
            double duration1=(double)((DBObject)dbo1.get("duration")).get("0");
            double loudness1=(double)((DBObject)dbo1.get("loudness")).get("0");
    //        System.out.println("title: "+title1+" tempo: "+tempo1+" duration: "+duration1+" loudness: "+loudness1);
      //        String check="{\"title\":{\"0\":\""+title1+"\"}}";
     
        //    if(title1.contains("'")&&)  
          //  DBObject dbObject1 = (DBObject) JSON.parse(check);
       
            //DBCursor cursor1=collection.find(dbObject1,(DBObject)JSON.parse("{'title':1,'tempo':1,'loudness':1,'duration':1}"));
            //System.out.println("cursor1: "+cursor1.next());
 
            List<Song> l=new ArrayList<Song>();
            DBCursor cursor1=collection.find();
            while(cursor1.hasNext())
            {
                DBObject dbo2=cursor1.next();
            
            String track_id2=(String)((DBObject)dbo2.get("track_id")).get("0");
            double tempo2=(double) ((DBObject)dbo2.get("tempo")).get("0");
            double duration2=(double)((DBObject)dbo2.get("duration")).get("0");
            double loudness2=(double)((DBObject)dbo2.get("loudness")).get("0");
            factor=(tempo2-tempo1)+(duration2-duration1)+(loudness2-loudness1);
           factor=Math.abs(factor);
            //    System.out.println("tr1: "+track_id+"tr2: "+track_id2+"factor"+factor);
               // HashMap hm=new HashMap();
               // hm.put("track_id2", track_id2);
               // hm.put("factor",factor);
                //ArrayList fac=new ArrayList();
                //fac.add(factor);
                //fac.sort(null);
                //for(int i=1;i<=5;i++)
                //{
            Song song=new Song();   
            song.setTrack_id(track_id2);
            song.setFactor(factor);
            l.add(song);
               
               //      }
               
            count2++;
            }
           
               Collections.sort(l, new FactorComparator() );
            //l.sort(Comparator.comparing(Person::getFactor));
           
                  
               DBObject dbs=(DBObject)JSON.parse("{'track_id':'"+track_id+"','similiar_songs':['"+l.get(1).getTrack_id()+"','"+l.get(2).getTrack_id()+"','"+l.get(3).getTrack_id()+"','"+l.get(4).getTrack_id()+"','"+l.get(5).getTrack_id()+"']}");
            System.out.println(dbs);
            collection1.insert(dbs);
//            for(int i=0;i<l.size();i++)
//            {
//            System.out.println(l.get(i).getFactor());
//            
//            }
//            
//           System.out.println("stop");
//               
          
            System.out.println(count2);
            count++;
        System.out.println(count);
   
        }
       
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    }
}
