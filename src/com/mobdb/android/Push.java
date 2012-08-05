package com.mobdb.android;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class generates JSON Push request string 
 * 
 * @version 1.0
 */
public class Push {
	
	public static final String IOS  		 = "ios";
	public static final String ANDROID  	 = "android";
	public static final String DEVICE_TOKEN  = "token";
	public static final String PAYLOAD 		 = "payload";
	public static final String WHEN 		 = "when";
	public static final String PUSH    		 = "push";
	public static final String DEVICE_TYPE   = "device";
	
	//-------------------iOS Payload keys--------------
	public static final String APS 		 	 	= "aps";
	public static final String ALERT 		 	= "alert";
	public static final String SOUND 			= "sound";
	public static final String BADGE 		 	= "badge";
	public static final String BODY 		 	= "body";
	public static final String ACTION_LOC_KEY 	= "action-loc-key";
	public static final String LOC_KEY 			= "loc-key";
	public static final String LOC_ARGS 		= "loc-args";
	public static final String LAUNCH_IMAGE 	= "launch-image";
	//---------------------END--------------------------

	public static final String US_EASTERN  = "GMT-05:00";
	public static final String US_CENTRAL  = "GMT-06:00";
	public static final String US_MOUNTAIN = "GMT-07:00";
	public static final String US_PACIFIC  = "GMT-08:00";
	public static final String US_ALASKA   = "GMT-09:00";
	public static final String US_HAWAII   = "GMT-10:00";
	
	private	JSONArray androidPayload;
	private	JSONObject iOSPayload;
	private String regId;
	private String when;
	private String deviceType;
	private boolean sendRegIDTomobDB;


	/**
	 * Send Push Notification to specific Device
	 * @param registrationID Device registrationID
	 */
	public  void sendPushTo(String registrationID){
		this.regId = registrationID;
	}

	/**
	 * Set date and time for Schedule Push Notification. 
	 * @param date String format must be <strong>MM/dd/yyyy</strong> 
	 * @param hour String format mush be <strong>HH:mm</strong>, example: 18:30
	 * @param gmt String format mush be <strong>GMT-05:00</strong>
	 * @throws InvalidParameterException
	 */
	public void setWhen(String month,String date, String year, String hours, String minutes, String gmt) throws InvalidParameterException{

		if(month == null ){
			throw new InvalidParameterException("Month required");
		}
		if(date == null ){
			throw new InvalidParameterException("Date required");
		}
		if(year == null ){
			throw new InvalidParameterException("Year required");
		}
		if(hours == null ){
			throw new InvalidParameterException("Hours required");
		}
		if(minutes == null ){
			throw new InvalidParameterException("Minutes required");
		}
		if(gmt == null || gmt.toUpperCase().indexOf("GMT-") == -1){
			throw new InvalidParameterException("Invalid GMT format, GMT format should be 'GMT-05:00'");
		}

		StringBuffer when = new StringBuffer();

		this.when = when.append(month).append("/").append(date).append("/").append(year).append(",").append(hours).append(":").append(minutes).append(",").append(gmt).toString();

	}

	/**
	 * Set Android payload HashMap, its required 
	 * @param payload HashMap contains key Value pair, which will be passed to app Intent Extra key and value
	 * @throws InvalidParameterException
	 */
	public void setPayload(HashMap payload) throws InvalidParameterException{

		try {
			
			if(payload == null || payload.size() <= 0){
				throw new InvalidParameterException("Payload required");
			}


			Set keys = payload.keySet();
			this.androidPayload = new JSONArray();

			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				JSONObject keyValue = new JSONObject();
				keyValue.put(SDKConstants.KEY, key);
				keyValue.put(SDKConstants.VALUE, (String)payload.get(key));
				this.androidPayload.put(keyValue);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Set iOS payload JSON Object, its required 
	 * @param iosPayload JSON object
	 * @throws InvalidParameterException
	 */
	public void setPayload(JSONObject iosPayload) throws InvalidParameterException{

		try {
			
			if(iosPayload == null ){
				throw new InvalidParameterException("Payload required");
			}
		
			//this.iOSPayload = new JSONObject();
			//this.iOSPayload.put(APS, iosPayload);
			this.iOSPayload = iosPayload;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Send received device token to mobDB 
	 * @param deviceType String value for device type <strong>'ios'</strong> or <strong>'android'</strong>
	 * @param registrationID device token
	 * @throws InvalidParameterException
	 */
	public void sendDeviceTokenToMobDB(String deviceType, String registrationID) throws InvalidParameterException{
		
		if( registrationID == null || registrationID.length() < 0 ){
			throw new InvalidParameterException("Device registration ID can't be null.");
		}
		
		if( deviceType == null || deviceType.length() < 0 ){
			throw new InvalidParameterException("Device type required.");
		}
		
		this.sendRegIDTomobDB = true;
		this.deviceType = deviceType;
		this.regId = registrationID;	
	}
	
	/**
	 * Generate JSON request string
	 * @return JSON request string value
	 */
	public String getQueryString(){

		JSONObject push = new JSONObject();

		try {

			if(this.regId != null)
			{
				push.put( DEVICE_TOKEN, this.regId );
			}

			if(this.sendRegIDTomobDB){
				push.put( DEVICE_TYPE, this.deviceType );
			}else{

				if(this.androidPayload != null){
					push.put(PAYLOAD, this.androidPayload);
				}else if(this.iOSPayload != null){
					push.put(PAYLOAD, this.iOSPayload);
				}else{
					throw new InvalidParameterException("Payload required.");
				}

				if(this.when != null){
					push.put( WHEN, this.when );
				}

			}


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return push.toString();
	}

}
