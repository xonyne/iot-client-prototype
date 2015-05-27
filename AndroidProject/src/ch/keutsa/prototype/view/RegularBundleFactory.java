package ch.keutsa.prototype.view;

import java.util.ArrayList;
import java.util.List;

import ch.keutsa.prototype.model.basic.RegularBundleFXML;
import ch.keutsa.prototype.model.basic.RegularBundle;

public class RegularBundleFactory {

	public static RegularBundleFXML transform(RegularBundle bundle) {
		return new RegularBundleFXML(bundle.clientMac.getMac(),
				bundle.clientSSID.getValue(),
				bundle.clientLocation.getLatitude() + ":"
						+ bundle.clientLocation.getLongitude(),
				bundle.clientTime, bundle.connectionCode.name());
	}

	public static List<RegularBundleFXML> transform(List<RegularBundle> bundles) {
		List<RegularBundleFXML> result = new ArrayList<>();
		for (RegularBundle bu : bundles) {
			result.add(transform(bu));
		}
		return result;
	}
}
