package ch.keutsa.prototype.view;

import java.util.ArrayList;
import java.util.List;

import ch.keutsa.prototype.model.RegularBundle;
import ch.keutsa.prototype.model.RegularBundleFXML;

public class RegularBundleFactory {

	public static RegularBundleFXML transform(RegularBundle bundle) {
		String ssid = "No SSID";
		if (bundle.clientSSID != null)
			ssid = bundle.clientSSID.getValue();
		System.out.println();
		return new RegularBundleFXML(bundle.clientMac.getMacAsString(), ssid,
				bundle.clientLocation.getLongitude() + ":"
						+ bundle.clientLocation.getLatitude(),
				bundle.clientTime.toString(), bundle.connectionCode.toString());
	}

	public static List<RegularBundleFXML> transform(List<RegularBundle> bundles) {
		List<RegularBundleFXML> result = new ArrayList<>();
		for (RegularBundle bu : bundles) {
			result.add(transform(bu));
		}
		return result;
	}
}