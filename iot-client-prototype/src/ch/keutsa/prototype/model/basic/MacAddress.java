package ch.keutsa.prototype.model.basic;

import java.io.Serializable;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class MacAddress implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5534324823414939222L;
	private static final String MAC_REGEX_DEFINITION = "^([a-fA-F0-9]{2}[:-]{1}){5}[a-fA-F0-9]{2}$";
    public final String mac;

    public MacAddress(String mac) {
        if (isValid(mac))
            this.mac = mac;
        else
            throw new IllegalArgumentException();
    }
    
    @SuppressWarnings("unused")
	private MacAddress(){
    	mac=null;
    	//only for JAXB -> Constructor without arguments is required
    }

    private boolean isValid(String string) {
        return string.matches(MAC_REGEX_DEFINITION);
    }

	public String getMacAsString() {
		return mac;
	}

	@Override
	public String toString() {
		return "MacAddress [mac=" + mac + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
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
		MacAddress other = (MacAddress) obj;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		return true;
	}

}
