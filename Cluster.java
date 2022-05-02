import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
//cluster class
/** 
 *
 *  @author Shivan Pillay
 */
public class Cluster {

    private ArrayList<TripRecord> cluster;
     public Cluster(ArrayList<TripRecord> cluster){
        this.cluster = cluster;
    }

    public ArrayList<TripRecord> getCluster(){
        return cluster;
    }
    // mean values of coordinates
    public Double lon_mean(){
        Double sum = 0.0;
        for(int i=0;i<cluster.size();i++){
            sum =sum+ cluster.get(i).getPickup_Location().getLon();
        }
        return sum /cluster.size();
    }
    public Double lat_mean(){
        Double sum = 0.0;
        for(int i=0;i<cluster.size();i++){
            sum =sum+ cluster.get(i).getPickup_Location().getLat();
        }
        return sum /cluster.size();
    }
   

}
	

	
