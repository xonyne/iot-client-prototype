package ch.keutsa.prototype.model.basic;

import java.io.Serializable;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public class Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3197114570652180038L;
	public final double longitude;
	public final double latitude;

	public Location(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
    @SuppressWarnings("unused")
	private Location(){
    	//only for JAXB -> Constructor without arguments is required
    	longitude=0d;
    	latitude=0d;
    }

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	@Override
	public String toString() {
		return "Location [longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

}
