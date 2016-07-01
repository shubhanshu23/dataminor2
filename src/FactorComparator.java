/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
import java.util.Comparator;

public  class FactorComparator implements Comparator<Song>{
    
 
    public int compare(Song s1, Song s2)
    {
            return s1.getFactor() > s2.getFactor() ? 1 : (s1.getFactor() < s2.getFactor() ? -1 : 0);
    }

}
