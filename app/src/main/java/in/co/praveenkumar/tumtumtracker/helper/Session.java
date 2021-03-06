package in.co.praveenkumar.tumtumtracker.helper;

import in.co.praveenkumar.tumtumtracker.model.TTTMarker;
import in.co.praveenkumar.tumtumtracker.model.TTTSiteResponse;

import java.util.List;

public class Session {
	public static TTTSiteResponse response = null;
	public static Boolean showIdle = false;

	/**
	 * Initializes the Session.response with data from Sql db. Returns false
	 * when init failed. Generally, Init fails if Sync never happened before or
	 * the last sync has no markers.
	 * 
	 * @return initStatus
	 */
	public static Boolean init() {
		response = TTTSiteResponse.findById(TTTSiteResponse.class,
				Param.responseDbId);
		if (response == null)
			response = new TTTSiteResponse();

		List<TTTMarker> markers = TTTMarker.listAll(TTTMarker.class);
		if (markers == null) {
			response = null;
			return false;
		}
		if (markers.size() == 0) {
			response = null;
			return false;
		}

		response.setMarkers(markers);
		return true;
	}
}
