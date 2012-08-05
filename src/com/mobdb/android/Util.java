package com.mobdb.android;

import org.json.JSONObject;


public class Util {
	
	//----------------------Data types-------------
	public static final String INTEGER = "integer";
	public static final String STRING  = "string";
	public static final String FLOAT   = "float";
	public static final String FILE   = "jsonObject";
	//----------------------END--------------------
	
	public static String getDataType( Object dataObj ){
		
		if( dataObj.getClass().equals( Integer.TYPE  ) ||  dataObj.getClass().equals( Integer.class  ) ){
			
			return INTEGER;
			
		}else if( dataObj.getClass().equals( String.class ) ){
			
			return STRING;
			
		}else if( dataObj.getClass().equals( Float.TYPE ) || dataObj.getClass().equals( Float.class ) || dataObj.getClass().equals( Double.TYPE ) || dataObj.getClass().equals( Double.class )){
			
			return FLOAT;
			
		}else if( dataObj.getClass().equals( JSONObject.class ) ){
			return FILE;
		}
		
		return null;
		
	}
}
