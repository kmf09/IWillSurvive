/*
 * MobDB.java Copyright (C) 2011 mobDB, LLC., This code is provided under the mobDB License.
 * A copy of this license has been distributed in a file called LICENSE with this source code.
 * Please visit www.mobdb.net for more information.
 */
package com.mobdb.android;

import java.security.InvalidParameterException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MobDB {
	
	private static boolean running;
	private static MobDBRequest currentRequest;
	private static MobDB singalton = null;
	private static Vector<MobDBRequest> requestQueue = new Vector<MobDBRequest>();
		
	private  MobDB() {
		
	}
	
	public static MobDB getInstance(){	
		if( singalton == null ){
			singalton = new MobDB();
		}		
		return singalton;
	}
	
	/**
	 * Send request for file byte array
	 * @param appKey Application key
	 * @param sql_query SQL string array 
	 * @param bargraph Analytics tag name
	 * @param listener MobDBResponseListener class object     
	 * @throws InvalidParameterException
	 */
	public synchronized void executeFileRequest( String appKey, String[] sql_query, String bargraph, boolean secure, MobDBResponseListener listener) throws InvalidParameterException {
		
		try {

			JSONObject req = new JSONObject();

			if(appKey == null ){
				throw new InvalidParameterException("Application key required");
			}

			req.put( SDKConstants.KEY, appKey );

			if(bargraph != null){
				req.put( SDKConstants.BAR_GRAPH, bargraph );	
			}

			JSONObject sql = new JSONObject();
			JSONArray quary = new JSONArray();

			if( sql_query == null ){
				throw new InvalidParameterException("SQL query required");
			}

			for (int i = 0; i < sql_query.length; i++) {

				if(!sql_query[0].startsWith( "GET file=" )){

					throw new InvalidParameterException("Invalid file request format");

				}

				quary.put( sql_query[i] );

			}

			sql.put( SDKConstants.QUERY, quary );
			req.put( SDKConstants.SQL, sql );

			MobDBRequest request = new MobDBRequest(secure, listener);
			request.setParams(req.toString());
			requestQueue.add(request);
			executeRequest();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Send's SELECT SQL request to mobDB 
	 * @param appKey String value Application key
	 * @param getRowdata GetRowData object
	 * @param bargraph String value for Analytics tag name
	 * @param secure boolean value to set SSL communication
	 * @param listener MobDBResponseListener object
	 * @throws InvalidParameterException
	 */
	public synchronized void execute(String appKey, GetRowData getRowdata, String bargraph, boolean secure, MobDBResponseListener listener) throws InvalidParameterException{
		
		try {
			execute(appKey, new String[]{getRowdata.getQueryString()}, null, bargraph, secure, listener);
		} catch (Exception e) {
			
			throw new InvalidParameterException( e.toString() );
			
		}
	}

	/**
	 * Send's INSERT SQL request to mobDB 
	 * @param appKey String value Application key
	 * @param insertRowdata InsertRowData object
	 * @param bargraph String value for Analytics tag name
	 * @param secure boolean value to set SSL communication
	 * @param listener MobDBResponseListener object
	 * @throws InvalidParameterException
	 */
	public synchronized void execute(String appKey, InsertRowData insertRowdata, String bargraph, boolean secure, MobDBResponseListener listener) throws InvalidParameterException{
	
		try{
			execute(appKey, new String[]{insertRowdata.getQueryString()}, insertRowdata.getParameters(), bargraph, secure, listener);
		}catch (Exception e) {
			// TODO: handle exception
			throw new InvalidParameterException(e.toString());
		}
		
	}

	/**
	 * Send's UPDATE SQL request to mobDB 
	 * @param appKey String value Application key
	 * @param updateRowdata UpdateRowData object
	 * @param bargraph String value for Analytics tag name
	 * @param secure boolean value to set SSL communication
	 * @param listener MobDBResponseListener object
	 * @throws InvalidParameterException
	 */
	public synchronized void execute(String appKey, UpdateRowData updateRowdata, String bargraph, boolean secure, MobDBResponseListener listener) throws InvalidParameterException{
		try{
			execute(appKey, new String[]{updateRowdata.getQueryString()}, updateRowdata.getParameters(), bargraph, secure, listener);
		}catch (Exception e) {
			// TODO: handle exception
			throw new InvalidParameterException(e.toString());
		}
	}

	/**
	 * Send's DELETE SQL request to mobDB 
	 * @param appKey String value Application key
	 * @param deleteRowdata DeleteRowData object
	 * @param bargraph String value for Analytics tag name
	 * @param secure boolean value to set SSL communication
	 * @param listener MobDBResponseListener object
	 * @throws InvalidParameterException
	 */
	public synchronized void execute(String appKey, DeleteRowData deleteRowdata, String bargraph, boolean secure, MobDBResponseListener listener) throws InvalidParameterException{
		try{
			execute(appKey, new String[]{deleteRowdata.getQueryString()}, null, bargraph, secure, listener);
		}catch (Exception e) {
			// TODO: handle exception
			throw new InvalidParameterException(e.toString());
		}
	}

	/**
	 * Send request for file byte array
	 * @param appKey String value Application key
	 * @param getRowdata GetRowData object
	 * @param bargraph String value for Analytics tag name
	 * @param secure boolean value to set SSL communication
	 * @param listener MobDBResponseListener object
	 * @throws InvalidParameterException
	 */
	public synchronized void execute(String appKey, GetFile getFile, String bargraph, boolean secure, MobDBResponseListener listener) throws InvalidParameterException{
	
		try{
			executeFileRequest(appKey, new String[]{getFile.getQueryString()},  bargraph, secure, listener);
		}catch (Exception e) {
			
			throw new InvalidParameterException(e.toString());
			
		}
		
	}

	/**
	 * Send's Multiple SQL statements in one request
	 * @param appKey String value Application key
	 * @param multiRequest MultiRequest object
	 * @param bargraph String value for Analytics tag name
	 * @param secure boolean value to set SSL communication
	 * @param listener MobDBResponseListener object
	 * @throws InvalidParameterException
	 */
	public synchronized void execute(String appKey, MultiRequest multiRequest, String bargraph, boolean secure, MobDBResponseListener listener) throws InvalidParameterException{
	
		try{
			execute(appKey, multiRequest.getQueryString(), null, bargraph, secure, listener);
		}catch (Exception e) {
			// TODO: handle exception
			throw new InvalidParameterException(e.toString());
		}
		
	}

	/**
	 * Send's Push notification related request
	 * @param appKey String value Application key
	 * @param push Push object
	 * @param bargraph String value for Analytics tag name
	 * @param secure boolean value to set for SSL communication
	 * @param listener MobDBResponseListener object
	 * @throws InvalidParameterException
	 */
	public synchronized void execute(String appKey, Push push, String bargraph, boolean secure, MobDBResponseListener listener) throws InvalidParameterException{

		try {

			JSONObject req = new JSONObject();

			if(appKey == null ){
				throw new InvalidParameterException("Application key required.");
			}

			req.put( SDKConstants.KEY, appKey );

			if(push == null){
				throw new InvalidParameterException("Push object required.");
			}

			if(bargraph != null){
				req.put( SDKConstants.BAR_GRAPH, bargraph );	
			}

			req.put( SDKConstants.PUSH, push.getQueryString() );

			MobDBRequest request = new MobDBRequest(secure,listener);
			request.setParams(req.toString());
			requestQueue.add(request);
			executeRequest();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Send request for file byte array
	 * @param appKey Application key
	 * @param sql_query SQL string array 
	 * @param bargraph Analytics tag name
	 * @param boolean value to set for SSL communication
	 * @param listener MobDBResponseListener class object     
	 * @throws InvalidParameterException
	 */
	public synchronized void execute( String appKey, String[] sql_query, Object[] parameter, String bargraph, boolean secure, MobDBResponseListener listener) throws InvalidParameterException {
		try {

			JSONObject req = new JSONObject();

			if(appKey == null ){
				throw new InvalidParameterException("Application key required");
			}

			req.put( SDKConstants.KEY, appKey );

			if(bargraph != null){
				req.put( SDKConstants.BAR_GRAPH, bargraph );	
			}

			JSONObject sql = new JSONObject();
			JSONArray quary = new JSONArray();

			if( sql_query == null ){
				throw new InvalidParameterException("SQL query required");
			}

			for (int i = 0; i < sql_query.length; i++) {
				quary.put(sql_query[i]);
			}

			if( sql_query.length == 1 && parameter != null ){

				JSONArray param = new JSONArray();

				for ( int i = 0; i < parameter.length; i++ ) {

					if( MobDBJSONHandler.getDataType(parameter[i]) == SDKConstants.STRING ){

						param.put( String.valueOf(parameter[i] ));

					}else if( MobDBJSONHandler.getDataType(parameter[i]) == SDKConstants.INTEGER ){

						param.put( (Integer)parameter[i] );

					} else if( MobDBJSONHandler.getDataType(parameter[i]) == SDKConstants.FLOAT ){

						param.put( (Float)parameter[i] );

					}else if( MobDBJSONHandler.getDataType(parameter[i]) == SDKConstants.FILE ){

						param.put( (JSONObject)parameter[i] );

					}

				}

				sql.put( SDKConstants.PARAM, param );

			}else if( sql_query.length > 1 && parameter != null ){

				throw new InvalidParameterException("In multi query request parameter is not allowed");

			}

			sql.put(SDKConstants.QUERY, quary);
			req.put(SDKConstants.SQL, sql);
			
			MobDBRequest request = new MobDBRequest(secure,listener);
			request.setParams(req.toString());
			requestQueue.add(request);
			executeRequest();
							
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void executeRequest(){
		
		if(!running){
			if(requestQueue.size() <= 0) return;
			running = true;
			currentRequest = requestQueue.elementAt(0);
			currentRequest.executeRequest();
		}
		
	}
	
	private static void next(){
		
		currentRequest = null;
		running = false;
		executeRequest();
		
	}
	
	public static void requestCompleted(MobDBRequest request){
		requestQueue.remove(request);
		next();
	}
		
}
