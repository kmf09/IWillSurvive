package com.mobdb.android;



import java.util.Vector;
/**
 * This Class generate SELECT SQL query string
 * @version 1.0
 */
public class GetRowData {

	private String query = "SELECT * FROM ";
	private String condition = null;
	private Vector andConditions = null;
	private Vector orConditions = null;
	private Vector fields = null;
	
	
	public GetRowData(String tableName) {		
		query = query + tableName;
	}
	
	/**
	 * Specify field name which needs to be retrieve 
	 * 
	 * @param field field name
	 */
	public void getField(String field){
		
		if(fields == null){
			
			fields = new Vector();
			
		}
		
		fields.add(field);
		
	}
	
	/**
	 * With conditions contracts SQL query 
	 * @return SQL query string
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
		
		if( fields != null ){
			
			StringBuffer f = new StringBuffer();
			
			if( fields.size() > 0 ){
				
				f.append( ( String )fields.elementAt( 0 ) );
				
				int len = fields.size();
				
				for ( int i = 1; i < len; i++ ) {
					
					f.append( "," ).append( ( String )fields.elementAt(i) );
					
				}
				
			}
			
			if( f.length() > 0 ){
				
				query = query.replace( "*", f );
				
			}
			
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
	 * Set <strong>WHERE field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The String value for condition
	 */
	public void whereEqualsTo(String field, String value){
		condition = field + "='" + value + "'";
	}
	
	/**
	 * Set <strong>WHERE field = value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The Double value for condition
	 */
	public void whereEqualsTo(String field, Double value){
		condition = field + "=" + String.valueOf(value);		
	}
	/**
	 * Set <strong>WHERE field > value</strong> condition in SQL query string
	 * @param field The field which needs to use for condition
	 * @param value The int value for condition
	 */
	public void whereGreaterThen(String field, int value){
		condition = field + ">" + String.valueOf(value);
	}
	
	/**
	 * Set <strong>WHERE field < value</strong> condition in SQL query string 
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
	 * @param value The string value for condition
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
	 * Sets <strong>OR field = 'value'</strong> condition in SQL query string
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
