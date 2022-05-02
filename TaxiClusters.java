import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.util.*;
import java.io.PrintWriter;

/** 
 *
 *  @author Shivan Pillay
 */

public class TaxiClusters{
	public static ArrayList<TripRecord> database = new ArrayList<TripRecord>();//db
	public static List<List<String>> data = new ArrayList<>();//list of cvs file
	public static ArrayList<Cluster> cluster_list = new ArrayList<Cluster>();//list of cluster
	public void dbScan(ArrayList<TripRecord> db,Double eps, int minPts){

      int c = 0;
      for(int i =0;i < db.size();i++){

         if(db.get(i).getLabel() !="undefined"){
            continue;
         }

         ArrayList<TripRecord> neighbours = rangeQuery(db, db.get(i).getPickup_Location(), eps);
          int magnitude=neighbours.size();

         if(magnitude < minPts){
            db.get(i).setLabel("Noise");
            continue;
           
         }

         c=c+1;

         db.get(i).setLabel(Integer.toString(c));

         Cluster cluster = new Cluster(neighbours);
         cluster.getCluster().remove(db.get(i));

         for(int j = 0; j < cluster.getCluster().size(); j++){

            if(cluster.getCluster().get(j).getLabel() == "Noise"){
               cluster.getCluster().get(j).setLabel(Integer.toString(c));
            }
            if(cluster.getCluster().get(j).getLabel() == "undefined"){
               continue;
            }
            cluster.getCluster().get(j).setLabel(Integer.toString(c));
            neighbours = rangeQuery(db, db.get(j).getPickup_Location(), eps);

            //int magnitude=neighbours.size();

            if(magnitude >= minPts){
               cluster_list.add(cluster);
            }
         }

      }

   }

   public ArrayList<TripRecord> rangeQuery(ArrayList<TripRecord> db, GPScoord q, Double eps) {

      ArrayList<TripRecord> neighbours2 = new ArrayList<TripRecord>();
      
      for (int i = 0; i < db.size(); i++) {
         // System.out.println("i = " + i);
         GPScoord p = db.get(i).getPickup_Location();
         Double distance= Math.sqrt(Math.pow((p.getLat()-q.getLat()),2) + Math.pow((p.getLon()-q.getLon()),2));
         if( distance<= eps ){
            neighbours2.add(db.get(i));
         }}
      
      return neighbours2;
   }
	public static void main(String[] args) throws Exception {
		TaxiClusters taxi_cluster = new TaxiClusters();
      //read file
		try (BufferedReader br = new BufferedReader(new FileReader("yellow_tripdata_2009-01-15_1hour_clean.csv"))) {

          
         br.readLine();
         String line;
         while ((line = br.readLine()) != null) {
              String[] values = line.split(",");
              data.add(Arrays.asList(values));
              
         }
         

      //getCoorddinates label and trip distance
      }
      for(int i = 0; i < data.size(); i++){

         String pick_up_data = data.get(i).get(4);
         Double startlat = Double.parseDouble(data.get(i).get(9));
         Double startlon = Double.parseDouble(data.get(i).get(8));
         Double endlat = Double.parseDouble(data.get(i).get(13));
         Double endlon = Double.parseDouble(data.get(i).get(12));
         GPScoord pick_up = new GPScoord(startlat, startlon);
         GPScoord drop_off = new GPScoord(endlat, endlon);
        float trip_distance = Float.parseFloat(data.get(i).get(7));

         String label = "undefined";//label for points?
         //System.out.println(i);

         TripRecord trip_records = new TripRecord(pick_up_data, pick_up, drop_off, trip_distance, label);

         database.add(trip_records);
            
      }
    
        
         taxi_cluster.dbScan(database, 0.0003, 5);
         System.out.println(cluster_list.size());
         //output cvs file
         File csvFile= new File("output.csv");
         PrintWriter pw= new PrintWriter(csvFile);
         for(int z = 0; z < cluster_list.size(); z++){
         System.out.println(cluster_list.get(z) + " " + cluster_list.get(z).lon_mean());
          System.out.println(cluster_list.get(z) + " " + cluster_list.get(z).lat_mean());
          pw.println(cluster_list.get(z) + "Average lon " + cluster_list.get(z).lon_mean() + "Average lat " + cluster_list.get(z).lat_mean());

         
      

      }
      pw.println(cluster_list.size());
      pw.close();

   }
	}
	


   /* Reference: https://en.wikipedia.org/wiki/DBSCAN */