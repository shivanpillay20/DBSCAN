/** public class of GPS coordinates
 *
 *  @author Shivan Pillay
 */
public class GPScoord {
	private Double lat;
	private Double lon;
	public GPScoord(Double lat,Double lon){
		this.lat=lat;
		this.lon=lon;
	}
	//getters and setters
	public Double getLat(){
		return lat;
	}
	public Double getLon(){
		return lon;
	}
	public void setLat(Double x){
        lat = x;
    }
    public void setLon(Double y){
        lon = y;
    }
	}