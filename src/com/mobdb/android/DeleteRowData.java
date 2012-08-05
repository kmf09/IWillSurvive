package com.mobdb.android;

import java.util.Vector;
/**
 * This Class generate Delete SQL query
 * @version 1.0
 */

public class DeleteRowData {

	private String query = "DELETE FROM ";
	private String condition = null;
	private Vector andConditions = null;
	private Vector orConditions = null;
		
	public DeleteRowData(String tableName) {		
		query = query + tableName;
	}
	
	/**
	 * Generates DELETE SQL string 
	 * @return string DELETE SQL string
	 */
	public String getQueryString(){
		
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
		
		return query;
		
	}
	
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
