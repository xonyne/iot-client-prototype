package ch.keutsa.prototype.view;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.RegularBundleFXML;
import ch.keutsa.prototype.model.basic.ConnectionCode;
import ch.keutsa.prototype.model.basic.Location;
import ch.keutsa.prototype.model.basic.MacAddress;
import ch.keutsa.prototype.model.basic.SSID;

public class InfoTableController {

	  @FXML
    private TableView infoTable = new TableView();
    /*@FXML
    private TableColumn<MacAddress, String> macAddressColumn;
    @FXML
    private TableColumn<SSID, String> ssidColumn;
    @FXML
    private TableColumn<Date, String> timeColumn;
    @FXML
    private TableColumn<Location, String> locationColumn;
    @FXML
    private TableColumn<ConnectionCode, String> connectionCodeColumn;*/
    
	private MainIoT main;
	private final ObservableList<RegularBundleFXML> bundles = FXCollections.observableArrayList(new RegularBundleFXML("12-b1-c8-30-00-4f", "Test", "0.5, 40.8", "hüt", ConnectionCode.OFFLINE.toString()));
			//new RegularBundle(new MacAddress("12-a3-c7-00-30-5d"), new SSID("Test2"), new Location(100.9, 30.1), new Date(), ConnectionCode.MOBILE));
	
	@FXML
	private void initialize() {
		
		TableColumn macAddressColumn = new TableColumn("MacAddress");
        TableColumn ssidColumn = new TableColumn("SSID");       
        TableColumn timeColumn = new TableColumn("Client Time");
        TableColumn locationColumn = new TableColumn("Location");
        TableColumn connectionCodeColumn = new TableColumn("Connection Code");
				
        
        infoTable.getColumns().addAll(macAddressColumn, ssidColumn, timeColumn, locationColumn, connectionCodeColumn);
    
        macAddressColumn.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>("clientMac"));
        ssidColumn.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>("clientSSID"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>("clientTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>("clientLocation"));
        connectionCodeColumn.setCellValueFactory(new PropertyValueFactory<RegularBundleFXML, String>("connectionCode"));
        
        /*ObservableList<String> macs = FXCollections.observableArrayList();
        for(MacAddress f : main.getMacAddressList()) {
        	String e = f.toString();
        	macs.add(e);
        }
        infoTable.getItems().setAll(macs);*/
        
        infoTable.setItems(bundles);

	}
	
    public void setMain(MainIoT main) {
        this.main = main;
    }
    
   // public void parseMacAddressList(ObservableList<MacAddress> macAddressList) {

    	
    //}
}
