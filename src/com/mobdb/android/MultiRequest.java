package com.mobdb.android;

import java.util.Vector;
/**
 * This Class generate multi SQL query request
 * 
 * @version 1.0
 */
public class MultiRequest {
	
	private Vector add = null;
	private Vector del = null;
	private Vector update = null;


	
	public void setInsertRowData(InsertRowData insertRowData )throws NullPointerException{
		
		if( insertRowData == null){
			throw new NullPointerException();
		}
		
		if(this.add == null){
			this.add = new Vector();
		}
		
		this.add.add(insertRowData);
		
	}
	
	public void setUpdateRowData(UpdateRowData updateRowData )throws NullPointerException{
		
		if( updateRowData == null){
			throw new NullPointerException();
		}
		
		if(this.update == null){
			this.update = new Vector();
		}
		
		this.update.add(updateRowData);
		
	}

	public void DeleteRowData(DeleteRowData deleteRowData )throws NullPointerException{
		
		if( deleteRowData == null){
			throw new NullPointerException();
		}
		
		if(this.del == null){
			this.del = new Vector();
		}
		this.del.add(deleteRowData);
	}

	public String[] getQueryString(){
		
		Vector q = new Vector();
		
		if(this.add != null){
			
			for (int i = 0; i < this.add.size(); i++) {
				q.add( ((InsertRowData)this.add.elementAt(i)).getQueryString());
			}
			
		}
		
		if(this.del != null){
			
			for (int i = 0; i < this.del.size(); i++) {
				q.add( ((DeleteRowData)this.del.elementAt(i)).getQueryString());
			}
			
		}
		
		if(this.update != null){
			for (int i = 0; i < this.update.size(); i++) {
				q.add( ((UpdateRowData)this.update.elementAt(i)).getQueryString());
			}
			
		}
		
		int len = q.size();
		
		String[] query = new String[len];
		
		for (int i = 0; i < len; i++) {
			query[i] = (String)q.elementAt(i);
		}
		
		return query;
	}

}
