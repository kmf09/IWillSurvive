package com.mobdb.android;

public class SDKConstants {
	
	public static final String URL_HTTP      = "http://api.mobdb.net/ClientRequest";
	public static final String URL_HTTPS     = "https://api.mobdb.net/ClientRequest";
	public static final String BOUNDERY      = "---------------------------7d159c1302d0y0";
	public static final String SEPARATOR     = "\r\n\r\n";
	public static final String TAG  = "mobDB";
	
	//---------------------JSON config--------------------------
	public static final String JSON_CONTENT  = "application/json";
	public static final String XML_CONTENT   = "application/xml";
	public static final String STATUS  		 = "status";
	public static final String KEY     		 = "key";
	public static final String SQL     		 = "sql";
	public static final String ROW     		 = "row";
	public static final String QUERY   		 = "query";
	public static final String PARAM   		 = "param";
	public static final String BAR_GRAPH     = "bargraph";
	public static final String PUSH    		 = "push";
	public static final String DEVICE_TYPE   = "device";
	public static final String IOS  		 = "ios";
	public static final String ANDROID  	 = "android";
	public static final String DEVICE_TOKEN  = "token";
	public static final String PAYLOAD 		 = "payload";
	public static final String WHEN 		 = "when";
	public static final String ALERT 		 = "alert";
	public static final String SOUND 		 = "sound";
	public static final String BADGE 		 = "badge";
	public static final String VALUE 		 = "value";
	public static final String FILE_NAME 	 = "name";
	public static final String FILE_DATA 	 = "data";
	//--------------------END------------------------------------
	
	public static final int COL_DATA = 0;
	public static final int COL_TYPE = 1;
	
	//----------------------Data types-------------
	public static final String INTEGER = "integer";
	public static final String STRING  = "string";
	public static final String FLOAT   = "float";
	public static final String FILE    = "jsonObject";
	//----------------------END--------------------
	
	
	//-------------------PUSH Message------------------------
    public static final String EXTRA_SENDER = "sender";
    public static final String EXTRA_APPLICATION_PENDING_INTENT = "app";
    public static final String REQUEST_UNREGISTRATION_INTENT = "com.google.android.c2dm.intent.UNREGISTER";
    public static final String REQUEST_REGISTRATION_INTENT = "com.google.android.c2dm.intent.REGISTER";
    public static final String LAST_REGISTRATION_CHANGE = "last_registration_change";
    public static final String BACKOFF = "backoff";
    public static final String GSF_PACKAGE = "com.google.android.gsf";
    // package
    public static final String PREFERENCE = "com.google.android.c2dm";
    
    private static final long DEFAULT_BACKOFF = 30000;
  //-------------------END------------------------
    
}
