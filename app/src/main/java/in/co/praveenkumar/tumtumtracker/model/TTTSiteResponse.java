package in.co.praveenkumar.tumtumtracker.model;

import in.co.praveenkumar.tumtumtracker.helper.Param;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

/**
 * TTT site data response object
 * 
 * @author Praveen Kumar Pendyala (praveen@praveenkumar.co.in)
 * 
 */
public class TTTSiteResponse extends SugarRecord<TTTSiteResponse> {
	@SerializedName("servertime")
	String servertime;

	@SerializedName("center")
	TTTMarker center;

	@Ignore
	@SerializedName("markers")
	List<TTTMarker> markers;

	@SerializedName("title")
	String title;

	public TTTSiteResponse() {
		this.center = new TTTMarker(Param.center);
	}

	/**
	 * Get the server timestamp of the marker.<br/>
	 * Time stamp is formatted as YYYY-MM-DD HH:mm:SS (24-hour format).
	 * 
	 * @return servertime
	 */
	public String getServertime() {
		return servertime;
	}

	/**
	 * Get map center
	 * 
	 * @return center
	 */
	public TTTMarker getCenter() {
		return center;
	}

	/**
	 * Get list of markers
	 * 
	 * @return markers
	 */
	public List<TTTMarker> getMarkers() {
		return markers;
	}

	/**
	 * Data title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set list of markers
	 * 
	 */
	public void setMarkers(List<TTTMarker> markers) {
		this.markers = markers;
	}
}
