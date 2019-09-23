import java.lang.* ;
import java.util.* ;
import java.io.* ;

// class General is made for general purpose functions
public class General{

	// static double eucleidian(...) calculates the eucleidian distance between two geodata points
	// Arguments: the two points' coordinates + the unit to which the distance will be calculated
	// M: for miles
	// K: for kilometers
	// Returns: the distance to the unit chosen
	public static double eucleidian(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}
	// I don't care what those do, just used for the eucleidian()
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}