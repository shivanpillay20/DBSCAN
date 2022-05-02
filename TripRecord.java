//class of records from cvs file
/** 
 *
 *  @author Shivan Pillay
 */
public class TripRecord{
	private String pickup_DateTime;
	private GPScoord pickup_Location;
	private GPScoord dropoff_Location;
	private float trip_distance;
	private String label;

	public TripRecord(String pickup_DateTime, GPScoord pickup_Location, GPScoord dropoff_Location,
			float trip_distance) {

		this.pickup_DateTime = pickup_DateTime;
		this.pickup_Location = pickup_Location;
		this.dropoff_Location = dropoff_Location;
		this.trip_distance = trip_distance;
	}
	public TripRecord(String pickup_DateTime, GPScoord pickup_Location, GPScoord dropoff_Location,
			float trip_distance,String label) {

		this.pickup_DateTime = pickup_DateTime;
		this.pickup_Location = pickup_Location;
		this.dropoff_Location = dropoff_Location;
		this.trip_distance = trip_distance;
		this.label=label;
	}
	
	//getters and setters
	public String getPickup_DateTime() {
		return pickup_DateTime;
	}
	public void setPickup_DateTime(String pickup_DateTime) {
		this.pickup_DateTime = pickup_DateTime;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String l) {
		this.pickup_DateTime = l;
	}
	public GPScoord getPickup_Location() {
		return pickup_Location;
	}
	public void setPickup_Location(GPScoord pickup_Location) {
		this.pickup_Location = pickup_Location;
	}
	public GPScoord getDropoff_Location() {
		return dropoff_Location;
	}
	public void setDropoff_Location(GPScoord dropoff_Location) {
		this.dropoff_Location = dropoff_Location;
	}
	public float getTrip_distance() {
		return trip_distance;
	}
	public void setTrip_distance(float trip_distance) {
		this.trip_distance = trip_distance;
	}

	
}