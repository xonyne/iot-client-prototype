package ch.keutsa.prototype.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ch.keutsa.prototype.javafxclient.MainIoT;
import ch.keutsa.prototype.model.basic.RegularBundleFXML;

public class InfoTableController {

	  @FXML
    private TableView infoTable = new TableView();
    
	  private MainIoT main;
	
	  public InfoTableController() {	
	  }
	  
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

	}
	
    public void setMain(MainIoT main) {
        this.main = main;
        infoTable.setItems(main.getBundles());
    }
}
