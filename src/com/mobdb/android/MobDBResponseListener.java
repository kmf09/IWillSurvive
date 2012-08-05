package com.mobdb.android;

import java.util.HashMap;
import java.util.Vector;

public interface MobDBResponseListener {
	
	
	/**
	 * mobDB response to Successful request execution	 
	 */
	public void mobDBSuccessResponse();
	
	/**
	 * Client request error
	 *  
	 * @param errValue error value
	 * @param errMsg error message
	 */
	public void mobDBErrorResponse( Integer errValue, String errMsg );
	
	/**
	 * mobDB JSON object response
	 * @param jsonObj raw JSON String
	 */
	public void mobDBResponse(String jsonObj);
	
	
	/**
	 * mobDB response with file name and byte array data 
	 * 
	 * @param fileName file Name with extension, 
	 * @param fileData file data in byte array 
	 */
	
	public void mobDBFileResponse( String fileName, byte[] fileData );
	
	
	/**
	 * mobDB response with JSON format row data which is parsed in Vector object
	 * 
	 * @param result Vector object contains HashMap&lt;String, Object[]&gt; objects, 
	 */
	public void mobDBResponse( Vector<HashMap<String, Object[]>> result );
	
}
