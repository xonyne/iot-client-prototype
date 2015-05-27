package ch.keutsa.prototype.model.basic;


import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import ch.keutsa.prototype.model.basic.ConnectionCode;
import ch.keutsa.prototype.model.basic.Location;
import ch.keutsa.prototype.model.basic.MacAddress;
import ch.keutsa.prototype.model.basic.SSID;

/**
 * Created by SoullessStone on 05.05.2015.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace="http://ch.fbi.xml.beispielEins", name="regular_bundle")
public final class RegularBundle implements Serializable{

	/**
     *
     */
	@XmlTransient
    private static final long serialVersionUID = -1695384745702291446L;
    @XmlElement(name="client_mac")
    public final MacAddress clientMac;
    @XmlElement(name="client_ssid")
    public final SSID clientSSID;
    @XmlElement(name="client_location")
    public final Location clientLocation;
    @XmlElement(name="client_time")
    public final Date clientTime;
    @XmlElement(name="client_connectioncode")
    public final ConnectionCode connectionCode;

    /**
     * Creates an example bundle
     */
    public RegularBundle(){
        this(new MacAddress("ff-ff-ff-ff-ff-ff"), new SSID("Example"), new Location(0.0, 0.0), new Date(), ConnectionCode.UNKNOWN);
    }

    public RegularBundle(MacAddress clientMac, SSID clientSSID, Location clientLocation, Date clientTime, ConnectionCode connectionCode) {
        this.clientMac = clientMac;
        this.clientSSID = clientSSID;
        this.clientLocation = clientLocation;
        this.clientTime = clientTime;
        this.connectionCode = connectionCode;
    }

    @Override
    public String toString() {
        return "RegularBundle [\n   clientMac=" + clientMac + "\n   clientSSID="
                + clientSSID + "\n   clientLocation=" + clientLocation
                + "\n   clientTime=" + clientTime + "\n   connectionCode="
                + connectionCode + "\n]";
    }

    public MacAddress getClientMac() {
    	return clientMac;
    }
    
    public SSID getClientSSID() {
    	return clientSSID;
    }
    
    public Location getClientLocation() {
    	return clientLocation;
    }
    
    public Date getClientTime() {
    	return clientTime;
    }
    
    public ConnectionCode getConnectionCode() {
    	return connectionCode;
    }

}
