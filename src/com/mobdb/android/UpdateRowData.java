package com.mobdb.android;



import java.security.InvalidParameterException;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This Class generate UPDATE SQL query
 * @version 1.0
 */

public class UpdateRowData {

	private String query = "UPDATE ";
	private String condition = null;
	private Vector andConditions = null;
	private Vector orConditions = null;
	private Vector fields = null;
	private Vector fieldValues = null;
		
	private boolean isFilePresent = false;
	
	public UpdateRowData(String tableName) {	
		
		query = query + tableName + " * ";
		
	}
	
	/**
	 * Set value to field in which data needs to be updated  
	 * @param field table field name
	 * @param value String value
	 */
	public void setValue(String field, String value){
		
		if( fields == null ){
			
			fields = new Vector();
			
		}
		if( fieldValues == null ){
			
			fieldValues = new Vector();
			
		}
		
		fields.add( field + "=?" );
		fieldValues.add("'"+value+"'");
		
	}
	
	/**
	 * Set value to field in which data needs to be updated  
	 * @param field table field name
	 * @param value int value to update
	 */
	public void setValue(String field, int value){
		
		if( fields == null ){
			
			fields = new Vector();
			
		}
		if( fieldValues == null ){
			
			fieldValues = new Vector();
			
		}
		
		fields.add( field + "=?" );
		fieldValues.add(Integer.valueOf(value) );
		
	}
	
	/**
	 * Set value to field in which data needs to be updated  
	 * @param field table filed name
	 * @param value Double value to update
	 */
	public void setValue(String field, Double value){
		
		if( fields == null ){
			
			fields = new Vector();
			
		}
		if( fieldValues == null ){
			
			fieldValues = new Vector();
			
		}
		
		fields.add( field + "=?" );
		fieldValues.add(value);
		
	}
	
	/**
	 * Set file bytes array value, which needs to updated in table
	 * @param field table field name
	 * @param fileNameWithFileExtension String value file name with file extension, example: 'image.png'
	 * @param fileBytes file bytes array
	 */
	public void setValue(String field, String fileNameWithFileExtension, byte[] fileBytes) throws InvalidParameterException{
		
		if(fileNameWithFileExtension == null || fileNameWithFileExtension.trim().length() <= 0){
			
			throw new InvalidParameterException("File name required");
			
		}
		
		if( fields == null ){
			
			fields = new Vector();
			
		}
		
		if( fieldValues == null ){
			
			fieldValues = new Vector();
			
		}
		

		String f =  field + "=?" ;
		fields.add(f);
		
		
		JSONObject file = new JSONObject();
		try {
			file.put( SDKConstants.FILE_NAME, fileNameWithFileExtension );
			//
			file.put( SDKConstants.FILE_DATA, com.mobdb.android.Base64.encodeBytes(fileBytes) );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fieldValues.add(file);
		isFilePresent = true;

		
	}
	
	/**
	 * Returns array of SQL parameter objects
	 * @return Object array
	 */
	public Object[] getParameters(){
		
		if(!isFilePresent){
		
			return null;
		
		}
		
		int size = fieldValues.size();
		
		Object[] o = new Object[size];
		
		for ( int i = 0; i < size; i++ ) {
		
			o[i] = fieldValues.elementAt(i);
			
		}
		return o;
	}
	
	public String getQueryString() throws InvalidParameterException{
		
		if( condition != null ){
			
			query = query + " WHERE " + condition;
			
		}
		
		if( orConditions != null ){
			
			if( condition != null ){
				
				query = query + " OR " + ( String )orConditions.elementAt( 0 );
				
			} else {
				
				query = query + " WHERE " + ( String )orConditions.elementAt( 0 );
				
			}
			
			StringBuffer or = new StringBuffer();
			
			int len = orConditions.size();
			
			for ( int i = 1; i < len; i++ ) {
				
				or.append(" OR ").append( ( String )orConditions.elementAt( i ) );
				
			}
			
			query = query + " " + or.toString();
			
		}
		
		if( andConditions != null ){
			
			if( condition != null || orConditions != null){
				
				query = query + " AND " + ( String )andConditions.elementAt( 0 );
				
			} else {
				
				query = query + " WHERE " + ( String )andConditions.elementAt( 0 );
				
			}
			
			StringBuffer or = new StringBuffer();
			
			int len = andConditions.size();
			
			for ( int i = 1; i < len; i++ ) {
				
				or.append(" AND ").append( ( String )andConditions.elementAt( i ) );
				
			}
			
			query = query + " " + or.toString();
			
		}
		
		if( fields != null ){
			
			StringBuffer f = new StringBuffer();
			
			if( fields.size() > 0 ){
				
				f.append(" SET ").append( ( String )fields.elementAt( 0 ) );
				
				int len = fields.size();
				
				for ( int i = 1; i < len; i++ ) {
					
					f.append( "," ).append( ( String )fields.elementAt(i) );
					
				}
				
			}
			
			if( f.length() > 0 ){
				
				query = query.replace( "*", f );
				
			}
			
		}else{
			throw new InvalidParameterException("SQL syntex error: setField required");
		}
		
		if(!isFilePresent){
			
			for ( int i = 0; i < fields.size(); i++ ) {
				
				String fieldValue = ( String )fields.elementAt(i);
		
				query = query.replace( fieldValue , fieldValue.substring(0, fieldValue.length() - 1 ) + String.valueOf( this.fieldValues.elementAt(i) ) );
				
			}
			
		}
		
		return query;
		
	}
	
//	private String formatValue( Object o ){
//		
//		if( Util.getDataType(o) == Util.STRING ){
//			
//			return "'" + (String)o + "'";
//			
//		}else if( Util.getDataType(o) == Util.INTEGER || Util.getDataType(o) == Util.FLOAT ){
//			
//			return String.valueOf(o);
//			
//		}
//		
//		return null;
//	}
	
	/**
	 * Sets <strong>WHERE field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void whereEqualsTo(String field, int value){
		 condition = field + "=" + String.valueOf(value);
	}
	
	/**
	 * Sets <strong>WHERE field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The String value for condition
	 */
	public void whereEqualsTo(String field, String value){
		condition = field + "='" + value + "'";
	}
	
	/**
	 * Sets <strong>WHERE field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The Double value for condition
	 */
	public void whereEqualsTo(String field, Double value){
		condition = field + "=" + String.valueOf(value);		
	}
	/**
	 * Sets <strong>WHERE field > value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void whereGreaterThen(String field, int value){
		condition = field + ">" + String.valueOf(value);
	}
	
	/**
	 * Sets <strong>WHERE field < value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void whereLessThen(String field, int value){
		condition = field + "<" + String.valueOf(value);		
	}

	/**
	 * Sets <strong>AND field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void andEqualsTo(String field, int value){
		String condition = field + "=" + String.valueOf(value);
		if(andConditions == null){
			andConditions = new Vector();
		}
		if(!andConditions.contains(condition)){
			andConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>AND field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The String value for condition
	 */
	public void andEqualsTo(String field, String value){
		String condition = field + "='" + value + "'";
		if(andConditions == null){
			andConditions = new Vector();
		}
		if(!andConditions.contains(condition)){
			andConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>AND field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The Double value for condition
	 */
	public void andEqualsTo(String field, Double value){
		String condition = field + "=" + String.valueOf(value);
		if(andConditions == null){
			andConditions = new Vector();
		}
		if(!andConditions.contains(condition)){
			andConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>AND field > value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void andGreaterThen(String field, int value){
		String condition = field + ">" + String.valueOf(value);
		if(andConditions == null){
			andConditions = new Vector();
		}
		if(!andConditions.contains(condition)){
			andConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>AND field > value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The Double value for condition
	 */
	public void andGreaterThen(String field, Double value){
		String condition = field + ">" + String.valueOf(value);
		if(andConditions == null){
			andConditions = new Vector();
		}
		if(!andConditions.contains(condition)){
			andConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>AND field < value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void andLessThen(String field, int value){
		String condition = field + "<" + String.valueOf(value);
		if(andConditions == null){
			andConditions = new Vector();
		}
		if(!andConditions.contains(condition)){
			andConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>AND field < value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The Double value for condition
	 */
	public void andLessThen(String field, Double value){
		String condition = field + "<" + String.valueOf(value);
		if(andConditions == null){
			andConditions = new Vector();
		}
		if(!andConditions.contains(condition)){
			andConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>OR field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void orEqualsTo(String field, int value){
		String condition = field + "=" + String.valueOf(value);
		
		if(orConditions == null){
			orConditions = new Vector();
		}
		if(!orConditions.contains(condition)){
			orConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>OR field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The String value for condition
	 */
	public void orEqualsTo(String field, String value){
		String condition = field + "='" + value + "'";
		if(orConditions == null){
			orConditions = new Vector();
		}
		if(!orConditions.contains(condition)){
			orConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>OR field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The Double value for condition
	 */
	public void orEqualsTo(String field, Double value){
		String condition = field + "=" + String.valueOf(value);
		if(orConditions == null){
			orConditions = new Vector();
		}
		if(!orConditions.contains(condition)){
			orConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>OR field > value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void orGreaterThen(String field, int value){
		String condition = field + ">" + String.valueOf(value);
		
		if(orConditions == null){
			orConditions = new Vector();
		}
		if(!orConditions.contains(condition)){
			orConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>OR field > value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The Double value for condition
	 */
	public void orGreaterThen(String field, Double value){
		String condition = field + ">" + String.valueOf(value);
		
		if(orConditions == null){
			orConditions = new Vector();
		}
		if(!orConditions.contains(condition)){
			orConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>OR field < value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void orLessThen(String field, int value){
		String condition = field + "<" + String.valueOf(value);
		if(orConditions == null){
			orConditions = new Vector();
		}
		if(!orConditions.contains(condition)){
			orConditions.add(condition);
		}
	}
	
	/**
	 * Sets <strong>OR field < value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The Double value for condition
	 */
	public void orLessThen(String field, Double value){
		String condition = field + "<" + String.valueOf(value);
		if(orConditions == null){
			orConditions = new Vector();
		}
		if(!orConditions.contains(condition)){
			orConditions.add(condition);
		}
	}
	

}
