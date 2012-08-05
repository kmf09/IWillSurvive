package com.mobdb.android;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * This class Parse mobDB response JSON string
 * 
 * @version 1.0
 */
public class MobDBJSONHandler  { 

	private MobDBResponseListener listner = null;

	private Vector<HashMap<String, Object[]>> rowList = new Vector<HashMap<String, Object[]>>();


	public MobDBJSONHandler( MobDBResponseListener listner ) {
		// TODO Auto-generated constructor stub

		this.listner = listner;

	}
	
	/**
	 * Takes raw JSON string, parse it and pass data MobDBResponceListener listener class 
	 * @param jsonString JSON format string value
	 */
	public void parse( String jsonString ){

		try {

			JSONObject o = new JSONObject(jsonString);

			for ( Iterator iterator = o.keys(); iterator.hasNext(); ) {

				String type = (String) iterator.next();

				if( type.equals( SDKConstants.STATUS ) ){

					Integer status = null;

					try{

						status = Integer.valueOf(o.getInt( SDKConstants.STATUS ));	

					}catch(Exception e){
						try{
							
						JSONArray a = o.getJSONArray( SDKConstants.STATUS );
						for (int i = 0; i < a.length(); i++) {
							status = Integer.valueOf(a.getInt(i));	
							if(listner != null && status != Messages.SUCCESS_VALUE){
								listner.mobDBErrorResponse(status, (String)Messages.msg.get(status));
								return;
							}
							
						}
						}catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}
					}


					if(  status == Messages.SUCCESS_VALUE ){


						if(listner != null){

							listner.mobDBSuccessResponse();

						}

					} else{

						if(listner != null){

							listner.mobDBErrorResponse( status, ( String )Messages.value.get( status ) );

						}

					}			

				}else if( type.equals( SDKConstants.ROW ) ){

					JSONArray rows = o.getJSONArray( SDKConstants.ROW );

					int len = rows.length();

					for ( int i = 0; i < len; i++ ) {

						JSONObject rowObj = new JSONObject( rows.get(i).toString() );

						HashMap<String, Object[]> row = new HashMap<String, Object[]>();

						for ( Iterator iterator2 = rowObj.keys(); iterator2
						.hasNext(); ) {

							String columnName = ( String ) iterator2.next();

							Object columnData = rowObj.get( columnName );

							String columnType = getDataType( columnData );

							row.put( columnName, new Object[]{ columnData, columnType } );

						}

						if( row != null ){

							rowList.add( row );

						}

					}

					//if( rowList.size() > 0 ){

						if( listner != null ){

							listner.mobDBResponse( rowList );

						}
					//}

				}		
			}

		} catch ( Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Check for Data Type
	 * @param dataObj data object 
	 * @return returns matching data type string value
	 */
	public static String getDataType( Object dataObj ){
		
		if( dataObj.getClass().equals( Integer.TYPE ) ){
			
			return SDKConstants.INTEGER;
			
		}else if( dataObj.getClass().equals( String.class ) ){
			
			return SDKConstants.STRING;
			
		}else if( dataObj.getClass().equals( Float.TYPE ) ){
			
			return SDKConstants.FLOAT;
			
		}else if( dataObj.getClass().equals( JSONObject.class ) ){
			return SDKConstants.FILE;
		}
		
		return null;
		
	}
}
