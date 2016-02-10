package com.GGI.uParty;

import java.awt.Point;

public interface Adapter {
	/**shows interstitial ad*/
	public void showOrLoadInterstital();
	
	/**gets the GPS location of the user
	 * @return Point
	 */
	public Point getGPSLocation();
	
	
	
	
}
