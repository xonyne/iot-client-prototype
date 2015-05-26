package ch.keutsa.prototype.model;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import ch.keutsa.prototype.model.basic.ConnectionCode;
import ch.keutsa.prototype.model.basic.Location;
import ch.keutsa.prototype.model.basic.MacAddress;
import ch.keutsa.prototype.model.basic.SSID;

/**
 * Created by SoullessStone on 05.05.2015.
 */
public final class RegularBundleFXML {

    public final SimpleStringProperty clientMac;
    public final SimpleStringProperty clientSSID;
    public final SimpleStringProperty clientLocation;
    public final SimpleStringProperty clientTime;
    public final SimpleStringProperty connectionCode;


    public RegularBundleFXML(String clientMac, String clientSSID, String clientLocation, String clientTime, String connectionCode) {
        this.clientMac = new SimpleStringProperty(clientMac);
        this.clientSSID = new SimpleStringProperty(clientSSID);
        this.clientLocation = new SimpleStringProperty(clientLocation);
        this.clientTime = new SimpleStringProperty(clientTime);
        this.connectionCode = new SimpleStringProperty(connectionCode);
    }

    @Override
    public String toString() {
        return "RegularBundle [\n   clientMac=" + clientMac + "\n   clientSSID="
                + clientSSID + "\n   clientLocation=" + clientLocation
                + "\n   clientTime=" + clientTime + "\n   connectionCode="
                + connectionCode + "\n]";
    }

    public String getClientMac() {
    	return clientMac.get();
    }
    
    public String getClientSSID() {
    	return clientSSID.get();
    }
    
    public String getClientLocation() {
    	return clientLocation.get();
    }
    
    public String getClientTime() {
    	return clientTime.get();
    }
    
    public String getConnectionCode() {
    	return connectionCode.get();
    }
}