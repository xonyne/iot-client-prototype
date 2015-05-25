package ch.keutsa.prototype.model.basic;

import java.io.Serializable;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public class SSID implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7997691026530368135L;
	public final String value;

	public SSID(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "SSID [value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		SSID other = (SSID) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
